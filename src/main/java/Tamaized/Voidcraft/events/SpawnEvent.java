package Tamaized.Voidcraft.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.EntityVoidBossMob;
import Tamaized.Voidcraft.mobs.EntityVoidMob;
import Tamaized.Voidcraft.mobs.EntityVoidNPC;

public class SpawnEvent {
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){
		if(!event.getWorld().isRemote && event.getWorld().provider.getDimension() == voidCraft.dimensionIdVoid) {
	    	if(event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityVoidMob || event.getEntity() instanceof EntityVoidBossMob || event.getEntity() instanceof EntityVoidNPC)){
	    		event.setCanceled(true);
	    		System.out.println(event.getEntity().getClass());
	    	}
	    }
	}

}
