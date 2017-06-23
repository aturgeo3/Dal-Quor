package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.VoidCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class VoidCraftLootTables {

	public static final ResourceLocation chest_voidFortress = new ResourceLocation(VoidCraft.modid, "chests/voidfortress");
	public static final ResourceLocation chest_voidCity = new ResourceLocation(VoidCraft.modid, "chests/voidcity");

	public static void postInit() {
		LootTableList.register(chest_voidFortress);
		LootTableList.register(chest_voidCity);
	}

}
