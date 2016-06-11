package Tamaized.Voidcraft.mobs.entity.boss.bar;

import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BossBarOverlay {
	
	@SubscribeEvent
	public void Render(RenderGameOverlayEvent.Post e){
		if(e.getType() != ElementType.BOSSHEALTH) return;
		
		Minecraft mc = Minecraft.getMinecraft();
		Profiler profiler = mc.mcProfiler;
		
		profiler.endStartSection("bossBar");
		RenderBossHeathBar.render(e.getResolution());
		profiler.endSection();
	}

}
