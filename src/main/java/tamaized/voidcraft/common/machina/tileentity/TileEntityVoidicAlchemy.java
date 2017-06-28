package tamaized.voidcraft.common.machina.tileentity;

import Tamaized.TamModized.particles.FX.network.ParticleFluffPacketHandler;
import Tamaized.TamModized.tileentity.TamTileEntityInventory;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.voidicpower.TileEntityVoidicPowerInventory;
import tamaized.voidcraft.common.capabilities.CapabilityList;
import tamaized.voidcraft.common.capabilities.vadeMecum.IVadeMecumCapability;
import tamaized.voidcraft.common.capabilities.vadeMecum.VadeMecumCapabilityHandler;
import tamaized.voidcraft.common.machina.VoidMacerator;
import tamaized.voidcraft.common.machina.addons.TERecipesAlchemy.AlchemyRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nullable;
import java.util.UUID;

public class TileEntityVoidicAlchemy extends TileEntityVoidicPowerInventory {

	public TamTileEntityInventory.ItemStackFilterHandler SLOT_OUTPUT;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_1;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_2;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_3;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_4;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_5;
	public TamTileEntityInventory.ItemStackFilterHandler SLOT_INPUT_6;
	public int cookingTick = 0;
	public int finishTick = 0;
	private Item[] lastItem = new Item[6];
	private AlchemyRecipe recipe;

	private String ownerName = "";
	private UUID ownerID;
	private IVadeMecumCapability owner;
	private int capTick = 1;

	public TileEntityVoidicAlchemy() {
		super();
	}

	public void setOwner(EntityPlayer player) {
		ownerName = player.getGameProfile().getName();
		ownerID = player.getGameProfile().getId();
		owner = player.getCapability(CapabilityList.VADEMECUM, null);
		player.sendMessage(new TextComponentTranslation("voidcraft.misc.te.alch.set", player.getGameProfile().getName()));
	}

