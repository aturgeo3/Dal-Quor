package Tamaized.Voidcraft.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.registry.IBasicVoid;

public class BasicVoidItems extends Item implements IBasicVoid{
	
	private final String name;
	
	//private ModelResourceLocation mrl;

	public BasicVoidItems(String n) {
		super();
		name = n;
		setUnlocalizedName(name);
		GameRegistry.registerItem(this, "items/"+n);
		//mrl = new ScrewModelResourceLocation("item_", name, "inventory");
	}
	
	@Override
	public String getName() {
		return name;
	}
	/*
	@SideOnly(Side.CLIENT)
	@Override
    public net.minecraft.client.resources.model.ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining){
        return mrl;
    }*/

}
