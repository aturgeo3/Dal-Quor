package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.armor.BasicVoidArmor;
import Tamaized.Voidcraft.armor.XiaArmor;
import Tamaized.Voidcraft.common.voidCraft;

public class Armors extends RegistryBase {
	
	public static ArrayList<Item> list = new ArrayList<Item>();

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
		voidHelmet = new BasicVoidArmor(voidCraft.materials.voidArmor, 0, 0, "void", "voidHelmet");
		voidChest = new BasicVoidArmor(voidCraft.materials.voidArmor, 0, 1, "void", "voidChest");
		voidLegs = new BasicVoidArmor(voidCraft.materials.voidArmor, 0, 2, "void", "voidLegs");
		voidBoots = new BasicVoidArmor(voidCraft.materials.voidArmor, 0, 3, "void", "voidBoots");

		demonHelmet = new BasicVoidArmor(voidCraft.materials.demonArmor, 0, 0, "demon", "demonHelmet");
		demonChest = new BasicVoidArmor(voidCraft.materials.demonArmor, 0, 1, "demon", "demonChest");
		demonLegs = new BasicVoidArmor(voidCraft.materials.demonArmor, 0, 2, "demon", "demonLegs");
		demonBoots = new BasicVoidArmor(voidCraft.materials.demonArmor, 0, 3, "demon", "demonBoots");

		xiaChest = new XiaArmor(voidCraft.materials.xiaArmor, 0, 1, "xia", "xiaChest");
		
		list.add(voidHelmet);
		list.add(voidChest);
		list.add(voidLegs);
		list.add(voidBoots);
		list.add(demonHelmet);
		list.add(demonChest);
		list.add(demonLegs);
		list.add(demonBoots);
		list.add(xiaChest);
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for(Item i : list){
			renderItem.getItemModelMesher().register(i, 0, new Tamaized.Voidcraft.common.client.ScrewModelResourceLocation("armors/", ((IBasicVoid)i).getName(), "inventory"));
		}
	}

}
