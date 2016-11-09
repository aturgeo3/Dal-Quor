package Tamaized.Voidcraft.vadeMecum.contents.documentation.fruit;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingAlchemy;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

public class VadeMecumPageListFruit implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.fruit", voidCraft.modid+".VadeMecum.docs.desc.fruit.pg1"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.fruit.pg2"),
				new VadeMecumPage("", voidCraft.modid+".VadeMecum.docs.desc.fruit.pg3"),
				new VadeMecumPage(voidCraft.modid+".VadeMecum.docs.title.plenum", voidCraft.modid+".VadeMecum.docs.desc.fruit.pg4"),
				new VadeMecumPageCrafting(new VadeMecumCraftingAlchemy(voidCraft.modid+".VadeMecum.recipe.alchemy", new ItemStack[] {
						new ItemStack(voidCraft.items.etherealFruit),
						new ItemStack(voidCraft.items.etherealFruit_redstone),
						new ItemStack(voidCraft.items.etherealFruit_lapis),
						new ItemStack(voidCraft.items.etherealFruit_gold),
						new ItemStack(voidCraft.items.etherealFruit_emerald),
						new ItemStack(voidCraft.items.etherealFruit_diamond) }, PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), voidCraft.potions.type_voidImmunity))) };
	}

}
