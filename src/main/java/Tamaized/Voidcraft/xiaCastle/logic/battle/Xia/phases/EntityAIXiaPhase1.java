package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import scala.languageFeature.postfixOps;
import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIXiaPhase1 extends EntityVoidNPCAIBase {

	private final Random rand = new Random();

	private ArrayList<BlockPos> teleportLocations;

	private int tick;
	private int actionTick = 20 * 10;

	public EntityAIXiaPhase1(EntityVoidBoss entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	public void Init() {
		super.Init();
		teleportLocations = new ArrayList<BlockPos>();
		// TODO validate these
		teleportLocations.add(getPosition().add(0, 12, 32));
		teleportLocations.add(getPosition().add(0, 10, 7));
		teleportLocations.add(getPosition().add(17, 6, 35));
		teleportLocations.add(getPosition().add(-12, 6, 35));
		teleportLocations.add(getPosition().add(0, 5, 19));
		teleportLocations.add(getEntity().getPosition());
	}

	@Override
	public void readPacket(Packet packet) {
		if (packet instanceof XiaTookDamagePacket) actionTeleport();
	}

	@Override
	public void update() {
		if (tick % actionTick == 0) {
			switch (rand.nextInt(4)) { // TODO: figure out what kind of attacks we want for phase 1 (set this back to 4)
				case 0:
					//actionTeleport();
					break;
				case 1: // Voidic Fire (Same as Lich)
					getEntity().worldObj.spawnEntityInWorld(new EntityLichInferno(getEntity().worldObj, getEntity().getPosition(), 10, 10));
					break;
				case 2: // Use the force luke :P some sort of choke mechanic idk
					break;
				case 3: // zues bolt
					break;
				case 4: // random knockback I guess
					break;
				default:
					actionTeleport();
					break;
			}
		}
		tick++;
	}

	private void actionTeleport() {
		BlockPos nextLoc = getNextTeleportLocation();
		// TODO: do some fancy particles and sounds here for the previous location and new location
		getEntity().setPositionAndUpdate(nextLoc.getX(), nextLoc.getY(), nextLoc.getZ());
	}

	private BlockPos getNextTeleportLocation() {
		int i = rand.nextInt(teleportLocations.size());
		BlockPos loc = teleportLocations.get(i > 0 ? i : 0);
		if (getEntity().getPosition().equals(loc)) return getNextTeleportLocation();
		return loc;
	}

	@Override
	public void doAction(BlockPos pos) {

	}

}
