package tamaized.voidcraft.common.blocks.tileentity;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tamaized.tammodized.common.tileentity.TamTileEntity;
import tamaized.voidcraft.common.blocks.AIBlock;
import tamaized.voidcraft.registry.VoidCraftBlocks;

public class TileEntityAIBlock extends TamTileEntity {

	private TileEntityAIBlock parent;

	private int state = 0;
	private int oldState = state;
	private boolean dead = false;
	private boolean fake = false;
	private boolean hasEntity = false;
	private Entity entity;

	public TileEntityAIBlock() {
		super();
	}

	public void setup(TileEntityAIBlock parent) {
		this.parent = parent;
	}

	public void setEntity(Entity e) {
		entity = e;
		hasEntity = true;
	}

	public void setFake() {
		fake = true;
	}

	public boolean isDead() {
		return dead;
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
		return !dead && (oldState.getBlock() != newSate.getBlock());
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void updateState() {
		if (parent != null) {
			parent.updateState();
			return;
		}
		SoundType soundType = SoundType.GLASS;
		world.playSound(null, getPos().getX(), getPos().getY(), getPos().getZ(), soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
		if (state < 2)
			state++;
		else {
			setDead();
		}
	}

	public void setDead() {
		dead = true;
	}

	@Override
	public void onUpdate() {
		if (!this.world.isRemote) {
			if (!fake) {
				if (hasEntity && (entity == null || entity.isDead)) {
					setDead();
					world.setBlockToAir(pos);
					return;
				}
				if (parent != null) {
					state = parent.getState();
					if (parent.isDead() || world.getTileEntity(parent.pos) != parent) {
						parent = null;
						setDead();
						world.setBlockToAir(pos);
						return;
					}
				}
			}
			if (oldState != state) {
				oldState = state;
				world.setBlockState(pos, VoidCraftBlocks.AIBlock.getDefaultState().withProperty(AIBlock.STATE, state));
			}
		}
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		state = nbt.getInteger("state");
		hasEntity = nbt.getBoolean("hasEntity");
		final int id = nbt.getInteger("entityID");
		entity = world == null ? null : id >= 0 ? world.getEntityByID(id) : null;
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("state", state);
		nbt.setBoolean("hasEntity", hasEntity);
		nbt.setInteger("entityID", entity != null ? entity.getEntityId() : -1);
		return nbt;
	}

}
