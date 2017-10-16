package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.fluids.IFaceFluidHandler;
import tamaized.voidcraft.common.machina.addons.VoidTank;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.registry.VoidCraftFluids;
import tamaized.voidcraft.registry.VoidCraftItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileEntityVoidicCrystallizer extends TileEntityVoidicPowerInventory implements IFaceFluidHandler {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_BUCKET;

	private VoidTank tank;

	private List<EnumFacing> fluidOutput = new ArrayList<>();
	private List<EnumFacing> fluidInput = new ArrayList<>();

	public TileEntityVoidicCrystallizer() {
		super();
		tank = new VoidTank(this, 1000);
		fluidInput.add(EnumFacing.UP);
		fluidInput.add(EnumFacing.DOWN);
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{SLOT_BUCKET = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[]{VoidCraftFluids.voidBucket.getBucket()}, true, new ItemStack[]{new ItemStack(Items.BUCKET)}, true)};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return SLOT_BUCKET;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing enumFacing) {
		return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY ? (T) this : super.getCapability(capability, enumFacing);
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
		// Create a Crystal
		if (getFluidAmount() >= 1000 && getPowerAmount() >= 144) {
			drain(1000, true);
			voidicPower -= 144;
			if (!world.isRemote) {
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(VoidCraftItems.voidcrystal));
				world.playSound(null, getPos(), SoundEvent.REGISTRY.getObject(new ResourceLocation("block.fire.extinguish")), SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
		}
		// Fill from a Bucket
		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (!SLOT_BUCKET.getStackInSlot(0).isEmpty() && SLOT_BUCKET.getStackInSlot(0).isItemEqual(VoidCraftFluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraftFluids.voidFluid, 1000), true);
				SLOT_BUCKET.setStackInSlot(0, new ItemStack(Items.BUCKET));
			}
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
		tank.setFluid(new FluidStack(VoidCraftFluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}

	public int getMaxFluidAmount() {
		return tank.getCapacity();
	}

	@Override
	public int getMaxPower() {
		return 10000;
	}

	@Override
	public int maxPowerTransfer() {
		return 500;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return face == EnumFacing.UP || face == EnumFacing.DOWN;
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