package tamaized.dalquor.common.entity.nonliving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.entity.boss.EntityBossCorruptedPawn;

public class EntityChainedSkull extends Entity {

	private int tick = 0;


	public EntityChainedSkull(World worldIn) {
		super(worldIn);
		ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

	@Override
	public void onUpdate() {
		if (tick++ >= maxTick()) {
			if (!world.isRemote) {
				world.newExplosion(this, posX, posY + (double) getEyeHeight(), posZ, 7.0F, false, world.getGameRules().getBoolean("mobGriefing"));
				EntityBossCorruptedPawn boss = new EntityBossCorruptedPawn(world);
				boss.setPositionAndUpdate(posX, posY, posZ);
				world.spawnEntity(boss);
				world.playBroadcastSound(1023, new BlockPos(this), 0);
				for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(50, 16, 50)))
					player.sendMessage(new TextComponentTranslation("voidcraft.misc.pawn.summon"));
				setDead();
			}
			return;
		}
		if (!world.isRemote)
			move(MoverType.SELF, 0, 0.025F, 0);
	}

	public int maxTick() {
		return 20 * 30;
	}

	public int getTicks() {
		return tick;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean canRenderOnFire() {
		return false;
	}

	@Override
	public boolean ignoreItemEntityData() {
		return true;
	}
}
