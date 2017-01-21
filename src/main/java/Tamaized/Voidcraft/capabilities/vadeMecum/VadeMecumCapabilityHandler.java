package Tamaized.Voidcraft.capabilities.vadeMecum;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Tamaized.Voidcraft.voidCraft;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.util.ResourceLocation;

public class VadeMecumCapabilityHandler implements IVadeMecumCapability {

	private boolean markDirty = false;

	public static final ResourceLocation ID = new ResourceLocation(voidCraft.modid, "VadeMecumCapabilityHandler");
	private boolean hasLoaded = false;

	private ArrayList<IVadeMecumCapability.Category> categoryList = new ArrayList<IVadeMecumCapability.Category>();
	private ArrayList<IVadeMecumCapability.ActivePower> activeList = new ArrayList<IVadeMecumCapability.ActivePower>();
	private ArrayList<IVadeMecumCapability.PassivePower> passiveList = new ArrayList<IVadeMecumCapability.PassivePower>();

	private ActivePower currActivePower;
	private String lastEntry = "null";

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
	public ArrayList<ActivePower> getActivePowers() {
		return activeList;
	}

	@Override
	public void setActivePowers(ArrayList<ActivePower> list) {
		activeList.clear();
		activeList.addAll(list);
		markDirty();
	}

	@Override
	public void addActivePower(ActivePower power) {
		if (!activeList.contains(power)) activeList.add(power);
		markDirty();
	}

	@Override
	public void removeActivePower(ActivePower power) {
		activeList.remove(power);
		markDirty();
	}

	@Override
	public void clearActivePowers() {
		activeList.clear();
		markDirty();
	}

	@Override
	public boolean hasActivePower(ActivePower power) {
		return activeList.contains(power);
	}

	@Override
	public ArrayList<PassivePower> getPassivePowers() {
		return passiveList;
	}

	@Override
	public void setPassivePowers(ArrayList<PassivePower> list) {
		passiveList.clear();
		passiveList.addAll(list);
		markDirty();
	}

	@Override
	public void addPassivePower(PassivePower power) {
		if (!passiveList.contains(power)) passiveList.add(power);
		markDirty();
	}

	@Override
	public void removePassivePower(PassivePower power) {
		passiveList.remove(power);
		markDirty();
	}

	@Override
	public void clearPassivePowers() {
		passiveList.clear();
		markDirty();
	}

	@Override
	public boolean hasPassivePower(PassivePower power) {
		return passiveList.contains(power);
	}

	@Override
	public void setCurrentActive(ActivePower power) {
		currActivePower = power;
		markDirty();
	}

	@Override
	public ActivePower getCurrentActive() {
		return currActivePower;
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
	public void copyFrom(IVadeMecumCapability cap) {
		if (cap == null) return;
		setObtainedCategories(cap.getObtainedCategories());
		setActivePowers(cap.getActivePowers());
		setPassivePowers(cap.getPassivePowers());
		setCurrentActive(cap.getCurrentActive());
		setLastEntry(cap.getLastEntry());
		setLoaded();
		markDirty();
	}

	@Override
	public void decodePacket(ByteBufInputStream stream) throws IOException {
		setCurrentActive(IVadeMecumCapability.getActivePowerFromID(stream.readInt()));
		setLastEntry(stream.readUTF());
		// Do Arrays last
		int category = stream.readInt();
		int active = stream.readInt();
		int passive = stream.readInt();
		clearCategories();
		int id;
		for (int i = 0; i < category; i++) {
			id = stream.readInt();
			addCategory(IVadeMecumCapability.getCategoryFromID(id));
		}
		clearActivePowers();
		for (int i = 0; i < active; i++) {
			id = stream.readInt();
			addActivePower(IVadeMecumCapability.getActivePowerFromID(id));
		}
		clearPassivePowers();
		for (int i = 0; i < passive; i++) {
			id = stream.readInt();
			addPassivePower(IVadeMecumCapability.getPassivePowerFromID(id));
		}
	}

	@Override
	public void encodePacket(DataOutputStream stream) throws IOException {
		stream.writeInt(IVadeMecumCapability.getActivePowerID(getCurrentActive()));
		stream.writeUTF(getLastEntry());
		// Do Arrays last
		stream.writeInt(getObtainedCategories().size());
		stream.writeInt(getActivePowers().size());
		stream.writeInt(getPassivePowers().size());
		for (Category cat : getObtainedCategories()) {
			stream.writeInt(IVadeMecumCapability.getCategoryID(cat));
		}
		for (ActivePower power : getActivePowers()) {
			stream.writeInt(IVadeMecumCapability.getActivePowerID(power));
		}
		for (PassivePower power : getPassivePowers()) {
			stream.writeInt(IVadeMecumCapability.getPassivePowerID(power));
		}
	}

}
