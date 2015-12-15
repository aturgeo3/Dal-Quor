package Tamaized.Voidcraft.events;

import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.entity.EntityMobSpectreChain;
import Tamaized.Voidcraft.mobs.entity.EntityMobVoidWrath;
import Tamaized.Voidcraft.mobs.entity.EntityMobWraith;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class SpawnEvent {
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){
		if(!event.world.isRemote && event.world.provider.dimensionId == voidCraft.dimensionIdVoid) {
			boolean flag = true;
	    	if(event.entity instanceof EntityMobWraith || event.entity instanceof EntityMobSpectreChain || event.entity instanceof EntityMobVoidWrath){
	    		//if(Math.floor(Math.random()*20) == 0) flag = false;
	    		flag = false;
	    	}
	    	if(flag){
	    		event.setCanceled(true);
	    		System.out.println(event.entity.getClass());
	    	}
	    }
	}

}
