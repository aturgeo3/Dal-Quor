package tamaized.dalquor.common.entity.boss.xia.finalphase;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.client.entity.boss.bossbar.RenderAlternateBossBars.IAlternateBoss;
import tamaized.dalquor.common.entity.EntityVoidNPC;
import tamaized.dalquor.common.entity.ai.EntityAIFindEntityNearestEntityNoSight;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia2;
import tamaized.dalquor.network.client.ClientPacketHandlerSheathe;
import tamaized.dalquor.registry.ModPotions;

public abstract class EntityTwinsXia extends EntityVoidNPC implements IAlternateBoss {

	private static final DataParameter<Boolean> FROZEN = EntityDataManager.createKey(EntityTwinsXia.class, DataSerializers.BOOLEAN);
	private EntityBossXia2 parent;

	public EntityTwinsXia(World worldIn) {
		super(worldIn);
		isImmuneToFire = true;
		canPush = false;
		moveHelper = new BossFlyNoclipMoveHelper(this) {
			@Override
			public void onUpdateMoveHelper() {
				if (isFrozen()) {
					setMoveTo(posX, posY, posZ, 0);
					action = Action.WAIT;
					motionX = 0;
					motionY = 0;
					motionZ = 0;
				} else
					super.onUpdateMoveHelper();
			}
		};
	}

	public final void setParent(EntityBossXia2 boss) {
		parent = boss;
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new AIChaseTarget(this, true) {
			@Override
			public boolean shouldExecute() {
				return !isFrozen() && super.shouldExecute();
			}
		});

		targetTasks.addTask(0, new EntityAIFindEntityNearestEntityNoSight(this, EntityPlayer.class));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(FROZEN, false);
	}

	protected abstract BossInfo.Color getBossBarColor();

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(25.0D);
	}

	@Override
	public float getHealthPerc() {
		return getHealth() / getMaxHealth();
	}

	public final boolean isFrozen() {
		return dataManager.get(FROZEN);
	}

	private void setFrozen() {
		setInvulnerable(true);
		dataManager.set(FROZEN, true);
		setHealth(0);
	}

	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {
		Potion pot = potioneffectIn.getPotion();
		if (pot != ModPotions.fireSheathe && pot != ModPotions.frostSheathe && pot != ModPotions.litSheathe && pot != ModPotions.acidSheathe)
			return;
		super.addPotionEffect(potioneffectIn);
		if (!world.isRemote) {
			DalQuor.network.sendToAllAround(new ClientPacketHandlerSheathe.Packet(getEntityId(), Potion.getIdFromPotion(pot), potioneffectIn.getDuration()), new TargetPoint(world.provider.getDimension(), posX, posY, posZ, 64));
		}
	}

	@Override
	protected void onDeathUpdate() {
		if (!this.world.isRemote) {
			if (parent == null || !parent.displayBossMode()) // Hacky yes, but I don't feel like dealing with this rn, will fix it later
				setDead();
			else if (!isFrozen())
				setFrozen();
		}
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (isFrozen()) {
				setHealth(getHealth() + 0.25F);
				if (getHealth() >= getMaxHealth()) {
					setHealth(getMaxHealth());
					setInvulnerable(false);
					dataManager.set(FROZEN, false);
				}
			} else {
				update();
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		if (!isFrozen() && nbt.getBoolean("isFrozen"))
			setFrozen();

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("isFrozen", isFrozen());
	}

	protected abstract void update();

}
