package tamaized.voidcraft.common.xiacastle.logic.battle.herobrine.phases;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import tamaized.voidcraft.common.blocks.AIBlock;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.voidcraft.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.voidcraft.common.xiacastle.logic.battle.EntityVoidNPCAIBase;
import tamaized.voidcraft.network.IVoidBossAIPacket;
import tamaized.voidcraft.registry.VoidCraftBlocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityAIHerobrinePhase2<T extends EntityBossHerobrine> extends EntityVoidNPCAIBase<T> {

	private boolean xPos = true;
	private boolean zPos = true;
	private boolean inBlock = false;

	private int tick_spawnPillar = 5 * 20;
	private int tick_damage = 0;
	private int tick_block = 0;

	private int pillarAmount = 0;
	private int maxPillars = 1;
	private Map<BlockPos, TileEntityAIBlock> pillars = new HashMap<>();

	public EntityAIHerobrinePhase2(T entityBoss, List<Class> c) {
		super(entityBoss, c);
	}

	@Override
	protected void preInit() {

	}

	@Override
	protected void postInit() {
		for (int x = -10; x <= 10; x++) {
			for (int z = -10; z <= 10; z++) {
				if ((x == 0 && z == 0) || Math.floor(Math.random() * 20) != 0)
					continue;
				world.setBlockState(getBlockPosition().add(x, -1, z), Blocks.LAVA.getDefaultState());
			}
		}
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {

	}

	@Override
	public void kill() {
		super.kill();
		clearPillars();
	}

	private void clearPillars() {
		for (TileEntityAIBlock te : pillars.values()) {
			te.setDead();
		}
		pillars.clear();
	}

	@Override
	public void doAction(BlockPos pos) {
		getEntity().doDamage(20);
		pillars.remove(pos);
		pillarAmount = pillars.size();
	}

	@Override
	protected void update() {
		updateLook();
		if (closestEntity != null)
			updateMotion();
		updateInPillarState();

		if (getEntity() == null)
			clearPillars();

		pillarAmount = pillars.size();

		if (tick % tick_spawnPillar == 0) {
			if (pillarAmount < maxPillars) {
				addRandomPillar();
			}
		}
	}

	private void addRandomPillar() {
		int randX = (int) Math.floor(Math.random() * 16);
		int randZ = (int) Math.floor(Math.random() * 16);
		int nX = (getBlockPosition().getX() - 8) + randX;
		int nY = getBlockPosition().getY();
		int nZ = (getBlockPosition().getZ() - 8) + randZ;
		if (world.getTileEntity(new BlockPos(nX, nY, nZ)) == null) {
			world.setBlockState(new BlockPos(nX, nY, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 1, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 2, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			TileEntityAIBlock b = (TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY, nZ));
			b.setup(this, null);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 1, nZ))).setup(null, b);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 2, nZ))).setup(null, b);
			pillars.put(new BlockPos(nX, nY, nZ), b);
			pillarAmount = pillars.size();
		} else {
			addRandomPillar();
		}
	}

	private void updateInPillarState() {
		Block b = getEntity().world.getBlockState(new BlockPos(MathHelper.floor(getEntity().posX), MathHelper.floor(getEntity().posY), MathHelper.floor(getEntity().posZ))).getBlock();
		if (b instanceof AIBlock) {
			if (!inBlock) {
				TileEntity te = AIBlock.getMyTileEntity(getEntity().world, new BlockPos(MathHelper.floor(getEntity().posX), MathHelper.floor(getEntity().posY), MathHelper.floor(getEntity().posZ)));
				if (te instanceof TileEntityAIBlock) {
					((TileEntityAIBlock) te).boom();
					inBlock = true;
					tick_block = 40;
				}
			}
		} else {
			if (tick_block <= 0)
				inBlock = false;
			else
				tick_block--;
		}

	}

	private void updateMotion() {

		double x = getEntity().posX;
		double y = getEntity().posY;
		double z = getEntity().posZ;

		double px = closestEntity.posX;
		double pz = closestEntity.posZ;

		double dx = 0;
		double dy = 0;
		double dz = 0;

		xPos = px > x;

		zPos = pz > z;

		if (x < px)
			dx = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (x == px)
			dx = 0;
		else if (!xPos && (x - px) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (xPos && (px - x) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dx = 0;
			getEntity().posX = px;
		} else if (px < x)
			dx = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		double ezMin = closestEntity.getEntityBoundingBox().minZ;
		double ezMax = closestEntity.getEntityBoundingBox().maxZ;
		double exMin = closestEntity.getEntityBoundingBox().minX;
		double exMax = closestEntity.getEntityBoundingBox().maxX;
		if (tick_damage <= 0) {
			if (z >= ezMin && z <= ezMax && x >= exMin && x <= exMax) {
				closestEntity.attackEntityFrom(DamageSource.causeMobDamage(getEntity()), 45);
				tick_damage = 20;
			}
		} else {
			tick_damage -= tick_damage > 0 ? 1 : 0;
		}

		/*
		 * if(zPos){ if(z < pz) dz = entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(); else if(z==pz) dz = 0; else dz = -(z-pz); }else{ if(pz < z) dz = -entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue(); else if(z==pz) dz = 0; else dz = (pz-z); }
		 */

		if (z < pz)
			dz = getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
		else if (z == pz)
			dz = 0;
		else if (!zPos && (z - pz) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (zPos && (pz - z) < getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()) {
			dz = 0;
			getEntity().posZ = pz;
		} else if (pz < z)
			dz = -(getEntity().getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());

		// entity.setVelocity(0, dy, 0);
		// entity.velocityChanged = true;
		getEntity().posX += dx;
		getEntity().posZ += dz;
		getEntity().posY += dy;
	}

	private void updateLook() {
		if (closestEntity != null) {
			getEntity().getLookHelper().setLookPosition(closestEntity.posX, closestEntity.posY + (double) closestEntity.getEyeHeight(), closestEntity.posZ, 10.0F, (float) getEntity().getVerticalFaceSpeed());
			double d0 = closestEntity.posX - getEntity().posX;
			double d2 = closestEntity.posZ - getEntity().posZ;
			float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = MathHelper.wrapDegrees(f - getEntity().rotationYaw);
			getEntity().rotationYaw = getEntity().rotationYaw + f3;
		}
	}

}
