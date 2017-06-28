package tamaized.voidcraft.common.events.client;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebugEvent {
	
	public static boolean debugMode = false;
	
	public static String textL = "";
	public static String textR = "";
	
	@SubscribeEvent
	public void drawDebugText(RenderGameOverlayEvent.Text e){
		if(!debugMode) return;
		for(String t : textL.split("\n")) e.getLeft().add(t);
		e.getRight().add(textR);
	}

}
