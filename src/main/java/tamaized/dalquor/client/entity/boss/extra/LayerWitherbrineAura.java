package tamaized.dalquor.client.entity.boss.extra;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityWitherbrine;

public class LayerWitherbrineAura implements LayerRenderer<EntityWitherbrine> {
	private static final ResourceLocation WITHER_ARMOR = new ResourceLocation(DalQuor.modid, "textures/entity/witherbrine/wither_armor.png");
	private final RenderWitherbrine witherRenderer;
	private final ModelWitherbrine witherModel = new ModelWitherbrine(0.5F);

	public LayerWitherbrineAura(RenderWitherbrine witherRendererIn) {
		this.witherRenderer = witherRendererIn;
	}

	public void doRenderLayer(EntityWitherbrine entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.isArmored()) {
			GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
			this.witherRenderer.bindTexture(WITHER_ARMOR);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			float f = (float) entitylivingbaseIn.ticksExisted + partialTicks;
			float f1 = MathHelper.cos(f * 0.02F) * 3.0F;
			float f2 = f * 0.01F;
			GlStateManager.translate(f1, f2, 0.0F);
			GlStateManager.matrixMode(5888);
			GlStateManager.enableBlend();
			float f3 = 0.5F;
			GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
			GlStateManager.disableLighting();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			this.witherModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
			this.witherModel.setModelAttributes(this.witherRenderer.getMainModel());
			this.witherModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.matrixMode(5890);
			GlStateManager.loadIdentity();
			GlStateManager.matrixMode(5888);
			GlStateManager.enableLighting();
			GlStateManager.disableBlend();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}