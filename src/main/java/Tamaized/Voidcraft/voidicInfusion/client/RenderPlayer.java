package Tamaized.Voidcraft.voidicInfusion.client;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import Tamaized.Voidcraft.handlers.CustomElytraHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderPlayer {

	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Pre e) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		EntityPlayer player = e.getEntityPlayer();
		if (player != null) {
			float f1 = 1.0f;
			if (player.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
				IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);
				float f2 = 1.65f - cap.getInfusionPerc();
				f1 = f2 < 0.65f ? 0.65f : f2;
				f1 = f1 > 1.0f ? 1.0f : f1;
			}
			// renderElytra(player, e.getPartialRenderTick());
			renderSheath(e.getRenderer(), player, e.getPartialRenderTick());
			GlStateManager.color(f1, f1, f1, f1);
		}
	}

	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Post e) {
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	private void renderElytra(EntityPlayer player, float partialTick) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{
			if (CustomElytraHandler.isElytraFlying(player)) {
				// rotateCorpse(player, player.ticksExisted + e.getPartialRenderTick(), interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, e.getPartialRenderTick()), e.getPartialRenderTick());
				float f = (float) player.getCapability(CapabilityList.ELYTRAFLYING, null).getElytraTime() + partialTick;
				float zf1 = MathHelper.clamp_float(f * f / 100.0F, 0.0F, 1.0F);
				GlStateManager.rotate(zf1 * (-90.0F - player.rotationPitch), 1.0F, 0.0F, 0.0F);
				Vec3d vec3d = player.getLook(partialTick);
				double d0 = player.motionX * player.motionX + player.motionZ * player.motionZ;
				double d1 = vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord;

				if (d0 > 0.0D && d1 > 0.0D) {
					double d2 = (player.motionX * vec3d.xCoord + player.motionZ * vec3d.zCoord) / (Math.sqrt(d0) * Math.sqrt(d1));
					double d3 = player.motionX * vec3d.zCoord - player.motionZ * vec3d.xCoord;
					GlStateManager.rotate((float) (Math.signum(d3) * Math.acos(d2)) * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
				}
			}
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	private void renderSheath(net.minecraft.client.renderer.entity.RenderPlayer render, EntityLivingBase entity, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		{

			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if (player == null) return;
			float[] colors = null;
			if (entity.getActivePotionEffect(voidCraft.potions.fireSheath) != null) {
				colors = new float[] { 1.0f, 0.85f, 0.0f, 0.85f };
			} else if (entity.getActivePotionEffect(voidCraft.potions.frostSheath) != null) {
				colors = new float[] { 0.0f, 1.0f, 1.0f, 0.85f };
			} else if (entity.getActivePotionEffect(voidCraft.potions.litSheath) != null) {
				colors = new float[] { 1.0f, 1.0f, 1.0f, 0.85f };
			} else if (entity.getActivePotionEffect(voidCraft.potions.acidSheath) != null) {
				colors = new float[] { 0.0f, 1.0f, 0.0f, 0.85f };
			}
			if (colors == null) return;
			GlStateManager.disableLighting();
			TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
			TextureAtlasSprite textureatlassprite = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_0");
			TextureAtlasSprite textureatlassprite1 = texturemap.getAtlasSprite("minecraft:blocks/fire_layer_1");
			GlStateManager.pushMatrix();
			GlStateManager.translate(player.posX - entity.posX, player.posY - entity.posY, player.posZ - entity.posZ);
			float f = entity.width * 1.4F;
			GlStateManager.scale(f, f, f);
			Tessellator tessellator = Tessellator.getInstance();
			VertexBuffer vertexbuffer = tessellator.getBuffer();
			float f1 = 0.5F;
			float f2 = 0.0F;
			float f3 = entity.height / f;
			float f4 = (float) (entity.posY - entity.getEntityBoundingBox().minY);
			// GlStateManager.rotate(-renderer.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, 0.0F, -0.3F + (float) ((int) f3) * 0.02F);
			// GlStateManager.rotate(180, 1, 0, 0);
			// GlStateManager.rotate(45, 0, 1, 0);
			// GlStateManager.translate(0, -2, 0);
			GlStateManager.rotate(render.getRenderManager().playerViewY, 0, 1, 0);
			GlStateManager.color(colors[0], colors[1], colors[2], colors[3]);
			float f5 = 0.0F;
			int i = 0;
			vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

			while (f3 > 0.0F) {
				TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
				// ResourceLocation resource = i % 2 == 0 ? layer0 : layer1;
				render.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				// renderer.bindTexture(resource);
				float f6 = textureatlassprite2.getMinU();
				float f7 = textureatlassprite2.getMinV();
				float f8 = textureatlassprite2.getMaxU();
				float f9 = textureatlassprite2.getMaxV();

				if (i / 2 % 2 == 0) {
					float f10 = f8;
					f8 = f6;
					f6 = f10;
				}

				vertexbuffer.pos((double) (f1 - 0.0F), (double) (0.0F - f4), (double) f5).tex((double) f8, (double) f9).endVertex();
				vertexbuffer.pos((double) (-f1 - 0.0F), (double) (0.0F - f4), (double) f5).tex((double) f6, (double) f9).endVertex();
				vertexbuffer.pos((double) (-f1 - 0.0F), (double) (1.4F - f4), (double) f5).tex((double) f6, (double) f7).endVertex();
				vertexbuffer.pos((double) (f1 - 0.0F), (double) (1.4F - f4), (double) f5).tex((double) f8, (double) f7).endVertex();
				f3 -= 0.45F;
				f4 -= 0.45F;
				f1 *= 0.9F;
				f5 += 0.03F;
				++i;
			}

			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.enableLighting();
		}
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	protected void rotateCorpse(EntityPlayer entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {
		GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0) {
			float f = ((float) entityLiving.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
			f = MathHelper.sqrt_float(f);

			if (f > 1.0F) {
				f = 1.0F;
			}

			GlStateManager.rotate(f * 90F, 0.0F, 0.0F, 1.0F);
		} else {
			String s = TextFormatting.getTextWithoutFormattingCodes(entityLiving.getName());

			if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer) entityLiving).isWearing(EnumPlayerModelParts.CAPE))) {
				GlStateManager.translate(0.0F, entityLiving.height + 0.1F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			}
		}
	}

	protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
		float f;

		for (f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F) {
			;
		}

		while (f >= 180.0F) {
			f -= 360.0F;
		}

		return prevYawOffset + partialTicks * f;
	}

}
