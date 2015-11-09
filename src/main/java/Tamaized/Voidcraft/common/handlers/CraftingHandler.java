package Tamaized.Voidcraft.common.handlers;

import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class CraftingHandler {
	
	@SubscribeEvent
	public void SomethingCrafted(ItemCraftedEvent event){
		if (event.crafting.getItem() == voidCraft.voidSword) {
			event.player.addStat(voidCraft.voidCraftAchSideLine1_1, 1);
		}
		else if(event.crafting.getItem() == voidCraft.angelicSword){
			event.player.addStat(voidCraft.voidCraftAchSideLine1_2, 1);
		}
		else if(event.crafting.getItem() == voidCraft.chainSword){
			event.player.addStat(voidCraft.voidCraftAchSideLine2_1, 1);
		}
		else if(event.crafting.getItem() == new ItemStack(voidCraft.voidInfuser).getItem()){
			event.player.addStat(voidCraft.voidCraftAchMainLine_3, 1);
		}
		else if(event.crafting.getItem() == voidCraft.moltenSword){
			event.player.addStat(voidCraft.voidCraftAchSideLine2_2, 1);
		}
		else if(event.crafting.getItem() == voidCraft.archSword){
			event.player.addStat(voidCraft.voidCraftAchSideLine2_3, 1);
		}
	}
	
	@SubscribeEvent
	public void MachineCrafted(ItemSmeltedEvent event){
		if (event.smelting.getItem() == new ItemStack(voidCraft.voidMacerator).getItem()) {
			event.player.addStat(voidCraft.voidCraftAchMainLine_4, 1);
		}
		else if(event.smelting.getItem() == voidCraft.demonSword){
			event.player.addStat(voidCraft.voidCraftAchSideLine2_4, 1);
		}
	}
	
}