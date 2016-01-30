package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Tabs extends RegistryBase{
	
	public static CreativeTabs tabVoid;
	public static CreativeTabs tForge;

	@Override
	public void preInit() {
		tabVoid = new CreativeTabs("tabVoid") {
			@Override
			public Item getTabIconItem() {
				return Item.getItemFromBlock(voidCraft.blocks.blockPortalVoid);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
