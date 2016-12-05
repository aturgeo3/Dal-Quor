package Tamaized.Voidcraft.entity.boss.xia.finalphase.render.layer;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityDragonXia;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.render.RenderDragonXia;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerEnderDragonXiaEyes implements LayerRenderer<EntityDragonXia> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(voidCraft.modid, "textures/entity/dragon/dragon_eyes.png");
	private final RenderDragonXia dragonRenderer;

	public LayerEnderDragonXiaEyes(RenderDragonXia dragonRendererIn) {
		this.dragonRenderer = dragonRendererIn;
	}

	@Override
	public void doRenderLayer(EntityDragonXia entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.dragonRenderer.bindTexture(TEXTURE);
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		GlStateManager.disableLighting();
		GlStateManager.depthFunc(514);
		int i = 61680;
		int j = 61680;
		int k = 0;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
		GlStateManager.enableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.dragonRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.dragonRenderer.setLightmap(entitylivingbaseIn, partialTicks);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.depthFunc(515);
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}