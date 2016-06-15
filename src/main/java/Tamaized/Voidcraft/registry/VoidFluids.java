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
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.fluid.BasicVoidFluidBlock;

public class VoidFluids extends RegistryBase {
	
	private static final ArrayList<BlockFluidBase> fluids = new ArrayList<BlockFluidBase>();
	
	public static Fluid voidFluid;
	
	public static MaterialLiquid voidMaterialLiquid;
	
	public static BlockFluidBase voidFluidBlock;
	
	public static ItemStack voidBucket;

	@Override
	public void preInit() {
		voidFluid = createFluid("void", "blocks/voidFluid", true);
		voidFluid.setLuminosity(3).setDensity(-400).setViscosity(1500).setGaseous(true);
		voidMaterialLiquid = new MaterialLiquid(MapColor.PURPLE);
		voidFluidBlock = new BasicVoidFluidBlock(voidFluid, Material.WATER, "blockVoidFluid");
		voidBucket = ForgeModContainer.getInstance().universalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, voidFluid);
		
		fluids.add(voidFluidBlock);
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {

	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void setupRender() {
		
	}
	
	@SideOnly(Side.CLIENT)
	public void preInitRender(){
		for(BlockFluidBase f : fluids){
			Item fluid = Item.getItemFromBlock(f);
			final net.minecraft.client.renderer.block.model.ModelResourceLocation modelResourceLocation = new net.minecraft.client.renderer.block.model.ModelResourceLocation(voidCraft.modid+":blocks/fluids", f.getFluid().getName());
			net.minecraft.client.renderer.block.model.ModelBakery.registerItemVariants(fluid);
			net.minecraftforge.client.model.ModelLoader.setCustomMeshDefinition(fluid, new net.minecraft.client.renderer.ItemMeshDefinition(){
				public net.minecraft.client.renderer.block.model.ModelResourceLocation getModelLocation(ItemStack stack){
					return modelResourceLocation;
				}
			});
			net.minecraftforge.client.model.ModelLoader.setCustomStateMapper(f, new net.minecraft.client.renderer.block.statemap.StateMapperBase(){
				protected net.minecraft.client.renderer.block.model.ModelResourceLocation getModelResourceLocation(IBlockState state){
					return modelResourceLocation;
				}
			});
		}
	}
	
	private static Fluid createFluid(String name, String textureName, boolean hasFlowIcon){
		ResourceLocation still = new ResourceLocation(voidCraft.modid, textureName + "_still");
		ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(voidCraft.modid, textureName + "_flow") : still;
		System.out.println(still);
		Fluid fluid = new Fluid(name, still, flowing);
		if (!FluidRegistry.registerFluid(fluid)) fluid = FluidRegistry.getFluid(name);
		FluidRegistry.addBucketForFluid(fluid);
		return fluid;
	}
	
}
