package Tamaized.Voidcraft.voidicInfusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidicInfusionHandler {
	
	private Map<UUID, PlayerInfusionHandler> playerData;
	private ArrayList<EntityPlayerMP> spooler = new ArrayList<EntityPlayerMP>();
	
	public VoidicInfusionHandler(){
		construct();
	}
	
	public void construct(){
		playerData = new HashMap<UUID, PlayerInfusionHandler>();
	}
	
	public void addPlayer(EntityPlayerMP player){
		UUID id = player.getGameProfile().getId();
		voidCraft.logger.info("Adding Player "+player.getGameProfile().getName()+"("+id+") to VoidicInfusionHandler");
		playerData.put(id, new PlayerInfusionHandler(player, player.getCapability(CapabilityList.VOIDICINFUSION, null).getInfusion(), player.getCapability(CapabilityList.VOIDICINFUSION, null).getMaxInfusion()));
	}
	
	public void unloadPlayer(EntityPlayerMP player){
		UUID id = player.getGameProfile().getId();
		voidCraft.logger.info("Unloading Player "+player.getGameProfile().getName()+"("+id+") from VoidicInfusionHandler");
		playerData.remove(id);
	}
	
	@SubscribeEvent
	public void update(ServerTickEvent e){
		if(e.phase == e.phase.END){
			Iterator<EntityPlayerMP> iter = spooler.iterator();
			while(iter.hasNext()){
				EntityPlayerMP player = iter.next();
				if(playerData.containsKey(player.getGameProfile().getId())){
					iter.remove();
					break;
				}
				if(player.hasCapability(CapabilityList.VOIDICINFUSION, null)){
					addPlayer(player);
					iter.remove();
					break;
				}
			}
			for(PlayerInfusionHandler data : playerData.values()) data.update();
		}
	}
	
	@SubscribeEvent
	public void onPlayerEvent(PlayerEvent.PlayerLoggedInEvent e) {
		if(e.player instanceof EntityPlayerMP) spooler.add((EntityPlayerMP) e.player);
	}
	
	@SubscribeEvent
	public void onPlayerEvent(PlayerEvent.PlayerLoggedOutEvent e) {
		if(e.player instanceof EntityPlayerMP) if(playerData.containsKey(e.player.getGameProfile().getId())) unloadPlayer((EntityPlayerMP) e.player);
	}
}