package Tamaized.Voidcraft.items;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.api.voidicpower.VoidicPowerItem;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.voidicPower.IVoidicPowerCapability;
import Tamaized.Voidcraft.items.inventory.InventoryItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class RealityTeleporter extends VoidicPowerItem {

	public RealityTeleporter(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	private void activate(ItemStack stack, EntityPlayer player) {
		Vec3d[] vecs = RayTraceHelper.getPlayerTraceVec(player, 64);
		RayTraceResult result = RayTraceHelper.tracePath(player.worldObj, vecs[0], vecs[1], 1, null);
		if (result != null && result.getBlockPos() != null) {
			BlockPos pos = result.getBlockPos();
			switch (result.sideHit) {
				case UP:
					pos = pos.add(0, 1, 0);
					break;
				case DOWN:
					pos = pos.add(0, -1, 0);
					break;
				case NORTH:
					pos = pos.add(0, 0, -1);
					break;
				case SOUTH:
					pos = pos.add(0, 0, 1);
					break;
				case EAST:
					pos = pos.add(1, 0, 0);
					break;
				case WEST:
					pos = pos.add(-1, 0, 0);
					break;
				default:
					break;
			}
			player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
		} else {
			player.setPositionAndUpdate(vecs[1].xCoord, vecs[1].yCoord, vecs[1].zCoord);
		}
	}
	
	public static InventoryItem createInventory(ItemStack stack){
		return new InventoryItem(stack, 1) {

			@Override
			public boolean hasCustomName() {
				return false;
			}

			@Override
			public String getName() {
				return "realityTeleporter";
			}

			@Override
			public int getInventoryStackLimit() {
				return 64;
			}

			@Override
			public ITextComponent getDisplayName() {
				return null;
			}

			@Override
			public boolean isItemValidForSlot(int index, ItemStack stack) {
				return stack == null ? false : stack.getItem() == Item.getItemFromBlock(voidCraft.blocks.realityHole);
			}
		};
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		if(world.isRemote) return ActionResult.newResult(EnumActionResult.PASS, stack);
		InventoryItem inv = createInventory(stack);
		ItemStack holes = inv.getStackInSlot(0);
		IVoidicPowerCapability cap = stack.getCapability(CapabilityList.VOIDICPOWER, null);
		if (!player.isSneaking() && cap != null && cap.getCurrentPower() >= useAmount() && holes != null && holes.getItem() == Item.getItemFromBlock(voidCraft.blocks.realityHole)) {
			activate(stack, player);
			cap.drain(useAmount());
			holes.stackSize--;
			inv.saveData();
			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		} else {
			player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.RealityTeleporter), world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
		}
		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	@Override
	protected int getDefaultVoidicPower() {
		return 0;
	}

	@Override
	protected int getDefaultMaxVoidicPower() {
		return 5000;
	}

	@Override
	protected int useAmount() {
		return 200;
	}

	@Override
	protected boolean canBeUsed() {
		return true;
	}

}
