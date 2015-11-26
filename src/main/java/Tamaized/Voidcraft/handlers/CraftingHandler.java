package Tamaized.Voidcraft.handlers;

import net.minecraft.item.ItemStack;
import Tamaized.Voidcraft.common.voidCraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class CraftingHandler {
	
	@SubscribeEvent
	public void SomethingCrafted(ItemCraftedEvent event){
		if (event.crafting.getItem() == voidCraft.tools.voidSword) {
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1, 1);
		}
		else if(event.crafting.getItem() == voidCraft.tools.angelicSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_2, 1);
		}
		else if(event.crafting.getItem() == voidCraft.tools.chainSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1_1, 1);
		}
		else if(event.crafting.getItem() == new ItemStack(voidCraft.blocks.voidInfuser).getItem()){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_1, 1);
		}
		else if(event.crafting.getItem() == voidCraft.tools.moltenSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1_2, 1);
		}
		else if(event.crafting.getItem() == voidCraft.tools.archSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1_3, 1);
		}
		else if(event.crafting.getItem() == voidCraft.items.ChainedSkull){
			event.player.addStat(voidCraft.achievements.voidCraftAchMainLine_3, 1);
		}
	}
	
	@SubscribeEvent
	public void MachineCrafted(ItemSmeltedEvent event){
		if (event.smelting.getItem() == new ItemStack(voidCraft.blocks.voidMacerator).getItem()) {
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_2, 1);
		}
		else if(event.smelting.getItem() == voidCraft.tools.demonSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1_4, 1);
		}
		else if(event.smelting.getItem() == new ItemStack(voidCraft.blocks.Heimdall).getItem()){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_3, 1);
		}
	}
	
}