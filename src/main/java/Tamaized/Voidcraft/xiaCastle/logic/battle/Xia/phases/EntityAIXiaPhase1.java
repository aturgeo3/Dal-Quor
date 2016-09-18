package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler.XiaLaserParticleData;
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
		teleportLocations.add(getPosition().add(0, 11, 32));
		teleportLocations.add(getPosition().add(1, 11, 32));
		teleportLocations.add(getPosition().add(-1, 11, 32));
		// teleportLocations.add(getPosition().add(0, 9, 7));
		// teleportLocations.add(getPosition().add(17, 5, 35));
		// teleportLocations.add(getPosition().add(-12, 5, 35));
		// teleportLocations.add(getPosition().add(0, 4, 19));
		// teleportLocations.add(getEntity().getPosition());
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof XiaTookDamagePacket) actionTeleport();
	}

	@Override
	public void update() {
		if (tick % actionTick == 0) {
			switch (rand.nextInt(1)) { // TODO: figure out what kind of attacks we want for phase 1 (set this back to 4)
				case 0:
					actionTeleport();
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
			tick = 0;
		}
		tick++;
	}

	private void actionTeleport() {
		BlockPos nextLoc = getNextTeleportLocation();
		// TODO: do some fancy particles and sounds here for the previous location and new location
		XiaLaserParticleData data = ((XiaLaserPacketHandler) ParticlePacketHandlerRegistry.getHandler(voidCraft.particles.xiaTeleportHandler)).new XiaLaserParticleData(getEntity().getEntityId(), 0, -90, new float[] { 1.0f, 1.0f, 1.0f });
		ParticleHelper.sendPacketToClients(world, voidCraft.particles.xiaTeleportHandler, new Vec3d(getEntity().posX, getEntity().posY, getEntity().posZ), 64, new ParticleHelper.ParticlePacketHelper(voidCraft.particles.xiaTeleportHandler, data));
		getEntity().setPositionAndUpdate(nextLoc.getX() + 0.5, nextLoc.getY(), nextLoc.getZ() + 0.5);
		tick = 1;
	}

	private BlockPos getNextTeleportLocation() {
		int i = rand.nextInt(teleportLocations.size());
		BlockPos loc = teleportLocations.get(i > 0 ? i : 0);
		return loc;
	}

	@Override
	public void doAction(BlockPos pos) {

	}

}
