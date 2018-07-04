package tamaized.dalquor.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModCreativeTabs {

	public static CreativeTabs tabVoid = new CreativeTabs("tabVoid") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModBlocks.portalVoid);
		}
	};

}
