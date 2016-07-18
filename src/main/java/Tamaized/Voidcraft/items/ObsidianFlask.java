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
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.projectiles.EntityObsidianFlask;

public class ObsidianFlask extends TamItem {

	public ObsidianFlask(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			EntityObsidianFlask e = new EntityObsidianFlask(worldIn, playerIn);
			e.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntityInWorld(e);
		}
		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!playerIn.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}