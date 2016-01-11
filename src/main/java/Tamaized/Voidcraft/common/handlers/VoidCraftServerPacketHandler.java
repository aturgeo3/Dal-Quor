package Tamaized.Voidcraft.common.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import Tamaized.Voidcraft.items.HookShot;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;

public class VoidCraftServerPacketHandler {
	
	public static final int TYPE_VOIDBOX_PLAY = 0;
	public static final int TYPE_VOIDBOX_STOP = 1;
	public static final int TYPE_VOIDBOX_LOOP = 2;
	public static final int TYPE_VOIDBOX_AUTO = 3;
	public static final int TYPE_HOOKSHOT_STOP = 4;
	
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) {
		try {
			
		EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).playerEntity;
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
		
		processPacketOnServer(event.packet.payload(), Side.SERVER, player);
		
		bbis.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP player){
		TileEntityVoidBox thisTileEntity; 
	  
		if(parSide == Side.SERVER){
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				pktType = bbis.readInt();
				if(pktType == TYPE_VOIDBOX_PLAY){
					thisTileEntity = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.isPlaying = true;
					thisTileEntity.doPlay = true;
				}else if(pktType == TYPE_VOIDBOX_STOP){
					thisTileEntity = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.isPlaying = false;
					thisTileEntity.loop = false;
				}else if(pktType == TYPE_VOIDBOX_LOOP){
					thisTileEntity = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.loop = bbis.readBoolean();
				}else if(pktType == TYPE_VOIDBOX_AUTO){
					thisTileEntity = (TileEntityVoidBox) player.worldObj.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.autoFill = bbis.readBoolean();
				}else if(pktType == TYPE_HOOKSHOT_STOP){
					HookShot.handler.put(player, false);
				}
			 }catch(Exception e){
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
