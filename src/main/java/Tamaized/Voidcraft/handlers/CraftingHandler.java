package Tamaized.Voidcraft.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;
import Tamaized.Voidcraft.voidCraft;

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
		else if(event.crafting.getItem() == Item.getItemFromBlock((voidCraft.blocks.voidInfuser))){
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
		else if(event.crafting.getItem() == Item.getItemFromBlock(voidCraft.blocks.voidicGen)){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_1_1, 1);
		}
		else if(event.crafting.getItem() == Item.getItemFromBlock(voidCraft.blocks.voidicCharger)){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_1_2, 1);
		}
		else if(event.crafting.getItem() == voidCraft.items.voidicSuppressor){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_1_3, 1);
		}
	}
	
	@SubscribeEvent
	public void MachineCrafted(ItemSmeltedEvent event){
		if (event.smelting.getItem() == Item.getItemFromBlock((voidCraft.blocks.voidMacerator))) {
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_2, 1);
		}
		else if(event.smelting.getItem() == voidCraft.tools.demonSword){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine1_1_4, 1);
		}
		else if(event.smelting.getItem() == Item.getItemFromBlock((voidCraft.blocks.Heimdall))){
			event.player.addStat(voidCraft.achievements.voidCraftAchSideLine2_3, 1);
		}
	}
	
}