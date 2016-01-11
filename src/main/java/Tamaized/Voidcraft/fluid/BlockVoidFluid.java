package Tamaized.Voidcraft.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockVoidFluid extends BlockFluidClassic{

	public BlockVoidFluid(Fluid fluid, Material material) {
		super(fluid, material);
		
		this.disableStats();
		this.quantaPerBlock = 4;
	} 
}
