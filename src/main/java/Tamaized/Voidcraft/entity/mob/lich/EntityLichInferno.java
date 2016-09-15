package Tamaized.Voidcraft.entity.mob.lich;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.FireVoid;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLichInferno extends Entity {

	private int tick = 0;
	private int actionTick = 20 * 1;

	private int range = 5;

	private int currIndex = 0;
	private boolean headBack = false;

	public EntityLichInferno(World worldIn) {
		super(worldIn);
	}

	public EntityLichInferno(World world, BlockPos pos) {
		this(world);
		setPosition(pos.getX(), pos.getY(), pos.getZ());
	}

	public EntityLichInferno(World world, BlockPos pos, int range) {
		this(world, pos);
		this.range = range;
	}

	public EntityLichInferno(World world, BlockPos pos, int range, int tick) {
		this(world, pos, range);
		actionTick = tick;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (tick % actionTick == 0) {
			killFire();
			calcIndex();
			if(!isDead) spreadFire();
			tick = 0;
		}
		tick++;
	}
	
	private void calcIndex(){
		if(headBack){
			currIndex--;
			if(currIndex <= 0) setDead();
		}else{
			currIndex++;
			if(currIndex >= range) headBack = true;
		}
	}
	
	private void killFire(){
		for (int x = -currIndex; x <= currIndex; x++) {
			for (int z = -currIndex; z <= currIndex; z++) {
				if (Math.abs(x) == currIndex || Math.abs(z) == currIndex) {
					BlockPos pos = getPosition().add(x, getLowY(getPosition().add(x, 0, z))-1, z);
					IBlockState state = worldObj.getBlockState(pos);
					if (state != null && state.getBlock() instanceof FireVoid) {
						worldObj.setBlockToAir(pos);
					}
				}
			}
		}
	}

	private void spreadFire() {
		for (int x = -currIndex; x <= currIndex; x++) {
			for (int z = -currIndex; z <= currIndex; z++) {
				if (Math.abs(x) == currIndex || Math.abs(z) == currIndex) {
					placeFireAt(getPosition().add(x, getLowY(getPosition().add(x, 0, z)), z));
				}
			}
		}
	}
	
	private int getLowY(BlockPos pos){
		if(worldObj.isAirBlock(pos)) return getLowY(pos.add(0, -1, 0));
		else return pos.getY()+1;
	}

	private void placeFireAt(BlockPos pos) {
		IBlockState state = worldObj.getBlockState(pos);
		if (state == null || state.getBlock().isReplaceable(worldObj, pos)) {
			worldObj.setBlockState(pos, voidCraft.blocks.fireVoid.getDefaultState());
		}
	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {

	}

}
