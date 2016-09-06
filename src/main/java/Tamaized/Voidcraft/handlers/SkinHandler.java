package Tamaized.Voidcraft.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;

import Tamaized.Voidcraft.voidCraft;

import com.google.common.io.Resources;
import com.google.gson.stream.JsonReader;
import com.mojang.authlib.GameProfile;

public class SkinHandler {

	private static final String SESSION_SERVER = "https://sessionserver.mojang.com";
	private static final String url = SESSION_SERVER + "/session/minecraft/profile/";
	private static final String loc = (System.getenv("APPDATA") == null || System.getenv("APPDATA").contains("null")) ? "./.minecraft/" : (System.getenv("APPDATA")) + "/.minecraft/" + voidCraft.modid + "/skins/";

	private Map<PlayerNameAlias, GameProfile> aliasProfile = new HashMap<PlayerNameAlias, GameProfile>();

	@SideOnly(Side.CLIENT)
	private Map<PlayerNameAlias, ResourceLocation> aliasSkin = new HashMap<PlayerNameAlias, ResourceLocation>();

	private static final Map<PlayerNameAlias, UUID> aliasUUID = new HashMap<PlayerNameAlias, UUID>();

	public static enum PlayerNameAlias {
		Tamaized, DireWolf20, Cpw11, Soaryn, Vazkii, Tlovetech, Boni, Azanor, Rorax, Slowpoke101, XCompWiz, Pahimar, iChun, RWTema, FireBall1725, TTFTCUTS
	}

	static {
		aliasUUID.put(PlayerNameAlias.Tamaized, UUID.fromString("16fea09e-314e-4955-88c2-6b552ecf314a"));
		aliasUUID.put(PlayerNameAlias.DireWolf20, UUID.fromString("bbb87dbe-690f-4205-bdc5-72ffb8ebc29d"));
		aliasUUID.put(PlayerNameAlias.Cpw11, UUID.fromString("59af7399-5544-4990-81f1-c8f2263b00e5"));
		aliasUUID.put(PlayerNameAlias.Soaryn, UUID.fromString("4f3a8d1e-33c1-44e7-bce8-e683027c7dac"));
		aliasUUID.put(PlayerNameAlias.Vazkii, UUID.fromString("8c826f34-113b-4238-a173-44639c53b6e6"));
		aliasUUID.put(PlayerNameAlias.Tlovetech, UUID.fromString("c2024e2a-dd76-4bc9-9ea3-b771f18f23b6"));
		aliasUUID.put(PlayerNameAlias.Boni, UUID.fromString("6078f46a-bae3-496b-bbdc-dcc25aca75ba"));
		aliasUUID.put(PlayerNameAlias.Azanor, UUID.fromString("0f95811a-b3b6-4dba-ba03-4adfec7cf5ab"));
		aliasUUID.put(PlayerNameAlias.Rorax, UUID.fromString("e8b46b33-3e17-4b64-8d07-9af116df7d3b"));
		aliasUUID.put(PlayerNameAlias.Slowpoke101, UUID.fromString("d2839efc-727a-4263-97ce-3c73cdee5013"));
		aliasUUID.put(PlayerNameAlias.XCompWiz, UUID.fromString("b97e12ce-dbb1-4c0c-afc8-132b708a9b88"));
		aliasUUID.put(PlayerNameAlias.Pahimar, UUID.fromString("0192723f-b3dc-495a-959f-52c53fa63bff"));
		aliasUUID.put(PlayerNameAlias.iChun, UUID.fromString("0b7509f0-2458-4160-9ce1-2772b9a45ac2"));
		aliasUUID.put(PlayerNameAlias.RWTema, UUID.fromString("72ddaa05-7bbe-4ae2-9892-2c8d90ea0ad8"));
		aliasUUID.put(PlayerNameAlias.FireBall1725, UUID.fromString("e43e9766-f903-48e1-818f-d41bb48d80d5"));
		aliasUUID.put(PlayerNameAlias.TTFTCUTS, UUID.fromString("48a16fc8-bc1f-4e72-84e9-7ec73b7d8ea1"));
	}

	public static UUID getUUID(PlayerNameAlias name) {
		return aliasUUID.get(name);
	}

	/**
	 * This will return null if we were unable to update from the Mojang servers or there was an issue with that specific player alias
	 */
	public GameProfile getGameProfile(PlayerNameAlias name) {
		return aliasProfile.get(name);
	}

	@SideOnly(Side.CLIENT)
	public ResourceLocation getSkinResource(PlayerNameAlias name) {
		return aliasSkin.get(name);
	}

