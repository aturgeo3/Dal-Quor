package tamaized.dalquor.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.client.gui.VadeMecumGUI;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.gui.GuiHandler;
import tamaized.dalquor.network.ItemStackNetworkHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientPacketHandlerVadeMecumUpdate implements IMessageHandler<ClientPacketHandlerVadeMecumUpdate.Packet, IMessage> {

	public static int getTypeID(Type type) {
		return type.ordinal();
	}

	public static Type getTypeFromID(int id) {
		return id > Type.values().length ? Type.NULL : Type.values()[id];
	}

	@SideOnly(Side.CLIENT)
	private static void processPacket(Packet message, EntityPlayer player, World world) {
		IVadeMecumCapability cap = Minecraft.getMinecraft().player.getCapability(CapabilityList.VADEMECUM, null);
		if (cap != null) {
			cap.setBookActive(message.bookActive);
			cap.setLastEntry(message.lastEntry);
			cap.setPage(message.page);
			cap.clearCategories();
			for (IVadeMecumCapability.Category cat : message.obtainedCategoryList)
				cap.addCategory(null, cat);
			cap.getActivePassiveList().clear();
			for (IVadeMecumCapability.Passive passive : message.passiveCategoryList)
				cap.addPassive(passive);
			cap.clearComponents();
			for (Map.Entry<IVadeMecumCapability.Category, ItemStack> entry : message.spellComponents.entrySet())
				cap.getComponents().put(entry.getKey(), entry.getValue());
			cap.setCurrentActive(message.currentActive);
		}
		switch (getTypeFromID(message.gui)) {
			case Normal:
				net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(new VadeMecumGUI(player));
				break;
			case Spells:
				FMLNetworkHandler.openGui(player, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecumSpells), player.world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
				break;
			default:
				break;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, net.minecraft.client.Minecraft.getMinecraft().player, net.minecraft.client.Minecraft.getMinecraft().world));
		return null;
	}

	public enum Type {
		NULL, Normal, Spells
	}

	public static class Packet implements IMessage {

		private int gui;
		private boolean bookActive;
		private String lastEntry;
		private int page;
		private List<IVadeMecumCapability.Category> obtainedCategoryList = new ArrayList<>();
		private List<IVadeMecumCapability.Passive> passiveCategoryList = new ArrayList<>();
		private Map<IVadeMecumCapability.Category, ItemStack> spellComponents = new HashMap<>();
		private IVadeMecumCapability.Category currentActive;

		public Packet() {

		}

		public Packet(IVadeMecumCapability cap, Type guiType) {
			gui = getTypeID(guiType);
			bookActive = cap.isBookActive();
			lastEntry = cap.getLastEntry();
			page = cap.getPage();
			obtainedCategoryList.clear();
			obtainedCategoryList.addAll(cap.getObtainedCategories());
			passiveCategoryList.clear();
			passiveCategoryList.addAll(cap.getActivePassiveList());
			spellComponents.clear();
			spellComponents.putAll(cap.getComponents());
			currentActive = cap.getCurrentActive();
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			gui = buf.readInt();
			bookActive = buf.readBoolean();
			lastEntry = ByteBufUtils.readUTF8String(buf);
			page = buf.readInt();
			currentActive = (IVadeMecumCapability.getCategoryFromID(buf.readInt()));
			{
				obtainedCategoryList.clear();
				int l = buf.readInt();
				for (int i = 0; i < l; i++) {
					obtainedCategoryList.add(IVadeMecumCapability.getCategoryFromID(buf.readInt()));
				}
			}
			{
				passiveCategoryList.clear();
				int l = buf.readInt();
				for (int i = 0; i < l; i++) {
					passiveCategoryList.add(IVadeMecumCapability.getPassiveFromID(buf.readInt()));
				}
			}
			{
				spellComponents.clear();
				int l = buf.readInt();
				for (int i = 0; i < l; i++) {
					spellComponents.put(IVadeMecumCapability.getCategoryFromID(buf.readInt()), ItemStackNetworkHelper.decodeStack(buf));
				}
			}
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(gui);
			buf.writeBoolean(bookActive);
			ByteBufUtils.writeUTF8String(buf, lastEntry);
			buf.writeInt(page);
			buf.writeInt(IVadeMecumCapability.getCategoryID(currentActive));
			// Do Arrays last
			buf.writeInt(obtainedCategoryList.size());
			for (IVadeMecumCapability.Category cat : obtainedCategoryList) {
				buf.writeInt(IVadeMecumCapability.getCategoryID(cat));
			}
			buf.writeInt(passiveCategoryList.size());
			for (IVadeMecumCapability.Passive passive : passiveCategoryList) {
				buf.writeInt(IVadeMecumCapability.getPassiveID(passive));
			}
			buf.writeInt(spellComponents.size());
			for (Map.Entry<IVadeMecumCapability.Category, ItemStack> entry : spellComponents.entrySet()) {
				buf.writeInt(IVadeMecumCapability.getCategoryID(entry.getKey()));
				ItemStackNetworkHelper.encodeStack(entry.getValue(), buf);
			}
		}
	}

}
