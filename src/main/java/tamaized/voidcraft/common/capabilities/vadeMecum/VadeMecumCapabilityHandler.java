package tamaized.voidcraft.common.capabilities.vadeMecum;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.entity.companion.EntityCompanion;
import tamaized.voidcraft.registry.VoidCraftAdvancements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VadeMecumCapabilityHandler implements IVadeMecumCapability {

	public static final ResourceLocation ID = new ResourceLocation(VoidCraft.modid, "VadeMecumCapabilityHandler");
	private final List<Passive> passiveList = new ArrayList<>();
	private boolean markDirty = false;
	private boolean hasLoaded = false;
	private List<IVadeMecumCapability.Category> categoryList = new ArrayList<>();
	private Category currActivePower;
	private String lastEntry = "null";
	private int page = 0;
	private EntityCompanion companion;
	private boolean bookActive = false;

	private Map<Category, ItemStack> spellComponents = new HashMap<Category, ItemStack>() {

		@Override
		public ItemStack get(Object key) {
			ItemStack stack = super.get(key);
			return stack == null ? ItemStack.EMPTY : stack;
		}

	};

	public void markDirty() {
		markDirty = true;
	}

	@Override
	public boolean isDirty() {
		return markDirty;
	}

	@Override
	public boolean isBookActive() {
		return bookActive;
	}

	@Override
	public void setBookActive(boolean state) {
		bookActive = state;
		markDirty();
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
	public void summonCompanion(EntityCompanion entity) {
		killCompanion();
		companion = entity;
	}

	@Override
	public void killCompanion() {
		if (companion != null)
			companion.setDead();
		companion = null;
	}

	@Override
	public EntityCompanion getCompanion() {
		return companion;
	}

	@Override
	public List<Category> getObtainedCategories() {
		return categoryList;
	}

	@Override
	public void setObtainedCategories(List<Category> list) {
		categoryList.clear();
		categoryList.addAll(list);
		markDirty();
	}

	@Override
	public void addCategory(EntityLivingBase entity, Category category) {
		if (!categoryList.contains(category)) {
			categoryList.add(category);
			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				switch (category) {
					case Voice:
						VoidCraftAdvancements.voice.trigger(player);
						break;
					case VoidicControl:
						VoidCraftAdvancements.anchor.trigger(player);
						break;
					case ImprovedCasting:
						VoidCraftAdvancements.stabilization.trigger(player);
						break;
					case Empowerment:
						VoidCraftAdvancements.empowerment.trigger(player);
						break;
					case Tolerance:
						VoidCraftAdvancements.tolerance.trigger(player);
						break;
					case TotalControl:
						VoidCraftAdvancements.totalcontrol.trigger(player);
						break;
					case Dreams:
						VoidCraftAdvancements.nightmare.trigger(player);
						break;
					default:
						break;
				}
			}
		}
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
		passiveList.clear();
		currActivePower = null;
		markDirty();
	}

	@Override
	public boolean hasCategory(Category category) {
		return categoryList.contains(category);
	}

	@Override
	public List<Category> getAvailableActivePowers() {
		List<Category> activeList = new ArrayList<>();
		for (Category cat : categoryList)
			if (IVadeMecumCapability.isActivePower(cat))
				activeList.add(cat);
		return activeList;
	}

	@Override
	public void clearActivePower() {
		currActivePower = null;
		markDirty();
	}

	@Override
	public Category getCurrentActive() {
		return currActivePower;
	}

	@Override
	public void setCurrentActive(Category power) {
		if (IVadeMecumCapability.isActivePower(power))
			currActivePower = power;
		markDirty();
	}

	@Override
	public List<Passive> getActivePassiveList() {
		return passiveList;
	}

	@Override
	public void addPassive(Passive ability) {
		if (canHavePassive(ability)) {
			passiveList.add(ability);
			markDirty();
		}
	}

	@Override
	public void removePassive(Passive ability) {
		if (hasPassive(ability)) {
			passiveList.remove(ability);
			markDirty();
		}
	}

	@Override
	public boolean hasPassive(Passive ability) {
		return passiveList.contains(ability);
	}

	@Override
	public String getLastEntry() {
		return lastEntry;
	}

	@Override
	public void setLastEntry(String e) {
		lastEntry = (e == null || e.isEmpty()) ? "null" : e;
		markDirty();
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
	public int getPage() {
		return page;
	}

	@Override
	public void setPage(int page) {
		this.page = page;
		markDirty();
	}

	@Override
	public void copyFrom(IVadeMecumCapability cap) {
		if (cap == null)
			return;
		clearComponents();
		spellComponents.putAll(cap.getComponents());
		setObtainedCategories(cap.getObtainedCategories());
		for (IVadeMecumCapability.Passive passive : cap.getActivePassiveList())
			addPassive(passive);
		setCurrentActive(cap.getCurrentActive());
		setLastEntry(cap.getLastEntry());
		setPage(cap.getPage());
		setLoaded();
		markDirty();
	}

}
