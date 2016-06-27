package Tamaized.Voidcraft.common.server;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.common.voidCraft;

public class VoidCraftCommonProxy {
	
	public void preInit() {}
	
	public void init() {}

	public void registerRenders() {
		System.out.println("Registering GUI Handler");
		NetworkRegistry.INSTANCE.registerGuiHandler(voidCraft.instance, new GuiHandler());
	}

	public void registerNetwork() {}

	public void registerInventoryRender() {}

}