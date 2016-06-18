package Tamaized.Voidcraft.capabilities;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VoidicInfusionCapabilityStorage implements IStorage<IVoidicInfusionCapability> {
	
	public VoidicInfusionCapabilityStorage(){
		System.out.println("Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side) {
		System.out.println("writeNBT");
		NBTTagCompound compound = new NBTTagCompound();
		compound.setFloat("preMaxHealth", instance.preMaxHealth());
		compound.setBoolean("isInfused10", instance.isInfused10());
		compound.setBoolean("isInfused90", instance.isInfused90());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVoidicInfusionCapability> capability, IVoidicInfusionCapability instance, EnumFacing side, NBTBase nbt) {
		System.out.println("readNBT");
		NBTTagCompound compound = (NBTTagCompound) nbt;
		instance.setPreMaxHealth(compound.getFloat("preMaxHealth"));
		instance.setInfused10(compound.getBoolean("isInfused10"));
		instance.setInfused90(compound.getBoolean("isInfused90"));
		instance.setLoaded();
	}

}
