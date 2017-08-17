package tamaized.voidcraft.common.entity.boss.herobrine;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.EntityVoidNPC;
import tamaized.voidcraft.common.entity.ai.EntityAIFindEntityNearestEntityNoSight;
import tamaized.voidcraft.common.entity.boss.IVoidBossData;
import tamaized.voidcraft.common.entity.boss.herobrine.ai.EntityAIHerobrinePhase1;
import tamaized.voidcraft.common.entity.boss.herobrine.ai.EntityAIHerobrinePhase2;
import tamaized.voidcraft.common.entity.boss.herobrine.ai.EntityAIHerobrinePhase3;
import tamaized.voidcraft.common.sound.VoidSoundEvents;

public class EntityBossHerobrine extends EntityVoidNPC implements IVoidBossData {

	private int phase = 0;
	private BlockPos initialPos;
	private boolean cantUpdatePos = false;

	public EntityBossHerobrine(World world) {
		super(world);
		noClip = true;
		setInvulnerable(true);
		setSize(0.6F, 1.8F);
		moveHelper = new EntityVoidNPC.BossFlyNoclipMoveHelper(this);
		updateStats();
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAIHerobrinePhase1(this));
		tasks.addTask(1, new EntityAIHerobrinePhase2(this));
		tasks.addTask(1, new EntityAIHerobrinePhase3(this));
		tasks.addTask(7, new EntityVoidNPC.AILookAround(this));

		targetTasks.addTask(1, new EntityAIFindEntityNearestEntityNoSight(this, EntityPlayer.class));
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.WITHER, 5.0F);
		if (entityIn instanceof EntityLivingBase)
			((EntityLivingBase) entityIn).knockBack(this, 0.5F, (double) MathHelper.sin(this.rotationYaw * 0.017453292F), (double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
		else
			entityIn.addVelocity((double) (-MathHelper.sin(this.rotationYaw * 0.017453292F) * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * 0.017453292F) * 0.5F));

	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {

	}

	public final BlockPos getInitialPos() {
		return initialPos;
	}

	public final void updateInitialPos() {
		if (!cantUpdatePos)
			initialPos = getPosition();
		cantUpdatePos = true;
	}

	public final void setPositionAndUpdate(BlockPos pos) {
		setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
	}

	public final int getPhase() {
		return phase;
	}

	@Override
	protected void encodePacketData(ByteBuf stream) {

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("phase", phase);
		compound.setBoolean("cantUpdatePos", cantUpdatePos);
		compound.setIntArray("initialPos", new int[]{initialPos.getX(), initialPos.getY(), initialPos.getZ()});
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		phase = compound.getInteger("phase");
		cantUpdatePos = compound.getBoolean("cantUpdatePos");
		int[] array = compound.getIntArray("initialPos");
		if (array.length == 3)
			initialPos = new BlockPos(array[0], array[1], array[2]);
		super.readFromNBT(compound);
	}

	private void updateStats() {
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40);
		setInvulnerable(true);
		switch (phase) {
			default:
			case 0:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
				break;
			case 1:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.025D);
				break;
			case 2:
				getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
				getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
				break;
		}
		setHealth(getMaxHealth());
		if(phase > 2) setDead();
	}

	public final void doDamage(int a) {
		if (getHealth() <= a) {
			phase++;
			updateStats();
			return;
		}
		setHealth(getHealth() - a);
		if (phase == 1)
			getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() + 0.025D);

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobHerobrineSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
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
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("entity.voidcraft.Herobrine.name");
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}

	@Override
	public float getPercentHPForBossBar() {
		return getHealth() / getMaxHealth();
	}

	@Override
	public ITextComponent getNameForBossBar() {
		return getDisplayName();
	}

}
