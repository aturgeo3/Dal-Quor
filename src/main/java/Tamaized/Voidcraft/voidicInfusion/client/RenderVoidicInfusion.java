package Tamaized.Voidcraft.voidicInfusion.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.IVoidicInfusionCapability;

public class RenderVoidicInfusion {
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Pre e){
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		Entity newEntity = Minecraft.getMinecraft().theWorld.getEntityByID(e.getEntityPlayer().getEntityId());
		float f1 = 1.0f;
		if(newEntity != null && newEntity.hasCapability(CapabilityList.VOIDICINFUSION, null)){
			IVoidicInfusionCapability cap = newEntity.getCapability(CapabilityList.VOIDICINFUSION, null);
			float f2 = 1.65f - cap.getInfusionPerc();
			f1 = f2 < 0.65f ? 0.65f : f2;
			f1 = f1 > 1.0f ? 1.0f : f1;
		}
		GlStateManager.color(f1, f1, f1, f1);
	}
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Post e){

		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

}
