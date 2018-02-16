package tamaized.dalquor.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.capabilities.voidicInfusion.IVoidicInfusionCapability;

public class ModelVoidSpikes extends ModelBase {

	public ModelRenderer armRight;
	public ModelRenderer body;
	public ModelRenderer armLeft;
	public ModelRenderer rightArmTop;
	public ModelRenderer rightArmBottom;
	public ModelRenderer rightSideTop;
	public ModelRenderer rightSideBottom;
	public ModelRenderer bodyLeft;
	public ModelRenderer bodyRight;
	public ModelRenderer leftArmTop;
	public ModelRenderer leftArmBottom;
	public ModelRenderer leftSideTop;
	public ModelRenderer leftSideBottom;

	public ModelRenderer bipedHead;
	public ModelBiped.ArmPose leftArmPose;
	public ModelBiped.ArmPose rightArmPose;
	public boolean isSneak;

	public ModelVoidSpikes(ModelBiped parent) {
		textureWidth = 46;
		textureHeight = 16;

		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0f);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

		rightSideTop = new ModelRenderer(this, 0, 0);
		rightSideTop.setRotationPoint(-1.5F, -1.5F, -0.5F);
		rightSideTop.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(rightSideTop, -0.8726646259971648F, -1.5707963267948966F, 0.0F);
		armLeft = new ModelRenderer(this, 22, 0);
		armLeft.setRotationPoint(5.0F, 2.0F, -0.0F);
		armLeft.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

		setRotateAngle(armLeft, 0.0F, 0.0F, -0.10000736613927509F);
		rightArmBottom = new ModelRenderer(this, 0, 0);
		rightArmBottom.setRotationPoint(-1.5F, 2.5F, 1.0F);
		rightArmBottom.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(rightArmBottom, -0.8726646259971648F, 0.0F, 0.0F);
		rightSideBottom = new ModelRenderer(this, 0, 0);
		rightSideBottom.setRotationPoint(-1.5F, 2.5F, -0.5F);
		rightSideBottom.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(rightSideBottom, -0.8726646259971648F, -1.5707963267948966F, 0.0F);
		rightArmTop = new ModelRenderer(this, 0, 0);
		rightArmTop.setRotationPoint(-1.5F, -1.5F, 1.0F);
		rightArmTop.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(rightArmTop, -0.8726646259971648F, 0.0F, 0.0F);
		leftSideTop = new ModelRenderer(this, 0, 0);
		leftSideTop.setRotationPoint(1.5F, -1.5F, 0.5F);
		leftSideTop.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(leftSideTop, -0.8726646259971648F, 1.5707963267948966F, 0.0F);
		leftArmBottom = new ModelRenderer(this, 0, 0);
		leftArmBottom.setRotationPoint(0.5F, 2.5F, 1.0F);
		leftArmBottom.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(leftArmBottom, -0.8726646259971648F, 0.0F, 0.0F);
		leftArmTop = new ModelRenderer(this, 0, 0);
		leftArmTop.setRotationPoint(0.5F, -1.5F, 1.0F);
		leftArmTop.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(leftArmTop, -0.8726646259971648F, 0.0F, 0.0F);
		bodyRight = new ModelRenderer(this, 0, 0);
		bodyRight.setRotationPoint(-2.5F, 2.5F, 1.0F);
		bodyRight.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(bodyRight, -0.8726646259971648F, 0.0F, 0.0F);
		leftSideBottom = new ModelRenderer(this, 0, 0);
		leftSideBottom.setRotationPoint(1.5F, 2.5F, 0.5F);
		leftSideBottom.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(leftSideBottom, -0.8726646259971648F, 1.5707963267948966F, 0.0F);
		body = new ModelRenderer(this, 22, 0);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		bodyLeft = new ModelRenderer(this, 0, 0);
		bodyLeft.setRotationPoint(1.5F, 2.5F, 1.0F);
		bodyLeft.addBox(0.0F, 0.0F, 0.0F, 1, 1, 10, 0.0F);

		setRotateAngle(bodyLeft, -0.8726646259971648F, 0.0F, 0.0F);
		armRight = new ModelRenderer(this, 22, 0);
		armRight.setRotationPoint(-5.0F, 2.0F, 0.0F);
		armRight.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

