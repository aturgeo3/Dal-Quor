package tamaized.dalquor.client.entity.nonliving;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.common.entity.nonliving.EntityObsidianFlask;
import tamaized.dalquor.registry.ModItems;

@SideOnly(Side.CLIENT)
public class RenderObsidianFlask<T extends EntityObsidianFlask> extends Render<T> {

	private final RenderItem itemRenderer;

	public RenderObsidianFlask(RenderManager renderManager, RenderItem itemRendererIn) {
		super(renderManager);
		itemRenderer = itemRendererIn;
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate((float) (renderManager.options.thirdPersonView == 2 ? -1 : 1) * renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		if (renderOutlines) {
			GlStateManager.enableColorMaterial();
			GlStateManager.enableOutlineMode(getTeamColor(entity));
		}

		itemRenderer.renderItem(getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

		if (renderOutlines) {
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	private ItemStack getStackToRender(T e) {
		Item item;
		switch (e.getType()) {
			default:
			case Normal:
				item = ModItems.obsidianFlask;
				break;
			case Fire:
				item = ModItems.obsidianFlaskFire;
				break;
			case Freeze:
				item = ModItems.obsidianFlaskFreeze;
				break;
			case Shock:
				item = ModItems.obsidianFlaskShock;
				break;
			case Acid:
				item = ModItems.obsidianFlaskAcid;
				break;
			case Void:
				item = ModItems.obsidianFlaskVoid;
				break;
		}
		return new ItemStack(item);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
