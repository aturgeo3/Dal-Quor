package tamaized.voidcraft.client.entity.companion.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import tamaized.voidcraft.client.entity.companion.model.ModelVoidParrot;
import tamaized.voidcraft.client.entity.companion.render.RenderVoidParrot;
import tamaized.voidcraft.common.entity.companion.EntityVoidParrot;

import javax.annotation.Nullable;
import java.util.UUID;

public class LayerVoidParrotShoulder implements LayerRenderer<EntityPlayer> {
	private final RenderManager renderManager;
	protected RenderLivingBase<? extends EntityLivingBase> leftRenderer;
	protected RenderLivingBase<? extends EntityLivingBase> rightRenderer;
	private ModelBase leftModel;
	private ResourceLocation leftResource;
	private UUID leftUniqueId;
	private Class<?> leftEntityClass;
	private ModelBase rightModel;
	private ResourceLocation rightResource;
	private UUID rightUniqueId;
	private Class<?> rightEntityClass;

	public LayerVoidParrotShoulder(RenderManager p_i47370_1_) {
		this.renderManager = p_i47370_1_;
	}

	public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.getLeftShoulderEntity() != null || entitylivingbaseIn.getRightShoulderEntity() != null) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			NBTTagCompound nbttagcompound = entitylivingbaseIn.getLeftShoulderEntity();//ParrotDataManagerHandler.getLeftShoulder(entitylivingbaseIn);

			if (!nbttagcompound.hasNoTags()) {
				LayerVoidParrotShoulder.DataHolder LayerVoidParrotShoulder$dataholder = this.func_192864_a(entitylivingbaseIn, this.leftUniqueId, nbttagcompound, this.leftRenderer, this.leftModel, this.leftResource, this.leftEntityClass, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
				this.leftUniqueId = LayerVoidParrotShoulder$dataholder.entityId;
				this.leftRenderer = LayerVoidParrotShoulder$dataholder.renderer;
				this.leftResource = LayerVoidParrotShoulder$dataholder.textureLocation;
				this.leftModel = LayerVoidParrotShoulder$dataholder.model;
				this.leftEntityClass = LayerVoidParrotShoulder$dataholder.clazz;
			}

			NBTTagCompound nbttagcompound1 = entitylivingbaseIn.getRightShoulderEntity();//ParrotDataManagerHandler.getRightShoulder(entitylivingbaseIn);

			if (!nbttagcompound1.hasNoTags()) {
				LayerVoidParrotShoulder.DataHolder LayerVoidParrotShoulder$dataholder1 = this.func_192864_a(entitylivingbaseIn, this.rightUniqueId, nbttagcompound1, this.rightRenderer, this.rightModel, this.rightResource, this.rightEntityClass, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, false);
				this.rightUniqueId = LayerVoidParrotShoulder$dataholder1.entityId;
				this.rightRenderer = LayerVoidParrotShoulder$dataholder1.renderer;
				this.rightResource = LayerVoidParrotShoulder$dataholder1.textureLocation;
				this.rightModel = LayerVoidParrotShoulder$dataholder1.model;
				this.rightEntityClass = LayerVoidParrotShoulder$dataholder1.clazz;
			}

			GlStateManager.disableRescaleNormal();
		}
	}

	private LayerVoidParrotShoulder.DataHolder func_192864_a(EntityPlayer p_192864_1_, @Nullable UUID p_192864_2_, NBTTagCompound p_192864_3_, RenderLivingBase<? extends EntityLivingBase> p_192864_4_, ModelBase p_192864_5_, ResourceLocation p_192864_6_, Class<?> p_192864_7_, float p_192864_8_, float p_192864_9_, float p_192864_10_, float p_192864_11_, float p_192864_12_, float p_192864_13_, float p_192864_14_, boolean p_192864_15_) {
		if (p_192864_2_ == null || !p_192864_2_.equals(p_192864_3_.getUniqueId("UUID"))) {
			p_192864_2_ = p_192864_3_.getUniqueId("UUID");
			p_192864_7_ = EntityList.getClassFromName(p_192864_3_.getString("id"));

			if (p_192864_7_ == EntityVoidParrot.class) {
				p_192864_4_ = new RenderVoidParrot(this.renderManager);
				p_192864_5_ = new ModelVoidParrot();
				p_192864_6_ = RenderVoidParrot.TEXTURE;
			}
		}
		if (p_192864_6_ == null)
			return new LayerVoidParrotShoulder.DataHolder(p_192864_2_, p_192864_4_, p_192864_5_, p_192864_6_, p_192864_7_);
		p_192864_4_.bindTexture(p_192864_6_);
		GlStateManager.pushMatrix();
		float f = p_192864_1_.isSneaking() ? -1.3F : -1.5F;
		float f1 = p_192864_15_ ? 0.4F : -0.4F;
		GlStateManager.translate(f1, f, 0.0F);

		if (p_192864_7_ == EntityVoidParrot.class) {
			p_192864_11_ = 0.0F;
		}

		p_192864_5_.setLivingAnimations(p_192864_1_, p_192864_8_, p_192864_9_, p_192864_10_);
		p_192864_5_.setRotationAngles(p_192864_8_, p_192864_9_, p_192864_11_, p_192864_12_, p_192864_13_, p_192864_14_, p_192864_1_);

		GlStateManager.enableBlend();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1, 1, 1, 0.75F);
		p_192864_5_.render(p_192864_1_, p_192864_8_, p_192864_9_, p_192864_11_, p_192864_12_, p_192864_13_, p_192864_14_);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.disableBlend();

		GlStateManager.popMatrix();
		return new LayerVoidParrotShoulder.DataHolder(p_192864_2_, p_192864_4_, p_192864_5_, p_192864_6_, p_192864_7_);
	}

	public boolean shouldCombineTextures() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	class DataHolder {
		public UUID entityId;
		public RenderLivingBase<? extends EntityLivingBase> renderer;
		public ModelBase model;
		public ResourceLocation textureLocation;
		public Class<?> clazz;

		public DataHolder(UUID p_i47463_2_, RenderLivingBase<? extends EntityLivingBase> p_i47463_3_, ModelBase p_i47463_4_, ResourceLocation p_i47463_5_, Class<?> p_i47463_6_) {
			this.entityId = p_i47463_2_;
			this.renderer = p_i47463_3_;
			this.model = p_i47463_4_;
			this.textureLocation = p_i47463_5_;
			this.clazz = p_i47463_6_;
		}
	}
}