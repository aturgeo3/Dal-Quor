package tamaized.voidcraft.common.entity.boss;

import com.google.common.base.Predicate;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.tammodized.common.entity.EntityDragonOld;
import tamaized.voidcraft.common.entity.EntityVoidNPC;
import tamaized.voidcraft.common.entity.ai.EntityAIFindEntityNearestEntityNoSight;
import tamaized.voidcraft.common.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import tamaized.voidcraft.common.entity.boss.xia.finalphase.EntityWitherbrine;
import tamaized.voidcraft.common.sound.VoidSoundEvents;
import tamaized.voidcraft.registry.VoidCraftItems;

public class EntityBossCorruptedPawn extends EntityVoidNPC implements IVoidBossData, IMob {

	private static final Predicate<Entity> NOT_UNDEAD = p_apply_1_ -> !(p_apply_1_ instanceof EntityBossCorruptedPawn) && !(p_apply_1_ instanceof EntityDragonOld) && !(p_apply_1_ instanceof EntityVoidNPC) && !(p_apply_1_ instanceof EntityVoidNPC) && p_apply_1_ instanceof EntityLivingBase && ((EntityLivingBase) p_apply_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;

	private static final DataParameter<Boolean> ATTACK_SKULLS = EntityDataManager.createKey(EntityBossCorruptedPawn.class, DataSerializers.BOOLEAN);

	public EntityBossCorruptedPawn(World world) {
		super(world);
		setHealth(getMaxHealth());
		setSize(1.5F, 2.0F);
		isImmuneToFire = true;
		experienceValue = 50;
		enablePersistence();
		moveHelper = new BossFlyNoclipMoveHelper(this);
	}

	@Override
	protected void encodePacketData(ByteBuf stream) {

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {

	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new AIShootSkulls(this));
		tasks.addTask(1, new EntityVoidNPC.AILookAround(this));
		tasks.addTask(2, new EntityVoidNPC.AIChaseTarget(this, true));

		targetTasks.addTask(1, new EntityAIFindEntityNearestEntityNoSight(this, EntityLivingBase.class));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ATTACK_SKULLS, false);
	}

	public final boolean getSkullState() {
		return dataManager.get(ATTACK_SKULLS);
	}

	public final void setSkullState(boolean b) {
		dataManager.set(ATTACK_SKULLS, b);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(300.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(15.0D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (!world.isRemote)
			destroyBlocks();
		if (getAttackTarget() == this)
			setAttackTarget(null);
	}

	private void destroyBlocks() {
		AxisAlignedBB box = getEntityBoundingBox();
		for (BlockPos pos : BlockPos.getAllInBox(new BlockPos(box.minX, box.minY, box.minZ), new BlockPos(box.maxX, box.maxY, box.maxZ))) {
			IBlockState state = world.getBlockState(pos);
			if (!state.getBlock().isAir(state, world, pos) && state.getBlock() != Blocks.OBSIDIAN && state.getBlock() != Blocks.END_STONE && state.getBlock() != Blocks.BEDROCK) {
				world.destroyBlock(pos, false);
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (isEntityInvulnerable(source)) {
			return false;
		} else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof EntityWitherbrine)) {
			if (isArmored())
				if (source.getImmediateSource() instanceof EntityArrow)
					return false;
			Entity entity1 = source.getTrueSource();
			return !(entity1 != null && !(entity1 instanceof EntityPlayer) && entity1 instanceof EntityLivingBase && ((EntityLivingBase) entity1).getCreatureAttribute() == getCreatureAttribute()) && super.attackEntityFrom(source, amount);
		} else {
			return false;
		}
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	public boolean isArmored() {
		return getHealth() <= getMaxHealth() / 2.0F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.ambientSound;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.hurtSound;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return VoidSoundEvents.EntityMobVoidBossSoundEvents.deathSound;
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return VoidCraftItems.voidStar;
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		dropItem(getDropItem(), 1);
	}

	@Override
	public float getPercentHPForBossBar() {
		return getHealth() / getMaxHealth();
	}

	@Override
	public ITextComponent getNameForBossBar() {
		return new TextComponentTranslation("entity.VoidBoss.name");
	}

	@Override
	public float getMaxHealthForBossBar() {
		return getMaxHealth();
	}

	@Override
	public float getHealthForBossBar() {
		return getHealth();
	}

	static class AIShootSkulls extends EntityAIBase {

		private final EntityBossCorruptedPawn boss;
		private int ticksLeft = 0;

		public AIShootSkulls(EntityBossCorruptedPawn entity) {
			boss = entity;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			return boss.getAttackTarget() != null && boss.getRNG().nextInt(50) == 0;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return ticksLeft > 0 && boss.getAttackTarget() != null;
		}

		@Override
		public void startExecuting() {
			ticksLeft = boss.getRNG().nextInt(200) + 100;
			boss.setSkullState(true);
			boss.moveHelper.setMoveTo(boss.posX, boss.posY, boss.posZ, 0);
			boss.moveHelper.action = EntityMoveHelper.Action.WAIT;
			boss.motionX *= 0.1F;
			boss.motionY *= 0.1F;
			boss.motionZ *= 0.1F;
		}

		@Override
		public void resetTask() {
			boss.setSkullState(false);
		}

		@Override
		public void updateTask() {
			Entity target = boss.getAttackTarget();
			if (target != null) {
				boss.getLookHelper().setLookPositionWithEntity(target, 30, 30);
				if (ticksLeft-- % 5 == 0) {
					EntityHerobrineWitherSkull skull = new EntityHerobrineWitherSkull(boss.world, boss, (target.posX + (1.5F - boss.getRNG().nextFloat() * 3F)) - boss.posX, (target.posY-1) - boss.posY, (target.posZ + (1.5F - boss.getRNG().nextFloat() * 3F)) - boss.posZ);
					skull.setPositionAndUpdate(boss.posX, boss.posY + 1, boss.posZ);
					boss.world.spawnEntity(skull);
				}

			}
		}
	}

}