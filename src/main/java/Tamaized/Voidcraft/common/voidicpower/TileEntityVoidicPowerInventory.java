package tamaized.voidcraft.common.voidicpower;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public abstract class TileEntityVoidicPowerInventory extends TileEntityVoidicPower {

	private final ItemStackHandler[] inventory;

	public TileEntityVoidicPowerInventory() {
		inventory = register();
	}

	public int getInventorySize() {
		return inventory.length;
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}

	public void dropInventoryItems(World worldIn, BlockPos pos) {
		dropInventoryItems(worldIn, pos.getX(), pos.getY(), pos.getZ());
	}

	public void dropInventoryItems(World worldIn, double x, double y, double z) {
		for (ItemStackHandler inv : inventory)
			for (int i = 0; i < inv.getSlots(); ++i) {
				ItemStack itemstack = inv.getStackInSlot(i);
				if (!itemstack.isEmpty())
					InventoryHelper.spawnItemStack(worldIn, x, y, z, itemstack);
			}
	}

	protected abstract ItemStackHandler[] register();

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		int index = 0;
		for (ItemStackHandler slot : inventory) {
			String id = "itemslot_" + index;
			if (nbt.hasKey(id))
				slot.deserializeNBT((NBTTagCompound) nbt.getTag(id));
			index++;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		int index = 0;
		for (ItemStackHandler slot : inventory) {
			String id = "itemslot_" + index;
			nbt.setTag(id, slot.serializeNBT());
			index++;
		}
		return nbt;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return getCap(facing) != null || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing enumFacing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? getCap(enumFacing) : super.getCapability(capability, enumFacing);
	}

	@Nullable
	protected abstract <T extends IItemHandler> T getCap(EnumFacing face);

}
