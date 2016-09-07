package Tamaized.Voidcraft.mobs.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBiped.ArmPose;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.mobs.entity.EntityGhostPlayerBase;

@SideOnly(Side.CLIENT)
public class RenderGhostPlayer<T extends EntityGhostPlayerBase> extends RenderLiving<T> {

	private final boolean playerModel;

	private enum DirectionState {
		POS, NEG, STILL
	}

	public RenderGhostPlayer(ModelBiped model) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.5F);
		playerModel = model instanceof ModelPlayer;
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(T entity, double x, double y, double z, float yaw, float partialTicks) {
		if (playerModel) setModelVisibilities(entity);
		GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		ModelBiped model = (ModelBiped) getMainModel();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
		if (entity.isRunning()) {
			model.leftArmPose = ArmPose.BOW_AND_ARROW;
			DirectionState stateX = DirectionState.STILL;
			DirectionState stateZ = DirectionState.STILL;
			switch ((int) entity.renderYawOffset+180) {
				case 0:
					stateZ = DirectionState.NEG;
					break;
				case 90:
					stateX = DirectionState.POS;
					break;
				case 180:
					stateZ = DirectionState.POS;
					break;
				case 270:
					stateX = DirectionState.NEG;
					break;
				default:
					break;
			}
			entity.worldObj.spawnParticle(EnumParticleTypes.PORTAL, entity.getPosition().getX()+0.5, entity.getPosition().getY()+0.5, entity.getPosition().getZ()+0.5, stateX == DirectionState.NEG ? -7.5D : stateX == DirectionState.POS ? 7.5D : 0.0D, 9.5D, stateZ == DirectionState.NEG ? -7.5D : stateZ == DirectionState.POS ? 7.5D : 0.0D);
		}
		super.doRender(entity, x, y, z, yaw, partialTicks);
		model.leftArmPose = ArmPose.EMPTY;
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
		GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
		this.renderLabel(entity, x, y, z);
	}

	protected void renderLabel(T yourentityLiving, double par2, double par4, double par6) {
		// if you're less then 32 blocks x-y-z away from this entity,it will display the entity's name.
		int distanceToEntity = 32;
		this.renderLivingLabel(yourentityLiving, yourentityLiving.getDisplayName().getFormattedText(), par2, par4, par6, distanceToEntity);
		par4 += (double) ((float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * par6);
	}

	private void setModelVisibilities(T entity) {
		ModelPlayer modelplayer = (ModelPlayer) this.getMainModel();

		modelplayer.setInvisible(true);
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
		float f = 0.9375F;
		GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return voidCraft.skinHandler.getSkinResource(entity.getAlias());
	}
}