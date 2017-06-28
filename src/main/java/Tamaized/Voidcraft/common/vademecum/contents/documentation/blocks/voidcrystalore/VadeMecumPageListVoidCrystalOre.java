package tamaized.voidcraft.common.vademecum.contents.documentation.blocks.voidcrystalore;

import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.vademecum.IVadeMecumPage;
import tamaized.voidcraft.common.vademecum.IVadeMecumPageProvider;
import tamaized.voidcraft.common.vademecum.VadeMecumPage;
import net.minecraft.item.ItemStack;

public class VadeMecumPageListVoidCrystalOre implements IVadeMecumPageProvider {

	public IVadeMecumPage[] getPageList(IVadeMecumCapability cap) {
		return new IVadeMecumPage[] {
				new VadeMecumPage(new ItemStack(VoidCraft.blocks.oreVoidcrystal).getDisplayName(), VoidCraft.modid+".VadeMecum.docs.desc.voidCrystalOre"), };
	}

}
