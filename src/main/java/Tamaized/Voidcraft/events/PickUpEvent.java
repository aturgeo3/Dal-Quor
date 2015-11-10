package Tamaized.Voidcraft.events;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PickUpEvent {
	
	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event){
		if (event.item.getEntityItem().isItemEqual(new ItemStack(voidCraft.voidcrystal))){
			event.entityPlayer.addStat(voidCraft.voidCraftAchMainLine_1, 1);
		}
	}

}
