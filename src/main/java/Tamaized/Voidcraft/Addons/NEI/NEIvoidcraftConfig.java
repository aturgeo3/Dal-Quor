package Tamaized.Voidcraft.Addons.NEI;

import Tamaized.Voidcraft.GUI.client.voidInfuserGUI;
import Tamaized.Voidcraft.GUI.client.voidMaceratorGUI;
import Tamaized.Voidcraft.common.voidCraft;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIvoidcraftConfig implements IConfigureNEI{ //loadConfig will only be called when NEI is installed.

	@Override
	public void loadConfig() {
		voidCraft.logger.info("Adding NEI support to VoidCraft");
		
		API.registerRecipeHandler(new VoidInfuserHandler());
		API.registerUsageHandler(new VoidInfuserHandler());
		API.setGuiOffset(voidInfuserGUI.class, 0, 0);
		
		API.registerRecipeHandler(new VoidMaceratorHandler());
		API.registerUsageHandler(new VoidMaceratorHandler());
		API.setGuiOffset(voidMaceratorGUI.class, 0, 0);
	}

	@Override
	public String getName() {
		return "VoidCraft";
	}

	@Override
	public String getVersion() {
		return voidCraft.getVersion();
	}

}
