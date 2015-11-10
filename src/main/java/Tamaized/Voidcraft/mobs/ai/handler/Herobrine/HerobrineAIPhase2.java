package Tamaized.Voidcraft.mobs.ai.handler.Herobrine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.mobs.ai.EntityAIHandler;
import Tamaized.Voidcraft.mobs.ai.handler.IHandlerAI;

public class HerobrineAIPhase2 implements IHandlerAI {

	private EntityAIHandler parent;
	
	private int tick_Spawn = 0;
	private int tick_Spawn_Call = 5*20;
	private int spawns = 0;
	private int maxSpawns = 1;
	
	private ArrayList<TileEntityAIBlock> aiBlocks = new ArrayList<TileEntityAIBlock>();
	private Map<int[], TileEntityAIBlock> raw = new HashMap<int[], TileEntityAIBlock>();
	
	public HerobrineAIPhase2(EntityAIHandler entityAIHandler) {
		parent = entityAIHandler;
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub	
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
		if(parent.getEntity().worldObj.getTileEntity(nX, nY, nZ) == null){
			parent.getEntity().worldObj.setBlock(nX, nY, nZ, ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(true));
			parent.getEntity().worldObj.setBlock(nX, nY+1, nZ, ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(false));
			parent.getEntity().worldObj.setBlock(nX, nY+2, nZ, ((AIBlock) voidCraft.blocks.AIBlock).allowTileEntityCreation(false));
			TileEntityAIBlock b = (TileEntityAIBlock) parent.getEntity().worldObj.getTileEntity(nX, nY, nZ);
			raw.put(new int[]{nX, nY, nZ}, b);
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

	public void removeTileEntity(int[] i){
		aiBlocks.remove(raw.get(i));
		raw.remove(i);
		spawns--;
	}

}
