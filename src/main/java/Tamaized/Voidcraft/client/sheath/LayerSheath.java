package Tamaized.Voidcraft.client.sheath;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class LayerSheath implements LayerRenderer {

	private static final ResourceLocation layer0 = new ResourceLocation(voidCraft.modid, "textures/layer/fire_layer_0.png");
	private static final ResourceLocation layer1 = new ResourceLocation(voidCraft.modid, "textures/layer/fire_layer_1.png");

	private final RenderPlayer renderer;

	public LayerSheath(RenderPlayer playerRenderer) {
		renderer = playerRenderer;
	}

	@Override
	public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
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
		//GlStateManager.rotate(-renderer.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, 0.0F, -0.3F + (float) ((int) f3) * 0.02F);
		//GlStateManager.rotate(180, 1, 0, 0);
		//GlStateManager.rotate(45, 0, 1, 0);
		//GlStateManager.translate(0, -2, 0);
		GlStateManager.rotate(renderer.getRenderManager().playerViewY, 0, 1, 0);
		GlStateManager.color(colors[0], colors[1], colors[2], colors[3]);
		float f5 = 0.0F;
		int i = 0;
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

		while (f3 > 0.0F) {
			TextureAtlasSprite textureatlassprite2 = i % 2 == 0 ? textureatlassprite : textureatlassprite1;
			// ResourceLocation resource = i % 2 == 0 ? layer0 : layer1;
			renderer.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
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

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
