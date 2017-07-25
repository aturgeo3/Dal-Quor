package tamaized.voidcraft.common.entity.nonliving;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityChainedSkull extends Entity {

	private int tick = 1;

	public EntityChainedSkull(World worldIn) {
		super(worldIn);
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
		if (!world.isRemote) {
			if (tick >= 180) {
				setDead();
				return;
			}
			move(MoverType.SELF, 0, 0.05F, 0);
		}
		tick++;
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
