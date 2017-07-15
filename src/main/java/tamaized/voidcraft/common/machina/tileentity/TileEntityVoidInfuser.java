package tamaized.voidcraft.common.machina.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import tamaized.tammodized.common.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.common.fluids.IFaceFluidHandler;
import tamaized.voidcraft.common.machina.addons.TERecipeInfuser.InfuserRecipe;
import tamaized.voidcraft.common.machina.addons.VoidTank;
import tamaized.voidcraft.registry.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileEntityVoidInfuser extends TamTileEntityInventory implements IFaceFluidHandler {

	public VoidTank tank;
	public int finishTick = 0;
	public int cookingTick = 0;
	public ItemStackFilterHandler SLOT_BUCKET;
	public ItemStackFilterHandler SLOT_INPUT;
	public ItemStackFilterHandler SLOT_OUTPUT;
	private InfuserRecipe recipe;

	private Item lastCookingItem = null;

	private List<EnumFacing> fluidOutput = new ArrayList<>();
	private List<EnumFacing> fluidInput = new ArrayList<>();

	public TileEntityVoidInfuser() {
		super();
		tank = new VoidTank(this, 3000);
		Collections.addAll(fluidInput, EnumFacing.VALUES);
	}

	public static boolean isItemFuel(ItemStack stack) {
		return (stack.isItemEqual(VoidCraftFluids.voidBucket.getBucket()) || stack.getItem() == VoidCraftItems.creativeVoidBucket);
	}

	@Override
	protected ItemStackHandler[] register() {
		return new ItemStackHandler[]{

				SLOT_BUCKET = new ItemStackFilterHandler(new ItemStack[]{VoidCraftFluids.voidBucket.getBucket(), new ItemStack(VoidCraftItems.creativeVoidBucket)}, true, new ItemStack[]{new ItemStack(Items.BUCKET)}, true),

				SLOT_INPUT = new ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false),

				SLOT_OUTPUT = new ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true)

		};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return new CombinedInvWrapper(SLOT_BUCKET, SLOT_INPUT, SLOT_OUTPUT);
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		if (tank.getFluid() != null)
			tank.setFluid(new FluidStack(VoidCraftFluids.voidFluid, nbt.getInteger("fluidAmount")));
		cookingTick = nbt.getInteger("cookingTick");
	}

	@Override
	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		nbt.setInteger("cookingTick", cookingTick);
		return nbt;
	}

	@Override
	public void onUpdate() {
		boolean cooking = false;
		if (lastCookingItem == null || SLOT_INPUT.getStackInSlot(0).isEmpty() || lastCookingItem != SLOT_INPUT.getStackInSlot(0).getItem()) {
			cookingTick = 0;
			lastCookingItem = (!SLOT_INPUT.getStackInSlot(0).isEmpty()) ? SLOT_INPUT.getStackInSlot(0).getItem() : null;
		}

		if (tank.getFluidAmount() > 0 && canCook()) {
			cooking = true;
			drain(new FluidStack(VoidCraftFluids.voidFluid, 1), true);
		}
		if (!SLOT_BUCKET.getStackInSlot(0).isEmpty() && SLOT_BUCKET.getStackInSlot(0).getItem() == VoidCraftItems.creativeVoidBucket && getFluidAmount() != getMaxFluidAmount()) {
			setFluidAmount(getMaxFluidAmount());
		}

		if (getFluidAmount() <= getMaxFluidAmount() - 1000) {
			if (!SLOT_BUCKET.getStackInSlot(0).isEmpty() && SLOT_BUCKET.getStackInSlot(0).isItemEqual(VoidCraftFluids.voidBucket.getBucket())) {
				fill(new FluidStack(VoidCraftFluids.voidFluid, 1000), true);
				SLOT_BUCKET.setStackInSlot(0, new ItemStack(Items.BUCKET));
			}
		}

		if (cooking) {
			cookingTick++;
			if (cookingTick >= (finishTick = getRequiredFluid())) {
				cookingTick = 0;
				bakeItem();
				markDirty();
			}
		}
	}

	private int getRequiredFluid() {
		ItemStack stack = SLOT_INPUT.getStackInSlot(0);
		return recipe == null ? (stack.getItemDamage()) : recipe.getRequiredFluid();
	}

	private void bakeItem() {
		if (canCook()) {
			if (recipe == null) {
				ItemStack stack = SLOT_INPUT.getStackInSlot(0).copy();
				stack.setItemDamage(0);
				SLOT_OUTPUT.setStackInSlot(0, stack);
				SLOT_INPUT.setStackInSlot(0, ItemStack.EMPTY);
				return;
			}
			if (SLOT_OUTPUT.getStackInSlot(0).isEmpty()) {
				SLOT_OUTPUT.setStackInSlot(0, recipe.getOutput().copy());
			} else if (SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput())) {
				SLOT_OUTPUT.getStackInSlot(0).grow(recipe.getOutput().getCount());
			}

			SLOT_INPUT.getStackInSlot(0).shrink(1);

			if (SLOT_INPUT.getStackInSlot(0).getCount() <= 0) {
				SLOT_INPUT.setStackInSlot(0, ItemStack.EMPTY);
			}
		}
	}

	private boolean canCook() {
		if (SLOT_INPUT.getStackInSlot(0).isEmpty())
			return false;
		recipe = VoidCraftTERecipes.infuser.getRecipe(new ItemStack[]{SLOT_INPUT.getStackInSlot(0)});
		if (recipe == null)
			return SLOT_OUTPUT.getStackInSlot(0).isEmpty() && canRepair(SLOT_INPUT.getStackInSlot(0));
		if (SLOT_OUTPUT.getStackInSlot(0).isEmpty())
			return true;
		if (!SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput()))
			return false;
		int result = SLOT_OUTPUT.getStackInSlot(0).getCount() + recipe.getOutput().getCount();
		return (result <= recipe.getOutput().getMaxStackSize());
	}

	private boolean canRepair(ItemStack stack) {
		Item item = stack.getItem();
		return (item == VoidCraftItems.voidCrystalShield ||

				item == VoidCraftTools.voidAxe ||

				item == VoidCraftTools.voidPickaxe ||

				item == VoidCraftTools.voidSpade ||

				item == VoidCraftTools.voidSword ||

				item == VoidCraftTools.voidHoe ||

				item == VoidCraftArmors.voidBoots ||

				item == VoidCraftArmors.voidLegs ||

				item == VoidCraftArmors.voidChest ||

				item == VoidCraftArmors.voidHelmet) &&

				(stack.getItemDamage() > 0);
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