		setRotateAngle(armRight, 0.0F, 0.0F, 0.10000736613927509F);
		armRight.addChild(rightSideTop);
		armRight.addChild(rightArmBottom);
		armRight.addChild(rightSideBottom);
		armRight.addChild(rightArmTop);
		armLeft.addChild(leftSideTop);
		armLeft.addChild(leftArmBottom);
		armLeft.addChild(leftArmTop);
		body.addChild(bodyRight);
		armLeft.addChild(leftSideBottom);
		body.addChild(bodyLeft);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		IVoidicInfusionCapability infusionCap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
		if (infusionCap == null)
			return;
		IVadeMecumCapability vadeMecumCap = entity.getCapability(CapabilityList.VADEMECUM, null);
		float perc = Math.min(1F, (infusionCap.getInfusionPerc() + (vadeMecumCap != null && vadeMecumCap.hasPassive(IVadeMecumCapability.Passive.Flight) ? 0.5F : 0.0F)));

		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(0, 0.05F, 0);
			GlStateManager.scale(perc, perc, perc);
			body.render(f5);
			GlStateManager.pushMatrix();
			GlStateManager.translate((1 - perc) + (perc > 0.75F ? ((1F - ((perc - 0.75F) / 0.25F)) * -0.15F) : -0.15F), 0, 0);
			armLeft.render(f5);
			GlStateManager.popMatrix();
			GlStateManager.translate((perc - 1) + (perc > 0.75F ? ((1F - ((perc - 0.75F) / 0.25F)) * 0.15F) : 0.15F), 0, 0);
			armRight.render(f5);
		}
		GlStateManager.popMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

		this.body.rotateAngleY = 0.0F;
		this.armRight.rotationPointZ = 0.0F;
		this.armRight.rotationPointX = -5.0F;
		this.armLeft.rotationPointZ = 0.0F;
		this.armLeft.rotationPointX = 5.0F;
		float f = 1.0F;

		this.armRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.armLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.armRight.rotateAngleZ = 0.0F;
		this.armLeft.rotateAngleZ = 0.0F;

		if (this.isRiding) {
			this.armRight.rotateAngleX += -((float) Math.PI / 5F);
			this.armLeft.rotateAngleX += -((float) Math.PI / 5F);
		}

		this.armRight.rotateAngleY = 0.0F;
		this.armRight.rotateAngleZ = 0.0F;

		switch (this.leftArmPose) {
			case EMPTY:
				this.armLeft.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.armLeft.rotateAngleX = this.armLeft.rotateAngleX * 0.5F - 0.9424779F;
				this.armLeft.rotateAngleY = 0.5235988F;
				break;
			case ITEM:
				this.armLeft.rotateAngleX = this.armLeft.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.armLeft.rotateAngleY = 0.0F;
			default:
				break;
		}

		switch (this.rightArmPose) {
			case EMPTY:
				this.armRight.rotateAngleY = 0.0F;
				break;
			case BLOCK:
				this.armRight.rotateAngleX = this.armRight.rotateAngleX * 0.5F - 0.9424779F;
				this.armRight.rotateAngleY = -0.5235988F;
				break;
			case ITEM:
				this.armRight.rotateAngleX = this.armRight.rotateAngleX * 0.5F - ((float) Math.PI / 10F);
				this.armRight.rotateAngleY = 0.0F;
			default:
				break;
		}

		if (this.swingProgress > 0.0F) {
			EnumHandSide enumhandside = this.getMainHand(entityIn);
			ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
			this.getArmForSide(enumhandside.opposite());
			float f1 = this.swingProgress;
			this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float) Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT) {
				this.body.rotateAngleY *= -1.0F;
			}

			this.armRight.rotationPointZ = MathHelper.sin(this.body.rotateAngleY) * 5.0F;
			this.armRight.rotationPointX = -MathHelper.cos(this.body.rotateAngleY) * 5.0F;
			this.armLeft.rotationPointZ = -MathHelper.sin(this.body.rotateAngleY) * 5.0F;
			this.armLeft.rotationPointX = MathHelper.cos(this.body.rotateAngleY) * 5.0F;
			this.armRight.rotateAngleY += this.body.rotateAngleY;
			this.armLeft.rotateAngleY += this.body.rotateAngleY;
			this.armLeft.rotateAngleX += this.body.rotateAngleY;
			f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float) Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float) Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float) ((double) modelrenderer.rotateAngleX - ((double) f2 * 1.2D + (double) f3));
			modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float) Math.PI) * -0.4F;
		}

		if (this.isSneak) {
			this.body.rotateAngleX = 0.5F;
			this.armRight.rotateAngleX += 0.4F;
			this.armLeft.rotateAngleX += 0.4F;
			this.bipedHead.rotationPointY = 1.0F;
		} else {
			this.body.rotateAngleX = 0.0F;
			this.bipedHead.rotationPointY = 0.0F;
		}

		this.armRight.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.armLeft.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.armRight.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.armLeft.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.armRight.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
			this.armLeft.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
			this.armRight.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.armLeft.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		} else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW) {
			this.armRight.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
			this.armLeft.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
			this.armRight.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.armLeft.rotateAngleX = -((float) Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}
	}

	@Override
	public void setModelAttributes(ModelBase model) {
		super.setModelAttributes(model);

		if (model instanceof ModelBiped) {
			ModelBiped modelbiped = (ModelBiped) model;
			this.leftArmPose = modelbiped.leftArmPose;
			this.rightArmPose = modelbiped.rightArmPose;
			this.isSneak = modelbiped.isSneak;
		}
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		this.getArmForSide(side).postRender(scale);
	}

	protected ModelRenderer getArmForSide(EnumHandSide side) {
		return side == EnumHandSide.LEFT ? this.armLeft : this.armRight;
	}

	protected EnumHandSide getMainHand(Entity entityIn) {
		return entityIn instanceof EntityLivingBase ? ((EntityLivingBase) entityIn).getPrimaryHand() : EnumHandSide.RIGHT;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
	}

}
