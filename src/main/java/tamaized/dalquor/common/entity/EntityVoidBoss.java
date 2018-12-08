package tamaized.dalquor.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.entity.boss.IVoidBossData;
import tamaized.dalquor.common.xiacastle.logic.battle.EntityVoidNPCAIBase;
import tamaized.dalquor.common.xiacastle.logic.battle.IBattleHandler;
import tamaized.dalquor.network.IVoidBossAIPacket;
import tamaized.dalquor.network.VoidBossAIBus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
public abstract class EntityVoidBoss<T extends IBattleHandler> extends EntityVoidNPC implements IVoidBossData {

	private T handler;
	@Deprecated
	private VoidBossAIBus bus;

	@Deprecated
	private int phase = 0;
	@Deprecated
	private boolean ready = false;
	@Deprecated
	private boolean active = false;
	@Deprecated
	private boolean isDone = false;

	@Deprecated
	private List<EntityAIBase> ai = new ArrayList<>();

	private Ticket chunkLoadTicket;

	public EntityVoidBoss(World world) {
		super(world);
		isImmuneToFire = immuneToFire();
		hurtResistantTime = 10;
		setSize(sizeWidth(), sizeHeight());
		addDefaultTasks();
		enablePersistence();
		chunkLoadTicket = ForgeChunkManager.requestTicket(DalQuor.instance, world, Type.ENTITY);
		if (chunkLoadTicket != null)
			chunkLoadTicket.bindEntity(this);
	}

	public EntityVoidBoss(World world, T handler, boolean hasIdleTask) {
		this(world);
		this.handler = handler;
		bus = new VoidBossAIBus();
		if (hasIdleTask)
			tasks.addTask(6, new EntityAILookIdle(this));
	}

