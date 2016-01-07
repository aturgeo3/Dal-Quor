package Tamaized.Voidcraft.common.server;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidCraftCommonProxy {

	public void registerRenderInformation() {}

	public void registerTiles() {}

	public void registerBlocks() {}

	public void registerItems() {}

	public void registerRenders() {}

	public void registerMISC() {
		System.out.println("Registering GUI Handler");
		NetworkRegistry.INSTANCE.registerGuiHandler(voidCraft.instance, new GuiHandler());
	}

	public void registerNetwork() {}

	public void registerAchievements() {}

}