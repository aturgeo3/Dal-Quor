package tamaized.dalquor.network.client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.voidicInfusion.IVoidicInfusionCapability;

public class ClientPacketHandlerInfusion implements IMessageHandler<ClientPacketHandlerInfusion.Packet, IMessage> {

	@SideOnly(Side.CLIENT)
	private static void processPacket(Packet message, EntityPlayer player, World world) {
		if (world == null)
			return;
		Entity entity = world.getEntityByID(message.entityID);
		if (entity != null && entity instanceof EntityLivingBase && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
			IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
			cap.setInfusion(message.infusion);
			cap.setMaxInfusion(message.maxInfusion);
			cap.setXiaDefeats(message.xiaDefeats);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(Packet message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> processPacket(message, Minecraft.getMinecraft().player, Minecraft.getMinecraft().world));
		return null;
	}

	public static class Packet implements IMessage {

		private int entityID;
		private int infusion;
		private int maxInfusion;
		private int xiaDefeats;

		public Packet() {

		}

		public Packet(int entity, int infusion, int maxInfusion, int xiaDefeats) {
			entityID = entity;
			this.infusion = infusion;
			this.maxInfusion = maxInfusion;
			this.xiaDefeats = xiaDefeats;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			entityID = buf.readInt();
			infusion = buf.readInt();
			maxInfusion = buf.readInt();
			xiaDefeats = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(entityID);
			buf.writeInt(infusion);
			buf.writeInt(maxInfusion);
			buf.writeInt(xiaDefeats);
		}
	}

}
