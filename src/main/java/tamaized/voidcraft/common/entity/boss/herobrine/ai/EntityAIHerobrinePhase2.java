package tamaized.voidcraft.common.entity.boss.herobrine.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tamaized.voidcraft.common.blocks.AIBlock;
import tamaized.voidcraft.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.voidcraft.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.voidcraft.registry.VoidCraftBlocks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EntityAIHerobrinePhase2 extends EntityAIBase {

	private static final int tick_spawnPillar = 5 * 20;
	private static final int maxPillars = 1;

	private final EntityBossHerobrine boss;
	private int tick = 1;
	private Map<BlockPos, TileEntityAIBlock> pillars = new HashMap<>();
	private boolean inBlock = false;

	public EntityAIHerobrinePhase2(EntityBossHerobrine entity) {
		boss = entity;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getPhase() == 1;
	}

	@Override
	public void resetTask() {
		clearPillars();
	}

	@Override
	public void startExecuting() {
		boss.setPositionAndUpdate(boss.getInitialPos().add(0.5F, 0, 0.5F));
		for (int x = -10; x <= 10; x++) {
			for (int z = -10; z <= 10; z++) {
				if ((x == 0 && z == 0) || Math.floor(Math.random() * 20) != 0)
					continue;
				boss.world.setBlockState(boss.getInitialPos().add(x, -1, z), Blocks.LAVA.getDefaultState());
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
		updateMotion();
		updateInPillarState();
		if (tick % tick_spawnPillar == 0) {
			if (pillars.size() < maxPillars) {
				addRandomPillar();
			}
		}
		tick++;
	}

	private void updateMotion() {
		EntityMoveHelper moveHelper = boss.getMoveHelper();
		if (boss.getAttackTarget() != null) {
			moveHelper.setMoveTo(boss.getAttackTarget().posX, boss.getAttackTarget().posY, boss.getAttackTarget().posZ, 1);
		}
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
			b.setEntity(boss);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 1, nZ))).setup(b);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 2, nZ))).setup(b);
			pillars.put(new BlockPos(nX, nY, nZ), b);
		} else {
			addRandomPillar();
		}
	}

	private void updateInPillarState() {
		Block b = boss.world.getBlockState(new BlockPos(MathHelper.floor(boss.posX), MathHelper.floor(boss.posY) + 1, MathHelper.floor(boss.posZ))).getBlock();
		if (b instanceof AIBlock) {
			if (!inBlock) {
				TileEntity te = boss.world.getTileEntity(boss.getPosition());
				if (te instanceof TileEntityAIBlock) {
					((TileEntityAIBlock) te).updateState();
					inBlock = true;
				}
			}
		} else {
			inBlock = false;
		}

	}
}
