package Tamaized.Voidcraft.client;

import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.client.VoidCraftClientProxy;
import Tamaized.Voidcraft.events.client.DebugEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderVoidicInfusion {
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Pre e){
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		GlStateManager.color(100f/100f, 100f/100f, 100f/100f, 1.0f);
	}
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Post e){

		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

}
