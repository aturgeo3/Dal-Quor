package Tamaized.Voidcraft.mobs.entity.boss.herobrine.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.mobs.entity.boss.herobrine.EntityHerobrineCreeper;
import Tamaized.Voidcraft.mobs.entity.boss.herobrine.render.layer.LayerHerobrineCreeperCharge;

public class RenderHerobrineCreeper extends RenderLiving<EntityHerobrineCreeper> {
	private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");

	public RenderHerobrineCreeper() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelCreeper(), 0.5F);
		this.addLayer(new LayerHerobrineCreeperCharge(this));
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	protected void preRenderCallback(EntityHerobrineCreeper entitylivingbaseIn, float partialTickTime) {
		float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);
		float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp_float(f, 0.0F, 1.0F);
		f = f * f;
		f = f * f;
		float f2 = (1.0F + f * 0.4F) * f1;
		float f3 = (1.0F + f * 0.1F) / f1;
		GlStateManager.scale(f2, f3, f2);
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntityHerobrineCreeper entity, double x, double y, double z, float entityYaw, float partialTicks) {
    	GlStateManager.pushAttrib();
    	GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(0.0f, 0.0f, 0.0f, 0.5f);
		//GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.disableBlend();
    	GlStateManager.popMatrix();
    	GlStateManager.popAttrib();
	}

	/**
	 * Gets an RGBA int color multiplier to apply.
	 */
	protected int getColorMultiplier(EntityHerobrineCreeper entitylivingbaseIn, float lightBrightness, float partialTickTime) {
		float f = entitylivingbaseIn.getCreeperFlashIntensity(partialTickTime);

		if ((int) (f * 10.0F) % 2 == 0) {
			return 0;
		} else {
			int i = (int) (f * 0.2F * 255.0F);
			i = MathHelper.clamp_int(i, 0, 255);
			return i << 24 | 822083583;
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityHerobrineCreeper entity) {
		return CREEPER_TEXTURES;
	}
}