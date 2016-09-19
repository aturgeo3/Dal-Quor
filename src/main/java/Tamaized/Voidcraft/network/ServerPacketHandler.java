package Tamaized.Voidcraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import Tamaized.Voidcraft.items.HookShot;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;

public class ServerPacketHandler {

	public static enum PacketType {
		VOIDBOX_PLAY, VOIDBOX_STOP, VOIDBOX_LOOP, VOIDBOX_AUTO, HOOKSHOT_STOP
	}

	public static int getPacketTypeID(PacketType type) {
		return type.ordinal();
	}

	public static PacketType getPacketTypeFromID(int id) {
		return PacketType.values()[id];
	}

	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		try {

			EntityPlayerMP player = ((NetHandlerPlayServer) event.getHandler()).playerEntity;
			ByteBufInputStream bbis = new ByteBufInputStream(event.getPacket().payload());

			processPacketOnServer(event.getPacket().payload(), Side.SERVER, player);

			bbis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player) {

		if (parSide == Side.SERVER) {
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				TileEntityVoidBox voidBox;
				pktType = bbis.readInt();
				switch (getPacketTypeFromID(pktType)) {
					case VOIDBOX_PLAY:
						voidBox = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.isPlaying = true;
						voidBox.doPlay = true;
						break;
					case VOIDBOX_STOP:
						voidBox = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.isPlaying = false;
						voidBox.loop = false;
						break;
					case VOIDBOX_LOOP:
						voidBox = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.loop = bbis.readBoolean();
						break;
					case VOIDBOX_AUTO:
						voidBox = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
						if (voidBox == null) {
							bbis.close();
							return;
						}
						voidBox.autoFill = bbis.readBoolean();
						break;
					case HOOKSHOT_STOP:
						HookShot.handler.put(player, false);
						break;
					default:
						break;
				}
			} catch (Exception e) {
				try {
					bbis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}

}
