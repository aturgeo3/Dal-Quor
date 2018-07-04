package tamaized.dalquor.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import tamaized.dalquor.common.world.SchematicLoader;
import tamaized.tammodized.common.items.TamItem;

import javax.annotation.Nullable;
import java.util.List;

public class Debugger extends TamItem {

	SchematicLoader sut = new SchematicLoader();

	public Debugger(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			/*EntityDolXia entity = new EntityDolXia(world);
			entity.setPositionAndUpdate(player.posX, player.posY, player.posZ);
			world.spawnEntity(entity);
			new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));*/
//			if (world.provider instanceof WorldProviderXia)
//				((WorldProviderXia) world.provider).getXiaCastleHandler().debug();
			SchematicLoader.buildSchematic("xiacastle_new_2.schematic", sut, world, player.getPosition());
		}
		return super.onItemRightClick(world, player, hand);

	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.DARK_PURPLE + "Dev Tool");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
