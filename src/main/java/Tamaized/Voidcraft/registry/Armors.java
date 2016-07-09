package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.armors.TamArmor;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.armor.XiaArmor;
import Tamaized.Voidcraft.common.voidCraft;

public class Armors implements ITamRegistry {
	
	public ArrayList<ITamModel> modelList;

	public static TamArmor voidHelmet;
	public static TamArmor voidChest;
	public static TamArmor voidLegs;
	public static TamArmor voidBoots;
	public static TamArmor demonHelmet;
	public static TamArmor demonChest;
	public static TamArmor demonLegs;
	public static TamArmor demonBoots;
	public static XiaArmor xiaChest;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();
		
		modelList.add(voidHelmet = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.HEAD, "void", "voidHelmet"));
		modelList.add(voidChest = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.CHEST, "void", "voidChest"));
		modelList.add(voidLegs = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.LEGS, "void", "voidLegs"));
		modelList.add(voidBoots = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.voidArmor, 0, EntityEquipmentSlot.FEET, "void", "voidBoots"));

		modelList.add(demonHelmet = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.demonArmor, 0, EntityEquipmentSlot.HEAD, "demon", "demonHelmet"));
		modelList.add(demonChest = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.demonArmor, 0, EntityEquipmentSlot.CHEST, "demon", "demonChest"));
		modelList.add(demonLegs = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.demonArmor, 0, EntityEquipmentSlot.LEGS, "demon", "demonLegs"));
		modelList.add(demonBoots = new TamArmor(voidCraft.tabs.tabVoid, voidCraft.materials.demonArmor, 0, EntityEquipmentSlot.FEET, "demon", "demonBoots"));

		modelList.add(xiaChest = new XiaArmor(voidCraft.tabs.tabVoid, voidCraft.materials.xiaArmor, 0, EntityEquipmentSlot.CHEST, "xia", "xiaChest"));
	}

	@Override
	public void init() {
		GameRegistry.addRecipe(new ItemStack(voidHelmet, 1), "XXX", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidChest, 1), "X X", "XXX", "XXX", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidLegs, 1), "XXX", "X X", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidBoots, 1), "X X", "X X", 'X', voidCraft.items.voidcrystal);
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return modelList;
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

}
