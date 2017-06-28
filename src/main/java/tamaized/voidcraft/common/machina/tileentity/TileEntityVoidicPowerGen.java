package tamaized.voidcraft.common.machina.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.common.voidicpower.VoidicPowerHandler;
import tamaized.voidcraft.common.fluids.IFaceFluidHandler;
import tamaized.voidcraft.common.machina.addons.VoidTank;
import tamaized.voidcraft.registry.VoidCraftFluids;
import tamaized.voidcraft.registry.VoidCraftItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TileEntityVoidicPowerGen extends TileEntityVoidicPowerInventory implements IFaceFluidHandler {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_DEFAULT;

	private VoidTank tank;
	private int useAmount = 1;
	private int genAmount = 2;
	private int rate = 1;

	private List<EnumFacing> fluidOutput = new ArrayList<>();
	private List<EnumFacing> fluidInput = new ArrayList<>();

	public TileEntityVoidicPowerGen() {
		super();
		tank = new VoidTank(this, 5000);
		fluidInput.addAll(Arrays.asList(EnumFacing.VALUES));
	}

	@Override
	protected ItemStackHandler[] register() {
		SLOT_DEFAULT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraftItems.creativeVoidBucket), VoidCraftFluids.voidBucket.getBucket()}, true, new ItemStack[]{new ItemStack(Items.BUCKET)}, true);
		SLOT_DEFAULT.setStackLimit(1);
		return new ItemStackHandler[]{SLOT_DEFAULT};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return SLOT_DEFAULT;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		tank.setFluid(new FluidStack(VoidCraftFluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		return nbt;
	}

	@Override
	public void onUpdate() {
		rate = 10;
		int gen = genAmount * rate;
		int use = useAmount * rate;
		if (!SLOT_DEFAULT.getStackInSlot(0).isEmpty() && SLOT_DEFAULT.getStackInSlot(0).getItem() == VoidCraftItems.creativeVoidBucket && getFluidAmount() != getMaxFluidAmount()) {
			setFluidAmount(getMaxFluidAmount());
		}
		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (!SLOT_DEFAULT.getStackInSlot(0).isEmpty() && SLOT_DEFAULT.getStackInSlot(0).isItemEqual(VoidCraftFluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraftFluids.voidFluid, 1000), true);
				SLOT_DEFAULT.setStackInSlot(0, new ItemStack(Items.BUCKET));
			}
		}
		if (getFluidAmount() >= use && voidicPower <= getMaxPower() - gen) {
			drain(new FluidStack(VoidCraftFluids.voidFluid, use), true);
			voidicPower += gen;
		}
		VoidicPowerHandler.sendToSurrounding(this, world, pos);
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return tank.getTankProperties();
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		return tank.drain(resource, doDrain);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
	}

	public int getFluidAmount() {
		return tank.getFluidAmount();
	}

	public void setFluidAmount(int amount) {
		tank.setFluid(new FluidStack(VoidCraftFluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}

	public int getMaxFluidAmount() {
		return tank.getCapacity();
	}

	@Override
	public int getMaxPower() {
		return 100000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return true;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return false;
	}

	@Override
	public List<EnumFacing> outputFaces() {
		return Collections.unmodifiableList(fluidOutput);
	}

	@Override
	public List<EnumFacing> inputFaces() {
		return Collections.unmodifiableList(fluidInput);
	}

}
