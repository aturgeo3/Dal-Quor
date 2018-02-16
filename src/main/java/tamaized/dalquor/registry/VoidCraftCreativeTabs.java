package tamaized.dalquor.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class VoidCraftCreativeTabs {

	public static CreativeTabs tabVoid = new CreativeTabs("tabVoid") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(VoidCraftBlocks.blockPortalVoid);
		}
	};
	;
	public static CreativeTabs tForge;

}
