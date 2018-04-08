package tamaized.dalquor.common.entity.mob;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.starforge.IStarForgeCapability;
import tamaized.dalquor.common.entity.EntityVoidMob;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.dalquor.common.sound.VoidSoundEvents;
import tamaized.dalquor.common.starforge.effects.StarForgeEffectList;
import tamaized.dalquor.network.client.ClientPacketHandlerSheathe;
import tamaized.dalquor.registry.VoidCraftItems;
import tamaized.dalquor.registry.VoidCraftPotions;
import tamaized.dalquor.registry.VoidCraftTools;

public class EntityMobEtherealGuardian extends EntityVoidMob {

	public EntityMobEtherealGuardian(World p_i1738_1_) {
		super(p_i1738_1_);
		isImmuneToFire = true;
		ItemStack stack = new ItemStack(VoidCraftTools.starforgedSword);
		IStarForgeCapability cap = stack.getCapability(CapabilityList.STARFORGE, null);
		if (cap != null) {
			cap.addEffect(StarForgeEffectList.firstDegreeBurns);
			cap.addEffect(StarForgeEffectList.secondDegreeBurns);
			cap.addEffect(StarForgeEffectList.thirdDegreeBurns);
		}
		setHeldItem(EnumHand.MAIN_HAND, stack);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIBreakDoor(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

		Predicate ies = new Predicate() {

			public boolean apply(Entity p_82704_1_) {
				return !(p_82704_1_ instanceof EntityWitherSkeleton) && !(p_82704_1_ instanceof EntityHerobrineCreeper || p_82704_1_ instanceof EntityShulker);
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return p_apply_1_ instanceof Entity && this.apply((Entity) p_apply_1_);
			}
		};

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntitySnowman.class, 0, true, false, ies));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityMob.class, 0, true, false, ies));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(45.0D);
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (getActivePotionEffect(VoidCraftPotions.fireSheathe) == null) {
				clearActivePotions();
				Potion potion = VoidCraftPotions.fireSheathe;
				PotionEffect effect = new PotionEffect(potion, 100);
				addPotionEffect(effect);
				DalQuor.network.sendToAllAround(new ClientPacketHandlerSheathe.Packet(getEntityId(), Potion.getIdFromPotion(potion), effect.getDuration()), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobWrathSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobWrathSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobWrathSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraftItems.voidicPhlogiston;
	}

	/**
	 * Overridden to remove the ability to drop Weapons and Armor
	 */
	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return this.experienceValue;
	}

}
