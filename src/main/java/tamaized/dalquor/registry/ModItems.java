package tamaized.dalquor.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import tamaized.tammodized.common.items.TamItem;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.dalquor.common.entity.nonliving.EntityObsidianFlask;
import tamaized.dalquor.common.events.DamageEvent;
import tamaized.dalquor.common.items.*;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModItems {

	public static VadeMecum vadeMecum;
	public static TamItem ectoplasm;
	public static TamItem voidcrystal;
	public static EmptyObsidianFlask emptyObsidianFlask;
	public static ObsidianFlask obsidianFlask;
	public static ObsidianFlask obsidianFlaskFire;
	public static ObsidianFlask obsidianFlaskFreeze;
	public static ObsidianFlask obsidianFlaskShock;
	public static ObsidianFlask obsidianFlaskAcid;
	public static ObsidianFlask obsidianFlaskVoid;
	public static TamItem voidChain;
	public static TamItem MoltenvoidChain;
	public static TamItem MoltenvoidChainPart;
	public static TamItem burnBone;
	public static VoidStar voidStar;
	public static ChainedSkull ChainedSkull;
	public static TamItem voidCloth;
	public static TamItem voidCrystalBucket;
	public static VoidicEssence voidicEssence;
	public static TamItem voidicPhlogiston;
	public static TamItem voidicDragonScale;
	public static TamItem astralEssence;
	public static TamItem quoriFragment;
	public static TamItem voidicSteel;
	public static Debugger debugger;
	public static VoidCrystalShield voidCrystalShield;
	public static ItemDreamBed dreamBed;
	private static List<ITamRegistry> modelList;

	static {
		modelList = new ArrayList<>();

		modelList.add(vadeMecum = new VadeMecum(ModCreativeTabs.tabVoid, "vademecum", 1));

		modelList.add(emptyObsidianFlask = new EmptyObsidianFlask(ModCreativeTabs.tabVoid, "emptyobsidianflask", 16));
		modelList.add(obsidianFlask = new ObsidianFlask(EntityObsidianFlask.Type.Normal, ModCreativeTabs.tabVoid, "obsidianflask", 16));
		modelList.add(obsidianFlaskFire = new ObsidianFlask(EntityObsidianFlask.Type.Fire, ModCreativeTabs.tabVoid, "flask_fire", 16));
		modelList.add(obsidianFlaskFreeze = new ObsidianFlask(EntityObsidianFlask.Type.Freeze, ModCreativeTabs.tabVoid, "flask_freeze", 16));
		modelList.add(obsidianFlaskShock = new ObsidianFlask(EntityObsidianFlask.Type.Shock, ModCreativeTabs.tabVoid, "flask_shock", 16));
		modelList.add(obsidianFlaskAcid = new ObsidianFlask(EntityObsidianFlask.Type.Acid, ModCreativeTabs.tabVoid, "flask_acid", 16));
		modelList.add(obsidianFlaskVoid = new ObsidianFlask(EntityObsidianFlask.Type.Void, ModCreativeTabs.tabVoid, "flask_void", 16));
		modelList.add(ectoplasm = new TamItem(ModCreativeTabs.tabVoid, "ectoplasm", 64));
		modelList.add(voidcrystal = new TamItem(ModCreativeTabs.tabVoid, "voidcrystal", 64));
		modelList.add(voidChain = new TamItem(ModCreativeTabs.tabVoid, "voidchain", 64));
		modelList.add(MoltenvoidChain = new TamItem(ModCreativeTabs.tabVoid, "moltenvoidchain", 64));
		modelList.add(MoltenvoidChainPart = new TamItem(ModCreativeTabs.tabVoid, "moltenvoidchainpart", 64));
		modelList.add(burnBone = new TamItem(ModCreativeTabs.tabVoid, "burnbone", 64));
		modelList.add(voidStar = new VoidStar(ModCreativeTabs.tabVoid, "voidstar", 1));
		modelList.add(ChainedSkull = new ChainedSkull(ModCreativeTabs.tabVoid, "chainedskull", 1));
		modelList.add(voidCloth = new TamItem(ModCreativeTabs.tabVoid, "voidcloth", 64));
		modelList.add(voidCrystalBucket = new TamItem(ModCreativeTabs.tabVoid, "voidcrystalbucket", 1));
		modelList.add(voidicEssence = new VoidicEssence(ModCreativeTabs.tabVoid, "voidicessence", 1));
		modelList.add(voidicPhlogiston = new TamItem(ModCreativeTabs.tabVoid, "voidicphlogiston", 64));
		modelList.add(voidicDragonScale = new TamItem(ModCreativeTabs.tabVoid, "voidicdragonscale", 64));
		modelList.add(astralEssence = new TamItem(ModCreativeTabs.tabVoid, "astralessence", 64));
		modelList.add(quoriFragment = new TamItem(ModCreativeTabs.tabVoid, "quorifragment", 64));

		modelList.add(voidicSteel = new TamItem(ModCreativeTabs.tabVoid, "voidicsteel", 64));

		modelList.add(debugger = new Debugger(ModCreativeTabs.tabVoid, "debugger", 1));

		modelList.add(voidCrystalShield = new VoidCrystalShield(ModCreativeTabs.tabVoid, "voidcrystalshield"));
		DamageEvent.shieldRegistry.add(voidCrystalShield);

		modelList.add(dreamBed = new ItemDreamBed(ModCreativeTabs.tabVoid, "dreambed"));

	}

	public static void init() {
		OreDictionary.registerOre("ingotVoidSteel", voidicSteel);
		OreDictionary.registerOre("ingotSteel", voidicSteel);

		GameRegistry.addSmelting(ModBlocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, ModFluids.voidBucket.getBucket(), 0.1F);
	}

	private static void addOreSmelting(Item i, String s) {
		for (ItemStack ore : OreDictionary.getOres(s)) {
			if (!ore.isEmpty()) {
				GameRegistry.addSmelting(i, ore, ore.getItemDamage());
			}
		}
	}

/*	public void clientPreInit() {
//		VadeMecumMeshDefinition.preRegister();
//		ModelLoader.setCustomMeshDefinition(vademecum, new VadeMecumMeshDefinition());
	}*/

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
