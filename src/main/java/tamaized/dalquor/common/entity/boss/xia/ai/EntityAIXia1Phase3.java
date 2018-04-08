package tamaized.dalquor.common.entity.boss.xia.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import tamaized.tammodized.common.helper.MotionHelper;
import tamaized.tammodized.common.particles.ParticleHelper;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia;
import tamaized.dalquor.common.entity.nonliving.ProjectileDisintegration;
import tamaized.dalquor.registry.ModPotions;

public class EntityAIXia1Phase3 extends EntityAIBase implements EntityBossXia.IDamageListener {

	private static final AxisAlignedBB teleportationBox = new AxisAlignedBB(-18, 0, -25, 18, 5, 6);
	private final EntityBossXia boss;
	private int actionTick = 20 * 5;
	private int tick = 1;
	private int resetAnimationTick = 0;

	public EntityAIXia1Phase3(EntityBossXia entity) {
		boss = entity;
		setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return boss.getPhase() == 2;
	}

	@Override
	public boolean execute() {
		return shouldExecute();
	}

	@Override
	public void startExecuting() {
		boss.setPositionAndUpdate(boss.getInitialPos().getX() + 0.5F, boss.getInitialPos().getY() - 0.5F, boss.getInitialPos().getZ() + 0.5F);
	}

	@Override
	public void onTakeDamage() {
		doBlast();
	}

	@Override
	public void onDoDamage(Entity entity) {
	}

	private void doBlast() {
		boss.setLimbRotations(135, 135, 45, -45);
		resetAnimationTick = 20 * 2;
		boss.world.playSound(null, boss.posX, boss.posY, boss.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (boss.world.rand.nextFloat() - boss.world.rand.nextFloat()) * 0.2F) * 0.7F);
		ParticleHelper.spawnVanillaParticleOnServer(boss.world, EnumParticleTypes.EXPLOSION_HUGE, boss.posX, boss.posY, boss.posZ, 1.0D, 0.0D, 0.0D);

		for (Entity e : boss.world.getEntitiesWithinAABBExcludingEntity(boss, new AxisAlignedBB(boss.posX - 5, boss.posY - 5, boss.posZ - 5, boss.posX + 5, boss.posY + 5, boss.posZ + 5))) {
			double mX = 0;
			double mY = 1;
			double mZ = -5;
			MotionHelper.addMotion(e, new Vec3d(mX, mY, mZ));
		}
	}

	private boolean hasSheathe() {
		return boss.getActivePotionEffect(ModPotions.fireSheathe) != null || boss.getActivePotionEffect(ModPotions.frostSheathe) != null || boss.getActivePotionEffect(ModPotions.litSheathe) != null || boss.getActivePotionEffect(ModPotions.acidSheathe) != null || boss.getActivePotionEffect(ModPotions.voidSheathe) != null;
	}

	@Override
	public void updateTask() {
		if (resetAnimationTick == 0) {
			resetAnimationTick--;
			boss.setLimbRotations(0, 0, 0, 0);
		} else if (resetAnimationTick >= 0) {
			resetAnimationTick--;
		}
		if (tick % (actionTick) == 0 && boss.getAttackTarget() != null) {
			actionTick = (3 + boss.getRNG().nextInt(3)) * 20;
			doAction();
		}
		tick++;
	}

	private void doAction() {
		if (hasSheathe()) {
			switch (boss.getRNG().nextInt(5)) {
				default:
				case 0: // Fireball
					boss.setLimbRotations(90, 0, 0, 0);
					resetAnimationTick = 20 * 2;
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
					break;
				case 1: // Lit Strike
					boss.setLimbRotations(0, 180, 0, 0);
					resetAnimationTick = 20 * 2;
					double x = boss.getAttackTarget().posX;
					double y = boss.getAttackTarget().posY;
					double z = boss.getAttackTarget().posZ;
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(boss.world, x, y, z, false);
					entitylightningbolt.setLocationAndAngles(x, y + 1 + entitylightningbolt.getYOffset(), z, boss.getAttackTarget().rotationYaw, boss.getAttackTarget().rotationPitch);
					boss.world.addWeatherEffect(entitylightningbolt);
					break;
				case 2: // Ice Spike
					boss.setLimbRotations(180, 180, 0, 0);
					resetAnimationTick = 20 * 2;
//					if (boss.world.isAirBlock(boss.getAttackTarget().getPosition())) TODO
//						boss.world.setBlockState(boss.getAttackTarget().getPosition(), VoidCraftBlocks.iceSpike.getDefaultState());
					break;
				case 3: // Disint
					if (boss.getAttackTarget() instanceof EntityLivingBase) {
						boss.setLimbRotations(0, 90, 0, 0);
						resetAnimationTick = 20 * 2;
						ProjectileDisintegration disint = new ProjectileDisintegration(boss.world, boss, boss.getAttackTarget(), 10.0F);
						boss.world.spawnEntity(disint);
					}
					break;
				case 4: // Voidic Infusion
					boss.setLimbRotations(90, 90, 45, -45);
					resetAnimationTick = 20 * 2;
					if (boss.getAttackTarget() instanceof EntityPlayer) {
						EntityPlayer player = (EntityPlayer) boss.getAttackTarget();
						player.addPotionEffect(new PotionEffect(ModPotions.voidicInfusion, 20 * 10));
					}
					break;
			}
		} else {
			castSheathe();
		}
	}

	private void castSheathe() {
		boss.setLimbRotations(180, 0, 0, 0);
		resetAnimationTick = 20 * 2;
		boss.clearActivePotions();
		Potion sheathe;
		switch (boss.getRNG().nextInt(5)) {
			default:
			case 0:
				sheathe = ModPotions.fireSheathe;
				break;
			case 1:
				sheathe = ModPotions.frostSheathe;
				break;
			case 2:
				sheathe = ModPotions.litSheathe;
				break;
			case 3:
				sheathe = ModPotions.acidSheathe;
				break;
			case 4:
				sheathe = ModPotions.voidSheathe;
				break;
		}
		boss.addPotionEffect(new PotionEffect(sheathe, 20 * 30));
	}
}
