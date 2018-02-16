package tamaized.dalquor.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import tamaized.dalquor.VoidCraft;

public class VoidCraftLootTables {

	public static final ResourceLocation chest_voidFortress = new ResourceLocation(VoidCraft.modid, "chests/voidfortress");
	public static final ResourceLocation chest_voidCity = new ResourceLocation(VoidCraft.modid, "chests/voidcity");

	public static void postInit() {
		LootTableList.register(chest_voidFortress);
		LootTableList.register(chest_voidCity);
	}

}
