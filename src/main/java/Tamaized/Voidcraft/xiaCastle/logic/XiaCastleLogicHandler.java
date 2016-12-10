package Tamaized.Voidcraft.xiaCastle.logic;

import java.util.List;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.xiaCastle.logic.battle.IBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.XiaBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.Xia2BattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.herobrine.HerobrineBattleHandler;
import Tamaized.Voidcraft.xiaCastle.logic.battle.twins.TwinsBattleHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class XiaCastleLogicHandler {

	private final World world;

	public static final BlockPos LOCATION = new BlockPos(52, 55, 4);

	private boolean running = false;
	private boolean xiaDoorOpen = false;

	private final IBattleHandler twins = new TwinsBattleHandler();
	private final IBattleHandler herobrine = new HerobrineBattleHandler();
	private final IBattleHandler xia = new XiaBattleHandler();
	private final IBattleHandler xia2 = new Xia2BattleHandler();

	private BlockPos twinsLoc;
	private BlockPos herobrineLoc;
	private BlockPos xiaLoc;
	private BlockPos xia2Loc;

	public XiaCastleLogicHandler(World world) {
		this.world = world;
	}

	public void onUpdate() {
		if (world != null && !world.isRemote) {
			validateInstance();
			if (running) {
				doHandlerStartChecks();
				if (!xiaDoorOpen && twins.isDone() && herobrine.isDone()) openDoor();
				if (twins.isRunning()) twins.update();
				if (herobrine.isRunning()) herobrine.update();
				if (xia.isRunning()) xia.update();
				if (xia2.isRunning()) xia2.update();
			}
			handleProgressVisual();
		}
	}

	private void doHandlerStartChecks() {
		if (twinsLoc != null && herobrineLoc != null && xiaLoc != null && xia2Loc != null) {
			AxisAlignedBB twinsBB = new AxisAlignedBB(twinsLoc.add(-5, 0, 0), twinsLoc.add(5, 2, -6));
			AxisAlignedBB herobrineBB = new AxisAlignedBB(herobrineLoc.add(0, 0, -10), herobrineLoc.add(8, 2, 10));
			AxisAlignedBB xiaBB = new AxisAlignedBB(xiaLoc.add(-18, 0, 7), xiaLoc.add(18, 10, 15));
			List<EntityPlayer> list;
			if (!twins.isRunning() && !twins.isDone()) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, twinsBB);
				if (!list.isEmpty()) twins.start(world, twinsLoc);
			}
			if (!herobrine.isRunning() && !herobrine.isDone()) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, herobrineBB);
				if (!list.isEmpty()) herobrine.start(world, herobrineLoc);
			}
			if (!xia.isRunning() && !xia.isDone() && xiaDoorOpen) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, xiaBB);
				if (!list.isEmpty()) xia.start(world, xiaLoc);
			}
			if (!xia2.isRunning() && !xia2.isDone() && xia.isDone()) {
				xia2.start(world, xia2Loc);
			}
		}
	}

	public boolean canPlayersFly() {
		return xia.isDone();
	}

	public boolean isActive() {
		validateInstance();
		return running;
	}

	public void validateInstance() {
		if (world != null) if ((world.playerEntities.isEmpty() && ((twins.isDone() && herobrine.isDone() && xia.isDone() && xia2.isDone()) || (!twins.isDone() && !herobrine.isDone() && !xia.isDone() && !xia2.isDone()))) || xia2Loc == null || xiaLoc == null || twinsLoc == null | herobrineLoc == null) stop();
		if (!running && world != null && !world.playerEntities.isEmpty()) start();
	}

	private void handleProgressVisual() {
		BlockPos pos1 = new BlockPos(48, 79, 82);
		BlockPos pos2 = new BlockPos(56, 79, 82);
		TileEntity te1 = world.getTileEntity(pos1);
		TileEntity te2 = world.getTileEntity(pos2);
		TileEntityAIBlock ai1 = null;
		TileEntityAIBlock ai2 = null;
		if (!(te1 instanceof TileEntityAIBlock)) {
			world.setBlockState(pos1, voidCraft.blocks.AIBlock.getDefaultState());
			ai1 = (TileEntityAIBlock) world.getTileEntity(pos1);
			ai1.setFake();
		} else {
			ai1 = (TileEntityAIBlock) world.getTileEntity(pos1);
		}
		if (!(te2 instanceof TileEntityAIBlock)) {
			world.setBlockState(pos2, voidCraft.blocks.AIBlock.getDefaultState());
			ai2 = (TileEntityAIBlock) world.getTileEntity(pos2);
			ai2.setFake();
		} else {
			ai2 = (TileEntityAIBlock) world.getTileEntity(pos2);
		}
		if (running) {
			int state1 = herobrine.isDone() ? 1 : 0;
			int state2 = twins.isDone() ? 1 : 0;
			if (ai1 != null && ai1.getState() != state1) ai1.setState(state1);
			if (ai2 != null && ai2.getState() != state2) ai2.setState(state2);
		}
	}

	public void start() {
		if (world == null) return;
		stop();
		setupPos();
		twins.setDone();
		herobrine.setDone();
		xia.setDone();
		running = true;
	}

	private void setupPos() {
		twinsLoc = LOCATION.add(93 - 52, 71 - 55, 70 - 4);
		herobrineLoc = LOCATION.add(12 - 52, 71 - 55, 70 - 4);
		xiaLoc = LOCATION.add(52 - 52, 75 - 55, 85 - 4);
		xia2Loc = LOCATION.add(0, 70, 170);
	}

	public void stop() {
		twins.stop();
		herobrine.stop();
		xia.stop();
		xia2.stop();
		closeDoor();
		running = false;
	}

	private void closeDoor() {
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				world.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? voidCraft.blocks.realityHole.getDefaultState() : voidCraft.blocks.blockNoBreak.getDefaultState());
			}
		}
		xiaDoorOpen = false;
	}

	private void openDoor() {
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				world.setBlockToAir(doorPos.add(x, y, 0));
			}
		}
		xiaDoorOpen = true;
	}

	public void readNBT(NBTTagCompound nbt) {
	}

	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

}
