package tamaized.dalquor.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.common.damagesources.DamageSourceAcid;
import tamaized.dalquor.common.fluids.ArcaneSludgeFluidBlock;
import tamaized.tammodized.common.fluids.TamFluidBlock;
import tamaized.tammodized.common.fluids.TamFluidFiniteBlock;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.tammodized.registry.RegistryHelper;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class ModFluids {

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

		voidFluid = fixLang(RegistryHelper.createFluid(DalQuor.modid, "void", "fluids/void/fluid", true, true));
		acidFluid = fixLang(RegistryHelper.createFluid(DalQuor.modid, "acid", "fluids/acid/fluid", true, false));
		arcaneSludgeFluid = fixLang(RegistryHelper.createFluid(DalQuor.modid, "arcanesludge", "fluids/arcanesludge/fluid", true, false));

		voidFluid.setLuminosity(3).setDensity(-400).setViscosity(1500).setGaseous(true);
		acidFluid.setLuminosity(7).setDensity(2).setViscosity(500).setGaseous(false);
		arcaneSludgeFluid.setLuminosity(15).setDensity(3100).setViscosity(250).setGaseous(false);

		voidMaterialLiquid = new MaterialLiquid(MapColor.PURPLE);
		acidMaterialLiquid = new MaterialLiquid(MapColor.GREEN);
		arcaneSludgeMaterialLiquid = new MaterialLiquid(MapColor.CYAN);

		modelList.add(voidFluidBlock = new TamFluidBlock(ModCreativeTabs.tabVoid, voidFluid, Material.WATER, "blockvoidfluid"));
		modelList.add(acidFluidBlock = new TamFluidFiniteBlock(ModCreativeTabs.tabVoid, acidFluid, Material.WATER, "blockacidfluid", new DamageSourceAcid(), 5));
		modelList.add(arcaneSludgeFluidBlock = new ArcaneSludgeFluidBlock(ModCreativeTabs.tabVoid, arcaneSludgeFluid, Material.WATER, "blockarcanesludgefluid"));
	}

	private static Fluid fixLang(Fluid fluid){
		return fluid.setUnlocalizedName(DalQuor.modid + "." + fluid.getName());
	}

	public static void init() {
		voidBucket = new BucketWrapper(FluidUtil.getFilledBucket(new FluidStack(voidFluid, 1000)));
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
