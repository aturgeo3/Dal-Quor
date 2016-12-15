package Tamaized.Voidcraft.capabilities.starforge;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class StarForgeCapabilityStorage implements IStorage<IStarForgeCapability> {

	public StarForgeCapabilityStorage() {
		voidCraft.logger.info("StarForgeCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IStarForgeCapability> capability, IStarForgeCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		return compound;
	}

	@Override
	public void readNBT(Capability<IStarForgeCapability> capability, IStarForgeCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
	}
	
}
