package tamaized.voidcraft.common.machina.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import tamaized.voidcraft.common.fluids.FluidHelper;
import tamaized.voidcraft.common.fluids.IFaceFluidHandler;
import tamaized.voidcraft.common.machina.addons.VoidTank;
import tamaized.voidcraft.registry.VoidCraftFluids;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileEntityVoidicAnchor extends TileEntityVoidicPowerInventory implements IFaceFluidHandler {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_DEFAULT;

	private VoidTank tank;
	private int radius = 10;
	private int rate = 10;

	private List<EnumFacing> fluidOutput = new ArrayList<>();
	private List<EnumFacing> fluidInput = new ArrayList<>();

	public TileEntityVoidicAnchor() {
		super();
		tank = new VoidTank(this, 5000);
		fluidOutput.add(EnumFacing.DOWN);
	}

	@Override
	protected ItemStackHandler[] register() {
		SLOT_DEFAULT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{new ItemStack(Items.BUCKET)}, true, new ItemStack[]{VoidCraftFluids.voidBucket.getBucket()}, true);
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
		// Fill a bucket
		if (!SLOT_DEFAULT.getStackInSlot(0).isEmpty() && SLOT_DEFAULT.getStackInSlot(0).getItem() == Items.BUCKET && getFluidAmount() >= 1000) {
			drain(1000, true);
			SLOT_DEFAULT.setStackInSlot(0, VoidCraftFluids.voidBucket.getBucket());
		}
		// Handle Infusion drain
		for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().add(-radius, -radius, -radius), getPos().add(radius, radius, radius)))) {
			if (voidicPower > 0 && getFluidAmount() + rate <= getMaxFluidAmount() && entity.hasCapability(CapabilityList.VOIDICINFUSION, null)) {
				IVoidicInfusionCapability cap = entity.getCapability(CapabilityList.VOIDICINFUSION, null);
				if (cap.getInfusion() >= rate) {
					voidicPower--;
					cap.addInfusion(-rate);
					fill(new FluidStack(VoidCraft.fluids.voidFluid, rate), true);
				}
			}
		}
		// Check if Void Machina is nearby; if so give it fluid
		if (tank.getFluidAmount() >= rate) {
			FluidHelper.sendToAllAround(this, world, pos, rate);
		}
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
		tank.setFluid(new FluidStack(VoidCraft.fluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
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
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return true;
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
