package Tamaized.Voidcraft.events.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.handlers.PortalDataHandler;

public class OverlayEvent {
	
	private static final String texture_void = (voidCraft.modid+":blocks/blockPortalVoid.png");
	private static final String texture_xia = (voidCraft.modid+":blocks/blockPortalXia.png");
	
	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		if(voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()) != null){
			if(e.type == e.type.PORTAL){
				float j = voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).tick;
				int type = voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).type;
				ScaledResolution scaledRes = new ScaledResolution(mc);
				GlStateManager.disableAlpha();
		        GlStateManager.disableDepth();
		        GlStateManager.depthMask(false);
		        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		        GlStateManager.color(1.0F, 1.0F, 1.0F, j);
		        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		        
		        TextureAtlasSprite textureatlassprite = mc.getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getTextureMap().getAtlasSprite(type == PortalDataHandler.PORTAL_XIA ?  texture_xia : texture_void);
		        float f = textureatlassprite.getMinU();
		        float f1 = textureatlassprite.getMinV();
		        float f2 = textureatlassprite.getMaxU();
		        float f3 = textureatlassprite.getMaxV();
		        
		        Tessellator tessellator = Tessellator.getInstance();
		        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		        worldrenderer.pos(0.0D, (double)scaledRes.getScaledHeight(), -90.0D).tex((double)f, (double)f3).endVertex();
		        worldrenderer.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0D).tex((double)f2, (double)f3).endVertex();
		        worldrenderer.pos((double)scaledRes.getScaledWidth(), 0.0D, -90.0D).tex((double)f2, (double)f1).endVertex();
		        worldrenderer.pos(0.0D, 0.0D, -90.0D).tex((double)f, (double)f1).endVertex();
		        tessellator.draw();
		        GlStateManager.depthMask(true);
		        GlStateManager.enableDepth();
		        GlStateManager.enableAlpha();
		        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
	}
}
