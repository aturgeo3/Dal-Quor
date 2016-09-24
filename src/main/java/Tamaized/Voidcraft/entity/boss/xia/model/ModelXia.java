package Tamaized.Voidcraft.entity.boss.xia.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;

public class ModelXia extends ModelBase {

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	ModelRenderer overlay_head;
	ModelRenderer overlay_body;
	ModelRenderer overlay_rightarm;
	ModelRenderer overlay_leftarm;
	ModelRenderer overlay_rightleg;
	ModelRenderer overlay_leftleg;

	public ModelXia() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(textureWidth, textureHeight);
		setRotation(head, 0F, 0F, 0F);

		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, 0F, 0F);
		body.setTextureSize(textureWidth, textureHeight);
		setRotation(body, 0F, 0F, 0F);

		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
		rightarm.setRotationPoint(-5F, 2F, 0F);
		rightarm.setTextureSize(textureWidth, textureHeight);
		setRotation(rightarm, 0F, 0F, 0F);

		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
		leftarm.setRotationPoint(5F, 2F, 0F);
		leftarm.setTextureSize(textureWidth, textureHeight);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);

		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 12F, 0F);
		rightleg.setTextureSize(textureWidth, textureHeight);
		setRotation(rightleg, 0F, 0F, 0F);

		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 12F, 0F);
		leftleg.setTextureSize(textureWidth, textureHeight);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);

		overlay_head = new ModelRenderer(this, 32, 0);
		overlay_head.addBox(-4F, -8F, -4F, 8, 8, 8, 0.25F);
		overlay_head.setRotationPoint(0F, 0F, 0F);
		overlay_head.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_head, 0F, 0F, 0F);

		overlay_body = new ModelRenderer(this, 16, 32);
		overlay_body.addBox(-4F, 0F, -2F, 8, 12, 4, 0.25F);
		overlay_body.setRotationPoint(0F, 0F, 0F);
		overlay_body.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_body, 0F, 0F, 0F);

		overlay_rightarm = new ModelRenderer(this, 40, 32);
		overlay_rightarm.addBox(-3F, -2F, -2F, 4, 12, 4, 0.25F);
		overlay_rightarm.setRotationPoint(-5F, 2F, 0F);
		overlay_rightarm.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_rightarm, 0F, 0F, 0F);

		overlay_leftarm = new ModelRenderer(this, 40, 32);
		overlay_leftarm.addBox(-1F, -2F, -2F, 4, 12, 4, 0.25F);
		overlay_leftarm.setRotationPoint(5F, 2F, 0F);
		overlay_leftarm.setTextureSize(textureWidth, textureHeight);
		overlay_leftarm.mirror = true;
		setRotation(overlay_leftarm, 0F, 0F, 0F);

		overlay_rightleg = new ModelRenderer(this, 0, 32);
		overlay_rightleg.addBox(-2F, 0F, -2F, 4, 12, 4, 0.25F);
		overlay_rightleg.setRotationPoint(-2F, 12F, 0F);
		overlay_rightleg.setTextureSize(textureWidth, textureHeight);
		overlay_rightleg.mirror = true;
		setRotation(overlay_rightleg, 0F, 0F, 0F);

		overlay_leftleg = new ModelRenderer(this, 0, 32);
		overlay_leftleg.addBox(-2F, 0F, -2F, 4, 12, 4, 0.25F);
		overlay_leftleg.setRotationPoint(2F, 12F, 0F);
		overlay_leftleg.setTextureSize(textureWidth, textureHeight);
		setRotation(overlay_leftleg, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		setAnimations((EntityBossXia) entity, 1.0f);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		overlay_head.render(f5);
		overlay_body.render(f5);
		overlay_rightarm.render(f5);
		overlay_leftarm.render(f5);
		overlay_rightleg.render(f5);
		overlay_leftleg.render(f5);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float f1, float f2, float partialTickTime) {
		// setAnimations((EntityBossXia) entitylivingbaseIn, partialTickTime);
	}

	private void setAnimations(EntityBossXia entity, float partialTickTime) {
		setRotation(leftarm, Math.toRadians(entity.leftArmPitch), Math.toRadians(entity.leftArmYaw), 0.0F);
		setRotation(rightarm, Math.toRadians(entity.rightArmPitch), Math.toRadians(entity.rightArmYaw), 0.0F);
		setRotation(overlay_leftarm, Math.toRadians(entity.leftArmPitch), Math.toRadians(entity.leftArmYaw), 0.0F);
		setRotation(overlay_rightarm, Math.toRadians(entity.rightArmPitch), Math.toRadians(entity.rightArmYaw), 0.0F);
	}

	private void setRotation(ModelRenderer model, double x, double y, double z) {
		model.rotateAngleX -= x;
		model.rotateAngleY -= y;
		model.rotateAngleZ -= z;
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.overlay_head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.overlay_head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);

		this.body.rotateAngleY = 0.0F;
		this.rightarm.rotationPointZ = 0.0F;
		this.rightarm.rotationPointX = -5.0F;
		this.leftarm.rotationPointZ = 0.0F;
		this.leftarm.rotationPointX = 5.0F;
		this.overlay_body.rotateAngleY = 0.0F;
		this.overlay_rightarm.rotationPointZ = 0.0F;
		this.overlay_rightarm.rotationPointX = -5.0F;
		this.overlay_leftarm.rotationPointZ = 0.0F;
		this.overlay_leftarm.rotationPointX = 5.0F;
		float f = 1.0F;

		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightleg.rotateAngleZ = 0.0F;
		this.leftleg.rotateAngleZ = 0.0F;
		this.overlay_rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.overlay_leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.overlay_rightarm.rotateAngleZ = 0.0F;
		this.overlay_leftarm.rotateAngleZ = 0.0F;
		this.overlay_rightleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
		this.overlay_leftleg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount / f;
		this.overlay_rightleg.rotateAngleY = 0.0F;
		this.overlay_leftleg.rotateAngleY = 0.0F;
		this.overlay_rightleg.rotateAngleZ = 0.0F;
		this.overlay_leftleg.rotateAngleZ = 0.0F;

		this.rightarm.rotateAngleY = 0.0F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.overlay_rightarm.rotateAngleY = 0.0F;
		this.overlay_rightarm.rotateAngleZ = 0.0F;

		this.body.rotateAngleX = 0.0F;
		this.rightleg.rotationPointZ = 0.1F;
		this.leftleg.rotationPointZ = 0.1F;
		this.rightleg.rotationPointY = 12.0F;
		this.leftleg.rotationPointY = 12.0F;
		this.head.rotationPointY = 0.0F;
		this.overlay_body.rotateAngleX = 0.0F;
		this.overlay_rightleg.rotationPointZ = 0.1F;
		this.overlay_leftleg.rotationPointZ = 0.1F;
		this.overlay_rightleg.rotationPointY = 12.0F;
		this.overlay_leftleg.rotationPointY = 12.0F;
		this.overlay_head.rotationPointY = 0.0F;

		this.rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.overlay_rightarm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.overlay_leftarm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.overlay_rightarm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.overlay_leftarm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	}
}
