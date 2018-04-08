package tamaized.dalquor.common.handlers;

import net.minecraft.item.EnumDyeColor;
import tamaized.dalquor.DalQuor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class ContributorHandler {

	private static final String URL_ELEMENTALCOLOR = "https://raw.githubusercontent.com/Tamaized/Dal-Quor/1.12/contributors/companion.properties";
	private static final String URL_SKINLIST = "https://raw.githubusercontent.com/Tamaized/Dal-Quor/1.12/contributors/ghost.properties";

	public static volatile Map<UUID, EnumDyeColor> elementalColor = new HashMap<>();
	public static volatile Map<String, UUID> skinList = new HashMap<>();

	private static boolean started = false;

	public static void start() {
		if (!started) {
			DalQuor.instance.logger.info("Starting Contributor Handler");
			started = true;
			new ThreadContributor();
		}
	}

	public static void loadElementalColors(Properties props) {
		elementalColor.clear();
		for (String s : props.stringPropertyNames()) {
			UUID key = UUID.fromString(s);
			String value = props.getProperty(s);
			try {
				int i = Integer.parseInt(value);
				if (i < 0 || i >= EnumDyeColor.values().length)
					throw new NumberFormatException();
				DalQuor.instance.logger.info(key + " -> " + i);
				elementalColor.put(key, EnumDyeColor.values()[i]);
			} catch (NumberFormatException e) {
				DalQuor.instance.logger.error("Error loading Elemental Color: " + value);
			}
		}
	}

	public static void loadSkinList(Properties props) {
		skinList.clear();
		for (String key : props.stringPropertyNames()) {
			UUID value = UUID.fromString(props.getProperty(key));
			DalQuor.instance.logger.info(key + " -> " + value);
			skinList.put(key, value);
		}
	}

	private static class ThreadContributor extends Thread {

		public ThreadContributor() {
			setName("DalQuor Contributor Loader");
			setDaemon(true);
			start();
		}

		@Override
		public void run() {
			try {
				{
					DalQuor.instance.logger.info("Loading Elemental Color Properties");
					URL url = new URL(URL_ELEMENTALCOLOR);
					Properties props = new Properties();
					InputStreamReader reader = new InputStreamReader(url.openStream());
					props.load(reader);
					loadElementalColors(props);
				}
				{
					DalQuor.instance.logger.info("Loading Skin List Properties");
					URL url = new URL(URL_SKINLIST);
					Properties props = new Properties();
					InputStreamReader reader = new InputStreamReader(url.openStream());
					props.load(reader);
					loadSkinList(props);

				}
			} catch (IOException e) {
				DalQuor.instance.logger.error("Could not load contributors");
			}
			SkinHandler.run();
		}

	}

}
