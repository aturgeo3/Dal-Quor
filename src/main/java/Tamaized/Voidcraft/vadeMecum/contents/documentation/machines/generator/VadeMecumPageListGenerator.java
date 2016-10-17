package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.generator;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListGenerator {

	public static final IVadeMecumPage[] getPageList() {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Generator", "They say there is no such thing as nothingness, for even empty space is filled with energy. But given the vacuous nature of The Void, there are plenty of opportunities to exploit this fundamental part of quantum physics. This energy manifests itself as pools of Liquid Void, whose strange physical properties make it behave in even stranger ways. This leaves one to wonder how it can be used; one potential answer can be found in the Voidic"),
				new VadeMecumPage("", "Generator. This machine, which is constructed by surrounding a Void Infusion Altar with Redstone, converts the chemical energy found in Liquid Void back into the unique power from which it is comprised. This \"Voidic Power\" has many applications, though, details on those details are best saved for another entry."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(voidCraft.blocks.voidInfuser),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE),
						new ItemStack(Items.REDSTONE) }, new ItemStack(voidCraft.blocks.voidicGen, 1))) };
	}

}
