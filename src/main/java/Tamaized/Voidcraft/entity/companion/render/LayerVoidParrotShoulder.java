package Tamaized.Voidcraft.entity.companion.render;

import Tamaized.Voidcraft.entity.companion.EntityVoidParrot;
import Tamaized.Voidcraft.entity.companion.model.ModelVoidParrot;
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

import javax.annotation.Nullable;
import java.util.UUID;

public class LayerVoidParrotShoulder implements LayerRenderer<EntityPlayer> {
	private final RenderManager field_192867_c;
	protected RenderLivingBase<? extends EntityLivingBase> field_192865_a;
	protected RenderLivingBase<? extends EntityLivingBase> field_192866_b;
	private ModelBase field_192868_d;
	private ResourceLocation field_192869_e;
	private UUID field_192870_f;
	private Class<?> field_192871_g;
	private ModelBase field_192872_h;
	private ResourceLocation field_192873_i;
	private UUID field_192874_j;
	private Class<?> field_192875_k;

	public LayerVoidParrotShoulder(RenderManager p_i47370_1_) {
		this.field_192867_c = p_i47370_1_;
	}

	public void doRenderLayer(EntityPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.func_192023_dk() != null || entitylivingbaseIn.func_192025_dl() != null) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			NBTTagCompound nbttagcompound = entitylivingbaseIn.func_192023_dk();//ParrotDataManagerHandler.getLeftShoulder(entitylivingbaseIn);

			if (!nbttagcompound.hasNoTags()) {
				LayerVoidParrotShoulder.DataHolder LayerVoidParrotShoulder$dataholder = this.func_192864_a(entitylivingbaseIn, this.field_192870_f, nbttagcompound, this.field_192865_a, this.field_192868_d, this.field_192869_e, this.field_192871_g, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
				this.field_192870_f = LayerVoidParrotShoulder$dataholder.field_192882_a;
				this.field_192865_a = LayerVoidParrotShoulder$dataholder.field_192883_b;
				this.field_192869_e = LayerVoidParrotShoulder$dataholder.field_192885_d;
				this.field_192868_d = LayerVoidParrotShoulder$dataholder.field_192884_c;
				this.field_192871_g = LayerVoidParrotShoulder$dataholder.field_192886_e;
			}

			NBTTagCompound nbttagcompound1 = entitylivingbaseIn.func_192025_dl();//ParrotDataManagerHandler.getRightShoulder(entitylivingbaseIn);

			if (!nbttagcompound1.hasNoTags()) {
				LayerVoidParrotShoulder.DataHolder LayerVoidParrotShoulder$dataholder1 = this.func_192864_a(entitylivingbaseIn, this.field_192874_j, nbttagcompound1, this.field_192866_b, this.field_192872_h, this.field_192873_i, this.field_192875_k, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, false);
				this.field_192874_j = LayerVoidParrotShoulder$dataholder1.field_192882_a;
				this.field_192866_b = LayerVoidParrotShoulder$dataholder1.field_192883_b;
				this.field_192873_i = LayerVoidParrotShoulder$dataholder1.field_192885_d;
				this.field_192872_h = LayerVoidParrotShoulder$dataholder1.field_192884_c;
				this.field_192875_k = LayerVoidParrotShoulder$dataholder1.field_192886_e;
			}

			GlStateManager.disableRescaleNormal();
		}
	}

	private LayerVoidParrotShoulder.DataHolder func_192864_a(EntityPlayer p_192864_1_, @Nullable UUID p_192864_2_, NBTTagCompound p_192864_3_, RenderLivingBase<? extends EntityLivingBase> p_192864_4_, ModelBase p_192864_5_, ResourceLocation p_192864_6_, Class<?> p_192864_7_, float p_192864_8_, float p_192864_9_, float p_192864_10_, float p_192864_11_, float p_192864_12_, float p_192864_13_, float p_192864_14_, boolean p_192864_15_) {
		if (p_192864_2_ == null || !p_192864_2_.equals(p_192864_3_.getUniqueId("UUID"))) {
			p_192864_2_ = p_192864_3_.getUniqueId("UUID");
			p_192864_7_ = EntityList.func_192839_a(p_192864_3_.getString("id"));

			if (p_192864_7_ == EntityVoidParrot.class) {
				p_192864_4_ = new RenderVoidParrot(this.field_192867_c);
				p_192864_5_ = new ModelVoidParrot();
				p_192864_6_ = RenderVoidParrot.TEXTURE;
			}
		}
		if(p_192864_6_ == null) return new LayerVoidParrotShoulder.DataHolder(p_192864_2_, p_192864_4_, p_192864_5_, p_192864_6_, p_192864_7_);
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
		public UUID field_192882_a;
		public RenderLivingBase<? extends EntityLivingBase> field_192883_b;
		public ModelBase field_192884_c;
		public ResourceLocation field_192885_d;
		public Class<?> field_192886_e;

		public DataHolder(UUID p_i47463_2_, RenderLivingBase<? extends EntityLivingBase> p_i47463_3_, ModelBase p_i47463_4_, ResourceLocation p_i47463_5_, Class<?> p_i47463_6_) {
			this.field_192882_a = p_i47463_2_;
			this.field_192883_b = p_i47463_3_;
			this.field_192884_c = p_i47463_4_;
			this.field_192885_d = p_i47463_5_;
			this.field_192886_e = p_i47463_6_;
		}
	}
}