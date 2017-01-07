package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.tools.TamAxe;
import Tamaized.TamModized.tools.TamHoe;
import Tamaized.TamModized.tools.TamPickaxe;
import Tamaized.TamModized.tools.TamSpade;
import Tamaized.TamModized.tools.TamSword;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.starforge.tools.StarForgeAxe;
import Tamaized.Voidcraft.starforge.tools.StarForgePickaxe;
import Tamaized.Voidcraft.starforge.tools.StarForgeShovel;
import Tamaized.Voidcraft.starforge.tools.StarForgeSword;
import Tamaized.Voidcraft.tools.VoidHoe;
import Tamaized.Voidcraft.tools.arch.ArchSword;
import Tamaized.Voidcraft.tools.chain.ChainSword;
import Tamaized.Voidcraft.tools.demon.DemonSword;
import Tamaized.Voidcraft.tools.molten.MoltenSword;
import Tamaized.Voidcraft.tools.spectre.AngelicSword;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VoidCraftTools implements ITamRegistry {

	public static ArrayList<ITamModel> modelList;

	public static TamPickaxe voidPickaxe;
	public static TamSpade voidSpade;
	public static TamAxe voidAxe;
	public static TamSword voidSword;
	public static TamHoe voidHoe;
	public static AngelicSword angelicSword;
	public static ChainSword chainSword;
	public static MoltenSword moltenSword;
	public static ArchSword archSword;
	public static DemonSword demonSword;
	public static TamPickaxe spectrePickaxe;
	public static TamAxe spectreAxe;
	public static TamSpade spectreSpade;
	public static TamHoe spectreHoe;

	public static StarForgeSword starforgedSword;
	public static StarForgePickaxe starforgedPickaxe;
	public static StarForgeAxe starforgedAxe;
	public static StarForgeShovel starforgedSpade;

	@Override
	public void preInit() {
		modelList = new ArrayList<ITamModel>();

		// Swords: 3+dmg; Axe: dmg; Pick: 1+dmg; Spade: 1.5+dmg;
		// Axe: -3; Pick: -2.8; Spade: -3;
		// Tools
		// void
		modelList.add(voidPickaxe = new TamPickaxe(voidCraft.tabs.tabVoid, voidCraft.materials.voidTools, "voidPickaxe"));
		modelList.add(voidSpade = new TamSpade(voidCraft.tabs.tabVoid, voidCraft.materials.voidTools, "voidShovel"));
		modelList.add(voidAxe = new TamAxe(voidCraft.tabs.tabVoid, voidCraft.materials.voidTools, "voidAxe"));
		modelList.add(voidSword = new TamSword(voidCraft.tabs.tabVoid, voidCraft.materials.voidTools, "voidSword"));
		modelList.add(voidHoe = new VoidHoe(voidCraft.tabs.tabVoid, voidCraft.materials.voidTools, "voidHoe"));

		// spectre
		modelList.add(angelicSword = new AngelicSword(voidCraft.tabs.tabVoid, voidCraft.materials.spectreTools, "angelicSword"));
		modelList.add(spectrePickaxe = new TamPickaxe(voidCraft.tabs.tabVoid, voidCraft.materials.spectreTools, "spectrePickaxe"));
		modelList.add(spectreAxe = new TamAxe(voidCraft.tabs.tabVoid, voidCraft.materials.spectreTools, "spectreAxe"));
		modelList.add(spectreSpade = new TamSpade(voidCraft.tabs.tabVoid, voidCraft.materials.spectreTools, "spectreSpade"));
		modelList.add(spectreHoe = new VoidHoe(voidCraft.tabs.tabVoid, voidCraft.materials.spectreTools, "spectreHoe"));
		// chain
		modelList.add(chainSword = new ChainSword(voidCraft.tabs.tabVoid, voidCraft.materials.chainTools, "chainSword"));
		// molten
		modelList.add(moltenSword = new MoltenSword(voidCraft.tabs.tabVoid, voidCraft.materials.MoltenTools, "moltenSword"));
		// arch
		modelList.add(archSword = new ArchSword(voidCraft.tabs.tabVoid, voidCraft.materials.ArchTools, "archSword"));
		// demon
		modelList.add(demonSword = new DemonSword(voidCraft.tabs.tabVoid, voidCraft.materials.DemonTools, "demonSword"));

		// Cosmic
		modelList.add(starforgedSword = new StarForgeSword(voidCraft.tabs.tabVoid, voidCraft.materials.CosmicTools, "starforgedSword"));
		modelList.add(starforgedPickaxe = new StarForgePickaxe(voidCraft.tabs.tabVoid, voidCraft.materials.CosmicTools, "starforgedPickaxe"));
		modelList.add(starforgedAxe = new StarForgeAxe(voidCraft.tabs.tabVoid, voidCraft.materials.CosmicTools, "starforgedAxe"));
		modelList.add(starforgedSpade = new StarForgeShovel(voidCraft.tabs.tabVoid, voidCraft.materials.CosmicTools, "starforgedShovel"));
	}

	@Override
	public void init() {

		GameRegistry.addRecipe(new ItemStack(voidPickaxe, 1), "XXX", " O ", " D ", 'X', voidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidAxe, 1), " XX", " OX", " D ", 'X', voidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidSpade, 1), " X ", " O ", " D ", 'X', voidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidHoe, 1), " XX", " O ", " D ", 'X', voidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		GameRegistry.addRecipe(new ItemStack(voidSword, 1), " X ", "OXO", " D ", 'X', voidCraft.items.voidcrystal, 'O', Blocks.OBSIDIAN, 'D', Items.DIAMOND);
		// +spectre
		GameRegistry.addRecipe(new ItemStack(angelicSword, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidSword, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectrePickaxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidPickaxe, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectreAxe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidAxe, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectreSpade, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidSpade, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		GameRegistry.addRecipe(new ItemStack(spectreHoe, 1), "XXX", "XSX", "XXX", 'S', new ItemStack(voidHoe, 1, voidCraft.WILDCARD_VALUE), 'X', voidCraft.items.ectoplasm);
		// +molten and beyond
		GameRegistry.addRecipe(new ItemStack(moltenSword), "XXX", "XSX", "XXX", 'X', voidCraft.items.MoltenvoidChain, 'S', new ItemStack(chainSword, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(chainSword), "XXX", "XSX", "XXX", 'X', voidCraft.items.voidChain, 'S', new ItemStack(voidSword, 1, voidCraft.WILDCARD_VALUE));
		GameRegistry.addRecipe(new ItemStack(archSword), "SSS", "XYZ", "SSS", 'S', voidCraft.items.MoltenvoidChain, 'X', new ItemStack(chainSword, 1, voidCraft.WILDCARD_VALUE), 'Y', new ItemStack(angelicSword, 1, voidCraft.WILDCARD_VALUE), 'Z', new ItemStack(moltenSword, 1, voidCraft.WILDCARD_VALUE));
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

	@Override
	public void clientPreInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientPostInit() {
		// TODO Auto-generated method stub

	}

}
