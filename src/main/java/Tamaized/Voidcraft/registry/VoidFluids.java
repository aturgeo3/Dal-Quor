package Tamaized.Voidcraft.registry;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.fluid.BlockVoidFluid;
import Tamaized.Voidcraft.handlers.BucketHandler;
import Tamaized.Voidcraft.items.ItemVoidCraftBucket;

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
		fluidVoid = new Fluid("void", new ResourceLocation("VoidCraft:textures/blocks/voidFluid_still.png"), new ResourceLocation("VoidCraft:textures/blocks/voidFluid_flow.png"));
		materialFluidVoid = new MaterialLiquid(MapColor.purpleColor);
		// This has to be here for Fluids
		FluidRegistry.registerFluid(fluidVoid);
		// This must be last
		blockVoidFluid = new BlockVoidFluid(fluidVoid, Material.water).setDensity(-400).setUnlocalizedName("blockVoidFluid");
		FluidRegistry.addBucketForFluid(fluidVoid);
		//voidBucket = new ItemVoidCraftBucket(blockVoidFluid).setUnlocalizedName("voidBucket").setMaxStackSize(1).setContainerItem(Items.bucket).setCreativeTab(voidCraft.tabs.tabVoid);
		//FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluidVoid.getName(), FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(voidBucket), new ItemStack(Items.bucket)));
		//BucketHandler.INSTANCE.buckets.put(blockVoidFluid, voidBucket);
		//MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		
	
	
	}

	@Override
	public void init() {
		GameRegistry.registerBlock(blockVoidFluid, "blockVoidFluid");
		//GameRegistry.registerItem(voidBucket, voidBucket.getUnlocalizedName());
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub

	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}

}
