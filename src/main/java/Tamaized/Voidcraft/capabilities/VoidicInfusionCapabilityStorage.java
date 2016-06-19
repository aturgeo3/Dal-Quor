package Tamaized.Voidcraft.capabilities;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VoidicInfusionCapabilityStorage implements IStorage<IVoidicInfusionCapability> {
	
	public VoidicInfusionCapabilityStorage(){
		voidCraft.logger.info("VoidicInfusionCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setFloat("preMaxHealth", instance.preMaxHealth());
		compound.setFloat("checkMaxHealth", instance.checkMaxHealth());
		compound.setInteger("infusion", instance.getInfusion());
		compound.setInteger("maxInfusion", instance.getMaxInfusion());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setPreMaxHealth(compound.getFloat("preMaxHealth"));
		instance.setCheckMaxHealth(compound.getFloat("checkMaxHealth"));
		instance.setInfusion(compound.getInteger("infusion"));
		instance.setMaxInfusion(compound.getInteger("maxInfusion"));
		instance.setLoaded();
	}

}
