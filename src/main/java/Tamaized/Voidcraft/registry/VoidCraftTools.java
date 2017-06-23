package Tamaized.Voidcraft.registry;

import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.tools.*;
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
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class VoidCraftTools {

	public static List<ITamRegistry> modelList;

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

	static {
		modelList = new ArrayList<>();

		// Swords: 3+dmg; Axe: dmg; Pick: 1+dmg; Spade: 1.5+dmg;
		// Axe: -3; Pick: -2.8; Spade: -3;
		// Tools
		// void
		modelList.add(voidPickaxe = new TamPickaxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.voidTools, "voidpickaxe"));
		modelList.add(voidSpade = new TamSpade(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.voidTools, "voidshovel"));
		modelList.add(voidAxe = new TamAxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.voidTools, "voidaxe"));
		modelList.add(voidSword = new TamSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.voidTools, "voidsword"));
		modelList.add(voidHoe = new VoidHoe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.voidTools, "voidhoe"));

		// spectre
		modelList.add(angelicSword = new AngelicSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.spectreTools, "angelicsword"));
		modelList.add(spectrePickaxe = new TamPickaxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.spectreTools, "spectrepickaxe"));
		modelList.add(spectreAxe = new TamAxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.spectreTools, "spectreaxe"));
		modelList.add(spectreSpade = new TamSpade(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.spectreTools, "spectrespade"));
		modelList.add(spectreHoe = new VoidHoe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.spectreTools, "spectrehoe"));
		// chain
		modelList.add(chainSword = new ChainSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.chainTools, "chainsword"));
		// molten
		modelList.add(moltenSword = new MoltenSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.MoltenTools, "moltensword"));
		// arch
		modelList.add(archSword = new ArchSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.ArchTools, "archsword"));
		// demon
		modelList.add(demonSword = new DemonSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.DemonTools, "demonsword"));

		// Cosmic
		modelList.add(starforgedSword = new StarForgeSword(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.CosmicTools, "starforgedsword"));
		modelList.add(starforgedPickaxe = new StarForgePickaxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.CosmicTools, "starforgedpickaxe"));
		modelList.add(starforgedAxe = new StarForgeAxe(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.CosmicTools, "starforgedaxe"));
		modelList.add(starforgedSpade = new StarForgeShovel(VoidCraftCreativeTabs.tabVoid, VoidCraftMaterials.CosmicTools, "starforgedshovel"));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (ITamRegistry b : modelList)
			b.registerBlock(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (ITamRegistry b : modelList)
			b.registerItem(event);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (ITamRegistry b : modelList)
			b.registerModel(event);
	}

}
