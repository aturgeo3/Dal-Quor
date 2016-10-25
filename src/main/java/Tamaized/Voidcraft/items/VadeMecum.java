package Tamaized.Voidcraft.items;

import Tamaized.TamModized.items.TamItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VadeMecum extends TamItem {

	public VadeMecum(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (worldIn.isRemote) openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	@SideOnly(Side.CLIENT)
	private void openBook(EntityPlayer player, World world, BlockPos pos) {
		// player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecum), world, pos.getX(), pos.getY(), pos.getZ());
		net.minecraft.client.Minecraft.getMinecraft().displayGuiScreen(new Tamaized.Voidcraft.GUI.client.VadeMecumGUI(player));
	}

}
