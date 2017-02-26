package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.network.ItemStackNetworkHelper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VadeMecumCapabilityHandler implements IVadeMecumCapability {

	private boolean markDirty = false;

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VadeMecumCapabilityHandler");
	private boolean hasLoaded = false;

	private ArrayList<IVadeMecumCapability.Category> categoryList = new ArrayList<IVadeMecumCapability.Category>();

	private Category currActivePower;
	private String lastEntry = "null";

	private Map<Category, ItemStack> spellComponents = new HashMap<Category, ItemStack>() {

		@Override
		public ItemStack get(Object key) {
			ItemStack stack = super.get(key);
			return stack == null ? ItemStack.EMPTY : stack;
		};

	};

	public void markDirty() {
		markDirty = true;
	}

	@Override
	public boolean isDirty() {
		return markDirty;
	}

	@Override
	public void resetDirty() {
		markDirty = false;
	}

	@Override
	public boolean hasLoaded() {
		return hasLoaded;
	}

	@Override
	public void setLoaded() {
		hasLoaded = true;
	}

	@Override
	public ArrayList<Category> getObtainedCategories() {
		return categoryList;
	}

	@Override
	public void setObtainedCategories(ArrayList<Category> list) {
		categoryList.clear();
		categoryList.addAll(list);
		markDirty();
	}

	@Override
	public void addCategory(Category category) {
		if (!categoryList.contains(category)) categoryList.add(category);
		markDirty();
	}

	@Override
	public void removeCategory(Category category) {
		categoryList.remove(category);
		markDirty();
	}

	@Override
	public void clearCategories() {
		categoryList.clear();
		markDirty();
	}

	@Override
	public boolean hasCategory(Category category) {
		return categoryList.contains(category);
	}

	@Override
	public ArrayList<Category> getAvailableActivePowers() {
		ArrayList<Category> activeList = new ArrayList<Category>();
		for (Category cat : categoryList)
			if (IVadeMecumCapability.isActivePower(cat)) activeList.add(cat);
		return activeList;
	}

	@Override
	public void setCurrentActive(Category power) {
		if (IVadeMecumCapability.isActivePower(power)) currActivePower = power;
		markDirty();
	}

	@Override
	public void clearActivePower() {
		currActivePower = null;
	}

	@Override
	public Category getCurrentActive() {
		return currActivePower;
	}
	
	@Override
	public int getFailureChance() {
		return 75;
	}

	@Override
	public void setLastEntry(String e) {
		lastEntry = (e == null || e.isEmpty()) ? "null" : e;
		markDirty();
	}

	@Override
	public String getLastEntry() {
		return lastEntry;
	}

	@Override
	public Map<Category, ItemStack> getComponents() {
		return spellComponents;
	}

	@Override
	public void clearComponents() {
		spellComponents.clear();
		markDirty();
	}

	@Override
	public ItemStack getStackInSlot(Category slot) {
		return spellComponents.get(slot);
	}

	@Override
	public ItemStack addStackToSlot(Category slot, ItemStack stack) {
		ItemStack slotStack = spellComponents.get(slot);
		if (slotStack == ItemStack.EMPTY) {
			setStackSlot(slot, stack);
			markDirty();
			return ItemStack.EMPTY;
		} else if (ItemStack.areItemsEqual(slotStack, stack)) {
			int room = slotStack.getMaxStackSize() - slotStack.getCount();
			int amount = Math.min(room, stack.getCount());
			slotStack.grow(amount);
			setStackSlot(slot, slotStack);
			stack.shrink(amount);
			markDirty();
			return stack.getCount() <= 0 ? ItemStack.EMPTY : stack;
		}
		markDirty();
		return stack;
	}

	@Override
	public void setStackSlot(Category slot, ItemStack stack) {
		spellComponents.put(slot, stack);
		markDirty();
	}

	@Override
	public ItemStack decrStackSize(Category slot, int amount) {
		if (!getStackInSlot(slot).isEmpty()) {
			ItemStack itemstack;
			if (getStackInSlot(slot).getCount() <= amount) {
				itemstack = getStackInSlot(slot);
				setStackSlot(slot, ItemStack.EMPTY);
				markDirty();
				return itemstack;
			} else {
				itemstack = getStackInSlot(slot).splitStack(amount);
				if (getStackInSlot(slot).getCount() == 0) {
					setStackSlot(slot, ItemStack.EMPTY);
				}
				markDirty();
				return itemstack;
			}
		}
		markDirty();
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeStackFromSlot(Category slot) {
		if (!getStackInSlot(slot).isEmpty()) {
			ItemStack itemstack = getStackInSlot(slot);
			setStackSlot(slot, ItemStack.EMPTY);
			markDirty();
			return itemstack;
		}
		markDirty();
		return ItemStack.EMPTY;
	}

	@Override
	public void copyFrom(IVadeMecumCapability cap) {
		if (cap == null) return;
		clearComponents();
		spellComponents.putAll(cap.getComponents());
		setObtainedCategories(cap.getObtainedCategories());
		setCurrentActive(cap.getCurrentActive());
		setCurrentActive(cap.getCurrentActive());
		setLastEntry(cap.getLastEntry());
		setLoaded();
		markDirty();
	}

	@Override
	public void decodePacket(ByteBuf buf, ByteBufInputStream stream) throws IOException {
		setCurrentActive(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
		setLastEntry(stream.readUTF());
		// Do Arrays last
		clearCategories();
		int l = stream.readInt();
		for (int i = 0; i < l; i++) {
			addCategory(IVadeMecumCapability.getCategoryFromID(stream.readInt()));
		}
		clearComponents();
		l = stream.readInt();
		for (int i = 0; i < l; i++) {
			spellComponents.put(IVadeMecumCapability.getCategoryFromID(stream.readInt()), ItemStackNetworkHelper.decodeStack(buf, stream));
		}
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeInt(IVadeMecumCapability.getCategoryID(getCurrentActive()));
		stream.writeUTF(getLastEntry());
		// Do Arrays last
		stream.writeInt(getObtainedCategories().size());
		for (Category cat : getObtainedCategories()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(cat));
		}
		stream.writeInt(spellComponents.size());
		for (Entry<Category, ItemStack> entry : spellComponents.entrySet()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(entry.getKey()));
			ItemStackNetworkHelper.encodeStack(entry.getValue(), stream);
		}
	}

}