	@Override
	protected ItemStackHandler[] register() {
		SLOT_INPUT_1 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_INPUT_2 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_INPUT_3 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_INPUT_4 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_INPUT_5 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_INPUT_6 = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], true, new ItemStack[0], false);
		SLOT_OUTPUT = new TamTileEntityInventory.ItemStackFilterHandler(new ItemStack[0], false, new ItemStack[0], true);
		return new ItemStackHandler[]{SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6, SLOT_OUTPUT};
	}

	@Nullable
	@Override
	protected IItemHandler getCap(EnumFacing face) {
		return new CombinedInvWrapper(SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6, SLOT_OUTPUT);
	}

	public String getOwnerName() {
		return ownerName;
	}

	@Override
	public int getMaxPower() {
		return 50000;
	}

	@Override
	public int maxPowerTransfer() {
		return 160;
	}

	@Override
	public boolean canOutputPower(EnumFacing face) {
		return false;
	}

	@Override
	public boolean canInputPower(EnumFacing face) {
		return face != EnumFacing.UP;
	}

	@Override
	protected void readNBT(NBTTagCompound nbt) {
		cookingTick = nbt.getInteger("cookingTick");
		NBTTagCompound ownerData = nbt.getCompoundTag("ownerData");
		ownerName = ownerData.getString("ownerName");
		String id = ownerData.getString("ownerID");
		ownerID = (id.isEmpty() || id.equals("null")) ? null : UUID.fromString(id);
		NBTTagCompound capData = ownerData.getCompoundTag("capData");
		if (!capData.hasNoTags()) {
			owner = new VadeMecumCapabilityHandler();
			CapabilityList.VADEMECUM.readNBT(owner, null, capData);
		}
	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		nbt.setInteger("cookingTick", cookingTick);
		NBTTagCompound ownerData = new NBTTagCompound();
		ownerData.setString("ownerName", ownerName);
		ownerData.setString("ownerID", ownerID == null ? "null" : ownerID.toString());
		NBTTagCompound capData = owner == null ? new NBTTagCompound() : (NBTTagCompound) CapabilityList.VADEMECUM.writeNBT(owner, null);
		ownerData.setTag("capData", capData);
		nbt.setTag("ownerData", ownerData);
		return nbt;
	}

	@Override
	protected void onUpdate() {
		boolean cooking = false;

		doLastItemChecks();
		if (capTick++ % 20 == 0)
			validateCapData();

		if (voidicPower > 0 && canCook()) {
			cooking = true;
			voidicPower--;
		}

		if (!world.isRemote) {
			if (cooking) {
				cookingTick++;
				if (cookingTick % 10 == 0)
					ParticleFluffPacketHandler.spawnOnServer(world, new Vec3d(pos.getX() + 0.45F + (world.rand.nextFloat() * 0.10), pos.getY() + 0.5F, pos.getZ() + 0.45F + (world.rand.nextFloat() * 0.10)), new Vec3d(0, 0, 0), world.rand.nextInt(20) + 30, -((world.rand.nextFloat() * 0.02F) + 0.01F), (world.rand.nextFloat() * 0.75F) + 0.25F, (world.rand.nextInt(0xFFFFFF) << 8) + 0xFF);
				if (cookingTick >= (finishTick = recipe.getRequiredPower())) {
					cookingTick = 0;
					bakeItem();
					markDirty();
				}
			}

			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof VoidMacerator) {
				VoidMacerator theMacerator = (VoidMacerator) state.getBlock();
				if (theMacerator != null) {
					if (theMacerator.getIsActive(state) && !cooking)
						theMacerator.setState(false, world, pos);
					if (!theMacerator.getIsActive(state) && cooking)
						theMacerator.setState(true, world, pos);
				}
			}
		}
	}

	private void validateCapData() {
		capTick = 1;
		if (ownerID == null)
			return;
		EntityPlayer player = world.getPlayerEntityByUUID(ownerID);
		if (player != null) {
			owner = new VadeMecumCapabilityHandler();
			owner.copyFrom(player.getCapability(CapabilityList.VADEMECUM, null));
		}
	}

	private void doLastItemChecks() {
		TamTileEntityInventory.ItemStackFilterHandler[] slots = new TamTileEntityInventory.ItemStackFilterHandler[]{SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6};
		for (int i = 0; i < 6; i++) {
			if (lastItem[i] == null || slots[i].getStackInSlot(0).isEmpty() || lastItem[i] != slots[i].getStackInSlot(0).getItem()) {
				cookingTick = 0;
				lastItem[i] = (!slots[i].getStackInSlot(0).isEmpty()) ? slots[i].getStackInSlot(0).getItem() : null;
			}
		}
	}

	private boolean canCook() {
		TamTileEntityInventory.ItemStackFilterHandler[] slots = new TamTileEntityInventory.ItemStackFilterHandler[]{SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6};
		for (int i = 0; i <= 5; i++) {
			if (slots[i].getStackInSlot(0).isEmpty()) {
				return false;
			}
		}
		recipe = VoidCraft.teRecipes.alchemy.getRecipe(owner, new ItemStack[]{SLOT_INPUT_1.getStackInSlot(0), SLOT_INPUT_2.getStackInSlot(0), SLOT_INPUT_3.getStackInSlot(0), SLOT_INPUT_4.getStackInSlot(0), SLOT_INPUT_5.getStackInSlot(0), SLOT_INPUT_6.getStackInSlot(0)});
		if (recipe == null)
			return false;
		if (SLOT_OUTPUT.getStackInSlot(0).isEmpty())
			return true;
		if (!SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput()))
			return false;
		int result = SLOT_OUTPUT.getStackInSlot(0).getCount() + recipe.getOutput().getCount();
		return (result <= 64 && result <= recipe.getOutput().getMaxStackSize());
	}

	private void bakeItem() {
		if (canCook()) {
			if (SLOT_OUTPUT.getStackInSlot(0).isEmpty()) {
				SLOT_OUTPUT.setStackInSlot(0, recipe.getOutput().copy());
			} else if (SLOT_OUTPUT.getStackInSlot(0).isItemEqual(recipe.getOutput())) {
				SLOT_OUTPUT.getStackInSlot(0).grow(recipe.getOutput().getCount());
			}
			TamTileEntityInventory.ItemStackFilterHandler[] slots = new TamTileEntityInventory.ItemStackFilterHandler[]{SLOT_INPUT_1, SLOT_INPUT_2, SLOT_INPUT_3, SLOT_INPUT_4, SLOT_INPUT_5, SLOT_INPUT_6};
			for (int i = 0; i <= 5; i++) {
				slots[i].getStackInSlot(0).shrink(1);
				if (slots[i].getStackInSlot(0).getCount() <= 0) {
					slots[i].setStackInSlot(0, ItemStack.EMPTY);
				}
			}
		}
	}

}
