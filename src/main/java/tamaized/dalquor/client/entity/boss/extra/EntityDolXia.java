package tamaized.dalquor.client.entity.boss.extra;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityTwinsXia;
import tamaized.dalquor.common.entity.nonliving.EntityBlockSpell;
import tamaized.dalquor.common.entity.nonliving.ProjectileDisintegration;
import tamaized.dalquor.registry.VoidCraftPotions;

import java.util.ArrayList;
import java.util.List;

public class EntityDolXia extends EntityTwinsXia {

	private final BossInfoServer bossInfo = new BossInfoServer(getDisplayName(), Color.GREEN, BossInfo.Overlay.PROGRESS);

	private static final List<IBlockState> BLOCKSTATES = Lists.newArrayList(

			Blocks.STONE.getDefaultState(),

			Blocks.MOSSY_COBBLESTONE.getDefaultState(),

			Blocks.COBBLESTONE.getDefaultState(),

			Blocks.DIRT.getDefaultState(),

			Blocks.GRASS.getDefaultState()

	);
	private static final Vec3d vector = new Vec3d(0, 0.5D, 0);
	private List<EntityBlockSpell> blocks = new ArrayList<>();
	private int blockCheckTick = 20 * 3;
	private int ticks = 0;

	public EntityDolXia(World worldIn) {
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
	protected void collideWithEntity(Entity entityIn) {

	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		tasks.addTask(1, new AICastDisint(this));
	}

	@Override
	protected void update() {
		if (getActivePotionEffect(VoidCraftPotions.acidSheathe) == null) {
			clearActivePotions();
			addPotionEffect(new PotionEffect(VoidCraftPotions.acidSheathe, 100));
		}
		if (blockCheckTick-- <= 0) {
			checkAndSpawnBlocks();
			blockCheckTick = 140 + getRNG().nextInt(60);
		}
		if (world.getTotalWorldTime() % 2 == 0)
			updateBlockMotion();
	}

	private void updateBlockMotion() {
		ticks++;
		double f = ticks;
		for (EntityBlockSpell block : blocks) {
			f += 2;
			switch (block.getMode()) {
				default:
				case IDLE:
					block.setMoveTo(new Vec3d(posX + 2.0F * Math.sin(f), posY + (Math.cos((double) ticks / 4D) + 0.75F), posZ + 2.0F * Math.cos(f)));
					if (getRNG().nextInt(100) == 0)
						block.setMode(EntityBlockSpell.Mode.ATTACKING);
					break;
				case ATTACKING:
					if (getAttackTarget() != null)
						block.setMoveTo(getAttackTarget().getPositionVector().addVector(getAttackTarget().motionX  + 2.0F * Math.sin(f), getAttackTarget().motionY + (Math.cos((double) ticks / 4D) + 0.75F), getAttackTarget().motionZ + 2.0F * Math.cos(f)));
					else
						block.setMode(EntityBlockSpell.Mode.IDLE);
					break;
			}
		}
	}

	private void checkAndSpawnBlocks() {
		blocks.removeIf(block -> block.isDead);
		if (blocks.size() < 3) {
			EntityBlockSpell block = new EntityBlockSpell(world, this);
			block.setState(BLOCKSTATES.get(getRNG().nextInt(BLOCKSTATES.size())));
			block.setPositionAndUpdate(posX + getRNG().nextFloat() * 2F - 1F, posY + 0.5F, posZ + getRNG().nextFloat() * 2F - 1F);
			world.spawnEntity(block);
			blocks.add(block);
		}
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentTranslation("entity.voidcraft.DolXia.name");
	}

	@Override
	protected Color getBossBarColor() {
		return Color.GREEN;
	}

	static class AICastDisint extends EntityAIBase {

		private final EntityDolXia boss;
		private int tick = 0;

		AICastDisint(EntityDolXia entity) {
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
				ProjectileDisintegration disint = new ProjectileDisintegration(boss.world, boss, boss.getAttackTarget(), 6.0F);
				boss.world.spawnEntity(disint);
			}
		}
	}

}
