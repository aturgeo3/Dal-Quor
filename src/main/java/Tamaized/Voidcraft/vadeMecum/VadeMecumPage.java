package Tamaized.Voidcraft.vadeMecum;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class VadeMecumPage {

	private String title;

	private VadeMecumBodyObject body;

	public VadeMecumPage(JsonReader json) throws IOException {
		json.beginObject();
		while (json.hasNext()) {
			String key = json.nextName();
			switch (key) {
				case "title":
					title = json.nextString();
					break;
				case "body":
					body = new VadeMecumBodyObject(json);
					break;
				default:
					json.skipValue();
					break;
			}
		}
		json.endObject();
	}
	
	public String getTitle(){
		return title;
	}
	
	public VadeMecumBodyObject getBody(){
		return body;
	}

}
