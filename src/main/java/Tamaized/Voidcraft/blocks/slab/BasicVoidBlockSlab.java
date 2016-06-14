package Tamaized.Voidcraft.blocks.slab;

import java.util.Random;

import net.minecraft.block.BlockPurpurSlab;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.registry.IBasicVoid;

public abstract class BasicVoidBlockSlab extends BlockSlab implements IBasicVoid{
	
	private final String name;
	private static final PropertyEnum<BasicVoidBlockSlab.Variant> VARIANT = PropertyEnum.<BasicVoidBlockSlab.Variant>create("variant", BasicVoidBlockSlab.Variant.class);
	private static final int HALF_META_BIT = 8;

	public BasicVoidBlockSlab(Material materialIn, String n) {
		super(materialIn);
		name = n;
		setUnlocalizedName(name);
		IBlockState blockState = this.blockState.getBaseState();
		if(!isDouble()){
			blockState = blockState.withProperty(HALF, EnumBlockHalf.BOTTOM);
			setCreativeTab(voidCraft.tabs.tabVoid);
		}
		setDefaultState(blockState);
		useNeighborBrightness = !isDouble();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}
	
	@Override
	public abstract boolean isDouble();
	
	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack){
		return BasicVoidBlockSlab.Variant.DEFAULT;
	}

	@Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockState = this.getDefaultState();
        blockState = blockState.withProperty(VARIANT, BasicVoidBlockSlab.Variant.DEFAULT);
        if (!isDouble()) {
            blockState = blockState.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return blockState;
    }
	
	@Override
    public final int getMetaFromState(final IBlockState state) {
		int i = 0;
		
		if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP){
			i |= 8;
		}
		
		return i;
    }
	
	@Override
    public final int damageDropped(final IBlockState state) {
        return 0;
    }
	
	@Override
    public abstract Item getItemDropped(IBlockState state, Random rand, int fortune);
	
	@Override
    protected final BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}): new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
	}
	
	public static abstract class Double extends BasicVoidBlockSlab{
		public Double(Material materialIn, String n) {
			super(materialIn, n);
		}

		@Override
		public boolean isDouble(){
			return true;
		}
	}
	
	public static abstract class Half extends BasicVoidBlockSlab{
		public Half(Material materialIn, String n) {
			super(materialIn, n);
		}

		@Override
		public boolean isDouble(){
			return false;
		}
	}
	
	public static enum Variant implements IStringSerializable{
		DEFAULT;

		@Override
		public String getName(){
			return "default";
		}
	}
}
