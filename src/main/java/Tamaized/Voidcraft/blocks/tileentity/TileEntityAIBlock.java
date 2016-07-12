package Tamaized.Voidcraft.blocks.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import Tamaized.TamModized.tileentity.TamTileEntity;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;

public class TileEntityAIBlock extends TamTileEntity {
	
	public EntityAIHandler aiHandler;
	public IHandlerAI ai;
	public int state = 0;
	private boolean keep = false;
	
	public TileEntityAIBlock(){
		super();
		keep = true;
	}
	
	public int getState(){
		return state;
	}
	
	public void boom(){
		if(aiHandler != null && aiHandler.getEntity() instanceof EntityMobHerobrine && state < 3) state++;
		if(state > 2){
			((EntityMobHerobrine) aiHandler.getEntity()).doDamage(20);
			ai.removeTileEntity(pos);
			this.worldObj.setBlockToAir(pos.add(0, 2, 0));
			this.worldObj.setBlockToAir(pos.add(0, 1, 0));
			this.worldObj.setBlockToAir(pos);
			this.worldObj.removeTileEntity(pos);
		}
	}
	
	@Override
	public void update(){
		super.update();
		if(!this.worldObj.isRemote){
			if(aiHandler == null || ai == null || !keep){
				this.worldObj.setBlockToAir(pos.add(0, 2, 0));
				this.worldObj.setBlockToAir(pos.add(0, 1, 0));
				this.worldObj.setBlockToAir(pos);
				this.worldObj.removeTileEntity(pos);
			}else{
				for(int i=0; i<3; i++){
					IBlockState Bstate = this.worldObj.getBlockState(pos.add(0, i, 0));
					if(Bstate.getBlock().getMetaFromState(Bstate) != state){
						worldObj.setBlockState(pos.add(0, i, 0), Bstate.getBlock().getStateFromMeta(state), 2);
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		state = nbt.getInteger("state");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("state", state);
		return nbt;
	}

}
