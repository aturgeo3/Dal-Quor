package Tamaized.Voidcraft.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

@SideOnly(Side.CLIENT)
public class MusicTickerStopper {

	private static Field currentMusic;
	private static Field timeUntilNextMusic;

	public static void stop(int timeTillNextMusic) {
		if (currentMusic == null || timeUntilNextMusic == null) {
			Class<? extends MusicTicker> c = MusicTicker.class;
			currentMusic = ReflectionHelper.findField(c, "field_147678_c", "currentMusic");
			currentMusic.setAccessible(true);
			timeUntilNextMusic = ReflectionHelper.findField(c, "field_147676_d", "timeUntilNextMusic");
			timeUntilNextMusic.setAccessible(true);
		}
		MusicTicker ticker = Minecraft.getMinecraft().getMusicTicker();
		try {
			ISound curr = (ISound) currentMusic.get(ticker);
			if (curr != null) {
				Minecraft.getMinecraft().getSoundHandler().stopSound(curr);
				currentMusic.set(ticker, null);
				timeUntilNextMusic.set(ticker, timeTillNextMusic);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
