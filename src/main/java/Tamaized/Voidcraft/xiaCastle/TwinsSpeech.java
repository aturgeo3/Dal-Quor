package Tamaized.Voidcraft.xiaCastle;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class TwinsSpeech {

	private int index = 0;
	private int tick = 1;
	private int messageTick = 20 * 5;
	private boolean done = false;

	public void reset() {
		index = 0;
		tick = 1;
		messageTick = 20 * 5;
		done = false;
	}

	public void update(List<EntityPlayer> list) {
		if (tick % messageTick == 0) {
			switch (index) {
				case 0:
					sendMessage(list, "Test");
					break;
				default:
					done = true;
					break;
			}
			tick = 0;
			index++;
		}
		tick++;
	}

	private void sendMessage(List<EntityPlayer> list, String message) {
		for (EntityPlayer player : list)
			player.sendMessage(new TextComponentString(message));
	}

	public boolean done() {
		return done;
	}

}
