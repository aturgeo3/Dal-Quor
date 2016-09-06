package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.AIBlock;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.xiaCastle.logic.battle.EntityAIHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IHandlerAI;

public class HerobrineAIPhase1 implements IHandlerAI {

	private EntityAIHandler parent;
	protected World world;

	private int tick_Spawn = 0;
	private int tick_Spawn_Call = 5 * 20;
	private int spawns = 0;
	private int maxSpawns = 6;

	private ArrayList<TileEntityAIBlock> aiBlocks = new ArrayList<TileEntityAIBlock>();
	private Map<BlockPos, TileEntityAIBlock> raw = new HashMap<BlockPos, TileEntityAIBlock>();

	public HerobrineAIPhase1(EntityAIHandler entityAIHandler) {
		parent = entityAIHandler;
	}

	public void removeTileEntity(BlockPos pos) {
		aiBlocks.remove(raw.get(pos));
		raw.remove(pos);
		spawns--;
	}

	@Override
	public void Init() {
		world = parent.getEntity().worldObj;
		for (int x = -10; x < 10; x++) {
			for (int z = -10; z < 10; z++) {
				if(x==0 && z==0) continue;
				world.setBlockState(parent.getPos().add(x, -1, z), Blocks.NETHER_BRICK.getDefaultState());
			}
		}
	}

	@Override
	public void update() {
		if (parent.getEntity() == null) {
			for (TileEntityAIBlock te : aiBlocks) {
				te.setAiHandler(null);
			}
		}

		if (tick_Spawn >= tick_Spawn_Call && !world.isRemote) {
			if (spawns < maxSpawns) {
				setRandomTileEntity();
			}
			tick_Spawn = 0;
		}

		tick_Spawn++;
	}

	private void setRandomTileEntity() {
		int randX = (int) Math.floor(Math.random() * 16);
		int randZ = (int) Math.floor(Math.random() * 16);
		int nX = (parent.getX() - 8) + randX;
		int nY = parent.getY();
		int nZ = (parent.getZ() - 8) + randZ;
		if (world.getTileEntity(new BlockPos(nX, nY, nZ)) == null) {
			world.setBlockState(new BlockPos(nX, nY, nZ), ((AIBlock) voidCraft.blocks.AIBlock).getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 1, nZ), ((AIBlock) voidCraft.blocks.AIBlock).getDefaultState());
			world.setBlockState(new BlockPos(nX, nY + 2, nZ), ((AIBlock) voidCraft.blocks.AIBlock).getDefaultState());
			TileEntityAIBlock b = (TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY, nZ));
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 1, nZ))).setParent(b);
			((TileEntityAIBlock) world.getTileEntity(new BlockPos(nX, nY + 2, nZ))).setParent(b);
			raw.put(new BlockPos(nX, nY, nZ), b);
			b.setAiHandler(parent);
			b.setAi(this);
			aiBlocks.add(b);
			spawns++;
		} else {
			setRandomTileEntity();
		}
	}

	@Override
	public void kill() {
		for (TileEntityAIBlock te : aiBlocks) {
			te.setAiHandler(null);
		}
	}

}
