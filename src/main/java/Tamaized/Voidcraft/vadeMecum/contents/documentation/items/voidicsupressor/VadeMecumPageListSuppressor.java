package Tamaized.Voidcraft.vadeMecum.contents.documentation.items.voidicsupressor;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingNormal;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListSuppressor implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Voidic Suppressor", "One of the few items that requires Voidic Power to function. To charge this device place it in a Voidic Charger. If this device contains power, it'll use that power to anchor the holder to the material plane. This means while in your hand or offhand the Voidic Suppressor will remove any Voidic Infusion on you and prevent more from being gained."),
				new VadeMecumPageCrafting(new VadeMecumCraftingNormal("Recipe", new ItemStack[] {
						new ItemStack(voidCraft.items.voidcrystal),
						new ItemStack(Items.COMPASS),
						null,
						new ItemStack(Items.REDSTONE),
						new ItemStack(voidCraft.items.voidCloth),
						null,
						null,
						null,
						null }, new ItemStack(voidCraft.items.voidicSuppressor)))};
	}

}
