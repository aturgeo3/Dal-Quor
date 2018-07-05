package tamaized.dalquor.common.xiacastle.logic;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.dalquor.common.blocks.tileentity.TileEntityAIBlock;
import tamaized.dalquor.common.capabilities.CapabilityList;
import tamaized.dalquor.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.dalquor.common.capabilities.voidicInfusion.IVoidicInfusionCapability;
import tamaized.dalquor.common.world.SchematicLoader;
import tamaized.dalquor.common.world.dim.xia.WorldProviderXia;
import tamaized.dalquor.common.xiacastle.TwinsSpeech;
import tamaized.dalquor.common.xiacastle.logic.battle.herobrine.HerobrineBattleHandler;
import tamaized.dalquor.common.xiacastle.logic.battle.twins.TwinsBattleHandler;
import tamaized.dalquor.common.xiacastle.logic.battle.xia.XiaBattleHandler;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.Xia2BattleHandler;
import tamaized.dalquor.registry.ModBlocks;
import tamaized.dalquor.registry.ModItems;

import java.util.List;

public class XiaCastleLogicHandler {

	private static final BlockPos LOCATION = new BlockPos(52, 55, 4);
	private final World world;
	private final TwinsBattleHandler twins = new TwinsBattleHandler();
	private final HerobrineBattleHandler herobrine = new HerobrineBattleHandler();
	private final XiaBattleHandler xia = new XiaBattleHandler();
	private final Xia2BattleHandler xia2 = new Xia2BattleHandler();
	private final TwinsSpeech twinsSpeech = new TwinsSpeech();
	private boolean running = false;
	private boolean xiaDoorOpen = false;
	private boolean hasFinished = false;
	private BlockPos twinsLoc;
	private BlockPos herobrineLoc;
	private BlockPos xiaLoc;
	private BlockPos xia2Loc;

	public XiaCastleLogicHandler(World world) {
		this.world = world;
	}

	public final TwinsBattleHandler getHandlerTwins() {
		return twins;
	}

	public final HerobrineBattleHandler getHandlerHerobrine() {
		return herobrine;
	}

	public final XiaBattleHandler getHandlerXia1() {
		return xia;
	}

	public final Xia2BattleHandler getHandlerXia2() {
		return xia2;
	}

	public void onUpdate() {
		if (world != null && !world.isRemote) {
			validateInstance();
			if (running) {
				if (hasFinished && !twinsSpeech.done())
					twinsSpeech.update(world.playerEntities);
				else {
					doHandlerStartChecks();
					if (!xiaDoorOpen && twins.isDone() && herobrine.isDone())
						openDoor();
					if (twins.isRunning())
						twins.update();
					if (herobrine.isRunning())
						herobrine.update();
					if (xia.isRunning())
						xia.update();
					if (xia2.isRunning())
						xia2.update();
					if (!hasFinished && xia2.isDone())
						finish();
				}
				handleProgressVisual();
			}
		}
	}

