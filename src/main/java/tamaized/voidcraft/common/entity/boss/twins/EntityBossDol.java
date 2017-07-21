package tamaized.voidcraft.common.entity.boss.twins;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.EntityVoidBoss;
import tamaized.voidcraft.common.sound.VoidSoundEvents;
import tamaized.voidcraft.common.xiacastle.logic.battle.twins.TwinsBattleHandler;

import java.util.ArrayList;
import java.util.List;

public class EntityBossDol extends EntityVoidBoss<TwinsBattleHandler> {

	public EntityBossDol(World par1World) {
		super(par1World);
		this.setInvulnerable(true);
	}

	public EntityBossDol(World world, TwinsBattleHandler handler) {
		super(world, handler, true);
		this.setInvulnerable(true);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 64.0F));
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {

	}

	@Override
	protected void deathHook() {

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
	protected List<Class> getFilters() {
		return new ArrayList<>();
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
	protected SoundEvent getHurtSound(DamageSource source) {
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

	@Override
	protected void encodePacketData(ByteBuf stream) {

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {

	}

}