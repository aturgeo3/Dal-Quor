package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability.Category;
import io.netty.buffer.ByteBufInputStream;

public interface IVadeMecumCapability {

	public static enum Category {
		NULL, INTRO, TOME,

		// Active
		Flame, FireSheathe, Fireball, FireTrap, ExplosionFire, RingOfFire,

		Shock, ShockSheathe, LitStrike, LitTrap, ExplosionLit, RingOfLit,

		Freeze, FrostSheathe, IceSpike, FrostTrap, ExplosionFrost, RingOfFrost,

		AcidSpray, AcidSheathe, Disint, AcidTrap, ExplosionAcid, RingOfAcid,

		VoidicTouch, VoidicSheathe, Implosion

	}

	public static int getCategoryID(Category c) {
		return c == null ? -1 : c.ordinal();
	}

	public static Category getCategoryFromID(int id) {
		return (id > Category.values().length || id < 0) ? null : Category.values()[id];
	}

	public static String getCategoryName(Category c) {
		if (c == null) return "null";
		switch (c) {
			case INTRO:
				return "Rituals";
			case TOME:
				return "Words of Power";
			case Flame:
				return "Word: Flame";
			case FireSheathe:
				return "Word: Sheathe - Fire";
			case Fireball:
				return "Word: Fireball";
			case FireTrap:
				return "Word: Trap - Fire";
			case ExplosionFire:
				return "Word: Explosion - Fire";
			case RingOfFire:
				return "Word: Ring of Fire";
			case Shock:
				return "Word: Shock";
			case ShockSheathe:
				return "Word: Sheathe - Shock";
			case LitStrike:
				return "Word: Lightning Strike";
			case LitTrap:
				return "Word: Trap - Lightning";
			case ExplosionLit:
				return "Word: Explosion - Shock";
			case RingOfLit:
				return "Word: Ring of Lightning";
			case Freeze:
				return "Word: Freeze";
			case FrostSheathe:
				return "Word: Sheathe - Frost";
			case IceSpike:
				return "Word: Ice Spike";
			case FrostTrap:
				return "Word: Trap - Frost";
			case ExplosionFrost:
				return "Word: Explosion - Frost";
			case RingOfFrost:
				return "Word: Ring of Frost";
			case AcidSpray:
				return "Word: Acid Spray";
			case AcidSheathe:
				return "Word: Sheathe - Acid";
			case Disint:
				return "Word: Disintegrate";
			case AcidTrap:
				return "Word: Trap - Acid";
			case ExplosionAcid:
				return "Word: Explosion - Acid";
			case RingOfAcid:
				return "Word: Ring of Acid";
			case VoidicTouch:
				return "Word: Voidic Touch";
			case VoidicSheathe:
				return "Word: Sheathe - Voidic";
			case Implosion:
				return "Word: Implosion";
			default:
				return "null";
		}
	}

	public static boolean isActivePower(Category c) {
		return getCategoryName(c).contains("Word:");
	}

	public static boolean isPassivePower(Category c) {
		return false;
	}

	public boolean isDirty();

	public void resetDirty();

	public ArrayList<Category> getObtainedCategories();

	public void setObtainedCategories(ArrayList<Category> list);

	public void addCategory(Category category);

	public void removeCategory(Category category);

	public void clearCategories();

	public boolean hasCategory(Category category);

	public ArrayList<Category> getAvailableActivePowers();

	public void setCurrentActive(Category power);

	public void clearActivePower();

	public Category getCurrentActive();

	public void setLastEntry(String e);

	public String getLastEntry();

	public boolean hasLoaded();

	public void setLoaded();

	public void copyFrom(IVadeMecumCapability cap);

	public void decodePacket(ByteBufInputStream stream) throws IOException;

	public void encodePacket(DataOutputStream stream) throws IOException;

}
