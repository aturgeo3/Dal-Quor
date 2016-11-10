package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia;

import java.util.ArrayList;
import java.util.Iterator;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XiaBattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;
	private boolean isDone = false;

	private World worldObj;
	private BlockPos pos;

	private AxisAlignedBB checkBB;
	private ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
	private EntityBossXia xia;

	@Override
	public void update() {
		if (worldObj != null && !worldObj.isRemote && running) {
			if (checkBB != null) {
				Iterator<EntityPlayer> iter = players.iterator();
				while (iter.hasNext()) {
					EntityPlayer player = iter.next();
					if (!checkBB.isVecInside(player.getPositionVector())) {
						iter.remove();
					}
				}
			}
			if (xia == null || !xia.isActive() || players.isEmpty()) {
				stop();
				return;
			}
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				worldObj.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? voidCraft.blocks.realityHole.getDefaultState() : voidCraft.blocks.blockNoBreak.getDefaultState());
			}
		}
		pos = p;
		stop();
		phase = 0;
		isDone = false;
		readyForInput = false;
		xia = new EntityBossXia(worldObj, this);
		xia.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 17.5, pos.getZ() + 43.5);
		xia.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(voidCraft.armors.xiaHelmet));
		xia.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(voidCraft.armors.xiaChest));
		xia.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(voidCraft.armors.xiaLegs));
		xia.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(voidCraft.armors.xiaBoots));
		worldObj.spawnEntityInWorld(xia);
		checkBB = new AxisAlignedBB(pos.add(-18, 0, -2), pos.add(18, 25, 50));
		players.addAll(worldObj.getEntitiesWithinAABB(EntityPlayer.class, checkBB));
		xia.start();
		running = true;
	}

	@Override
	public void stop() {
		players.clear();
		readyForInput = false;
		isDone = false;
		if (xia != null) {
			if (xia.isDone()) isDone = true;
			worldObj.removeEntity(xia);
		}
		xia = null;
		if (worldObj != null) {
			BlockPos doorPos = new BlockPos(54, 76, 82);
			for (int x = 0; x > -5; x--) {
				for (int y = 0; y < 4; y++) {
					worldObj.setBlockToAir(doorPos.add(x, y, 0));
				}
			}
		}
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

}
