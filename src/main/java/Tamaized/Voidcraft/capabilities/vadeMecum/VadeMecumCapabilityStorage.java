package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.util.ArrayList;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class VadeMecumCapabilityStorage implements IStorage<IVadeMecumCapability> {

	public VadeMecumCapabilityStorage() {
		voidCraft.logger.info("VadeMecumCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		ArrayList<Integer> array = new ArrayList<Integer>();
		{
			for (IVadeMecumCapability.Category category : instance.getObtainedCategories()) {
				array.add(IVadeMecumCapability.getCategoryID(category));
			}
			compound.setIntArray("category", array.stream().mapToInt(i -> i).toArray());
		}
		array.clear();
		{
			for (IVadeMecumCapability.ActivePower activePower : instance.getActivePowers()) {
				array.add(IVadeMecumCapability.getActivePowerID(activePower));
			}
			compound.setIntArray("activePower", array.stream().mapToInt(i -> i).toArray());
		}
		array.clear();
		{
			for (IVadeMecumCapability.PassivePower passivePower : instance.getPassivePowers()) {
				array.add(IVadeMecumCapability.getPassivePowerID(passivePower));
			}
			compound.setIntArray("passivePower", array.stream().mapToInt(i -> i).toArray());
		}
		compound.setInteger("currentActive", IVadeMecumCapability.getActivePowerID(instance.getCurrentActive()));
		compound.setString("lastEntry", instance.getLastEntry());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		int[] array;
		{
			ArrayList<IVadeMecumCapability.Category> list = new ArrayList<IVadeMecumCapability.Category>();
			array = compound.getIntArray("category");
			for (int i = 0; i < array.length - 1; i++) {
				list.add(IVadeMecumCapability.getCategoryFromID(array[i]));
			}
			instance.setObtainedCategories(list);
		}
		{
			ArrayList<IVadeMecumCapability.ActivePower> list = new ArrayList<IVadeMecumCapability.ActivePower>();
			array = compound.getIntArray("activePower");
			for (int i = 0; i < array.length - 1; i++) {
				list.add(IVadeMecumCapability.getActivePowerFromID(array[i]));
			}
			instance.setActivePowers(list);
		}
		{
			ArrayList<IVadeMecumCapability.PassivePower> list = new ArrayList<IVadeMecumCapability.PassivePower>();
			array = compound.getIntArray("passivePower");
			for (int i = 0; i < array.length - 1; i++) {
				list.add(IVadeMecumCapability.getPassivePowerFromID(array[i]));
			}
			instance.setPassivePowers(list);
		}
		instance.setCurrentActive(IVadeMecumCapability.getActivePowerFromID(compound.getInteger("currentActive")));
		instance.setLastEntry(compound.getString("lastEntry"));
		instance.setLoaded();
	}

}
