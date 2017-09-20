package tamaized.voidcraft.client.entity.nonliving;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tamaized.voidcraft.client.event.ClientRenderTicker;
import tamaized.voidcraft.common.entity.nonliving.EntityBlockSpell;

public class RenderBlockSpell extends Render<EntityBlockSpell> {

	public RenderBlockSpell(RenderManager renderManagerIn) {
		super(renderManagerIn);
		shadowSize = 0.5F;
	}

	@Override
	public void doRender(EntityBlockSpell entity, double x, double y, double z, float entityYaw, float partialTicks) {

		IBlockState state = entity.getState();
		if (state.getRenderType() == EnumBlockRenderType.MODEL) {
			World world = entity.world;

			if (state != world.getBlockState(new BlockPos(entity)) && state.getRenderType() != EnumBlockRenderType.INVISIBLE) {
				bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				GlStateManager.pushMatrix();
				GlStateManager.disableLighting();
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();

				if (renderOutlines) {
					GlStateManager.enableColorMaterial();
					GlStateManager.enableOutlineMode(getTeamColor(entity));
				}

				BlockPos blockpos = new BlockPos(entity.posX, entity.getEntityBoundingBox().maxY, entity.posZ);

				GlStateManager.pushMatrix();
				{
					float f = (float) ClientRenderTicker.tick + partialTicks;
					GlStateManager.translate(x, y, z);
					GlStateManager.rotate(f, 0, 1, 0);
					GlStateManager.translate(-0.5F, 0.0F, 0.0F);
					GlStateManager.rotate(f, 1, 1, 0);
					GlStateManager.translate(0.5F, 0.0F, 0.0F);
					GlStateManager.translate(0.0F, 0.0F, -0.5F);
					GlStateManager.rotate(f, 0, 1, 1);
					GlStateManager.translate(0.0F, 0.0F, 0.5F);
					GlStateManager.translate(-0.5F, 0.0F, -0.5F);
					bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
					GlStateManager.translate(-blockpos.getX(), -blockpos.getY(), -blockpos.getZ());
					BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
					blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(state), state, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(entity.getPosition()));
					tessellator.draw();
				}
				GlStateManager.popMatrix();

				if (renderOutlines) {
					GlStateManager.disableOutlineMode();
					GlStateManager.disableColorMaterial();
				}

				GlStateManager.enableLighting();
				GlStateManager.popMatrix();
				super.doRender(entity, x, y, z, entityYaw, partialTicks);
			}
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBlockSpell entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
