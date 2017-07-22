package tamaized.voidcraft.common.entity.boss.twins;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.EntityVoidNPC;
import tamaized.voidcraft.common.sound.VoidSoundEvents;

public class EntityBossZol extends EntityVoidNPC {

	public EntityBossZol(World par1World) {
		super(par1World);
		this.setInvulnerable(true);
		isImmuneToFire = true;
		canMove = false;
		setSize(0.6F, 1.8F);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 64.0F));
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobZolSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobZolSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.5F;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("entity.voidcraft.Zol");
	}

	@Override
	protected void encodePacketData(ByteBuf stream) {

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {

	}

}