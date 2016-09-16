package Tamaized.Voidcraft.entity.boss.herobrine;

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
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.EntityAIHerobrinePhase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.EntityAIHerobrinePhase2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases.EntityAIHerobrinePhase3;

public class EntityBossHerobrine extends EntityVoidBoss {

	public EntityBossHerobrine(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossHerobrine(World world, IBattleHandler handler) {
		super(world, handler);
		this.setInvul(true);
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		if (phase == 2) getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() + 0.05D);
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
			 * Cycle: - Herobrine shoots fireballs. - Pillars need to get hit with fireball, cycle through textures of green wool, yellow, red. 4th hit will damage herobrine. - Pillars Spawn every 5 seconds - Max of 6 Pillars at a time
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			this.setHealth(this.getMaxHealth());
			// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);

			addAI(EntityAIHerobrinePhase1.class);
		} else if (phase == 2) {
			/**
			 * Cycle: - Herobrine chases the player. - On touching a player, deal damage. - Herobrine must run through a pillar to be dealt damage. - Pillars Spawn every 5 seconds - Max of 6 Pillars at a time - Increase his speed everytime he is hurt
			 */
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
			this.setHealth(this.getMaxHealth());

			addAI(EntityAIHerobrinePhase2.class);
		} else if (phase == 3) {
			/**
			 * Cycle: - Herobrine floats in the air standstill. - Does various attacks. - 4 Npcs spawn at random and must be interacted with by the player, deals 25 hp to herobrine. - npcs spawn every 30s - Max of 1 npc at a time and timer doesnt move while an npc is active
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			this.setHealth(this.getMaxHealth());

			addAI(EntityAIHerobrinePhase3.class);

		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobHerobrineSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobHerobrineSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobHerobrineSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Avatar of Herobrine");
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