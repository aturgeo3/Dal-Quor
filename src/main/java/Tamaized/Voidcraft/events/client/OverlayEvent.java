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
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.handlers.PortalDataHandler;

public class OverlayEvent {
	
	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayerSP player = mc.thePlayer;
		
		if(voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()) != null){
			if(e.type == e.type.PORTAL){
				float j = voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).tick;
				int type = voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).type;
		        IBlockState b = type == PortalDataHandler.PORTAL_VOID ? voidCraft.blocks.blockPortalVoid.getDefaultState() : type == PortalDataHandler.PORTAL_XIA ? voidCraft.blocks.blockPortalXia.getDefaultState() : null;
		        if(b == null) return;
				ScaledResolution scaledRes = new ScaledResolution(mc);
				GlStateManager.disableAlpha();
		        GlStateManager.disableDepth();
		        GlStateManager.depthMask(false);
		        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		        GlStateManager.color(1.0F, 1.0F, 1.0F, j);
		        mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		        TextureAtlasSprite textureatlassprite = mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(b);
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
				
				/*ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
				int k = scaledresolution.getScaledWidth();
				int l = scaledresolution.getScaledHeight();
				float j = voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).tick;
			
				GL11.glDisable(GL11.GL_ALPHA_TEST);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL11.glDepthMask(false);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, j);
				IIcon iicon = 
						voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).type == PortalDataHandler.PORTAL_VOID ?
							voidCraft.blocks.blockPortalVoid.getBlockTextureFromSide(1) :
						voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()).type == PortalDataHandler.PORTAL_XIA ?
							voidCraft.blocks.blockPortalXia.getBlockTextureFromSide(1) :
							null;
				if(iicon != null){
					mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
					float f1 = iicon.getMinU();
					float f2 = iicon.getMinV();
					float f3 = iicon.getMaxU();
	        		float f4 = iicon.getMaxV();
	        		Tessellator tessellator = Tessellator.instance;
	        		tessellator.startDrawingQuads();
	        		tessellator.addVertexWithUV(0.0D, (double)l, -90.0D, (double)f1, (double)f4);
	        		tessellator.addVertexWithUV((double)k, (double)l, -90.0D, (double)f3, (double)f4);
	        		tessellator.addVertexWithUV((double)k, 0.0D, -90.0D, (double)f3, (double)f2);
	        		tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)f1, (double)f2);
	        		tessellator.draw();
				}
	        	GL11.glDepthMask(true);
	        	GL11.glEnable(GL11.GL_DEPTH_TEST);
	        	GL11.glEnable(GL11.GL_ALPHA_TEST);
	        	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);*/
			}
			
		}
	}

}
