package Tamaized.Voidcraft.common.handlers;

import ibxm.Player;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import io.netty.buffer.ByteBufInputStream;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class VoidCraftServerPacketHandler {
	
	public static final int TYPE_VOIDBOX_PLAY = 0;
	public static final int TYPE_VOIDBOX_STOP = 1;
	public static final int TYPE_VOIDBOX_LOOP = 2;
	public static final int TYPE_VOIDBOX_AUTO = 3;
	
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
	
	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP playerEntityMP){
		Entity playerEntity = (Entity)playerEntityMP;
		TileEntityVoidBox thisTileEntity; 
	  
		if(parSide == Side.SERVER){
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType;
			try {
				pktType = bbis.readInt();
				if(pktType == TYPE_VOIDBOX_PLAY){
					thisTileEntity = (TileEntityVoidBox) playerEntity.worldObj.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.isPlaying = true;
					thisTileEntity.doPlay = true;
				}else if(pktType == TYPE_VOIDBOX_STOP){
					thisTileEntity = (TileEntityVoidBox) playerEntity.worldObj.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.isPlaying = false;
					thisTileEntity.loop = false;
				}else if(pktType == TYPE_VOIDBOX_LOOP){
					thisTileEntity = (TileEntityVoidBox) playerEntity.worldObj.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.loop = bbis.readBoolean();
				}else if(pktType == TYPE_VOIDBOX_AUTO){
					thisTileEntity = (TileEntityVoidBox) playerEntity.worldObj.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
					if (thisTileEntity == null){
						bbis.close();
						return;
					}
					thisTileEntity.autoFill = bbis.readBoolean();
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
