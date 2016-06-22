package Tamaized.Voidcraft.power;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public abstract class TileEntityVoidicPower extends TileEntity implements IVoidicPower, ITickable {
	
	protected int voidicPower = 0;
	protected ArrayList<EnumFacing> blockFace = new ArrayList<EnumFacing>();

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		this.voidicPower = nbt.getInteger("voidicPower");
	}

	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound nbt){
		super.func_189515_b(nbt);
		nbt.setInteger("voidicPower",  this.voidicPower);
		return nbt;
	}
	
	@Override
	public SPacketUpdateTileEntity func_189518_D_() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.func_189515_b(nbt);
		return new SPacketUpdateTileEntity(pos, 2, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public void update() {
		markDirty();
	}

	@Override
	public int getPowerAmount() {
		return voidicPower;
	}

	@Override
	public abstract int getMaxPower();

	@Override
	public abstract int maxPowerTransfer();

	@Override
	public int recievePower(int a) {
		return VoidicPowerHandler.recievePower(this, a);
	}

	@Override
	public int sendPower(int limit) {
		return VoidicPowerHandler.sendPower(this, limit);
	}
	
	@Override
	public void setPowerAmount(int amount){
		voidicPower = amount > getMaxPower() ? getMaxPower() : amount < 0 ? 0 : amount;
	}

	@Override
	public abstract boolean canOutputPower(EnumFacing face);

	@Override
	public abstract boolean canInputPower(EnumFacing face);

	@Override
	public void addBlockFace(EnumFacing face) {
		blockFace.add(face);
	}

	@Override
	public void removeBlockFace(EnumFacing face) {
		blockFace.remove(face);
	}

	@Override
	public void clearBlockFace() {
		blockFace.clear();
	}

}
