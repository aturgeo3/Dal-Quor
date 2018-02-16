package tamaized.dalquor.common.entity.boss.xia.finalphase;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import tamaized.dalquor.registry.VoidCraftPotions;

public class EntityZolXia extends EntityTwinsXia {

	private final BossInfoServer bossInfo = new BossInfoServer(getDisplayName(), Color.WHITE, BossInfo.Overlay.PROGRESS);

	public EntityZolXia(World worldIn) {
		super(worldIn);
		ignoreFrustumCheck = true;
	}

	@Override
	public void setCustomNameTag(String name) {
		super.setCustomNameTag(name);
		this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public void addTrackingPlayer(EntityPlayerMP player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(EntityPlayerMP player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		if (this.hasCustomName())
			this.bossInfo.setName(this.getDisplayName());
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote)
			bossInfo.setPercent(getHealth() / getMaxHealth());
		super.onLivingUpdate();
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		tasks.addTask(1, new EntityZolXia.AICastLitBolt(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000000298023224D);
	}

	@Override
	protected void update() {
		if (!world.isRemote) {
			if (getActivePotionEffect(VoidCraftPotions.litSheathe) == null) {
				clearActivePotions();
				addPotionEffect(new PotionEffect(VoidCraftPotions.litSheathe, 100));
			}
		}
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentTranslation("entity.voidcraft.ZolXia.name");
	}

	@Override
	protected Color getBossBarColor() {
		return Color.WHITE;
	}

	static class AICastLitBolt extends EntityAIBase {

		private final EntityZolXia boss;
		private int tick = 0;

		AICastLitBolt(EntityZolXia entity) {
			boss = entity;
			setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			return tick > 0 && boss.getAttackTarget() != null && !boss.isFrozen();
		}

		@Override
		public void startExecuting() {
			tick = 20 + boss.getRNG().nextInt(60);
		}

		@Override
		public void updateTask() {
			if (boss.getAttackTarget() != null && tick-- <= 0) {
				double x = boss.getAttackTarget().posX;
				double y = boss.getAttackTarget().posY;
				double z = boss.getAttackTarget().posZ;
				EntityLightningBolt entitylightningbolt = new EntityLightningBolt(boss.world, x, y, z, false);
				boss.world.addWeatherEffect(entitylightningbolt);
			}
		}
	}

}
