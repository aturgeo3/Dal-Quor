package Tamaized.Voidcraft.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.voidCraft;

public class PickUpEvent {
	
	@SubscribeEvent
	public void SomethingPickedup(EntityItemPickupEvent event){
		if(event.getItem().getEntityItem().isItemEqual(new ItemStack(voidCraft.items.voidcrystal))){
			event.getEntityPlayer().addStat(voidCraft.achievements.voidCraftAchMainLine_1, 1);
		}
		else if(event.getItem().getEntityItem().isItemEqual(new ItemStack(voidCraft.items.voidStar))){
			event.getEntityPlayer().addStat(voidCraft.achievements.voidCraftAchMainLine_4, 1);
		}
	}

}
