package Tamaized.Voidcraft.blocks.tileentity;

import Tamaized.TamModized.tileentity.TamTileEntity;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityVoidNPCAIBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityAIBlock extends TamTileEntity {

	private TileEntityAIBlock parent;
	private EntityVoidNPCAIBase ai;

	private int state = 0;
	private int oldState = state;
	private boolean dead = false;

	public TileEntityAIBlock() {
		super();
	}

	public void setup(EntityVoidNPCAIBase ai, TileEntityAIBlock parent) {
		this.parent = parent;
		this.ai = ai;
	}

	public boolean isDead() {
		return dead;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return (oldState.getBlock() != newSate.getBlock());
	}

	public int getState() {
		return state;
	}

	public void boom() {
		if (ai != null) {
			if (state < 2) state++;
			else {
				ai.doAction(getPos());
				setDead();
			}
		}
	}

	public void setDead() {
		this.worldObj.setBlockToAir(pos);
		this.worldObj.removeTileEntity(pos);
		dead = true;
	}

	@Override
	public void onUpdate() {
		if (!this.worldObj.isRemote) {
			if (ai == null && parent == null) {
				setDead();
			} else {
				if (parent != null) {
					state = parent.getState();
					if (parent.isDead()) {
						parent = null;
						setDead();
						return;
					}
				}
				if (oldState != state) {
					oldState = state;
					IBlockState Bstate = this.worldObj.getBlockState(pos);
					if (Bstate.getBlock().getMetaFromState(Bstate) != state) {
						worldObj.setBlockState(pos, Bstate.getBlock().getStateFromMeta(state), 2);
					}
				}
			}
		}
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		state = nbt.getInteger("state");
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("state", state);
		return nbt;
	}

}
