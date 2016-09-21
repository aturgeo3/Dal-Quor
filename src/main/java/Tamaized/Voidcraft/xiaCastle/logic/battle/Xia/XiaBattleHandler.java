package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;

public class XiaBattleHandler implements IBattleHandler {

	private int phase = 0;
	private int tick = 0;
	private int childPhase = 0;
	private int childPhaseModulate = 20;
	private boolean readyForInput = false;

	private boolean running;

	private World worldObj;
	private BlockPos pos;

	private EntityBossXia xia;

	@Override
	public void update() {
		if (!worldObj.isRemote && running) {
			if (xia == null || !xia.isActive()) {
				stop();
				return;
			}
		}
	}

	@Override
	public void start(World world, BlockPos p) {
		worldObj = world;
		pos = p;
		stop();
		phase = 0;
		readyForInput = false;
		xia = new EntityBossXia(worldObj, this);
		xia.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 17.5, pos.getZ() + 43.5);
		xia.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(voidCraft.armors.xiaHelmet));
		xia.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(voidCraft.armors.xiaChest));
		xia.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(voidCraft.armors.xiaLegs));
		xia.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(voidCraft.armors.xiaBoots));
		worldObj.spawnEntityInWorld(xia);
		xia.start();
		running = true;
	}

	@Override
	public void stop() {
		readyForInput = false;
		if (xia != null) worldObj.removeEntity(xia);
		xia = null;
	}

	@Override
	public boolean isRunning() {
		return running;
	}

}
