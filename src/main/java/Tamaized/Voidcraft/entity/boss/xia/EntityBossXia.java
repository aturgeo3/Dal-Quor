package Tamaized.Voidcraft.entity.boss.xia;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import Tamaized.Voidcraft.entity.EntityVoidBoss;
import Tamaized.Voidcraft.network.VoidBossAIBus.Packet;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.EntityAIXiaPhase1;

public class EntityBossXia extends EntityVoidBoss {

	public EntityBossXia(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossXia(World world, IBattleHandler handler) {
		super(world, handler);
		this.setInvul(true);
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		bus.sendPacket(new XiaTookDamagePacket());
	}

	public class XiaTookDamagePacket extends Packet {

		public XiaTookDamagePacket() {
			bus.super();
		}

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
		// start();
		return super.processInteract(player, hand, stack);
	}

	@Override
	protected void initPhase(int phase) {
		if (phase == 1) {
			/**
			 * Cycle: - Teleports around, does various attacks, can be hit directly
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());
			// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
			setInvul(false);
			addAI(EntityAIXiaPhase1.class);
		} else if (phase == 2) {
			/**
			 * Cycle: - Teleports a short distance from a targetted player, walks towards them with a giant sword. do some sword mechanic stuff that can be dodged
			 */
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());

			// addAI(EntityAIPathHerobrineFlightPhase2.class); TODO
		} else if (phase == 3) {
			/**
			 * Cycle: - Stands still at his throne, various attacks, can take direct hits, upon taking a hit cause a massive blast that throws everyone back
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
			this.setHealth(this.getMaxHealth());

			// addAI(EntityAIPathHerobrineFlightPhase3.class); TODO

		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobXiaSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Xia");
	}

	@Override
	protected void updatePhase(int phase) {

	}

	@Override
	protected ArrayList<Class> getFilters() {
		ArrayList<Class> filter = new ArrayList<Class>();
		filter.add(EntityPlayer.class);
		return filter;
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 0.6F;
	}

	@Override
	protected float sizeHeight() {
		return 1.8F;
	}

	@Override
	protected int maxPhases() {
		return 3;
	}
}