package tamaized.dalquor.common.sound;

import java.util.HashMap;
import java.util.Map;

public class VanillaRecordLengths {

	private static final Map<String, Integer> times = new HashMap<String, Integer>();

	static {
		times.put("13", 178);
		times.put("cat", 185);
		times.put("blocks", 345);
		times.put("chirp", 185);
		times.put("far", 174);
		times.put("mall", 197);
		times.put("mellohi", 96);
		times.put("stal", 150);
		times.put("strad", 188);
		times.put("ward", 251);
		times.put("11", 71);
		times.put("wait", 238);
	}

	public static int getLength(String key) {
		return times.get(key);
	}

}
