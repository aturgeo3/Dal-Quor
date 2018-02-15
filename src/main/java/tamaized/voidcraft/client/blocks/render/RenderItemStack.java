package tamaized.voidcraft.client.blocks.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import tamaized.voidcraft.common.blocks.TileEntityNoBreak;
import tamaized.voidcraft.registry.VoidCraftBlocks;

public class RenderItemStack extends TileEntityItemStackRenderer {

	private static final TileEntityNoBreak noBreakInstance = new TileEntityNoBreak();

	@Override
	public void renderByItem(ItemStack itemStack) {
		Block block = Block.getBlockFromItem(itemStack.getItem());
		if (block == VoidCraftBlocks.blockNoBreak) {
			TileEntityRendererDispatcher.instance.render(noBreakInstance, 0.0D, 0.0D, 0.0D, 0.0F);
		} else {
			super.renderByItem(itemStack);
		}
	}

}
