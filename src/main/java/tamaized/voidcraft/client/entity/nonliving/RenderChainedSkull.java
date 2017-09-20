package tamaized.voidcraft.client.entity.nonliving;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import tamaized.voidcraft.common.entity.nonliving.EntityChainedSkull;

import java.util.Random;

public class RenderChainedSkull extends Render<EntityChainedSkull> {

	private IBlockState state = Blocks.BEDROCK.getDefaultState();

	public RenderChainedSkull(RenderManager renderManagerIn) {
		super(renderManagerIn);
		shadowSize = 0.5F;
	}

	@Override
	public void doRender(EntityChainedSkull entity, double x, double y, double z, float entityYaw, float partialTicks) {

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
					bufferbuilder.begin(7, DefaultVertexFormats.BLOCK);
					GlStateManager.translate((float) (x - (double) blockpos.getX() - 0.5D), (float) (y - (double) blockpos.getY()), (float) (z - (double) blockpos.getZ() - 0.5D));
					BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
					blockrendererdispatcher.getBlockModelRenderer().renderModel(world, blockrendererdispatcher.getModelForState(state), state, blockpos, bufferbuilder, false, MathHelper.getPositionRandom(entity.getPosition()));
					tessellator.draw();
				}
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(x, y + 0.5F, z);
					renderEffects(entity, partialTicks);
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

	private void renderEffects(EntityChainedSkull entity, float partialTicks) {
		if (entity.getTicks() > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder vertexbuffer = tessellator.getBuffer();
			RenderHelper.disableStandardItemLighting();
			float perc = Math.min(((float) entity.getTicks() + partialTicks) / (float) entity.maxTick(), 1);

			Random random = new Random(432L);
			GlStateManager.disableTexture2D();
			GlStateManager.shadeModel(7425);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			GlStateManager.disableAlpha();
			GlStateManager.enableCull();
			GlStateManager.depthMask(false);
			GlStateManager.pushMatrix();

			for (int i = 0; (float) i < (perc + perc * perc) / 2.0F * 60.0F; ++i) {
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F + perc * 90.0F, 0.0F, 0.0F, 1.0F);
				float f2 = random.nextFloat() * 0.1F + 5.0F + (perc * 10.0F);
				float f3 = random.nextFloat() * 0.1F + 1.0F + (perc * 10.0F);
				vertexbuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
				vertexbuffer.pos(0.0D, 0.0D, 0.0D).color(0, 0, 0, (int) (255.0F * (perc))).endVertex();
				vertexbuffer.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 0, 0).endVertex();
				vertexbuffer.pos(0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 0, 0).endVertex();
				vertexbuffer.pos(0.0D, (double) f2, (double) (1.0F * f3)).color(255, 0, 0, 0).endVertex();
				vertexbuffer.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(255, 0, 0, 0).endVertex();
				tessellator.draw();
			}

			GlStateManager.popMatrix();
			GlStateManager.depthMask(true);
			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.shadeModel(7424);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableTexture2D();
			GlStateManager.enableAlpha();
			RenderHelper.enableStandardItemLighting();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityChainedSkull entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}
