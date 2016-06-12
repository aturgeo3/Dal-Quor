package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.tools.BasicVoidItemAxe;
import Tamaized.Voidcraft.tools.BasicVoidItemHoe;
import Tamaized.Voidcraft.tools.BasicVoidItemPickaxe;
import Tamaized.Voidcraft.tools.BasicVoidItemSpade;
import Tamaized.Voidcraft.tools.BasicVoidItemSword;
import Tamaized.Voidcraft.tools.arch.ArchSword;
import Tamaized.Voidcraft.tools.chain.ChainSword;
import Tamaized.Voidcraft.tools.demon.DemonSword;
import Tamaized.Voidcraft.tools.molten.MoltenSword;
import Tamaized.Voidcraft.tools.spectre.AngelicSword;
import Tamaized.Voidcraft.tools.spectre.SpectreAxe;
import Tamaized.Voidcraft.tools.spectre.SpectrePickaxe;

public class Tools extends RegistryBase {
	
	public static ArrayList<Item> list = new ArrayList<Item>();
	
	public static Item voidPickaxe;
	public static Item voidSpade;
	public static Item voidAxe;
	public static Item voidSword;
	public static Item voidHoe;
	public static Item angelicSword;
	public static Item chainSword;
	public static Item moltenSword;
	public static Item archSword;
	public static Item demonSword;
	public static Item spectrePickaxe;
	public static Item spectreAxe;

	@Override
	public void preInit() {
		// Tools
		// void
		voidPickaxe = new BasicVoidItemPickaxe(voidCraft.materials.voidTools, "voidPickaxe").setCreativeTab(voidCraft.tabs.tabVoid);
		voidSpade = new BasicVoidItemSpade(voidCraft.materials.voidTools, "voidShovel").setCreativeTab(voidCraft.tabs.tabVoid);
		voidAxe = new BasicVoidItemAxe(voidCraft.materials.voidTools, "voidAxe").setCreativeTab(voidCraft.tabs.tabVoid);
		voidSword = new BasicVoidItemSword(voidCraft.materials.voidTools, "voidSword").setCreativeTab(voidCraft.tabs.tabVoid);
		voidHoe = new BasicVoidItemHoe(voidCraft.materials.voidTools, "voidHoe").setCreativeTab(voidCraft.tabs.tabVoid);

		// spectre
		angelicSword = new AngelicSword(voidCraft.materials.spectreTools, "angelicSword").setCreativeTab(voidCraft.tabs.tabVoid);
		spectrePickaxe = new SpectrePickaxe(voidCraft.materials.spectreTools, "spectrePickaxe").setCreativeTab(voidCraft.tabs.tabVoid);
		spectreAxe = new SpectreAxe(voidCraft.materials.spectreTools, "spectreAxe").setCreativeTab(voidCraft.tabs.tabVoid);
		// chain
		chainSword = new ChainSword(voidCraft.materials.chainTools, "chainSword").setCreativeTab(voidCraft.tabs.tabVoid);
		// molten
		moltenSword = new MoltenSword(voidCraft.materials.MoltenTools, "moltenSword").setCreativeTab(voidCraft.tabs.tabVoid);
		// arch
		archSword = new ArchSword(voidCraft.materials.ArchTools, "archSword").setCreativeTab(voidCraft.tabs.tabVoid);
		// demon
		demonSword = new DemonSword(voidCraft.materials.DemonTools, "demonSword").setCreativeTab(voidCraft.tabs.tabVoid);

		list.add(voidPickaxe);
		list.add(voidSpade);
		list.add(voidAxe);
		list.add(voidSword);
		list.add(voidHoe);
		list.add(angelicSword);
		list.add(spectrePickaxe);
		list.add(spectreAxe);
		list.add(chainSword);
		list.add(moltenSword);
		list.add(archSword);
		list.add(demonSword);
	}

	@Override
	public void init() {

		GameRegistry.addRecipe(new ItemStack(voidPickaxe, 1), "XXX", " S ", " S ", 'X', voidCraft.items.voidcrystal, 'S', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(voidAxe, 1), " XX", " SX", " S ", 'X', voidCraft.items.voidcrystal, 'S', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(voidSpade, 1), " X ", " S ", " S ", 'X', voidCraft.items.voidcrystal, 'S', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(voidHoe, 1), " XX", " S ", " S ", 'X', voidCraft.items.voidcrystal, 'S', Items.STICK);
		GameRegistry.addRecipe(new ItemStack(voidSword, 1), " X ", " X ", " S ", 'X', voidCraft.items.voidcrystal, 'S', Items.STICK);
		// +spectre
		GameRegistry.addRecipe(new ItemStack(spectrePickaxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidPickaxe, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectreAxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidAxe, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(angelicSword, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidSword, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		// +molten and beyond
		GameRegistry.addRecipe(new ItemStack(moltenSword), "XXX", "XSX", "XXX", 'X', voidCraft.items.MoltenvoidChain, 'S', new ItemStack(chainSword, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(chainSword), "XXX", "XSX", "XXX", 'X', voidCraft.items.voidChain, 'S', new ItemStack(voidSword, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(archSword), "SSS", "XYZ", "SSS", 'S', voidCraft.items.MoltenvoidChain, 'X', new ItemStack(chainSword, 1, voidCraft.WILDCARD_VALUE), 'Y', new ItemStack(angelicSword, 1, voidCraft.WILDCARD_VALUE), 'Z', new ItemStack(moltenSword, 1, voidCraft.WILDCARD_VALUE));
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
			renderItem.getItemModelMesher().register(i, 0, new Tamaized.Voidcraft.common.client.ScrewModelResourceLocation("tools/", ((IBasicVoid)i).getName(), "inventory"));
		}
	}

}
