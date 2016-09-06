package Tamaized.Voidcraft.mobs.entity.boss;

import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;
import Tamaized.Voidcraft.mobs.entity.boss.bar.IVoidBossData;
import Tamaized.Voidcraft.sound.BossMusicManager;
import Tamaized.Voidcraft.sound.VoidSoundEvents;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.EntityAIPathHerobrineFlightPhase1;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.EntityAIPathHerobrineFlightPhase2;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.EntityAIPathHerobrineFlightPhase3;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.EntityVoidNPCAIBase;

public class EntityMobHerobrine extends EntityVoidNPC implements IVoidBossData {

	private IBattleHandler handler;
	private int phase = 0;
	private static final int PHASE_01 = 1;
	private static final int PHASE_02 = 2;
	private static final int PHASE_03 = 3;
	private boolean ready = false;
	private boolean active = false;

	private ArrayList<EntityAIBase> ai = new ArrayList<EntityAIBase>();
	private ArrayList<Class> filter = new ArrayList<Class>();

	public EntityMobHerobrine(World par1World) {
		super(par1World);
		this.isImmuneToFire = true;
		this.hurtResistantTime = 10;
		this.setSize(0.6F, 1.8F);
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		filter.add(EntityPlayer.class);

		// this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.setInvul(true);
	}

	public EntityMobHerobrine(World world, IBattleHandler handler) {
		this(world);
		this.handler = handler;
	}

	public boolean isActive() {
		return active;
	}

	public boolean displayBossMode() {
		return (PHASE_03 > phase && phase > 0);
	}

	public void doDamage(int a) {
		this.setHealth(this.getHealth() - a);
		if (phase == PHASE_02) getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() + 0.05D);
	}

	public void start() {
		if (phase == 0) ready = true;
		else doDamage((int) this.getMaxHealth());
		active = true;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand, @Nullable ItemStack stack) {
		// start();
		return super.processInteract(player, hand, stack);
	}

	public boolean hasStartedFight() {
		return phase > 0 ? true : false;
	}

	@Override
	public float getPercentHP() {
		return this.getHealth() / this.getMaxHealth();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!worldObj.isRemote) {
			if (handler == null) {
				setDead();
				return;
			}
			updateAI();
		}
	}

	private void updateAI() {
		if(worldObj.isRemote) return;
		if (ready) {
			phase++;
			System.out.println("Starting Phase Init: " + phase);
			InitPhase(phase);
			System.out.println("Phase (" + phase + ") Init finished");
			ready = false;
		}

		if (phase == PHASE_01) {

		} else if (phase == PHASE_02) {

		} else if (phase == PHASE_03) {
			
		} else {
			if (this.getHealth() <= 0.0F) {
				this.trueDeathUpdate();
			}
		}
	}

	private void InitPhase(int p) {
		Iterator iter = ai.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof EntityVoidNPCAIBase) {
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if (o instanceof EntityAIBase) tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		canDie = false;
		if (p == PHASE_01) {
			/**
			 * Cycle: - Herobrine shoots fireballs. - Pillars need to get hit with fireball, cycle through textures of green wool, yellow, red. 4th hit will damage herobrine. - Pillars Spawn every 5 seconds - Max of 6 Pillars at a time
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			this.setHealth(this.getMaxHealth());

			EntityVoidNPCAIBase newAI = new EntityAIPathHerobrineFlightPhase1(this, filter);
			ai.add(newAI);
			newAI.Init();
			this.tasks.addTask(1, newAI);
			// BossMusicManager.PlayTheSound(this.worldObj, this, new ItemStack(voidCraft.items.voidDiscs.get(10)), new int[]{(int) this.posX, (int) this.posY, (int) this.posZ}, true);
		} else if (p == PHASE_02) {
			/**
			 * Cycle: - Herobrine chases the player. - On touching a player, deal damage. - Herobrine must run through a pillar to be dealt damage. - Pillars Spawn every 5 seconds - Max of 6 Pillars at a time - Increase his speed everytime he is hurt
			 */
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
			this.setHealth(this.getMaxHealth());

			EntityVoidNPCAIBase newAI = new EntityAIPathHerobrineFlightPhase2(this, filter);
			ai.add(newAI);
			newAI.Init();
			this.tasks.addTask(1, newAI);
		} else if (p == PHASE_03) {
			/**
			 * Cycle: - Herobrine floats in the air standstill. - Does various attacks. - 4 Npcs spawn at random and must be interacted with by the player, deals 25 hp to herobrine. - npcs spawn every 30s - Max of 1 npc at a time and timer doesnt move while an npc is active
			 */
			isFlying = true;
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			this.setHealth(this.getMaxHealth());

			EntityVoidNPCAIBase newAI = new EntityAIPathHerobrineFlightPhase3(this, filter);
			ai.add(newAI);
			newAI.Init();
			this.tasks.addTask(1, newAI);

		} else {
			canDie = true;
			onDeathUpdate();
		}
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource p_70645_1_) { // Switch phases when we fake death
		if (phase > PHASE_03) {
			this.setHealth(0);
			this.isDead = true;
			super.onDeath(p_70645_1_);
			setDead();
		} else {
			ready = true;
			updateAI();
		}
	}

	private void trueDeathUpdate() {
		active = false;
		++this.deathTime;

		if (this.deathTime >= 20) {
			int i;

			if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && this.canDropLoot() && this.worldObj.getGameRules().getBoolean("doMobLoot")) {
				i = this.getExperiencePoints(this.attackingPlayer);

				while (i > 0) {
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
				}
			}

			this.isDead = true;

			for (i = 0; i < 20; ++i) {
				double d2 = this.rand.nextGaussian() * 0.02D;
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d2, d0, d1);
			}
		}
	}

	@Override
	protected void onDeathUpdate() { // Intercept deathUpdate to keep entity alive
		if (ready) return;
		this.isDead = false;
		onDeath(DamageSource.generic);
	}

	@Override
	public void setDead() { // True death
		BossMusicManager.StopTheSound();
		Iterator iter = ai.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof EntityVoidNPCAIBase) {
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if (o instanceof EntityAIBase) tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
		phase = 100;
		setHealth(0);
		this.isDead = true;
	}

	/**
	 * Checks whether target entity is alive.
	 */
	@Override
	public boolean isEntityAlive() {
		return phase > PHASE_03;
	}

	@Override
	protected void despawnEntity() {
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(999.0D);
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
}