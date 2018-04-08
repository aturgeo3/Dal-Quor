package tamaized.dalquor.common.capabilities.vadeMecumItem;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import tamaized.dalquor.DalQuor;

public class VadeMecumItemCapabilityStorage implements IStorage<IVadeMecumItemCapability> {

	public VadeMecumItemCapabilityStorage() {
		DalQuor.instance.logger.info("VadeMecumItemCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVadeMecumItemCapability> capability, IVadeMecumItemCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("bookstate", instance.getBookState());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumItemCapability> capability, IVadeMecumItemCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setBookState(compound.getBoolean("bookstate"));
		instance.setLoaded();
	}

}
