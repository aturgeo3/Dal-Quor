package Tamaized.Voidcraft.events.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.handlers.PortalDataHandler;

public class OverlayEvent {
	
	@SubscribeEvent
	public void InGameOverlay(RenderGameOverlayEvent e){
		Minecraft mc = Minecraft.getMinecraft();
		EntityClientPlayerMP player = mc.thePlayer;
		
		if(voidCraft.instance.VoidTickEvent.data.get(player.getGameProfile().getId()) != null){
			if(e.type == e.type.PORTAL){
				ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
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
	        	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			}
			
		}
	}

}
