package Tamaized.Voidcraft.handlers;

import Tamaized.Voidcraft.voidCraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	private Configuration config;

	private int dimensionIdVoid = -2;
	private int dimensionIdXia = -3;

	public ConfigHandler(Configuration c) {
		config = c;
		config.load();
		sync();
	}
	
	public Configuration getConfig(){
		return config;
	}

	public void sync() {
		dimensionIdVoid = config.get(Configuration.CATEGORY_GENERAL, "dimension_id_void", -2).getInt();
		dimensionIdXia = config.get(Configuration.CATEGORY_GENERAL, "dimension_id_xia", -3).getInt();
		if (config.hasChanged()) config.save();
	}

	@SubscribeEvent
	public void configChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(voidCraft.modid)) sync();
	}

	public int getDimensionIDvoid() {
		return dimensionIdVoid;
	}

	public int getDimensionIDxia() {
		return dimensionIdXia;
	}

}
