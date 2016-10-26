package Tamaized.Voidcraft.vadeMecum.contents.documentation.weapons.demonsword;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListDemonSword implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Demonic Sword", "Foes struck by this sword are dealt the same effects as the Arch-Angelic sword though the durations are now 20 seconds each."),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(voidCraft.tools.archSword), new ItemStack(voidCraft.tools.demonSword))) };
	}

}