	protected Ticket getChunkloader() {
		return chunkLoadTicket;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Deprecated
	protected void addDefaultTasks() {
		for (Class c : getFilters())
			tasks.addTask(6, new EntityAIWatchClosest(this, c, 64.0F));
	}

	public T getHandler() {
		return handler;
	}

	@Deprecated
	public void start() {
		isDone = false;
		if (phase == 0 && bus != null)
			ready = true;
		else
			doDamage((int) getMaxHealth());
		active = true;
	}

	@Deprecated
	public boolean hasStartedFight() {
		return phase > 0;
	}

	@Deprecated
	public int getCurrentPhase() {
		return phase;
	}

	@Deprecated
	public boolean isActive() {
		return active;
	}

	@Deprecated
	public boolean isDone() {
		return isDone;
	}

	@Deprecated
	public boolean displayBossMode() {
		return (maxPhases() >= phase && phase > 0);
	}

	public void doDamage(int a) {
		setHealth(getHealth() - a);
		triggerOnDamage(phase, null, a);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean flag = super.attackEntityFrom(source, amount);
		if (flag)
			triggerOnDamage(phase, source, amount);
		return flag;
	}

	@Override
	public float getPercentHPForBossBar() {
		return getHealth() / getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (chunkLoadTicket != null) {
			for (ChunkPos pos : chunkLoadTicket.getChunkList()) {
				ForgeChunkManager.unforceChunk(chunkLoadTicket, pos);
			}
			for (int x = -1; x <= 1; x++)
				for (int z = -1; z <= 1; z++)
					ForgeChunkManager.forceChunk(chunkLoadTicket, world.getChunk((getPosition().getX() >> 4) + x, (getPosition().getZ() >> 4) + z).getPos());
		}
		handlerUpdate();
	}

	private void handlerUpdate() {
		if (!world.isRemote) {
			if (handler == null || bus == null) {
				setDead();
				return;
			}
			updateAI();
			bus.readNextPacket();
		}
	}

	@Deprecated
	protected void sendPacketToBus(IVoidBossAIPacket packet) {
		if (bus != null)
			bus.sendPacket(packet);
	}

	@Deprecated
	private void updateAI() {
		if (world.isRemote)
			return;

		if (ready) {
			phase++;
			preInitPhase(phase);
			ready = false;
		}

		if (phase > maxPhases()) {
			isDone = true;
			if (getHealth() <= 0.0F) {
				trueDeathUpdate();
			}
		} else {
			updatePhase(phase);
		}
	}

	@Deprecated
	private void preInitPhase(int p) {
		if (bus == null)
			return;
		Iterator iter = ai.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof EntityVoidNPCAIBase) {
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if (o instanceof EntityAIBase)
				tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		addDefaultTasks();
		bus.clearListeners();
		canDie = false;
		if (p > maxPhases()) {
			canDie = true;
			onDeathUpdate();
		} else {
			initPhase(p);
		}
	}

	@Deprecated
	protected void addAI(EntityVoidNPCAIBase<? extends EntityVoidBoss> newAi) {
		ai.add(newAi);
		newAi.Init();
		tasks.addTask(1, newAi);
		bus.addListener(newAi);
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	@Override
	public void onDeath(DamageSource p_70645_1_) { // Switch phases when we fake death
		if (phase >= maxPhases())
			deathHook();
		// setHealth(0);
		// isDead = true;
		// super.onDeath(p_70645_1_);
		// setDead();
		ready = true;
		updateAI();
	}

	/**
	 * called upon a true death for bosses to do final tasks such as drop items
	 */
	protected abstract void deathHook();

	private void trueDeathUpdate() {
		active = false;
		++deathTime;

		if (deathTime >= 20) {
			int i;

			if (!world.isRemote && (recentlyHit > 0 || isPlayer()) && canDropLoot() && world.getGameRules().getBoolean("doMobLoot")) {
				i = getExperiencePoints(attackingPlayer);

				while (i > 0) {
					int j = EntityXPOrb.getXPSplit(i);
					i -= j;
					world.spawnEntity(new EntityXPOrb(world, posX, posY, posZ, j));
				}
			}

			isDead = true;

			for (i = 0; i < 20; ++i) {
				double d2 = rand.nextGaussian() * 0.02D;
				double d0 = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX + (double) (rand.nextFloat() * width * 2.0F) - (double) width, posY + (double) (rand.nextFloat() * height), posZ + (double) (rand.nextFloat() * width * 2.0F) - (double) width, d2, d0, d1);
			}
		}
	}

	@Override
	protected void onDeathUpdate() { // Intercept deathUpdate to keep entity alive
		if (ready)
			return;
		isDead = false;
		onDeath(DamageSource.GENERIC);
	}

	@Override
	public void setDead() { // True death
		Iterator iter = ai.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			if (o instanceof EntityVoidNPCAIBase) {
				EntityVoidNPCAIBase ai = (EntityVoidNPCAIBase) o;
				ai.kill();
			}
			if (o instanceof EntityAIBase)
				tasks.removeTask((EntityAIBase) o);
			iter.remove();
		}
		if (bus != null)
			bus.clearListeners();
		phase = 100;
		setHealth(0);
		isDead = true;
		active = false;
	}

	/**
	 * Checks whether target entity is alive.
	 */
	@Override
	@Deprecated
	public boolean isEntityAlive() {
		return maxPhases() >= phase;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected void despawnEntity() {

	}

	@Override
	protected boolean canDespawn() {
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
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(999.0D);
	}

	@Deprecated
	protected abstract void initPhase(int phase);

	@Deprecated
	protected abstract void updatePhase(int phase);

	@Deprecated
	protected abstract List<Class> getFilters();

	protected abstract boolean immuneToFire();

	protected abstract float sizeWidth();

	protected abstract float sizeHeight();

	protected abstract int maxPhases();

	protected abstract void triggerOnDamage(int phase, DamageSource source, float amount);

	@Override
	public abstract ITextComponent getDisplayName();

	@Override
	public ITextComponent getNameForBossBar() {
		return getDisplayName();
	}

}
