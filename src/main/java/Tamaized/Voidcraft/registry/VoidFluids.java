package Tamaized.Voidcraft.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.BucketHandler;
import Tamaized.Voidcraft.fluid.BlockVoidFluid;
import Tamaized.Voidcraft.items.ItemVoidCraftBucket;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public class VoidFluids extends RegistryBase {

	public static final int idFluidVoid = 198;
	public static Fluid fluidVoid;
	public static Block fluidVoidz;
	public static Material materialFluidVoid;

	public static Item voidBucket;
	public static Block blockVoidFluid;

	@Override
	public void preInit() {
		// Fluid
		fluidVoid = new Fluid("void");
		materialFluidVoid = new MaterialLiquid(MapColor.purpleColor);
		// -This has to be here for Fluids
		FluidRegistry.registerFluid(fluidVoid);
		// +This here must be last
		blockVoidFluid = new BlockVoidFluid(fluidVoid, Material.water).setDensity(-400).setBlockName("fluidVoid").setBlockTextureName("VoidCraft:voidFluid");
		voidBucket = new ItemVoidCraftBucket(blockVoidFluid).setUnlocalizedName("voidBucket").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(voidCraft.tabs.tabVoid).setTextureName("VoidCraft:voidBucket");
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluidVoid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(voidBucket), new ItemStack(Items.bucket)));
		BucketHandler.INSTANCE.buckets.put(blockVoidFluid, voidBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
	}

	@Override
	public void init() {
		GameRegistry.registerBlock(blockVoidFluid, "blockVoidFluid");
		GameRegistry.registerItem(voidBucket, voidBucket.getUnlocalizedName());
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}

}
