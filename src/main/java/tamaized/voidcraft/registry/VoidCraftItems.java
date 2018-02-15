package tamaized.voidcraft.registry;

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
import tamaized.voidcraft.common.entity.nonliving.EntityObsidianFlask;
import tamaized.voidcraft.common.events.DamageEvent;
import tamaized.voidcraft.common.items.*;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class VoidCraftItems {

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

		modelList.add(vadeMecum = new VadeMecum(VoidCraftCreativeTabs.tabVoid, "vademecum", 1));

		modelList.add(emptyObsidianFlask = new EmptyObsidianFlask(VoidCraftCreativeTabs.tabVoid, "emptyobsidianflask", 16));
		modelList.add(obsidianFlask = new ObsidianFlask(EntityObsidianFlask.Type.Normal, VoidCraftCreativeTabs.tabVoid, "obsidianflask", 16));
		modelList.add(obsidianFlaskFire = new ObsidianFlask(EntityObsidianFlask.Type.Fire, VoidCraftCreativeTabs.tabVoid, "flask_fire", 16));
		modelList.add(obsidianFlaskFreeze = new ObsidianFlask(EntityObsidianFlask.Type.Freeze, VoidCraftCreativeTabs.tabVoid, "flask_freeze", 16));
		modelList.add(obsidianFlaskShock = new ObsidianFlask(EntityObsidianFlask.Type.Shock, VoidCraftCreativeTabs.tabVoid, "flask_shock", 16));
		modelList.add(obsidianFlaskAcid = new ObsidianFlask(EntityObsidianFlask.Type.Acid, VoidCraftCreativeTabs.tabVoid, "flask_acid", 16));
		modelList.add(obsidianFlaskVoid = new ObsidianFlask(EntityObsidianFlask.Type.Void, VoidCraftCreativeTabs.tabVoid, "flask_void", 16));
		modelList.add(ectoplasm = new TamItem(VoidCraftCreativeTabs.tabVoid, "ectoplasm", 64));
		modelList.add(voidcrystal = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcrystal", 64));
		modelList.add(voidChain = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidchain", 64));
		modelList.add(MoltenvoidChain = new TamItem(VoidCraftCreativeTabs.tabVoid, "moltenvoidchain", 64));
		modelList.add(MoltenvoidChainPart = new TamItem(VoidCraftCreativeTabs.tabVoid, "moltenvoidchainpart", 64));
		modelList.add(burnBone = new TamItem(VoidCraftCreativeTabs.tabVoid, "burnbone", 64));
		modelList.add(voidStar = new VoidStar(VoidCraftCreativeTabs.tabVoid, "voidstar", 1));
		modelList.add(ChainedSkull = new ChainedSkull(VoidCraftCreativeTabs.tabVoid, "chainedskull", 1));
		modelList.add(voidCloth = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcloth", 64));
		modelList.add(voidCrystalBucket = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidcrystalbucket", 1));
		modelList.add(voidicEssence = new VoidicEssence(VoidCraftCreativeTabs.tabVoid, "voidicessence", 1));
		modelList.add(voidicPhlogiston = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicphlogiston", 64));
		modelList.add(voidicDragonScale = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicdragonscale", 64));
		modelList.add(astralEssence = new TamItem(VoidCraftCreativeTabs.tabVoid, "astralessence", 64));
		modelList.add(quoriFragment = new TamItem(VoidCraftCreativeTabs.tabVoid, "quorifragment", 64));

		modelList.add(voidicSteel = new TamItem(VoidCraftCreativeTabs.tabVoid, "voidicsteel", 64));

		modelList.add(debugger = new Debugger(VoidCraftCreativeTabs.tabVoid, "debugger", 1));

		modelList.add(voidCrystalShield = new VoidCrystalShield(VoidCraftCreativeTabs.tabVoid, "voidcrystalshield"));
		DamageEvent.shieldRegistry.add(voidCrystalShield);

		modelList.add(dreamBed = new ItemDreamBed(VoidCraftCreativeTabs.tabVoid, "dreambed"));

	}

	public static void init() {
		OreDictionary.registerOre("ingotVoidSteel", voidicSteel);
		OreDictionary.registerOre("ingotSteel", voidicSteel);

		GameRegistry.addSmelting(VoidCraftBlocks.oreVoidcrystal, new ItemStack(voidcrystal), 0.1F);
		GameRegistry.addSmelting(voidChain, new ItemStack(MoltenvoidChainPart), 0.1F);
		GameRegistry.addSmelting(voidCrystalBucket, VoidCraftFluids.voidBucket.getBucket(), 0.1F);
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
