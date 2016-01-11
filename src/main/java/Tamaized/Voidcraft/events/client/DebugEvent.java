package Tamaized.Voidcraft.events.client;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebugEvent {
	
	public static String textL = "";
	public static String textR = "";
	
	@SubscribeEvent
	public void drawDebugText(RenderGameOverlayEvent.Text e){
		
		e.left.add(textL);
		e.right.add(textR);
	}

}
