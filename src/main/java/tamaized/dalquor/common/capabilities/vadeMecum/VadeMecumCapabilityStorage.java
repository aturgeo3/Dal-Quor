package tamaized.dalquor.common.capabilities.vadeMecum;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import tamaized.dalquor.DalQuor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class VadeMecumCapabilityStorage implements IStorage<IVadeMecumCapability> {

	public VadeMecumCapabilityStorage() {
		DalQuor.instance.logger.info("VadeMecumCapabilityStorage Registered");
	}

	@Override
	public NBTBase writeNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		{
			NBTTagCompound comp = new NBTTagCompound();
			for (Entry<IVadeMecumCapability.Category, ItemStack> entry : instance.getComponents().entrySet()) {
				NBTTagCompound c = new NBTTagCompound();
				if (entry.getValue().isEmpty())
					c.setBoolean("notnull", false);
				else
					c.setBoolean("notnull", true);
				if(!entry.getValue().isEmpty())
				comp.setTag(String.valueOf(IVadeMecumCapability.getCategoryID(entry.getKey())), entry.getValue().writeToNBT(c));
			}
			compound.setTag("SpellComponents", comp);

		}
		{
			List<Integer> array = new ArrayList<>();
			for (IVadeMecumCapability.Category category : instance.getObtainedCategories()) {
				array.add(IVadeMecumCapability.getCategoryID(category));
			}
			compound.setIntArray("category", array.stream().mapToInt(i -> i).toArray());
		}
		{
			List<Integer> array = new ArrayList<>();
			for (IVadeMecumCapability.Passive passive : instance.getActivePassiveList()) {
				array.add(IVadeMecumCapability.getPassiveID(passive));
			}
			compound.setIntArray("passive", array.stream().mapToInt(i -> i).toArray());
		}
		compound.setInteger("currentActive", IVadeMecumCapability.getCategoryID(instance.getCurrentActive()));
		compound.setString("lastEntry", instance.getLastEntry());
		compound.setInteger("page", instance.getPage());
		compound.setBoolean("active", instance.isBookActive());
		return compound;
	}

	@Override
	public void readNBT(Capability<IVadeMecumCapability> capability, IVadeMecumCapability instance, EnumFacing side, NBTBase nbt) {
		NBTTagCompound compound = (NBTTagCompound) nbt;
		{
			NBTTagCompound spellComponents = compound.getCompoundTag("SpellComponents");
			instance.clearComponents();
			for (String key : spellComponents.getKeySet()) {
				NBTTagCompound value = spellComponents.getCompoundTag(key);
				instance.setStackSlot(IVadeMecumCapability.getCategoryFromID(Integer.valueOf(key)), value.getBoolean("notnull") ? new ItemStack(value) : ItemStack.EMPTY);
			}
		}
		{
			int[] array;
			List<IVadeMecumCapability.Category> list = new ArrayList<>();
			array = compound.getIntArray("category");
			for (int anArray : array) {
				list.add(IVadeMecumCapability.getCategoryFromID(anArray));
			}
			instance.setObtainedCategories(list);
		}
		{
			for (int i : compound.getIntArray("passive")) {
				instance.addPassive(IVadeMecumCapability.getPassiveFromID(i));
			}
		}
		instance.setCurrentActive(IVadeMecumCapability.getCategoryFromID(compound.getInteger("currentActive")));
		instance.setLastEntry(compound.getString("lastEntry"));
		instance.setPage(compound.getInteger("page"));
		instance.setBookActive(compound.getBoolean("active"));
		instance.setLoaded();
	}

}
