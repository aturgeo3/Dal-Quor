package Tamaized.Voidcraft.items;

import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.voidCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class EmptyObsidianFlask extends TamItem {

	public EmptyObsidianFlask(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer player, EnumHand hand) {
		if (worldIn.provider.getDimension() != voidCraft.config.getDimensionIDvoid() && player.getPosition().getY() > 6) return ActionResult.newResult(EnumActionResult.FAIL, stack);
		--stack.stackSize;
		ItemStack newStack = new ItemStack(voidCraft.items.obsidianFlask, 1);
		ItemHandlerHelper.giveItemToPlayer(player, newStack);
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

}