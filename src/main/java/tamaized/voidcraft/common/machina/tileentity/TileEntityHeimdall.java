package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import tamaized.tammodized.common.particles.network.ParticleFluffPacketHandler;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.fluids.FluidHelper;
import tamaized.voidcraft.common.fluids.IFaceFluidHandler;
import tamaized.voidcraft.common.machina.addons.VoidTank;
import tamaized.voidcraft.registry.VoidCraftFluids;
import tamaized.voidcraft.registry.VoidCraftItems;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileEntityHeimdall extends TamTileEntityInventory implements IEnergyStorage, IFaceFluidHandler {

	private static final int quartzAmount = 144 * 5;
	private VoidTank tank;
	public ItemStackHandler SLOT_INPUT;
	public ItemStackHandler SLOT_BUCKET;
	private int forgeEnergy = 0;
	private int maxForgeEnergy = 20000;

	private List<EnumFacing> fluidOutput = new ArrayList<>();
	private List<EnumFacing> fluidInput = new ArrayList<>();

	public TileEntityHeimdall() {
		super();
		tank = new VoidTank(this, 10000);
		fluidOutput.add(EnumFacing.DOWN);
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{

				SLOT_INPUT = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(VoidCraftItems.quartzDust)}, true, new ItemStack[0], false),

				SLOT_BUCKET = new ItemStackFilterHandler(new ItemStack[]{new ItemStack(Items.BUCKET)}, true, new ItemStack[]{VoidCraftFluids.voidBucket.getBucket()}, true)

		};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return new CombinedInvWrapper(SLOT_BUCKET, SLOT_INPUT);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing enumFacing) {
		return capability == CapabilityEnergy.ENERGY ? (T) this : super.getCapability(capability, enumFacing);
	}

	@Override
	public int receiveEnergy(int power, boolean simulate) {
		int consumed = forgeEnergy + power > maxForgeEnergy ? power - ((forgeEnergy + power) - maxForgeEnergy) : power;
		if (!simulate)
			forgeEnergy += consumed;
		return consumed;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public boolean canExtract() {
		return false;
	}

	@Override
	public boolean canReceive() {
		return true;
	}

	@Override
	public int getEnergyStored() {
		return forgeEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxForgeEnergy;
	}

	public void setEnergyAmount(int a) {
		forgeEnergy = a;
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (tank.getFluid() != null)
			tank.setFluid(new FluidStack(VoidCraftFluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		return nbt;
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote) {

			// Fill A Bucket
			if (tank.getFluidAmount() >= 1000) {
				if (!SLOT_BUCKET.getStackInSlot(0).isEmpty() && SLOT_BUCKET.getStackInSlot(0).getItem() == Items.BUCKET) {
					tank.drain(new FluidStack(VoidCraftFluids.voidFluid, 1000), true);
					SLOT_BUCKET.setStackInSlot(0, VoidCraftFluids.voidBucket.getBucket());
				}
			}

			// Check for quartz dust and handle
			if (forgeEnergy + quartzAmount < maxForgeEnergy && !SLOT_INPUT.getStackInSlot(0).isEmpty() && SLOT_INPUT.getStackInSlot(0).getItem() == VoidCraftItems.quartzDust) {
				if (SLOT_INPUT.getStackInSlot(0).getCount() > 1)
					SLOT_INPUT.getStackInSlot(0).shrink(1);
				else
					SLOT_INPUT.setStackInSlot(0, ItemStack.EMPTY);
				forgeEnergy += quartzAmount;
			}

			// Convert Power to Fluid
			if (forgeEnergy >= 1 && getFluidAmount() < getMaxFluidAmount()) {
				int rate = MathHelper.clamp(forgeEnergy, 1, 100);
				forgeEnergy -= rate;
				fill(new FluidStack(VoidCraftFluids.voidFluid, rate), true);
				ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F), new Vec3d(0, 0, 0), world.rand.nextInt(20 * 2) + 20, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.50F) + 0.25F, 0x7700FFFF);
			}

			// Check if Void Machina is nearby; if so give it fluid
			if (tank.getFluidAmount() > 0) {
				FluidHelper.sendToAllAround(this, world, pos, MathHelper.clamp(getFluidAmount(), 1, 100));
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
	public List<EnumFacing> outputFaces() {
		return Collections.unmodifiableList(fluidOutput);
	}

	@Override
	public List<EnumFacing> inputFaces() {
		return Collections.unmodifiableList(fluidInput);
	}
}