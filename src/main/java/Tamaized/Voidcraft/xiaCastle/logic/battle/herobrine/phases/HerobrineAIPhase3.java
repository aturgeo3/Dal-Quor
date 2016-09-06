package Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.phases;

import java.util.ArrayList;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.handlers.SkinHandler;
import Tamaized.Voidcraft.handlers.SkinHandler.PlayerNameAlias;
import Tamaized.Voidcraft.mobs.entity.EntityGhostPlayerBase;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.EntityAIHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.ai.handler.IHandlerAI;

public class HerobrineAIPhase3 implements IHandlerAI {

	private EntityAIHandler parent;
	protected World world;

	private int tick_Spawn = 0;
	private int tick_Spawn_Call = 30 * 20;
	private int spawns = 0;
	private int maxSpawns = 1;

	private EntityGhostPlayerBase currGhost;

	private ArrayList<UUID> alreadyUsed = new ArrayList<UUID>();
	private ArrayList<Integer> usedLocs = new ArrayList<Integer>();

	public HerobrineAIPhase3(EntityAIHandler entityAIHandler) {
		parent = entityAIHandler;
	}

	@Override
	public void Init() {
		world = parent.getEntity().worldObj;
		for (int x = -10; x < 10; x++) {
			for (int z = -10; z < 10; z++) {
				if ((x == 0 && z == 0) || Math.floor(Math.random() * 10) != 0) continue;
				world.setBlockState(parent.getPos().add(x, -1, z), Blocks.LAVA.getDefaultState());
			}
		}
		for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(parent.getPos().add(-10, -10, -10), parent.getPos().add(10, 10, 10)))) {
			alreadyUsed.add(player.getGameProfile().getId());
		}
	}

	@Override
	public void update() {
		if (parent.getEntity() == null) {
			if (currGhost != null) currGhost.setDead();
			currGhost = null;
		}

		if (tick_Spawn >= tick_Spawn_Call && !world.isRemote) {
			if (spawns < maxSpawns) {
				setRandomGhost(0);
			}
			tick_Spawn = 0;
		}

		if (currGhost == null) tick_Spawn++;
		else {
			if(currGhost.hasInteracted()){
				currGhost.setDead();
				currGhost = null;
				parent.getEntity().setHealth(parent.getEntity().getHealth()-25);
			}
		}
	}

	private void setRandomGhost(int j) {
		int i = 0;
		if(j == 0) i = (int) Math.floor(Math.random() * 3);
		else i = j;
		if(i > 3) i = 0;
		voidCraft.logger.info(i);
		if (usedLocs.contains(i)) {
			setRandomGhost(i+1);
			return;
		}
		PlayerNameAlias alias = getRandomUnusedAlias(0);
		alreadyUsed.add(SkinHandler.getUUID(alias));
		usedLocs.add(i);
		EntityGhostPlayerBase entity = EntityGhostPlayerBase.newInstance(world, alias);
		currGhost = entity;
		BlockPos pos = parent.getPos();
		switch (i) {
			case 0:
				entity.setLocationAndAngles(pos.getX() + 10, pos.getY(), pos.getZ(), 0, 0);
				world.spawnEntityInWorld(entity);
				break;
			case 1:
				entity.setLocationAndAngles(pos.getX() - 10, pos.getY(), pos.getZ(), 0, 0);
				world.spawnEntityInWorld(entity);
				break;
			case 2:
				entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() + 10, 0, 0);
				world.spawnEntityInWorld(entity);
				break;
			case 3:
				entity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ() - 10, 0, 0);
				world.spawnEntityInWorld(entity);
				break;
			default:
				break;
		}
	}

	private PlayerNameAlias getRandomUnusedAlias(int j) {
		int i = 0;
		if(j == 0) i = (int) Math.floor(Math.random() * PlayerNameAlias.values().length);
		else i = j;
		if(i >= PlayerNameAlias.values().length) i = 0;
		return alreadyUsed.contains(SkinHandler.getUUID(PlayerNameAlias.values()[i])) ? getRandomUnusedAlias(i+1) : PlayerNameAlias.values()[i];
	}

	@Override
	public void kill() {
		if (currGhost != null) currGhost.setDead();
		currGhost = null;
	}

	@Override
	public void removeTileEntity(BlockPos i) {

	}

}
