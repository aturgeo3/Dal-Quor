package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.infusionAltar;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListInfusionAltar implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(voidCraft.blocks.voidInfuser).getDisplayName(), voidCraft.modid+".VadeMecum.docs.desc.voidInfuser"),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.voidInfuserInert).getDisplayName(), new ItemStack[] {
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(Items.CAULDRON),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick),
						new ItemStack(voidCraft.items.voidCloth),
						new ItemStack(voidCraft.blocks.blockVoidbrick) }, new ItemStack(voidCraft.blocks.voidInfuserInert, 1))),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal(new ItemStack(voidCraft.blocks.voidInfuser).getDisplayName(), new ItemStack[] {
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
