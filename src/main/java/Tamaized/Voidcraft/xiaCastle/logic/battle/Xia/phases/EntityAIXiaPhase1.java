package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIXiaPhase1 extends EntityVoidNPCAIBase {

	private final Random rand = new Random();

	private ArrayList<BlockPos> teleportLocations;
	private BlockPos currPos;

	private int tick;
	private int actionTick = 20 * 10;

	public EntityAIXiaPhase1(EntityVoidBoss entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	public void Init() {
		super.Init();
		teleportLocations = new ArrayList<BlockPos>();
		// TODO fill with the set location positions
		teleportLocations.add(getEntity().getPosition());
		currPos = getEntity().getPosition();
	}

	@Override
	public void readPacket(Packet packet) {
		if(packet instanceof XiaTookDamagePacket) actionTeleport();
	}

	@Override
	public void updateTask() {
		super.updateTask();
		if (tick % actionTick == 0) {
			switch (rand.nextInt(4)) { // TODO: figure out what kind of attacks we want for phase 1
				case 0:
					actionTeleport();
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
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
		// TODO: do some fancy particles and sounds here for the prev location and new location
		getEntity().setPositionAndUpdate(nextLoc.getX(), nextLoc.getY(), nextLoc.getZ());
	}

	private BlockPos getNextTeleportLocation() {
		int i = rand.nextInt(teleportLocations.size());
		BlockPos loc = teleportLocations.get(i > 0 ? i : 0);
		if (currPos.equals(loc)) return getNextTeleportLocation();
		return loc;
	}

}
