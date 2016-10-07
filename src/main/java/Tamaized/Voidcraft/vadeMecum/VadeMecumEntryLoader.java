package Tamaized.Voidcraft.vadeMecum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.stream.JsonReader;

import Tamaized.Voidcraft.voidCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class VadeMecumEntryLoader {

	public static VadeMecumEntry loadEntry(ResourceLocation loc) {
		voidCraft.logger.info("Loading Entry: " + loc);
		VadeMecumEntry entry = null;
		ArrayList<VadeMecumPage> pages = new ArrayList<VadeMecumPage>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream(), StandardCharsets.UTF_8));
			JsonReader json = new JsonReader(reader);
			json.beginObject();
			while (json.hasNext()) {
				switch (json.nextName()) {
					case "Pages":
						json.beginArray();
						while (json.hasNext()) {
							voidCraft.logger.info("Page Found");
							pages.add(new VadeMecumPage(json));
						}
						json.endArray();
						break;
					default:
						json.skipValue();
						break;
				}
			}
			json.endObject();
			json.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		entry = new VadeMecumEntry(pages);
		return entry;
	}

}
