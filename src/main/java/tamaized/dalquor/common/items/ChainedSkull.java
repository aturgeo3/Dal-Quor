package tamaized.dalquor.common.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.items.TamItem;
import tamaized.dalquor.common.entity.nonliving.EntityChainedSkull;

import javax.annotation.Nullable;
import java.util.List;

public class ChainedSkull extends TamItem {

	public ChainedSkull(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (world.isRemote) {
			return EnumActionResult.PASS;
		} else {
			EntityChainedSkull entity = new EntityChainedSkull(world);
			entity.setPositionAndUpdate(pos.getX() + 0.5F, pos.getY() + 1, pos.getZ() + 0.5F);
			world.spawnEntity(entity);
			stack.shrink(1);
			return EnumActionResult.SUCCESS;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("dalquor.misc.pawn.desc"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
