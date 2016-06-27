package Tamaized.Voidcraft.machina.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import Tamaized.Voidcraft.power.IVoidicPower;
import Tamaized.Voidcraft.power.TileEntityVoidicPower;
import Tamaized.Voidcraft.power.VoidicPowerHandler;

public class TileEntityVoidicPowerCable extends TileEntityVoidicPower{
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		return nbt;
	}
	
	@Override
	public void update() {
		super.update();
		VoidicPowerHandler.sendToSurrounding(this, worldObj, pos);
	}

	@Override
	public int getMaxPower() {
		return 160;
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
		return !blockFace.contains(face);
	}

}
