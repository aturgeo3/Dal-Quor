package tamaized.dalquor.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.tools.*;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.dalquor.common.starforge.tools.StarForgeAxe;
import tamaized.dalquor.common.starforge.tools.StarForgePickaxe;
import tamaized.dalquor.common.starforge.tools.StarForgeShovel;
import tamaized.dalquor.common.starforge.tools.StarForgeSword;
import tamaized.dalquor.common.tools.VoidHoe;
import tamaized.dalquor.common.tools.arch.ArchSword;
import tamaized.dalquor.common.tools.chain.ChainSword;
import tamaized.dalquor.common.tools.demon.DemonSword;
import tamaized.dalquor.common.tools.molten.MoltenSword;
import tamaized.dalquor.common.tools.spectre.AngelicSword;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModTools {

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
		modelList.add(voidPickaxe = new TamPickaxe(ModCreativeTabs.tabVoid, ModMaterials.voidTools, "voidpickaxe"));
		modelList.add(voidSpade = new TamSpade(ModCreativeTabs.tabVoid, ModMaterials.voidTools, "voidshovel"));
		modelList.add(voidAxe = new TamAxe(ModCreativeTabs.tabVoid, ModMaterials.voidTools, "voidaxe"));
		modelList.add(voidSword = new TamSword(ModCreativeTabs.tabVoid, ModMaterials.voidTools, "voidsword"));
		modelList.add(voidHoe = new VoidHoe(ModCreativeTabs.tabVoid, ModMaterials.voidTools, "voidhoe"));

		// spectre
		modelList.add(angelicSword = new AngelicSword(ModCreativeTabs.tabVoid, ModMaterials.spectreTools, "angelicsword"));
		modelList.add(spectrePickaxe = new TamPickaxe(ModCreativeTabs.tabVoid, ModMaterials.spectreTools, "spectrepickaxe"));
		modelList.add(spectreAxe = new TamAxe(ModCreativeTabs.tabVoid, ModMaterials.spectreTools, "spectreaxe"));
		modelList.add(spectreSpade = new TamSpade(ModCreativeTabs.tabVoid, ModMaterials.spectreTools, "spectrespade"));
		modelList.add(spectreHoe = new VoidHoe(ModCreativeTabs.tabVoid, ModMaterials.spectreTools, "spectrehoe"));
		// chain
		modelList.add(chainSword = new ChainSword(ModCreativeTabs.tabVoid, ModMaterials.chainTools, "chainsword"));
		// molten
		modelList.add(moltenSword = new MoltenSword(ModCreativeTabs.tabVoid, ModMaterials.MoltenTools, "moltensword"));
		// arch
		modelList.add(archSword = new ArchSword(ModCreativeTabs.tabVoid, ModMaterials.ArchTools, "archsword"));
		// demon
		modelList.add(demonSword = new DemonSword(ModCreativeTabs.tabVoid, ModMaterials.DemonTools, "demonsword"));

		// Cosmic
		modelList.add(starforgedSword = new StarForgeSword(ModCreativeTabs.tabVoid, ModMaterials.CosmicTools, "starforgedsword"));
		modelList.add(starforgedPickaxe = new StarForgePickaxe(ModCreativeTabs.tabVoid, ModMaterials.CosmicTools, "starforgedpickaxe"));
		modelList.add(starforgedAxe = new StarForgeAxe(ModCreativeTabs.tabVoid, ModMaterials.CosmicTools, "starforgedaxe"));
		modelList.add(starforgedSpade = new StarForgeShovel(ModCreativeTabs.tabVoid, ModMaterials.CosmicTools, "starforgedshovel"));
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
