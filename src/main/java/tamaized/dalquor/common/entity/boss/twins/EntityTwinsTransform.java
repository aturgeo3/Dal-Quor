package tamaized.dalquor.common.entity.boss.twins;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityTwinsTransform extends Entity {

	private static final DataParameter<Integer> PHASE = EntityDataManager.createKey(EntityTwinsTransform.class, DataSerializers.VARINT);

	public EntityTwinsTransform(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void entityInit() {
		dataManager.register(PHASE, 0);
	}

	public int getPhase() {
		return dataManager.get(PHASE);
	}

	public void incrementPhase() {
		if(getPhase() < 3)
			dataManager.set(PHASE, getPhase() + 1);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		dataManager.set(PHASE, compound.getInteger("phase"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		compound.setInteger("phase", getPhase());
	}
}
