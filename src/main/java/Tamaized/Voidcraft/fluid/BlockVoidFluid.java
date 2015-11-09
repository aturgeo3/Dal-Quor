package Tamaized.Voidcraft.fluid;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockVoidFluid extends BlockFluidClassic{
	
	// @SideOnly(Side.CLIENT)
    // protected static IIcon stillIcon;
    // @SideOnly(Side.CLIENT)
    // protected static IIcon flowingIcon;
	
	public IIcon stillIcon;
    public IIcon flowIcon;

	public BlockVoidFluid(Fluid fluid, Material material) {
		super(fluid, material);
		
		this.disableStats();
		//this.blockIcon = stillIcon;
		this.quantaPerBlock = 4;
		//this.textureName = "VoidCraft:voidFluid";
	}
	
	 
     
     @SideOnly(Side.CLIENT)
     public void registerBlockIcons(IIconRegister register) {
             stillIcon = register.registerIcon("VoidCraft:voidFluid_still");
             flowIcon = register.registerIcon("VoidCraft:voidFluid_flow");
             this.getFluid().setIcons(stillIcon, flowIcon);
     }
     
     @Override
     @SideOnly(Side.CLIENT)
     public IIcon getIcon (int side, int meta)
     {
         if (side == 0 || side == 1)
             return stillIcon;
         return flowIcon;
     }
     
     
  //   @SideOnly(Side.CLIENT)
  //   public static IIcon getFluidIcon(String string) {
  //  	 return stillIcon;
  //   }
     
     
     
     @Override
     public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
             return super.canDisplace(world, x, y, z);
     }
     
     @Override
     public boolean displaceIfPossible(World world, int x, int y, int z) {
             return super.displaceIfPossible(world, x, y, z);
     }
     
     
}
