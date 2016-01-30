package Tamaized.Voidcraft.registry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.armor.DemonArmor;
import Tamaized.Voidcraft.armor.VoidArmor;
import Tamaized.Voidcraft.armor.XiaArmor;
import Tamaized.Voidcraft.common.voidCraft;

public class Armors extends RegistryBase {

	public static Item voidHelmet;
	public static Item voidChest;
	public static Item voidLegs;
	public static Item voidBoots;
	public static Item demonHelmet;
	public static Item demonChest;
	public static Item demonLegs;
	public static Item demonBoots;
	public static Item xiaChest;

	@Override
	public void preInit() {
		voidHelmet = new VoidArmor(voidCraft.materials.voidArmor, 0, 0, "void").setUnlocalizedName("voidHelmet");
		voidChest = new VoidArmor(voidCraft.materials.voidArmor, 0, 1, "void").setUnlocalizedName("voidChest");
		voidLegs = new VoidArmor(voidCraft.materials.voidArmor, 0, 2, "void").setUnlocalizedName("voidLegs");
		voidBoots = new VoidArmor(voidCraft.materials.voidArmor, 0, 3, "void").setUnlocalizedName("voidBoots");

		demonHelmet = new DemonArmor(voidCraft.materials.demonArmor, 0, 0, "demon").setUnlocalizedName("demonHelmet");
		demonChest = new DemonArmor(voidCraft.materials.demonArmor, 0, 1, "demon").setUnlocalizedName("demonChest");
		demonLegs = new DemonArmor(voidCraft.materials.demonArmor, 0, 2, "demon").setUnlocalizedName("demonLegs");
		demonBoots = new DemonArmor(voidCraft.materials.demonArmor, 0, 3, "demon").setUnlocalizedName("demonBoots");

		xiaChest = new XiaArmor(voidCraft.materials.xiaArmor, 0, 1, "xia").setUnlocalizedName("xiaChest");
	}

	@Override
	public void init() {
		GameRegistry.registerItem(voidHelmet, voidHelmet.getUnlocalizedName());
		GameRegistry.registerItem(voidChest, voidChest.getUnlocalizedName());
		GameRegistry.registerItem(voidLegs, voidLegs.getUnlocalizedName());
		GameRegistry.registerItem(voidBoots, voidBoots.getUnlocalizedName());
		GameRegistry.registerItem(demonHelmet, demonHelmet.getUnlocalizedName());
		GameRegistry.registerItem(demonChest, demonChest.getUnlocalizedName());
		GameRegistry.registerItem(demonLegs, demonLegs.getUnlocalizedName());
		GameRegistry.registerItem(demonBoots, demonBoots.getUnlocalizedName());
		GameRegistry.registerItem(xiaChest, xiaChest.getUnlocalizedName());

		GameRegistry.addRecipe(new ItemStack(voidHelmet, 1), "XXX", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidChest, 1), "X X", "XXX", "XXX", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidLegs, 1), "XXX", "X X", "X X", 'X', voidCraft.items.voidcrystal);
		GameRegistry.addRecipe(new ItemStack(voidBoots, 1), "X X", "X X", 'X', voidCraft.items.voidcrystal);
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
