package Tamaized.Voidcraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.nonliving.EntityObsidianFlask;

public class EmptyObsidianFlask extends TamItem {

	public EmptyObsidianFlask(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		if(worldIn.provider.getDimension() != voidCraft.dimensionIdVoid && player.getPosition().getY() > 6) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		--stack.stackSize;
		ItemStack newStack = new ItemStack(voidCraft.items.obsidianFlask, 1);
		ItemHandlerHelper.giveItemToPlayer(player, newStack);
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

}