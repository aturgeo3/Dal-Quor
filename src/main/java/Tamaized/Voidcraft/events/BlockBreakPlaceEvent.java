package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockBreakPlaceEvent {
	
	@SubscribeEvent
	public void BreakBlockEvent(BlockEvent.BreakEvent e){
		EntityPlayer player = e.getPlayer();
		World world = player.worldObj;
		if(world.provider.getDimension() == voidCraft.dimensionIdXia && !player.capabilities.isCreativeMode){
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void BreakBlockEvent(BlockEvent.PlaceEvent e){
		EntityPlayer player = e.getPlayer();
		World world = player.worldObj;
		if(world.provider.getDimension() == voidCraft.dimensionIdXia && !player.capabilities.isCreativeMode){
			e.setCanceled(true);
		}
	}

}
