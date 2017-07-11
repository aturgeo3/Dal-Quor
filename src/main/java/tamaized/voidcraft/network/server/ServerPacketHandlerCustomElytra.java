package tamaized.voidcraft.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tamaized.voidcraft.common.armor.ArmorCustomElytra;
import tamaized.voidcraft.common.events.CustomElytraHandler;

public class ServerPacketHandlerCustomElytra implements IMessageHandler<ServerPacketHandlerCustomElytra.Packet, IMessage> {

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		if (!player.onGround && player.motionY < 0.0D && !CustomElytraHandler.isElytraFlying(player) && !player.isInWater()) {
			ItemStack itemstack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorCustomElytra && ArmorCustomElytra.isBroken(itemstack)) {
				CustomElytraHandler.setFlying(player, true);
			}
		} else {
			CustomElytraHandler.setFlying(player, false);
		}
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public static class Packet implements IMessage {

		public Packet() {

		}

		@Override
		public void fromBytes(ByteBuf buf) {
		}

		@Override
		public void toBytes(ByteBuf buf) {
		}
	}

}
