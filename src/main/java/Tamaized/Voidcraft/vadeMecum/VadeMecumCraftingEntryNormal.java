package Tamaized.Voidcraft.vadeMecum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.stream.JsonReader;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VadeMecumCraftingEntryNormal implements IVadeMecumCraftingEntry {

	private Item topL;
	private Item topC;
	private Item topR;

	private Item centerL;
	private Item centerC;
	private Item centerR;

	private Item botL;
	private Item botC;
	private Item botR;
	
	private ItemStack result;

	public VadeMecumCraftingEntryNormal(JsonReader json) throws IOException {
		json.beginObject();
		Map<String, Item> index = new HashMap<String, Item>();
		String encodeTop = "   ";
		String encodeCenter = "   ";
		String encodeBot = "   ";
		String resultItem = "minecraft:apple";
		int resultAmount = 1;
		while(json.hasNext()){
			String key = json.nextName();
			switch (key) {
				case "result":
					resultItem = json.nextString();
					break;
				case "resultAmount":
					resultAmount = json.nextInt();
					break;
				case "top":
					encodeTop = json.nextString();
					break;
				case "center":
					encodeCenter = json.nextString();
					break;
				case "bottom":
					encodeBot = json.nextString();
					break;
				case "define":
					json.beginObject();
					while (json.hasNext()) {
						index.put(json.nextName(), Item.getByNameOrId(json.nextString()));
					}
					json.endObject();
					break;
				default:
					json.skipValue();
					break;
			}
		}
		topL = index.get(encodeTop.substring(0, 1));
		topC = index.get(encodeTop.substring(1, 2));
		topR = index.get(encodeTop.substring(2, 3));
		centerL = index.get(encodeCenter.substring(0, 1));
		centerC = index.get(encodeCenter.substring(1, 2));
		centerR = index.get(encodeCenter.substring(2, 3));
		botL = index.get(encodeBot.substring(0, 1));
		botC = index.get(encodeBot.substring(1, 2));
		botR = index.get(encodeBot.substring(2, 3));
		result = new ItemStack(Item.getByNameOrId(resultItem), resultAmount);
		json.endObject();
	}

	@Override
	public void render() {

	}

}