	public void run() {
		voidCraft.logger.info("Running SkinHandler");
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) handleResources();
		if (isOnline()) {
			voidCraft.logger.info("Able to Connect to Mojang Servers, updating skins");
			updateSkins();
		} else {
			voidCraft.logger.info("Unable to Connect to Mojang Servers, using cache");
			cacheSkins();
		}
	}

	@SideOnly(Side.CLIENT)
	private void handleResources() {
		new File(loc).mkdirs();
		net.minecraft.client.resources.FolderResourcePack pack = new net.minecraft.client.resources.FolderResourcePack(new File(loc));
		try {
			Field f = net.minecraft.client.Minecraft.getMinecraft().getClass().getDeclaredField("defaultResourcePacks");
			f.setAccessible(true);
			List<IResourcePack> defaultResourcePacks = (List<IResourcePack>) f.get(net.minecraft.client.Minecraft.getMinecraft());
			defaultResourcePacks.add(pack);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void updateSkins() {
		aliasList: for (PlayerNameAlias alias : PlayerNameAlias.values()) {
			voidCraft.logger.info("Updating alias: " + alias);
			try {
				BufferedReader reader = Resources.asCharSource(new URL(url + ("" + getUUID(alias)).replace("-", "")), StandardCharsets.UTF_8).openBufferedStream();

				String encodedString = null;
				String profileName = null;
				String skinUrl = "";

				JsonReader json = new JsonReader(reader);
				{
					json.beginObject();
					{
						while (json.hasNext()) {
							String key = json.nextName();
							switch (key) {
								case "properties":
									json.beginArray(); // properties
									{
										json.beginObject(); // 0
										{
											while (json.hasNext()) {
												String key2 = json.nextName();
												switch (key2) {
													case "value":
														encodedString = json.nextString();
														break;
													default:
														json.skipValue();
														break;
												}
											}
										}
										json.endObject();
									}
									json.endArray();
									break;
								case "error":
									useCachedAliasResource(alias);
									continue aliasList;
								default:
									json.skipValue();
									break;
							}
						}

					}
					json.endObject();
				}
				json.close();

				if (encodedString == null) {
					useCachedAliasResource(alias);
					continue;
				}

				InputStream is = new ByteArrayInputStream(StringUtils.newStringUtf8(Base64.getDecoder().decode(encodedString)).getBytes());
				reader = new BufferedReader(new InputStreamReader(is));
				json = new JsonReader(reader);
				{
					json.beginObject();
					{
						while (json.hasNext()) {
							String key = json.nextName();
							switch (key) {
								case "profileName":
									profileName = json.nextString();
									break;
								case "textures":
									json.beginObject(); // textures
									{
										while (json.hasNext()) {
											String key2 = json.nextName();
											switch (key2) {
												case "SKIN":
													json.beginObject(); // SKIN
													{
														while (json.hasNext()) {
															String key3 = json.nextName();
															switch (key3) {
																case "url":
																	skinUrl = json.nextString();
																	break;
																default:
																	json.skipValue();
																	break;
															}
														}
													}
													json.endObject();
													break;
												default:
													json.skipValue();
													break;
											}
										}
									}
									json.endObject();
									break;
								default:
									json.skipValue();
									break;
							}
						}
					}
					json.endObject();
				}
				json.close();
				ResourceLocation resource = null;
				if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
					voidCraft.logger.info("Downloading skin: " + skinUrl);
					FileUtils.copyURLToFile(new URL(skinUrl), new File(loc + alias.toString() + ".png"));
					resource = new ResourceLocation(loc + alias.toString());
				}
				updateAlias(alias, profileName, resource);
			} catch (IOException e) {
				voidCraft.logger.info("Request was sent too often or there was an issue with the Mojang servers, using cache for "+alias);
				useCachedAliasResource(alias);
			}
		}
	}
	
	private void cacheSkins(){
		for(PlayerNameAlias alias : PlayerNameAlias.values()){
			useCachedAliasResource(alias);
		}
	}

	private void useCachedAliasResource(PlayerNameAlias alias) {
		voidCraft.logger.info("Using alias cache: " + alias);
		aliasProfile.put(alias, new GameProfile(getUUID(alias), alias.toString()));
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) aliasSkin.put(alias, new ResourceLocation(loc + alias.toString()));
	}

	private void updateAlias(PlayerNameAlias alias, String name, ResourceLocation skin) {
		voidCraft.logger.info("Using updated alias: " + alias);
		aliasProfile.put(alias, new GameProfile(getUUID(alias), name));
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) aliasSkin.put(alias, skin);
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

}
