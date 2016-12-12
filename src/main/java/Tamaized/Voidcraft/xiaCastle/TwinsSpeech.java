package Tamaized.Voidcraft.xiaCastle;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicInfusion.IVoidicInfusionCapability;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class TwinsSpeech {

	private SpeechBase speech1 = new Speech1();
	private SpeechBase speech2 = new Speech2();

	private ArrayList<EntityPlayer> list1 = new ArrayList<EntityPlayer>();
	private ArrayList<EntityPlayer> list2 = new ArrayList<EntityPlayer>();

	public void reset() {
		speech1.reset();
		speech2.reset();
	}

	public void update(List<EntityPlayer> list) {
		for (EntityPlayer player : list) {
			IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);
			if (cap != null) {
				int i = cap.getXiaDefeats();
				switch (i) {
					case 1:
						list1.add(player);
						break;
					case 2:
						list2.add(player);
						break;
					default:
						break;
				}
			}
		}
		if (!speech1.done()) speech1.update(list1);
		if (!speech2.done()) speech2.update(list2);
		list1.clear();
		list2.clear();
	}

	public boolean done() {
		return speech1.done() && speech2.done();
	}

	private void sendMessage(List<EntityPlayer> list, String message) {
		for (EntityPlayer player : list)
			player.sendMessage(new TextComponentString(message.replace("[Player]", player.getName())));
	}

	private abstract class SpeechBase {

		protected int index = 0;
		protected int tick = 1;
		protected int messageTick = 20 * 5;
		protected boolean done = false;

		public abstract void update(List<EntityPlayer> list);

		public final void reset() {
			index = 0;
			tick = 1;
			messageTick = 20 * 5;
			done = false;
		}

		public final boolean done() {
			return done;
		}

	}

	private class Speech1 extends SpeechBase {

		@Override
		public void update(List<EntityPlayer> list) {
			if (tick % messageTick == 0) {
				switch (index) {
					case 0:
						sendMessage(list, TextFormatting.AQUA + "Hello. Do you remember us?");
						messageTick = 20 * 4;
						break;
					case 1:
						sendMessage(list, TextFormatting.GREEN + "Believe it or not, we've met before.");
						messageTick = 20 * 4;
						break;
					case 2:
						sendMessage(list, TextFormatting.AQUA + "In fact, we've never left your side.");
						messageTick = 20 * 4;
						break;
					case 3:
						sendMessage(list, TextFormatting.GREEN + "Though, I could imagine that our presence is all too subtle and overbearing for you to even acknowledge our presence.");
						messageTick = 20 * 7;
						break;
					case 4:
						sendMessage(list, TextFormatting.AQUA + "I wouldn't blame you. After all, it's easy to lose sight of reality while living in your dreams.");
						messageTick = 20 * 7;
						break;
					case 5:
						sendMessage(list, TextFormatting.GREEN + "We are the universe. We are everything you think isn't you.");
						messageTick = 20 * 6;
						break;
					case 6:
						sendMessage(list, TextFormatting.AQUA + "We are kind and loving, but Dol is not very humble.");
						messageTick = 20 * 6;
						break;
					case 7:
						sendMessage(list, TextFormatting.GREEN + "Were it not for me you'd use that line every chance you could, Zol.");
						messageTick = 20 * 6;
						break;
					case 8:
						sendMessage(list, TextFormatting.AQUA + "You're not wrong, but let's get back on topic.");
						messageTick = 20 * 5;
						break;
					case 9:
						sendMessage(list, TextFormatting.GREEN + "We are here because you've reached an even higher level, [Player].");
						messageTick = 20 * 6;
						break;
					case 10:
						sendMessage(list, TextFormatting.GREEN + "You’ve overcome great trial and tribulation, so we’ve come to see you, player.");
						messageTick = 20 * 6;
						break;
					case 11:
						sendMessage(list, TextFormatting.GREEN + "To let you know that you played well.");
						messageTick = 20 * 6;
						break;
					case 12:
						sendMessage(list, TextFormatting.AQUA + "But the question remains: Have you reached the highest level?");
						messageTick = 20 * 6;
						break;
					case 13:
						sendMessage(list, TextFormatting.GREEN + "That is something only you hold the answer to.");
						messageTick = 20 * 5;
						break;
					case 14:
						sendMessage(list, TextFormatting.GREEN + "Your potential is only barred by your own willpower.");
						messageTick = 20 * 5;
						break;
					case 15:
						sendMessage(list, TextFormatting.AQUA + "Your presence here is a testament to your character, but there’s not much room for growth here.");
						messageTick = 20 * 7;
						break;
					case 16:
						sendMessage(list, TextFormatting.AQUA + "Perhaps you’ll find the answers you seek by leaping into the abyss below.");
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

	}

	private class Speech2 extends SpeechBase {

		@Override
		public void update(List<EntityPlayer> list) {

		}

	}

}
