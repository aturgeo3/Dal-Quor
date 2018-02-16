package tamaized.dalquor.common.entity.boss.herobrine.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import tamaized.dalquor.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.dalquor.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.dalquor.registry.VoidCraftBlocks;

import java.util.*;

public class EntityAIHerobrinePhase1 extends EntityAIBase {

	private static final int tick_spawnFireball = 3 * 20;
	private static final int tick_spawnPillar = 5 * 20;
	private static final int maxPillars = 6;
	private static final List<BlockPos> locations = new ArrayList<>();

	private final EntityBossHerobrine boss;
	private int tick = 1;
	private Map<BlockPos, TileEntityAIBlock> pillars = new HashMap<>();

	public EntityAIHerobrinePhase1(EntityBossHerobrine entity) {
		boss = entity;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getPhase() == 0;
	}

	@Override
	public void resetTask() {
		clearPillars();
	}

	@Override
	public void startExecuting() {
		locations.clear();
		locations.add(new BlockPos(8, 8, 0));
		locations.add(new BlockPos(5, 8, 5));
		locations.add(new BlockPos(0, 8, 8));
		locations.add(new BlockPos(-5, 8, 5));
		locations.add(new BlockPos(-8, 8, 0));
		locations.add(new BlockPos(-5, 8, -5));
		locations.add(new BlockPos(0, 8, -8));
		locations.add(new BlockPos(5, 8, -5));
		boss.updateInitialPos();
		for (int x = -10; x <= 10; x++) {
			for (int z = -10; z <= 10; z++) {
				if (x == 0 && z == 0)
					continue;
				boss.world.setBlockState(boss.getInitialPos().add(x, -1, z), Blocks.NETHER_BRICK.getDefaultState());
			}
		}
	}

	@Override
	public void updateTask() {
		Iterator<Map.Entry<BlockPos, TileEntityAIBlock>> iter = pillars.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<BlockPos, TileEntityAIBlock> entry = iter.next();
			if (entry.getValue().isDead()) {
				boss.doDamage(20);
				boss.world.setBlockToAir(entry.getKey());
				iter.remove();
			}
		}
		if (tick % tick_spawnFireball == 0) {
			spawnFireball();
			updateMotion();
		}
		if (tick % tick_spawnPillar == 0) {
			if (pillars.size() < maxPillars) {
				addRandomPillar();
			}
		}
		tick++;
	}

	private void updateMotion() {
		EntityMoveHelper moveHelper = boss.getMoveHelper();
		BlockPos pos = locations.get(boss.getRNG().nextInt(locations.size())).add(boss.getInitialPos());
		while (locations.size() > 1 && pos.getX() == moveHelper.getX() && pos.getY() == moveHelper.getY() && pos.getZ() == moveHelper.getZ())
			pos = locations.get(boss.getRNG().nextInt(locations.size())).add(boss.getInitialPos());
		moveHelper.setMoveTo(pos.getX(), pos.getY(), pos.getZ(), 1);
	}

	private void clearPillars() {
		for (TileEntityAIBlock te : pillars.values()) {
			te.setDead();
			boss.world.setBlockToAir(te.getPos());
		}
		pillars.clear();
	}

	private void addRandomPillar() {
		World world = boss.world;
		int randX = (int) Math.floor(Math.random() * 16);
		int randZ = (int) Math.floor(Math.random() * 16);
		int nX = (boss.getInitialPos().getX() - 8) + randX;
		int nY = boss.getInitialPos().getY();
		int nZ = (boss.getInitialPos().getZ() - 8) + randZ;
		if (world.getTileEntity(new BlockPos(nX, nY, nZ)) == null) {
			world.setBlockState(new BlockPos(nX, nY, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 1, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 2, nZ), VoidCraftBlocks.AIBlock.getDefaultState());
			TileEntityAIBlock b = (TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY, nZ));
			b.setup(null);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 1, nZ))).setup(b);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 2, nZ))).setup(b);
			pillars.put(new BlockPos(nX, nY, nZ), b);
		} else {
			addRandomPillar();
		}
	}

	private void spawnFireball() {
		if (boss.getAttackTarget() != null) {
			boss.world.playRecord(new BlockPos((int) boss.posX, (int) boss.posY, (int) boss.posZ), null);// ((EntityPlayer)null, 1008, new BlockPos((int)theWatcher.posX, (int)theWatcher.posY, (int)theWatcher.posZ), 0);
			double d5 = boss.getAttackTarget().posX - boss.posX;
			double d6 = boss.getAttackTarget().getEntityBoundingBox().minY + (double) (boss.getAttackTarget().height / 2.0F) - (boss.posY + (double) (boss.height / 2.0F));
			double d7 = boss.getAttackTarget().posZ - boss.posZ;
			EntityHerobrineFireball entitylargefireball = new EntityHerobrineFireball(boss.world, boss, d5, d6, d7);
			double d8 = 4.0D;
			Vec3d vec3 = boss.getLook(1.0F);
			entitylargefireball.posX = boss.posX;// + vec3.xCoord * d8;
			entitylargefireball.posY = boss.posY + (double) (boss.height / 2.0F) + 0.5D;
			entitylargefireball.posZ = boss.posZ;// + vec3.zCoord * d8;
			boss.world.spawnEntity(entitylargefireball);
		}
	}
}
