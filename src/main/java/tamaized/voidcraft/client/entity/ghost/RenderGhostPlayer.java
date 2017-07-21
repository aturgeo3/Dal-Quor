package tamaized.voidcraft.client.entity.ghost;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.entity.ghost.EntityGhostPlayerBase;
import tamaized.voidcraft.common.handlers.SkinHandler;

@SideOnly(Side.CLIENT)
public class RenderGhostPlayer<T extends EntityGhostPlayerBase> extends RenderLiving<T> {

	private static final ResourceLocation TEXTURE_RUNE = new ResourceLocation(VoidCraft.modid, "textures/entity/rune.png");

	public RenderGhostPlayer(RenderManager manager, boolean slim) {
		super(manager, new ModelPlayer(0.0F, slim), 0.5F);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
		this.setModelVisibilities(entity);
		GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
		ModelBiped model = (ModelBiped) getMainModel();
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(x, y + 2F, z);
			if (entity.hasRuneState())
				renderRuneState(entity, model);
		}
		GlStateManager.popMatrix();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
		super.doRender(entity, x, y, z, yaw, partialTicks); // Entity texture is bound here, we're free to bind whatever we want before this and not care
		model.leftArmPose = ArmPose.EMPTY;
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
		this.renderLabel(entity, x, y, z);
	}

	private void renderRuneState(T entity, ModelBiped model) {
		model.leftArmPose = ArmPose.BOW_AND_ARROW;
		GlStateManager.color((119F * entity.getRuneStatePerc()) / 255F, 0.0F, entity.getRuneStatePerc(), 1.0F);
		GlStateManager.pushMatrix();
		{
			GlStateManager.rotate(-entity.rotationYawHead + 180, 0, 1, 0);
			GlStateManager.rotate(30, 1, 0, 0);
			GlStateManager.rotate(entity.getRuneRotationForRender(), 0, 0, 1);
			GlStateManager.disableLighting();
			GlStateManager.disableCull();
			drawRune();
			GlStateManager.enableCull();
			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
	}

	private void drawRune() {
		bindTexture(TEXTURE_RUNE);
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tess.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		double x = 2.0D;
		double y = 2.205D;
		double z = -1.0D;
		vertexbuffer.pos(x - 4.0D, y, z).tex(0.0D, 1.0D).endVertex();
		vertexbuffer.pos(x, y, z).tex(1.0D, 1.0D).endVertex();
		vertexbuffer.pos(x, y - 4.0D, z).tex(1.0D, 0.0D).endVertex();
		vertexbuffer.pos(x - 4.0D, y - 4.0D, z).tex(0.0D, 0.0D).endVertex();
		tess.draw();
	}

	private void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		int distanceToEntity = 32;
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
	}

	private void setModelVisibilities(T entity) {
		ModelPlayer modelplayer = (ModelPlayer) this.getMainModel();

		modelplayer.setVisible(true);
		modelplayer.bipedHead.showModel = true;
		modelplayer.bipedHeadwear.showModel = true;// entity.isWearing(EnumPlayerModelParts.HAT);
		modelplayer.bipedBodyWear.showModel = true;// entity.isWearing(EnumPlayerModelParts.JACKET);
		modelplayer.bipedLeftLegwear.showModel = true;// entity.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
		modelplayer.bipedRightLegwear.showModel = true;// entity.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
		modelplayer.bipedLeftArmwear.showModel = true;// entity.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
		modelplayer.bipedRightArmwear.showModel = true;// entity.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
		modelplayer.isSneak = entity.isSneaking();
		ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
		ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;

		if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
			modelplayer.rightArmPose = modelbiped$armpose;
			modelplayer.leftArmPose = modelbiped$armpose1;
		} else {
			modelplayer.rightArmPose = modelbiped$armpose1;
			modelplayer.leftArmPose = modelbiped$armpose;
		}
	}

	@Override
	public void transformHeldFull3DItemLayer() {
		GlStateManager.translate(0.0F, 0.1875F, 0.0F);
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	@Override
	protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
		float scale = 0.9375F;
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return SkinHandler.getSkinResource(entity.getUUID());
	}

	private enum DirectionState {
		POS, NEG, STILL
	}
}