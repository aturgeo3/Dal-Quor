package tamaized.voidcraft.common.vademecum.contents.documentation.fruit;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.*;

public class VadeMecumPageListFruit implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[]{new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.fruit", VoidCraft.modid + ".VadeMecum.docs.desc.fruit.pg1"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.fruit.pg2"), new VadeMecumPage("", VoidCraft.modid + ".VadeMecum.docs.desc.fruit.pg3"), new VadeMecumPage(VoidCraft.modid + ".VadeMecum.docs.title.plenum", VoidCraft.modid + ".VadeMecum.docs.desc.fruit.pg4"), new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(VoidCraft.modid + ".VadeMecum.recipe.alchemy", PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), VoidCraft.potions.type_voidImmunity)))};
	}

}
