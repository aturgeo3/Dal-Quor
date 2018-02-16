package tamaized.dalquor.registry;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tamaized.dalquor.VoidCraft;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecumItem.IVadeMecumItemCapability;

public class VadeMecumMeshDefinition implements ItemMeshDefinition {

	private static final ModelResourceLocation closed = new ModelResourceLocation(new ResourceLocation(VoidCraft.modid, "items/vademecum"), "inventory");
	private static final ModelResourceLocation open = new ModelResourceLocation(new ResourceLocation(VoidCraft.modid, "items/vademecum_open"), "inventory");

	public static void preRegister() {
		//ModelLoader.setCustomModelResourceLocation(voidCraft.items.vademecum, 0, closed);
		//ModelLoader.setCustomModelResourceLocation(voidCraft.items.vademecum, 0, open);
		ModelBakery.registerItemVariants(VoidCraft.items.vadeMecum, closed, open);
		//ModelBakery.registerItemVariants(voidCraft.items.vademecum, open);
	}

	public static void register() {
	}

	@Override
	public ModelResourceLocation getModelLocation(ItemStack stack) {
		IVadeMecumItemCapability cap = stack.getCapability(CapabilityList.VADEMECUMITEM, null);
		if (cap != null && cap.getBookState())
			return open;
		return closed;
	}

}
