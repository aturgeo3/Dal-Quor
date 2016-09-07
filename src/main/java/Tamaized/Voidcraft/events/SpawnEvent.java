package Tamaized.Voidcraft.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.EntityVoidMob;
import Tamaized.Voidcraft.entity.EntityVoidNPC;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawnBase;

public class SpawnEvent {
	
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){
		if(!event.getWorld().isRemote && event.getWorld().provider.getDimension() == voidCraft.dimensionIdVoid) {
	    	if(event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer || event.getEntity() instanceof EntityVoidMob || event.getEntity() instanceof EntityBossCorruptedPawnBase || event.getEntity() instanceof EntityVoidNPC)){
	    		event.setCanceled(true);
	    		System.out.println(event.getEntity().getClass());
	    	}
	    }
	}

}
