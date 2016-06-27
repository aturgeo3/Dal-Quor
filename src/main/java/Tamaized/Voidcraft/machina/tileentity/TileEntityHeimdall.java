package Tamaized.Voidcraft.machina.tileentity;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.machina.addons.VoidTank;

public class TileEntityHeimdall extends TileEntityInventoryBase implements IFluidHandler{
	
	public VoidTank tank;
	
	private boolean isDraining = false;
	
	public TileEntityHeimdall(){
		super(1);
		tank = new VoidTank(this, 10000);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		if(tank.getFluid() != null) tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, nbt.getInteger("fluidAmount")));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("fluidAmount", tank.getFluidAmount());
		return nbt;
	}
	
	@Override
	public void update(){
		//Generate Fluid
		if(tank.getFluidAmount() < tank.getCapacity()){
			float perc = (float)(worldObj.getActualHeight()-getPos().getY())/(float)worldObj.getActualHeight();
			int fillAmount = (int) Math.ceil(20.0f*perc);
			fill(new FluidStack(voidCraft.fluids.voidFluid, (int) Math.floor(Math.random()*(fillAmount))),true);
		}
		
		//Fill A Bucket
		if(!worldObj.isRemote){
			if(tank.getFluidAmount() >= 1000){
				if(slots[0] != null && slots[0].getItem() == Items.BUCKET){
					tank.drain(new FluidStack(voidCraft.fluids.voidFluid, 1000), true);
					slots[0] = voidCraft.fluids.getBucket();
				}
			}
		}
		
		//Check if Void Machina is nearby; if so give it fluid
		if(!worldObj.isRemote){
			if(tank.getFluidAmount() > 0){
				for(EnumFacing face : EnumFacing.VALUES){
					TileEntity te = worldObj.getTileEntity(getPos().offset(face));
					if(te instanceof IFluidHandler){
						IFluidHandler fte =  ((IFluidHandler) te);
						if(fte.fill(new FluidStack(voidCraft.fluids.voidFluid, 1), true) > 0){
							drain(new FluidStack(voidCraft.fluids.voidFluid, 1), true);
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 0 ? itemstack.isItemEqual(new ItemStack(Items.BUCKET)) : false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing var1) {
		return new int[]{0};
	}

	@Override
	protected boolean canExtractSlot(int i) {
		return i == 0 ? slots[0] != null ? slots[0].isItemEqual(voidCraft.fluids.getBucket()) : false : false;
	}

	@Override
	protected boolean canInsertSlot(int i) {
		return i == 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public String getName() {
		return "heimdall";
	}

	@Override
	public boolean hasCustomName() {
		return false;
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
	
	public int getFluidAmount(){
		return tank.getFluidAmount();
	}
	
	public int getMaxFluidAmount(){
		return tank.getCapacity();
	}
	
	public void setFluidAmount(int amount){
		tank.setFluid(new FluidStack(voidCraft.fluids.voidFluid, amount > tank.getCapacity() ? tank.getCapacity() : amount));
	}
}