	private void doHandlerStartChecks() {
		if (twinsLoc != null && herobrineLoc != null && xiaLoc != null && xia2Loc != null) {
			AxisAlignedBB twinsBB = new AxisAlignedBB(twinsLoc.add(-10, 0, -10), twinsLoc.add(10, 2, 10));
			AxisAlignedBB herobrineBB = new AxisAlignedBB(herobrineLoc.add(-10, 0, -10), herobrineLoc.add(10, 2, 10));
			AxisAlignedBB xiaBB = new AxisAlignedBB(xiaLoc.add(-18, 0, 7), xiaLoc.add(18, 10, 15));
			List<EntityPlayer> list;
			if (!twins.isRunning() && !twins.isDone()) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, twinsBB);
				if (!list.isEmpty())
					twins.start(world, twinsLoc);
			}
			if (!herobrine.isRunning() && !herobrine.isDone()) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, herobrineBB);
				if (!list.isEmpty())
					herobrine.start(world, herobrineLoc);
			}
			if (!xia.isRunning() && !xia.isDone() && xiaDoorOpen) {
				list = world.getEntitiesWithinAABB(EntityPlayer.class, xiaBB);
				if (!list.isEmpty())
					xia.start(world, xiaLoc);
			}
			if (!xia2.isRunning() && !xia2.isDone() && xia.isDone()) {
				xia2.start(world, xia2Loc);
			}
		}
	}

	public boolean canPlayersFly() {
		return xia2.isRunning();
	}

	public boolean isActive() {
		validateInstance();
		return running;
	}

	public boolean hasFinished() {
		return hasFinished;
	}

	public void validateInstance() {
		if (world != null)
			if (running && ((world.playerEntities.isEmpty() && ((twins.isDone() && herobrine.isDone() && xia.isDone() && xia2.isDone()) || (!twins.isDone() && !herobrine.isDone() && !xia.isDone() && !xia2.isDone()))) || xia2Loc == null || xiaLoc == null || twinsLoc == null | herobrineLoc == null))
				stop();
		if (running && hasFinished && (twinsSpeech.done() || world.playerEntities.isEmpty()))
			stop();
		if (!running && world != null && !world.playerEntities.isEmpty())
			start();
	}

	private void handleProgressVisual() {
		BlockPos pos1 = new BlockPos(48, 79, 82);
		BlockPos pos2 = new BlockPos(56, 79, 82);
		TileEntity te1 = world.getTileEntity(pos1);
		TileEntity te2 = world.getTileEntity(pos2);
		TileEntityAIBlock ai1;
		TileEntityAIBlock ai2;
		if (!(te1 instanceof TileEntityAIBlock)) {
			world.setBlockToAir(pos1);
			world.setBlockState(pos1, ModBlocks.aiBlock.getDefaultState());
			ai1 = (TileEntityAIBlock) world.getTileEntity(pos1);
			if (ai1 != null)
				ai1.setFake();
		} else {
			ai1 = (TileEntityAIBlock) world.getTileEntity(pos1);
		}
		if (!(te2 instanceof TileEntityAIBlock)) {
			world.setBlockToAir(pos2);
			world.setBlockState(pos2, ModBlocks.aiBlock.getDefaultState());
			ai2 = (TileEntityAIBlock) world.getTileEntity(pos2);
			if (ai2 != null)
				ai2.setFake();
		} else {
			ai2 = (TileEntityAIBlock) world.getTileEntity(pos2);
		}
		if (running) {
			int state1 = herobrine.isDone() ? 1 : 0;
			int state2 = twins.isDone() ? 1 : 0;
			if (ai1 != null && ai1.getState() != state1)
				ai1.setState(state1);
			if (ai2 != null && ai2.getState() != state2)
				ai2.setState(state2);
		}
	}

	private void finish() {
		stop();
		running = true;
		SchematicLoader loader = new SchematicLoader();
		BlockPos pos = new BlockPos(5000, 100, 5000);
		SchematicLoader.buildSchematic("starforge.schematic", loader, world, pos);
		int i = 0;
		for (EntityPlayer player : world.playerEntities) {
			i++;
			player.setPositionAndUpdate(pos.getX() + 23.5, pos.getY() + 8.5, pos.getZ() + 13.5);
			IVoidicInfusionCapability cap = player.getCapability(CapabilityList.VOIDICINFUSION, null);
			if (cap != null)
				cap.setXiaDefeats(cap.getXiaDefeats() + 1);
			//			player.addStat(VoidCraft.achievements.Ascension, 1); TODO
			if (player.hasCapability(CapabilityList.VADEMECUM, null)) {
				IVadeMecumCapability vade = player.getCapability(CapabilityList.VADEMECUM, null);
				if (vade.hasCategory(IVadeMecumCapability.Category.TotalControl) && !vade.hasCategory(IVadeMecumCapability.Category.Dreams)) {
					vade.addCategory(player, IVadeMecumCapability.Category.Dreams);
					player.sendMessage(new TextComponentTranslation("dalquor.VadeMecum.voice.Dreams"));
				}
			}
		}
		BlockPos chestPos = pos.add(21, 8, 13);
		world.setBlockState(chestPos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.EAST));
		TileEntity te = world.getTileEntity(chestPos);
		if (te instanceof TileEntityChest) {
			TileEntityChest chest = (TileEntityChest) te;
			chest.setInventorySlotContents(0, new ItemStack(ModItems.quoriFragment, i));
		}
		hasFinished = true;
		if (world != null && world.provider instanceof WorldProviderXia)
			world.provider.onWorldSave();
	}

	public void start() {
		if (world == null)
			return;
		stop();
		setupPos();
		twinsSpeech.reset();
		hasFinished = false;
		running = true;
	}

	public void debug() {
		stop();
		start();
		twins.setDone();
		herobrine.setDone();
		xia.setDone();
	}

	private void setupPos() {
		twinsLoc = LOCATION.add(93 - 52, 71 - 55, 70 - 4);
		herobrineLoc = LOCATION.add(12 - 52, 71 - 55, 70 - 4);
		xiaLoc = LOCATION.add(0, 75 - 55, 85 - 4);
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
		if (!world.isBlockLoaded(new BlockPos(54, 76, 82)))
			return;
		BlockPos doorPos = new BlockPos(54, 76, 82);
		for (int x = 0; x > -5; x--) {
			for (int y = 0; y < 4; y++) {
				world.setBlockState(doorPos.add(x, y, 0), (x == 0 || x == -4 || y == 0 || y == 3) ? ModBlocks.realityHole.getDefaultState() : ModBlocks.noBreak.getDefaultState());
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
		if (twins != null && herobrine != null && xia != null) {
			if (nbt.getBoolean("herobrine"))
				herobrine.setDone();
			if (nbt.getBoolean("twins"))
				twins.setDone();
			if (nbt.getBoolean("xia"))
				xia.setDone();
		}
	}

	public NBTTagCompound writeNBT(NBTTagCompound nbt) {
		if (twins == null || herobrine == null || xia == null || xia2 == null || xia2.isDone()) {
			nbt.setBoolean("herobrine", false);
			nbt.setBoolean("twins", false);
			nbt.setBoolean("xia", false);
		} else {
			nbt.setBoolean("herobrine", herobrine.isDone());
			nbt.setBoolean("twins", twins.isDone());
			nbt.setBoolean("xia", xia.isDone());
		}
		return nbt;
	}

}
