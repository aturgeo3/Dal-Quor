package Tamaized.Voidcraft.voidicInfusion;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerDisconnectionFromClientEvent;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidicInfusionHandler {
	
	private Map<UUID, PlayerInfusionHandler> playerData;
	
	public VoidicInfusionHandler(){
		construct();
	}
	
	public void construct(){
		playerData = new HashMap<UUID, PlayerInfusionHandler>();
	}
	
	public void addPlayer(EntityPlayerMP player){
		UUID id = player.getGameProfile().getId();
		voidCraft.logger.info("Adding Player "+player.getGameProfile().getName()+"("+id+") to VoidicInfusionHandler");
		//check if player has data stored
		NBTTagCompound data = player.getEntityData();
		if(data.hasKey("voidcraft")){
			NBTTagCompound dat1 = data.getCompoundTag("voidcraft");
			if(dat1.hasKey("voidicInfusion")){
				playerData.put(id, new PlayerInfusionHandler(player, dat1.getInteger("voidicInfusion")).sendPacketUpdates());
				return;
			}
		}
		playerData.put(id, new PlayerInfusionHandler(player).sendPacketUpdates());
	}
	
	public void unloadPlayer(EntityPlayerMP player){
		UUID id = player.getGameProfile().getId();
		voidCraft.logger.info("Unloading Player "+player.getGameProfile().getName()+"("+id+") from VoidicInfusionHandler");
		NBTTagCompound ctForge = player.getEntityData();
		NBTTagCompound ctVoid = new NBTTagCompound();
		if(!ctForge.hasKey("voidcraft")) ctVoid = ctForge.getCompoundTag("voidcraft");
		if(playerData.containsKey(id)){
			int value = playerData.get(id).getAmount();
			ctVoid.setInteger("voidicInfusion", value);
		}
		ctForge.setTag("voidcraft", ctVoid);
		playerData.remove(id);
	}
	
	@SubscribeEvent
	public void update(ServerTickEvent e){
		if(e.phase == e.phase.END){
			for(PlayerInfusionHandler data : playerData.values()) data.update();
		}
	}
	
	@SubscribeEvent
	public void onPlayerEvent(PlayerEvent.PlayerLoggedInEvent e) {
		if(e.player instanceof EntityPlayerMP){
			System.out.println("beep");
			if(!playerData.containsKey(e.player.getGameProfile().getId())) addPlayer((EntityPlayerMP) e.player);
		}else{
			System.out.println(e.player);
		}
	}
	
	@SubscribeEvent
	public void onPlayerEvent(PlayerEvent.PlayerLoggedOutEvent e) {
		if(e.player instanceof EntityPlayerMP){
			System.out.println("beep");
			if(playerData.containsKey(e.player.getGameProfile().getId())) unloadPlayer((EntityPlayerMP) e.player);
		}else{
			System.out.println(e.player);
		}
	}
	
	//@SubscribeEvent
	//public void onSinglePlayerEvent(ServerConnectionFromClientEvent e){
	//	System.out.println("ServerConnectionFromClientEvent");
	//	NetHandlerPlayServer server = (NetHandlerPlayServer) e.getHandler();
	//	EntityPlayerMP player = server.playerEntity;
	//	if(!playerData.containsKey(player.getGameProfile().getId())) addPlayer(player);
	//}
	
	//@SubscribeEvent
	//public void onSinglePlayerEvent(ServerDisconnectionFromClientEvent e){
	//	System.out.println("ServerDisconnectionFromClientEvent");
	//	NetHandlerPlayServer server = (NetHandlerPlayServer) e.getHandler();
	//	EntityPlayerMP player = server.playerEntity;
	//	if(playerData.containsKey(player.getGameProfile().getId())) unloadPlayer(player);
	//}

}
