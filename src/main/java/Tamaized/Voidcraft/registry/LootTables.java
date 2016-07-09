package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.common.voidCraft;

public class LootTables implements ITamRegistry {

	public static final ResourceLocation chest_voidFortress = new ResourceLocation(voidCraft.modid + ":chests/voidFortress");

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
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

}
