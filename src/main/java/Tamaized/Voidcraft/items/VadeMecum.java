package Tamaized.Voidcraft.items;

import java.util.HashSet;

import Tamaized.TamModized.helper.RayTraceHelper;
import Tamaized.TamModized.items.TamItem;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.GuiHandler;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.vadeMecum.IVadeMecumCapability;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class VadeMecum extends TamItem {

	public VadeMecum(CreativeTabs tab, String n, int maxStackSize) {
		super(tab, n, maxStackSize);
	}

	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (player.isSneaking()) {
			player.getCapability(CapabilityList.VADEMECUM, null).toggleBookState();
			return EnumActionResult.FAIL;
		} else {
			IVadeMecumCapability cap = player.getCapability(CapabilityList.VADEMECUM, null);
			if (cap != null && cap.getBookState()) {
				RayTraceResult result = RayTraceHelper.tracePath(world, player, 32, 1, new HashSet<Entity>());
				if(result != null){
					BlockPos bp = result.getBlockPos();
					EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, bp.getX(), bp.getY(), bp.getZ(), false);
					world.addWeatherEffect(entitylightningbolt);
					return EnumActionResult.FAIL;
				}
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
			openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote)
			openBook(playerIn, worldIn, playerIn.getPosition());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	private void openBook(EntityPlayer player, World world, BlockPos pos) {
		player.openGui(voidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.VadeMecum), world, pos.getX(),
				pos.getY(), pos.getZ());
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.isRemote){
			IVadeMecumCapability cap = entity.getCapability(CapabilityList.VADEMECUM, null);
			if(cap != null && cap.getBookState()){
				double xOffset = 0.5D;
				double yOffset = 0.5D;
				double zOffset = 0.0D;
				world.spawnParticle(EnumParticleTypes.PORTAL, entity.getPosition().getX()+xOffset, entity.getPosition().getY()+yOffset, entity.getPosition().getZ()+zOffset, 0, 1, 0);
			}
		}
	}

}
