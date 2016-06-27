package Tamaized.Voidcraft.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import Tamaized.Voidcraft.common.voidCraft;


public class ClientRenderTicker {
	
	public static int tick = 0;
	
	@SubscribeEvent
	public void update(TickEvent.RenderTickEvent e){
		if(e.phase == e.phase.END && e.type == e.type.RENDER) tick++;
	}

}
