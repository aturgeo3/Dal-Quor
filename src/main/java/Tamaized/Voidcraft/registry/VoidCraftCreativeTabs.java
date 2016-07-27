package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;

public class VoidCraftCreativeTabs implements ITamRegistry {

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

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

}
