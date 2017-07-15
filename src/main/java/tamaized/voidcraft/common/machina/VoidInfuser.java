package tamaized.voidcraft.common.machina;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.common.blocks.TamBlockContainer;
import tamaized.voidcraft.VoidCraft;
import tamaized.voidcraft.common.gui.GuiHandler;
import tamaized.voidcraft.common.machina.tileentity.TileEntityVoidInfuser;

import java.util.Random;

public class VoidInfuser extends TamBlockContainer {
	private Random rand = new Random();

	public VoidInfuser(CreativeTabs tab, Material material, String n, float hardness) {
		super(tab, material, n, hardness, SoundType.METAL);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem(hand);
		if (worldIn.isRemote) {
			return true;
		} else {
			FMLNetworkHandler.openGui(playerIn, VoidCraft.instance, GuiHandler.getTypeID(GuiHandler.Type.Infuser), worldIn, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		int l = MathHelper.floor((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		if (l == 0) {
			world.setBlockState(pos, this.getStateFromMeta(2), 2);
		}

		if (l == 1) {
			world.setBlockState(pos, this.getStateFromMeta(5), 2);
		}

		if (l == 2) {
			world.setBlockState(pos, this.getStateFromMeta(3), 2);
		}

		if (l == 3) {
			world.setBlockState(pos, this.getStateFromMeta(4), 2);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
		double d1 = (double) ((float) pos.getY() + 0.0F + rand.nextFloat() * 0.3F);
		double d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.5D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, -1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 1.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, -1.0D);

		d0 = (double) ((float) pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
		d1 = (double) ((float) pos.getY() + 0.0F + rand.nextFloat() * 0.3F);
		d2 = (double) ((float) pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.5D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, -1.0D, 0.0D, 0.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, 1.0D);
		worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, 0.0D, 0.0D, -1.0D);
	}

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new TileEntityVoidInfuser();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityVoidInfuser) {
			((TileEntityVoidInfuser) tileentity).dropInventoryItems(worldIn, pos);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}
}
