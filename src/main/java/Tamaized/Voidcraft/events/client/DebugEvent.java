package Tamaized.Voidcraft.events.client;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebugEvent {
	
	public static boolean debugMode = false;
	
	public static String textL = "";
	public static String textR = "";
	
	@SubscribeEvent
	public void drawDebugText(RenderGameOverlayEvent.Text e){
		if(!debugMode) return;
		e.getLeft().add(textL);
		e.getRight().add(textR);
	}

}
