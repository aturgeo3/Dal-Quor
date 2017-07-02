package tamaized.voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.fluids.TamFluidBlock;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.tammodized.registry.RegistryHelper;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.damagesources.DamageSourceAcid;
import tamaized.voidcraft.common.fluids.ArcaneSludgeFluidBlock;
import tamaized.voidcraft.common.fluids.TamFluidFiniteBlock;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class VoidCraftFluids {

	public static Fluid voidFluid;
	public static Fluid acidFluid;
	public static Fluid arcaneSludgeFluid;

	public static MaterialLiquid voidMaterialLiquid;
	public static MaterialLiquid acidMaterialLiquid;
	public static MaterialLiquid arcaneSludgeMaterialLiquid;

	public static TamFluidBlock voidFluidBlock;
	public static TamFluidFiniteBlock acidFluidBlock;
	public static TamFluidBlock arcaneSludgeFluidBlock;

	public static BucketWrapper voidBucket;

	private static List<ITamRegistry> modelList;

	static {
		modelList = new ArrayList<>();

		voidFluid = RegistryHelper.createFluid(VoidCraft.modid, "void", "fluids/void/fluid", true, true);
		acidFluid = RegistryHelper.createFluid(VoidCraft.modid, "acid", "fluids/acid/fluid", true, false);
		arcaneSludgeFluid = RegistryHelper.createFluid(VoidCraft.modid, "arcanesludge", "fluids/arcanesludge/fluid", true, false);

		voidFluid.setLuminosity(3).setDensity(-400).setViscosity(1500).setGaseous(true);
		acidFluid.setLuminosity(7).setDensity(2).setViscosity(500).setGaseous(false);
		arcaneSludgeFluid.setLuminosity(15).setDensity(3100).setViscosity(250).setGaseous(false);

		voidMaterialLiquid = new MaterialLiquid(MapColor.PURPLE);
		acidMaterialLiquid = new MaterialLiquid(MapColor.GREEN);
		arcaneSludgeMaterialLiquid = new MaterialLiquid(MapColor.CYAN);

		modelList.add(voidFluidBlock = new TamFluidBlock(VoidCraftCreativeTabs.tabVoid, voidFluid, Material.WATER, "blockvoidfluid"));
		modelList.add(acidFluidBlock = new TamFluidFiniteBlock(VoidCraftCreativeTabs.tabVoid, acidFluid, Material.WATER, "blockacidfluid", new DamageSourceAcid(), 5));
		modelList.add(arcaneSludgeFluidBlock = new ArcaneSludgeFluidBlock(VoidCraftCreativeTabs.tabVoid, arcaneSludgeFluid, Material.WATER, "blockarcanesludgefluid"));
	}

	public static void init(){
		voidBucket = new BucketWrapper(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, voidFluid));
	}

	public static class BucketWrapper {

		private final ItemStack bucket;

		public BucketWrapper(ItemStack stack) {
			bucket = stack;
		}

		public ItemStack getBucket() {
			return bucket.copy();
		}

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
