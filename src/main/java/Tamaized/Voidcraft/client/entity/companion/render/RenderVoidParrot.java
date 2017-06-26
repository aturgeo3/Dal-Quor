package Tamaized.Voidcraft.client.entity.companion.render;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.client.entity.companion.model.ModelVoidParrot;
import Tamaized.Voidcraft.common.entity.companion.EntityVoidParrot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderVoidParrot extends RenderLiving<EntityVoidParrot> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(VoidCraft.modid, "textures/entity/voidparrot.png");

	public RenderVoidParrot(RenderManager p_i47375_1_) {
		super(p_i47375_1_, new ModelVoidParrot(), 0.3F);
	}

	@Override
	public void doRender(EntityVoidParrot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.enableBlend();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableBlend();
	}

	@Override
	protected void preRenderCallback(EntityVoidParrot entitylivingbaseIn, float partialTickTime) {
		GlStateManager.color(1, 1, 1, 0.75F);
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVoidParrot entity) {
		return TEXTURE;
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	@Override
	public float handleRotationFloat(EntityVoidParrot livingBase, float partialTicks) {
		return this.getCustomBob(livingBase, partialTicks);
	}

	private float getCustomBob(EntityVoidParrot parrot, float p_192861_2_) {
		float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
		float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
		return (MathHelper.sin(f) + 1.0F) * f1;
	}
}
