package Tamaized.Voidcraft.registry;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTables extends RegistryBase {
	
	public static final ResourceLocation chest_voidFortress = new ResourceLocation(voidCraft.modid+":chests/voidFortress");

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postInit() {
		LootTableList.register(chest_voidFortress);
	}

	@Override
	public void setupRender() {
		// TODO Auto-generated method stub

	}

}
