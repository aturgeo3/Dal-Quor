package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListInfusionAltar {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Void Infusion Altar", "The special properties of Liquid Void are quite interesting indeed. While one can ponder what exactly makes it behave the way it does, this entry will focus more on harnessing its power. By combining Void Bricks, Void Cloth, a Cauldron, and a Void Star, one can create a machine that can change the physical properties of certain items in much the same way that you change via prolonged exposure to the Void's environment."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Inert Infusion Altar Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(Items.CAULDRON),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.voidInfuserInert, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Infusion Altar Recipe", new ItemStack[] {
						new ItemStack(voidCraft.blocks.voidInfuserInert),
						new ItemStack(voidCraft.items.voidStar),
						null,
						null,
						null,
						null,
						null,
						null,
						null }, new ItemStack(voidCraft.blocks.voidInfuser, 1))) };
	}

}
