package Tamaized.Voidcraft.entity.boss.twins;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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

public class EntityBossDol extends EntityVoidBoss {

	public EntityBossDol(World par1World) {
		super(par1World);
		this.setInvul(true);
	}

	public EntityBossDol(World world, IBattleHandler handler) {
		super(world, handler, true);
		this.setInvul(true);
	}

	@Override
	protected void addDefaultTasks() {
		for (Class c : getFilters())
			this.tasks.addTask(6, new EntityAIWatchClosest(this, c, 64.0F));
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
		// start();
		return super.processInteract(player, hand, stack);
	}

	@Override
	protected void initPhase(int phase) {

	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

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

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobDolSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound() {
		return VoidSoundEvents.EntityMobDolSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobDolSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Dol");
	}

}