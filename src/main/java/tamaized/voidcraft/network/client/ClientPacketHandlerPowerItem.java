package tamaized.voidcraft.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.voidicPower.IVoidicPowerCapability;
import tamaized.voidcraft.common.voidicpower.VoidicPowerItem;
import tamaized.voidcraft.network.ItemStackNetworkHelper;

public class ClientPacketHandlerPowerItem implements IMessageHandler<ClientPacketHandlerPowerItem.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayer player, World world) {
		int slot = message.slot;
		ItemStack stack = message.stack;
		ItemStack checkStack;
		switch (slot) {
			case VoidicPowerItem.PLAYER_INV_SLOT_OFFHAND:
				checkStack = Minecraft.getMinecraft().player.inventory.offHandInventory.get(0);
				break;
			case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_HELM:
				checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(0);
				break;
			case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_CHEST:
				checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(1);
				break;
			case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_LEGS:
				checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(2);
				break;
			case VoidicPowerItem.PLAYER_INV_SLOT_ARMOR_BOOTS:
				checkStack = Minecraft.getMinecraft().player.inventory.armorInventory.get(3);
				break;
			default:
				checkStack = Minecraft.getMinecraft().player.inventory.mainInventory.get(slot);
				break;
		}
		if (checkStack.isEmpty() || stack.isEmpty() || !ItemStack.areItemStacksEqual(checkStack, stack) || checkStack.hasCapability(CapabilityList.VOIDICPOWER, null))
			return;
		IVoidicPowerCapability cap = checkStack.getCapability(CapabilityList.VOIDICPOWER, null);
		cap.setMaxPower(message.maxPower);
		cap.setCurrentPower(message.currPower);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, Minecraft.getMinecraft().player, Minecraft.getMinecraft().world));
		return null;
	}

	public static class Packet implements IMessage {

		private int slot;
		private ItemStack stack;
		private int currPower;
		private int maxPower;

		public Packet() {

		}

		public Packet(int slot, ItemStack stack, IVoidicPowerCapability cap) {
			this.slot = slot;
			this.stack = stack;
			currPower = cap.getCurrentPower();
			maxPower = cap.getMaxPower();
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			slot = buf.readInt();
			currPower = buf.readInt();
			maxPower = buf.readInt();
			stack = ItemStackNetworkHelper.decodeStack(buf);
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(slot);
			buf.writeInt(currPower);
			buf.writeInt(maxPower);
			ItemStackNetworkHelper.encodeStack(stack, buf);
		}
	}

}
