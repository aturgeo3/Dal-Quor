package tamaized.voidcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tamaized.tammodized.registry.ITamRegistry;
import tamaized.voidcraft.common.events.VoidTickEvent;

import java.util.Random;

public class BlockDreamBed extends BlockBed implements ITamRegistry {

	private final String name;

	public BlockDreamBed(CreativeTabs tab, String n) {
		super();
		name = n;
		setUnlocalizedName(name);
		setLightLevel(1.0F);
		setRegistryName(getModelDir() + "/" + name);
		this.setCreativeTab(tab);
	}

	public String getModelDir() {
		return "blocks";
	}

	@Override
	public void registerBlock(RegistryEvent.Register<Block> e) {
		e.getRegistry().register(this);
	}

	@Override
	public void registerItem(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(new ItemBlock(this).setRegistryName(getModelDir() + "/" + name));
	}

	@Override
	public void registerModel(ModelRegistryEvent e) {
		ModelLoader.registerItemVariants(new ItemBlock(this), getRegistryName());
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
			VoidTickEvent.dream(playerIn);
		return true;
	}

}
