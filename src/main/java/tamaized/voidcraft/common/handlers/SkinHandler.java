package tamaized.voidcraft.common.handlers;

import com.google.common.base.MoreObjects;
import com.google.common.io.Resources;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import tamaized.voidcraft.VoidCraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

public class SkinHandler {

	private static final String SESSION_SERVER = "https://sessionserver.mojang.com";
	private static final String nameUrl = "https://api.mojang.com/user/profiles/";
	private static volatile List<UUID> uuidList = new ArrayList<>();
	private static volatile Map<UUID, PlayerSkinInfoWrapper> uuidProfile = new HashMap<>();
	private static volatile Map<String, UUID> uuidNames = new HashMap<>(); // This becomes empty after SkinHandler finishes loading

	private SkinHandler() {
	}

	public static synchronized UUID getUUID(int index) {
		return uuidList.get(index);
	}

	public static synchronized int getSize() {
		return uuidList.size();
	}

	public static synchronized PlayerSkinInfoWrapper getGhostInfo(UUID id) {
		return uuidProfile.get(id);
	}

	public static synchronized boolean isSlimModel(UUID id) {
		PlayerSkinInfoWrapper ghostInfo = getGhostInfo(id);
		return ghostInfo != null && ghostInfo.isSlim();
	}

	public static synchronized ResourceLocation getSkinResource(UUID id) {
		PlayerSkinInfoWrapper ghostInfo = getGhostInfo(id);
		return ghostInfo == null ? DefaultPlayerSkin.getDefaultSkin(id) : ghostInfo.getSkin();
	}

	public static synchronized void run() {
		VoidCraft.instance.logger.info("Running SkinHandler");
		fillNames();
		if (isOnline()) {
			VoidCraft.instance.logger.info("Able to Connect to Mojang Servers, validating names");
			validateNames();
		} else {
			VoidCraft.instance.logger.info("Unable to Connect to Mojang Servers");
		}
		for (Entry<String, UUID> entry : uuidNames.entrySet())
			uuidProfile.put(entry.getValue(), new PlayerSkinInfoWrapper(entry.getKey(), entry.getValue()));
		uuidNames.clear();
	}

	private static void fillNames() {
		if (!ContributorHandler.skinList.isEmpty()) {
			for (Entry<String, UUID> entry : ContributorHandler.skinList.entrySet()) {
				String key = entry.getKey();
				UUID value = entry.getValue();
				uuidNames.put(key, value);
				uuidList.add(value);
			}
		}
	}

	private static void validateNames() {
		VoidCraft.instance.logger.info("Validating Names to UUIDs");
		Map<String, UUID> tempMap = new HashMap<>();
		for (Entry<String, UUID> entry : uuidNames.entrySet()) {
			String name = entry.getKey();
			UUID id = entry.getValue();
			try {
				URL url = new URL(nameUrl + id.toString().replace("-", "") + "/names");
				BufferedReader reader = Resources.asCharSource(url, StandardCharsets.UTF_8).openBufferedStream();
				JsonReader json = new JsonReader(reader);
				json.beginArray();
				while (json.hasNext()) {
					json.beginObject();
					while (json.hasNext()) {
						switch (json.nextName()) {
							case "name":
								String newName = json.nextString();
								if (!name.equals(newName)) {
									VoidCraft.instance.logger.info("Detected Name Change (" + name + ") is now (" + newName + ")");
									name = newName;
								}
								break;
							default:
								json.skipValue();
								break;
						}
					}
					json.endObject();
				}
				json.close();
				tempMap.put(name, id);
				VoidCraft.instance.logger.info("Mapped " + name + " -> " + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		uuidNames.clear();
		uuidNames.putAll(tempMap);
	}

	private static boolean isOnline() {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(SESSION_SERVER).openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static class PlayerSkinInfoWrapper {

		private final GameProfile gameProfile;
		private boolean playerTexturesLoaded = false;
		private String skinType;
		private ResourceLocation skin;

		public PlayerSkinInfoWrapper(String name, UUID uuid) {
			gameProfile = new GameProfile(uuid, name);
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
				FMLCommonHandler.instance().getMinecraftServerInstance().getMinecraftSessionService().fillProfileProperties(gameProfile, false);
			else
				net.minecraft.client.Minecraft.getMinecraft().getSessionService().fillProfileProperties(gameProfile, false);
			loadPlayerTextures(); // Lets preload the skin here so we get the correct model on the first initial spawn

		}

		public final String getName() {
			return gameProfile.getName();
		}

		public final boolean isSlim() {
			loadPlayerTextures();
			return skinType != null && !skinType.equals("default");
		}

		public final ResourceLocation getSkin() {
			loadPlayerTextures();
			return MoreObjects.firstNonNull(skin, DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId()));
		}

		private void loadPlayerTextures() {
			synchronized (this) {
				if (!this.playerTexturesLoaded) {
					this.playerTexturesLoaded = true;
					if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
						MinecraftProfileTexture tex = FMLCommonHandler.instance().getMinecraftServerInstance().getMinecraftSessionService().getTextures(gameProfile, false).get(MinecraftProfileTexture.Type.SKIN);
						if (tex != null) {
							skin = new ResourceLocation("skins/" + tex.getHash());
							skinType = tex.getMetadata("model");
							if (PlayerSkinInfoWrapper.this.skinType == null)
								PlayerSkinInfoWrapper.this.skinType = "default";
						}
					} else {
						net.minecraft.client.Minecraft.getMinecraft().getSkinManager().loadProfileTextures(this.gameProfile, (typeIn, location, profileTexture) -> {
							switch (typeIn) {
								case SKIN:
									PlayerSkinInfoWrapper.this.skin = location;
									PlayerSkinInfoWrapper.this.skinType = profileTexture.getMetadata("model");
									if (PlayerSkinInfoWrapper.this.skinType == null)
										PlayerSkinInfoWrapper.this.skinType = "default";
									break;
								default:
									break;
							}
						}, false);
					}
				}
			}
		}
	}

}
