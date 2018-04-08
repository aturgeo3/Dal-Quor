package tamaized.dalquor.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import tamaized.dalquor.DalQuor;

public class ModLootTables {

	public static final ResourceLocation chest_voidFortress = new ResourceLocation(DalQuor.modid, "chests/voidfortress");
	public static final ResourceLocation chest_voidCity = new ResourceLocation(DalQuor.modid, "chests/voidcity");

	public static void postInit() {
		LootTableList.register(chest_voidFortress);
		LootTableList.register(chest_voidCity);
	}

}
