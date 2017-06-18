package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.armors.TamArmor;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.armor.XiaArmor;
import net.minecraft.inventory.EntityEquipmentSlot;

import java.util.ArrayList;

public class VoidCraftArmors implements ITamRegistry {

	public ArrayList<ITamModel> modelList;

	public static TamArmor voidHelmet;
	public static TamArmor voidChest;
	public static TamArmor voidLegs;
	public static TamArmor voidBoots;
	public static XiaArmor xiaHelmet;
	public static XiaArmor xiaChest;
	public static XiaArmor xiaLegs;
	public static XiaArmor xiaBoots;
	// public static ArmorCustomElytra elytra;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		modelList.add(voidHelmet = new TamArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidArmor, 0, EntityEquipmentSlot.HEAD, "void", "voidhelmet"));
		modelList.add(voidChest = new TamArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidArmor, 0, EntityEquipmentSlot.CHEST, "void", "voidchest"));
		modelList.add(voidLegs = new TamArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidArmor, 0, EntityEquipmentSlot.LEGS, "void", "voidlegs"));
		modelList.add(voidBoots = new TamArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.voidArmor, 0, EntityEquipmentSlot.FEET, "void", "voidboots"));

		modelList.add(xiaHelmet = new XiaArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.HEAD, "xia", "xiahelmet"));
		modelList.add(xiaChest = new XiaArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.CHEST, "xia", "xiachest"));
		modelList.add(xiaLegs = new XiaArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.LEGS, "xia", "xialegs"));
		modelList.add(xiaBoots = new XiaArmor(VoidCraft.tabs.tabVoid, VoidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.FEET, "xia", "xiaboots"));

		// modelList.add(elytra = new ArmorCustomElytra(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.CHEST, "void", "voidElytra"));
	}

	@Override
	public void init() {
		/*VoidCraft.addShapedRecipe(new ItemStack(voidHelmet, 1), 3, 2,
				
				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal
				
				);
		VoidCraft.addShapedRecipe(new ItemStack(voidChest, 1), 3, 3,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal
				
				);
		VoidCraft.addShapedRecipe(new ItemStack(voidLegs, 1), 3, 3,
				
				VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal
				
				);
		VoidCraft.addShapedRecipe(new ItemStack(voidBoots, 1), 3, 2,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal,
				
				VoidCraft.items.voidcrystal,  ItemStack.EMPTY, VoidCraft.items.voidcrystal
				
				);*/

		// GameRegistry.addShapelessRecipe(new ItemStack(elytra, 1), voidChest, Items.ELYTRA);
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
		return VoidCraft.modid;
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
