package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tabs extends RegistryBase{
	
	public static CreativeTabs tabVoid;
	public static CreativeTabs tForge;

	@Override
	public void preInit() {
		tabVoid = new CreativeTabs("tabVoid") {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(voidCraft.blocks.blockTeleporterVoid);
			}
		};
	
		tForge = new CreativeTabs("tForge") {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(voidCraft.blocks.blockNoBreak);
			}
		};
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

}
