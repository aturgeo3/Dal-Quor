package tamaized.dalquor.common.entity.ghost;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.entity.EntityVoidNPC;
import tamaized.dalquor.common.handlers.SkinHandler;
import tamaized.dalquor.common.sound.VoidSoundEvents;

import java.util.UUID;

public class EntityGhostPlayerBase extends EntityVoidNPC {

	private static final DataParameter<Boolean> SHOULD_DIE = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RUNNING = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RUNE = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> RUNE_TARGET = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.VARINT);
	private static final DataParameter<Float> RUNE_STATE = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> MAX_RUNE_STATE = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.FLOAT);
	private static final DataParameter<String> NAME = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.STRING);
	private static final DataParameter<String> ID = EntityDataManager.createKey(EntityGhostPlayerBase.class, DataSerializers.STRING);
	private boolean canInteract = false;
	private int tick = 0;
	private int finalTick = 20 * 6;
	private int runeRotation = 0;

	public EntityGhostPlayerBase(World par1World) {
		super(par1World);

		this.isImmuneToFire = true;
		this.hurtResistantTime = 10;
		this.setSize(0.6F, 1.8F);
		canMove = false;
		// this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
		// this.tasks.addTask(6, new EntityAILookIdle(this));
		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setInvulnerable(true);

	}

	protected EntityGhostPlayerBase(World world, UUID id, boolean interactable) {
		this(world);
		SkinHandler.PlayerSkinInfoWrapper info = SkinHandler.getGhostInfo(id);
		setGhostName(info == null ? "null" : info.getName());
		setUUID(id);
		canInteract = interactable;
		setShouldDie(false);
	}

	protected EntityGhostPlayerBase(World world, UUID id, boolean interactable, Entity target, int length) {
		this(world);
		SkinHandler.PlayerSkinInfoWrapper info = SkinHandler.getGhostInfo(id);
		setGhostName(info == null ? "null" : info.getName());
		setUUID(id);
		canInteract = interactable;
		setRune(true);
		setRuneTarget(target.getEntityId());
		setRuneState(0);
		setMaxRuneState(length);
		setRunning(!interactable);
		tick = 0;
		setShouldDie(false);
	}

	public static EntityGhostPlayerBase newInstance(World world, UUID id, boolean interactable) {
		return SkinHandler.isSlimModel(id) ? new EntityGhostPlayerSlim(world, id, interactable) : new EntityGhostPlayer(world, id, interactable);
	}

	public static EntityGhostPlayerBase newInstance(World world, UUID id, boolean interactable, Entity target, int length) {
		return SkinHandler.isSlimModel(id) ? new EntityGhostPlayerSlim(world, id, interactable, target, length) : new EntityGhostPlayer(world, id, interactable, target, length);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SHOULD_DIE, true);
		dataManager.register(RUNNING, false);
		dataManager.register(RUNE, false);
		dataManager.register(RUNE_TARGET, 0);
		dataManager.register(RUNE_STATE, 0.0F);
		dataManager.register(MAX_RUNE_STATE, 100.0F);
		dataManager.register(NAME, "Steve");
		dataManager.register(ID, "00000000-0000-0000-0000-000000000000");
	}

	public boolean getShouldDie() {
		return dataManager.get(SHOULD_DIE);
	}

	public void setShouldDie(boolean die) {
		dataManager.set(SHOULD_DIE, die);
	}

	public boolean getRunning() {
		return dataManager.get(RUNNING);
	}

	public void setRunning(boolean running) {
		dataManager.set(RUNNING, running);
	}

	public void setGhostName(String name) {
		dataManager.set(NAME, name);
	}

	public String getGhostName() {
		return dataManager.get(NAME);
	}

	public void setUUID(UUID id) {
		dataManager.set(ID, id.toString());
	}

	public UUID getUUID() {
		return UUID.fromString(dataManager.get(ID));
	}

	public boolean getRune() {
		return dataManager.get(RUNE);
	}

	public void setRune(boolean rune) {
		dataManager.set(RUNE, rune);
	}

	public int getRuneTarget() {
		return dataManager.get(RUNE_TARGET);
	}

	public void setRuneTarget(int entityID) {
		dataManager.set(RUNE_TARGET, entityID);
	}

	public float getRuneState() {
		return dataManager.get(RUNE_STATE);
	}

	public void setRuneState(float state) {
		dataManager.set(RUNE_STATE, state);
	}

	public float getMaxRuneState() {
		return dataManager.get(MAX_RUNE_STATE);
	}

	public void setMaxRuneState(float state) {
		dataManager.set(MAX_RUNE_STATE, state);
	}

	@Override
	public void knockBack(Entity entityIn, float strength, double xRatio, double zRatio) {

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	public boolean isInteractable() {
		return canInteract;
	}

	public float getRuneStatePerc() {
		return getRuneState() / getMaxRuneState();
	}

	public boolean isRuneCharged() {
		return getRuneStatePerc() >= 1.0F;
	}

	public int getRuneRotationForRender() {
		return runeRotation;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!world.isRemote) {
			if (getShouldDie()) {
				setDead();
				return;
			}
			if (getRunning()) {
				if (getRune() && getRuneState() < getMaxRuneState())
					setRuneState(getRuneState() + 1F);
			}
		}
		runeRotation += (int) Math.ceil(20F * getRuneStatePerc());
		if (runeRotation >= 360)
			runeRotation = 0;
		if (getRune()) {
			if (world.getEntityByID(getRuneTarget()) != null) {
				updateLook();
				if (world.isRemote && isRuneCharged()) {
					spawnParticles();
				}
			} else {
				setDead();
			}
		}
		if (getRunning())
			tick++;
	}

	@SideOnly(Side.CLIENT)
	private void spawnParticles() {
		for (int i = 0; i < 10; i++) {
			double rx = (world.rand.nextDouble() * 1.5) - 0.5;
			double ry = (world.rand.nextDouble() * 1.5) - 0.5;
			double rz = (world.rand.nextDouble() * 1.5) - 0.5;
			double ox = posX;
			double oy = posY;
			double oz = posZ;
			Entity target = world.getEntityByID(getRuneTarget());
			double tx = target.posX + rx;
			double ty = target.posY + (target.height / 2.0F) + ry;
			double tz = target.posZ + rz;
			Vec3d vec = getLookVec();
			double offsetX = (vec.x * 1);
			double offsetY = 1.5;
			double offsetZ = (vec.z * 1);
			double dx = ox - (tx - offsetX);
			double dy = getEntityBoundingBox().minY + (double) (height / 2.0F) - (((ty - offsetY) + (double) (target.height / 2.0F)));
			double dz = oz - (tz - offsetZ);
			net.minecraft.client.Minecraft.getMinecraft().effectRenderer.addEffect(new tamaized.dalquor.client.particles.ParticleColorEnchantmentTable(world, tx, ty + (target.height / 2F), tz, dx, dy, dz));
		}
	}

	private void updateLook() {
		Entity runeTarget = world.getEntityByID(getRuneTarget());
		getLookHelper().setLookPosition(runeTarget.posX, runeTarget.posY + (double) runeTarget.getEyeHeight(), runeTarget.posZ, 10.0F, (float) getVerticalFaceSpeed());
		double d0 = runeTarget.posX - posX;
		double d2 = runeTarget.posZ - posZ;
		float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
		float f3 = MathHelper.wrapDegrees(f - rotationYaw);
		rotationYaw = rotationYaw + f3;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (getRunning() || !canInteract)
			return false;
		setRunning(true);
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void collideWithEntity(Entity par1Entity) {
	}

	@Override
	public void applyEntityCollision(Entity par1Entity) {
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0D);
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
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public ITextComponent getDisplayName() {
		return new TextComponentString(getGhostName());
	}

}