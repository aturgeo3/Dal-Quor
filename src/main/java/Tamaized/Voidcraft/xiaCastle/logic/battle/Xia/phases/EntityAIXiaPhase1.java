package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import Tamaized.TamModized.particles.ParticleHelper;
import Tamaized.TamModized.particles.ParticlePacketHandlerRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.damageSources.DamageSourceVoidicInfusion;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia.XiaTookDamagePacket;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.network.IVoidBossAIPacket;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler;
import Tamaized.Voidcraft.particles.network.XiaLaserPacketHandler.XiaLaserParticleData;
import Tamaized.Voidcraft.voidicInfusion.PlayerInfusionHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;

public class EntityAIXiaPhase1<T extends EntityBossXia> extends EntityVoidNPCAIBase<T> {

	private final Random rand = new Random();

	private ArrayList<Double[]> teleportLocations;
	private Double[] currLoc = new Double[] { 0.0D, 0.0D, 0.0D };

	private int actionTick = 20 * 10;
	private int teleportTick = 20 * 3;

	private int resetAnimationTick = 0;

	private boolean isTeleporting = false;

	public EntityAIXiaPhase1(T entityBoss, ArrayList<Class> c) {
		super(entityBoss, c);
	}

	@Override
	public void Init() {
		super.Init();
		teleportLocations = new ArrayList<Double[]>();
		teleportLocations.add(currLoc = new Double[] { 0.0D, -0.5D, 0.0D });
		teleportLocations.add(new Double[] { 0.0D, -7.0D, -12.0D });
		teleportLocations.add(new Double[] { 16.0D, -13.0D, -12.0D });
		teleportLocations.add(new Double[] { -16.0D, -13.0D, -12.0D });
	}

	@Override
	public void readPacket(IVoidBossAIPacket packet) {
		if (packet instanceof XiaTookDamagePacket) doTeleport();
	}

	@Override
	public void update() {
		if (resetAnimationTick == 0) {
			resetAnimationTick--;
			getEntity().setArmRotations(0, 0, 0, 0);
		} else if (resetAnimationTick >= 0) {
			resetAnimationTick--;
		}

		if (tick % actionTick == 0) {
			switch (rand.nextInt(5)) {
				case 0:
					getEntity().setArmRotations(180, 180, 0, 0);
					resetAnimationTick = 20 * 4;
					actionTeleport();
					break;
				case 1: // Voidic Fire (Same as Lich)
					getEntity().setArmRotations(180, 0, 0, 0);
					resetAnimationTick = 20 * 4;
					getEntity().worldObj.spawnEntityInWorld(new EntityLichInferno(getEntity().worldObj, getEntity().getPosition(), 10, 10));
					break;
				case 2: // Use the force luke :P some sort of choke mechanic idk
					if (closestEntity == null) break;
					resetAnimationTick = 20 * 4;
					getEntity().setArmRotations(90, 90, 0, 0);
					closestEntity.attackEntityFrom(new DamageSourceVoidicInfusion(), 8.0f);
					break;
				case 3: // litBolt
					if (closestEntity == null) break;
					getEntity().setArmRotations(90, 0.0f, 0, 0);
					resetAnimationTick = 20 * 2;
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, closestEntity.posX, closestEntity.posY, closestEntity.posZ, false);
					entitylightningbolt.setLocationAndAngles(closestEntity.posX, closestEntity.posY + 1 + entitylightningbolt.getYOffset(), closestEntity.posZ, closestEntity.rotationYaw, closestEntity.rotationPitch);
					world.addWeatherEffect(entitylightningbolt);
					break;
				case 4: // Give less than 1 of the max voidic infusion to the player
					if (closestEntity == null) break;
					getEntity().setArmRotations(0, 90, 0, 0);
					resetAnimationTick = 20 * 2;
					if (closestEntity instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) closestEntity;
						PlayerInfusionHandler handler = voidCraft.infusionHandler.getPlayerInfusionHandler(player.getGameProfile().getId());
						handler.addInfusion(handler.getMaxInfusion() - (1 + handler.getInfusion()));
					}
					break;
				default:
					actionTeleport();
					break;
			}
			tick = 1;
		}
		if (isTeleporting) {
			spawnLaser();
			if (tick % teleportTick == 0) {
				doTeleport();
			}

		}
	}

	private void actionTeleport() {
		isTeleporting = true;
		spawnLaser();
	}

	private void spawnLaser() {
		XiaLaserParticleData data = ((XiaLaserPacketHandler) ParticlePacketHandlerRegistry.getHandler(voidCraft.particles.xiaTeleportHandler)).new XiaLaserParticleData(getEntity().getEntityId(), 0, -90, new float[] { 1.0f, 1.0f, 1.0f });
		ParticleHelper.sendPacketToClients(world, voidCraft.particles.xiaTeleportHandler, new Vec3d(getEntity().posX, getEntity().posY, getEntity().posZ), 64, new ParticleHelper.ParticlePacketHelper(voidCraft.particles.xiaTeleportHandler, data));
	}

	private void doTeleport() {
		Double[] nextLoc = getNextTeleportLocation();
		getEntity().setPositionAndUpdate(getBlockPosition().getX() + nextLoc[0] + 0.5, getBlockPosition().getY() + nextLoc[1], getBlockPosition().getZ() + nextLoc[2] + 0.5);
		isTeleporting = false;
	}

	private Double[] getNextTeleportLocation() {
		int i = rand.nextInt(teleportLocations.size());
		Double[] loc = teleportLocations.get(i > 0 ? i : 0);
		if (Arrays.equals(loc, currLoc)) loc = getNextTeleportLocation();
		currLoc = loc;
		return loc;
	}

	@Override
	public void doAction(BlockPos pos) {

	}

}
