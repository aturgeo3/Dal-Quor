package Tamaized.Voidcraft.events.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class DebugEvent {
	
	public static String textL = "";
	public static String textR = "";
	
	@SubscribeEvent
	public void drawDebugText(RenderGameOverlayEvent.Text e){
		
		e.left.add(textL);
		e.right.add(textR);
	}

}
