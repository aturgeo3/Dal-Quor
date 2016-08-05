package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.TamModized.fluids.TamFluidBlock;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.TamModized.registry.TamFluidRegistryBase;
import Tamaized.Voidcraft.voidCraft;

public class VoidCraftFluids extends TamFluidRegistryBase {

	public static Fluid voidFluid;

	public static MaterialLiquid voidMaterialLiquid;

	public static BlockFluidBase voidFluidBlock;

	private static ItemStack voidBucket;

	public static ItemStack getBucket() {
		return voidBucket.copy();
	}

	@Override
	public void preInit() {
		voidFluid = createFluid("void", "blocks/voidFluid", true);
		voidFluid.setLuminosity(3).setDensity(-400).setViscosity(1500).setGaseous(true);
		voidMaterialLiquid = new MaterialLiquid(MapColor.PURPLE);
		register(voidFluidBlock = new TamFluidBlock(voidCraft.tabs.tabVoid, voidFluid, Material.WATER, "blockVoidFluid"));
		voidBucket = ForgeModContainer.getInstance().universalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, voidFluid);
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public String getModID() {
		return voidCraft.modid;
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
