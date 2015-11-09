package Tamaized.Voidcraft.common.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;

public class ItemRenderNoBreak implements IItemRenderer {
	
	private RenderNoBreak render;
	private TileEntity entity;

	public ItemRenderNoBreak(RenderNoBreak renderNoBreak, TileEntityNoBreak tileEntityNoBreak) {
		this.entity = tileEntityNoBreak;
		this.render = renderNoBreak;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(type == IItemRenderer.ItemRenderType.ENTITY) GL11.glTranslatef(-0.5F,  0.0F,  -0.5F);
		this.render.renderTileEntityAt(this.entity, 0.0d, 0.0d, 0.0d, 0.0f);
	}

}
