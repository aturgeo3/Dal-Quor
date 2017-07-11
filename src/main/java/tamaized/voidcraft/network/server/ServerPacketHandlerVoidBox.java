package tamaized.voidcraft.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidBox;

public class ServerPacketHandlerVoidBox implements IMessageHandler<ServerPacketHandlerVoidBox.Packet, IMessage> {

	private static int getRequestID(RequestType type) {
		return type.ordinal();
	}

	private static RequestType getRequestFromID(int id) {
		return id > RequestType.values().length - 1 ? RequestType.NULL : RequestType.values()[id];
	}

	private static void processPacket(Packet message, EntityPlayerMP player, World world) {
		if (!world.isBlockLoaded(message.pos))
			return;
		TileEntity te = world.getTileEntity(message.pos);
		if (!(te instanceof TileEntityVoidBox))
			return;
		TileEntityVoidBox voidBox = (TileEntityVoidBox) te;
		switch (getRequestFromID(message.request)) {
			case PLAY:
				voidBox.PlayNextSound();
				break;
			case STOP:
				voidBox.StopTheSoundAndDeposit();
				break;
			case LOOP:
				voidBox.setLoopState();
				break;
			case AUTO:
				voidBox.setAutoState();
				break;
			default:
				break;
		}
	}

	@Override
	public IMessage onMessage(Packet message, MessageContext ctx) {
		EntityPlayerMP player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> processPacket(message, player, player.world));
		return null;
	}

	public enum RequestType {
		NULL, PLAY, STOP, LOOP, AUTO
	}

	public static class Packet implements IMessage {

		private int request;
		private BlockPos pos;

		public Packet() {

		}

		public Packet(RequestType type, BlockPos pos) {
			request = getRequestID(type);
			this.pos = pos;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			request = buf.readInt();
			pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(request);
			buf.writeInt(pos.getX());
			buf.writeInt(pos.getY());
			buf.writeInt(pos.getZ());
		}
	}

}
