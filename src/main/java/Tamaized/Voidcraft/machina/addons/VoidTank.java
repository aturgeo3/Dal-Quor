package Tamaized.Voidcraft.machina.addons;

import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

public class VoidTank extends FluidTank implements IFluidTank{

	public VoidTank(TileEntity te, int capacity) {
		super(capacity);

		tile = te;
	}

	public boolean fillFromContainer(ItemStack container)
	{
		FluidStack stack = FluidContainerRegistry.getFluidForFilledItem(container);
		if (stack != null)
		{
			if (getCapacity() - getFluidAmount() > stack.amount)
			{
				if (getFluid() != null)
				{
					if (getFluid().isFluidEqual(stack))
					{
						fluid.amount += stack.amount;
						return true;
					}
				}
				else
				{
					fluid = stack.copy();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getFluidAmount()
	{
		if (fluid == null)
		{
			return 0;
		}
		return fluid.amount;
	}
	
	
}