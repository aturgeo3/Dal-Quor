package Tamaized.Voidcraft.vadeMecum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.gson.stream.JsonReader;

public class VadeMecumBodyObject {

	private String rawText = "";
	public static final String craftingEntry = "<crafting>";
	public static final String newLine = "<newline>";
	
	private IVadeMecumCraftingEntry crafting;
	
	public VadeMecumBodyObject(JsonReader json) throws IOException {
		json.beginObject();
		while (json.hasNext()) {
			String key = json.nextName();
			switch (key) {
				case "text":
					rawText = rawText.concat(json.nextString().concat(newLine));
					break;
				case "craft_normal":
					crafting = new VadeMecumCraftingEntryNormal(json);
					rawText = rawText.concat(craftingEntry+newLine);
					break;
				default:
					json.skipValue();
					break;
			}
		}
		json.endObject();
	}
	
	public String getRawText(){
		return rawText;
	}
	
	public String[] getFormattedText(){
		return rawText.split(newLine);
	}
	
	public IVadeMecumCraftingEntry getCraftingEntry(){
		return crafting;
	}

}
