package Tamaized.Voidcraft.mobs.ai.handler.Herobrine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.BlockPos;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;

public class HerobrineAIPhase1 implements IHandlerAI {
	
	private EntityAIHandler parent;
	
	private int tick_Spawn = 0;
	private int tick_Spawn_Call = 5*20;
	private int spawns = 0;
	private int maxSpawns = 6;
	
	private ArrayList<TileEntityAIBlock> aiBlocks = new ArrayList<TileEntityAIBlock>();
	private Map<BlockPos, TileEntityAIBlock> raw = new HashMap<BlockPos, TileEntityAIBlock>();

	public HerobrineAIPhase1(EntityAIHandler entityAIHandler) {
		parent = entityAIHandler;
	}
	
	public void removeTileEntity(BlockPos pos){
		aiBlocks.remove(raw.get(pos));
		raw.remove(pos);
		spawns--;
	}
	

	@Override
	public void Init() {
		
	}

	@Override
	public void update() {
		if(parent.getEntity() == null){
			for(TileEntityAIBlock te : aiBlocks){
				te.aiHandler = null;
			}
		}
		
		if(tick_Spawn >= tick_Spawn_Call && !parent.getEntity().worldObj.isRemote){
			if(spawns < maxSpawns){
				setRandomTileEntity();
			}
			tick_Spawn = 0;
		}
		
		tick_Spawn++;
	}
	
	private void setRandomTileEntity(){
		int randX = (int) Math.floor(Math.random()*16);
		int randZ = (int) Math.floor(Math.random()*16);
		int nX = (parent.getX()-8)+randX;
		int nY = parent.getY();
		int nZ = (parent.getZ()-8)+randZ;
		if(parent.getEntity().worldObj.getTileEntity(new BlockPos(nX, nY, nZ)) == null){
			parent.getEntity().worldObj.setBlockState(new BlockPos(nX, nY, nZ), ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(true).getDefaultState());
			parent.getEntity().worldObj.setBlockState(new BlockPos(nX, nY+1, nZ), ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(false).getDefaultState());
			parent.getEntity().worldObj.setBlockState(new BlockPos(nX, nY+2, nZ), ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(false).getDefaultState());
			TileEntityAIBlock b = (TileEntityAIBlock) parent.getEntity().worldObj.getTileEntity(new BlockPos(nX, nY, nZ));
			raw.put(new BlockPos(nX, nY, nZ), b);
			b.aiHandler = parent;
			b.ai = this;
			aiBlocks.add(b);
			spawns++;
		}else{
			setRandomTileEntity();
		}
	}

	@Override
	public void kill() {
		for(TileEntityAIBlock te : aiBlocks){
			te.aiHandler = null;
		}
	}

}
