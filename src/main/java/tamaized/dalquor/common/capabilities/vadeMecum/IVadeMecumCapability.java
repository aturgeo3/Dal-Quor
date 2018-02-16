package tamaized.dalquor.common.capabilities.vadeMecum;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import tamaized.dalquor.common.entity.companion.EntityCompanion;
import tamaized.dalquor.common.vademecum.progression.VadeMecumWordsOfPower;

import java.util.List;
import java.util.Map;

public interface IVadeMecumCapability {

	static int getCategoryID(Category c) {
		return c == null ? -1 : c.ordinal();
	}

	static Category getCategoryFromID(int id) {
		return (id > Category.values().length || id < 0) ? null : Category.values()[id];
	}

	static int getPassiveID(Passive c) {
		return c == null ? -1 : c.ordinal();
	}

	static Passive getPassiveFromID(int id) {
		return (id > Passive.values().length || id < 0) ? null : Passive.values()[id];
	}

	static String getPassiveName(Passive passive) {
		switch (passive) {
			case Anchor:
				return "voidcraft.VadeMecum.passive.Anchor";
			case Empowerment:
				return "voidcraft.VadeMecum.passive.Empowerment";
			case Tolerance:
				return "voidcraft.VadeMecum.passive.Tolerance";
			case Flight:
				return "voidcraft.VadeMecum.passive.Flight";
			case Vigor:
				return "voidcraft.VadeMecum.passive.Vigor";
			default:
				return "null";
		}
	}

	static boolean isActivePower(Category c) {
		return VadeMecumWordsOfPower.getCategoryData(c).getElement() != CategoryDataWrapper.Element.NULL;
	}

	default boolean canHavePassive(Passive passive) {
		switch (passive) {
			case Anchor:
				return hasCategory(Category.VoidicControl);
			case Empowerment:
				return hasCategory(Category.Empowerment);
			case Tolerance:
				return hasCategory(Category.Tolerance);
			case Flight:
				return hasCategory(Category.TotalControl);
			case Vigor:
				return hasCategory(Category.Dreams);
			default:
				return false;
		}
	}

	boolean isDirty();

	boolean isBookActive();

	void setBookActive(boolean state);

	void resetDirty();

	void summonCompanion(EntityCompanion entity);

	void killCompanion();

	EntityCompanion getCompanion();

	List<Category> getObtainedCategories();

	void setObtainedCategories(List<Category> list);

	void addCategory(EntityLivingBase entity, Category category);

	void removeCategory(Category category);

	void clearCategories();

	boolean hasCategory(Category category);

	List<Category> getAvailableActivePowers();

	void clearActivePower();

	Category getCurrentActive();

	void setCurrentActive(Category power);

	List<Passive> getActivePassiveList();

	void addPassive(Passive ability);

	void removePassive(Passive ability);

	boolean hasPassive(Passive ability);

	/**
	 * Return a value from 0 to 100
	 */
	default int getFailureChance() {
		int chance = 75;
		if (hasCategory(Category.Voice))
			chance -= 25;
		if (hasCategory(Category.ImprovedCasting))
			chance -= 25;
		if (hasCategory(Category.TotalControl))
			chance -= 25;
		return chance;
	}

	String getLastEntry();

	void setLastEntry(String e);

	Map<Category, ItemStack> getComponents();

	void clearComponents();

	ItemStack getStackInSlot(Category slot);

	ItemStack addStackToSlot(Category slot, ItemStack stack);

	void setStackSlot(Category slot, ItemStack stack);

	ItemStack decrStackSize(Category slot, int amount);

	ItemStack removeStackFromSlot(Category slot);

	int getPage();

	void setPage(int page);

	boolean hasLoaded();

	void setLoaded();

	void copyFrom(IVadeMecumCapability cap);

	enum Category {
		NULL, INTRO, TOME,

		// Active
		Flame, FireSheathe, Fireball, FireTrap, ExplosionFire, RingOfFire,

		Shock, ShockSheathe, LitStrike, LitTrap, ExplosionLit, RingOfLit,

		Freeze, FrostSheathe, IceSpike, FrostTrap, ExplosionFrost, RingOfFrost,

		AcidSpray, AcidSheathe, Disint, AcidTrap, ExplosionAcid, RingOfAcid,

		VoidicTouch, VoidicSheathe, Implosion,

		Invoke, SummonFireElemental,

		// Tasks
		Voice, VoidicControl, ImprovedCasting, Empowerment, Tolerance, TotalControl, Dreams
	}

	enum Passive {
		Anchor, Empowerment, Tolerance, Flight, Vigor
	}

	class CategoryDataWrapper {

		private final Element element;
		private final String name;
		private final ItemStack stack;

		public CategoryDataWrapper(Element type, String name, ItemStack stack) {
			element = type;
			this.name = name;
			this.stack = stack;
		}

		public Element getElement() {
			return element;
		}

		public String getName() {
			return name;
		}

		public ItemStack getStack() {
			return stack;
		}

		public enum Element {
			NULL, FIRE, WATER, EARTH, AIR, VOID
		}
	}

}
