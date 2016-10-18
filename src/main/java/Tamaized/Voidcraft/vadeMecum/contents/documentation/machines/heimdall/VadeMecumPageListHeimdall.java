package Tamaized.Voidcraft.vadeMecum.contents.documentation.machines.heimdall;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.IVadeMecumPageProvider;
import Tamaized.Voidcraft.vadeMecum.VadeMecumCraftingInfuser;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPage;
import Tamaized.Voidcraft.vadeMecum.VadeMecumPageCrafting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListHeimdall implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage("Heimdall", "Beacons have the curious effect of seemingly generating a powerful beam of concentrated light. Though, such power cannot possibly be generated on its own. The more logical explanation is that it creates a small gate or rift to the Nether from which it can retrieve this energy. By modifying a Beacon via Void Infusion Altar, one can attune this gate to gather energy from the Void instead. Though, unlike a Beacon, which works effectively in"),
				new VadeMecumPage("", "most places with an open ceiling, the Heimdall requires fuel to keep its gate open. Since it is a modified rift to the nether recalibrated to go to the Void, it requires energy from both realms to function properly. Void Infused Quartz Dust will the job quite nicely."),
				new VadeMecumPageCrafting(new VadeMecumCraftingInfuser("Infusion Recipe", new ItemStack(Blocks.BEACON), new ItemStack(voidCraft.blocks.Heimdall, 1))) };
	}

}
