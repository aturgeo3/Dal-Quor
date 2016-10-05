package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.potion.PotionVoidicInfusionImmunity;
import net.minecraft.potion.Potion;

public class VoidCraftPotions implements ITamRegistry {

	private ArrayList<ITamModel> modelList;

	public static Potion voidicInfusionImmunity;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();
		voidicInfusionImmunity = new PotionVoidicInfusionImmunity("voidicInfusionImmunity");
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

}
