package Tamaized.Voidcraft.blocks.slab;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.IBasicVoidBlock;
import Tamaized.Voidcraft.common.voidCraft;

public abstract class BasicVoidBlockSlab extends BlockSlab implements IBasicVoidBlock{
	
	private final String name;
	private static final PropertyBool VARIANT_PROPERTY = PropertyBool.create("variant");
	private static final int HALF_META_BIT = 8;

	public BasicVoidBlockSlab(Material materialIn, String n) {
		super(materialIn);
		name = n;
		setUnlocalizedName(name);
		IBlockState blockState = this.blockState.getBaseState();
		blockState = blockState.withProperty(VARIANT_PROPERTY, false);
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
		return VARIANT_PROPERTY;
	}

	@Override
	public Object getVariant(ItemStack stack) {
		return false;
	}
	
	@Override
    public final IBlockState getStateFromMeta(final int meta) {
        IBlockState blockState = this.getDefaultState();
        blockState = blockState.withProperty(VARIANT_PROPERTY, false);
        if (!isDouble()) {
            EnumBlockHalf value = EnumBlockHalf.BOTTOM;
            if((meta & HALF_META_BIT) != 0) value = EnumBlockHalf.TOP;
            blockState = blockState.withProperty(HALF, value);
        }
        return blockState;
    }
	
	@Override
    public final int getMetaFromState(final IBlockState state) {
        if(isDouble()) return 0;
        if((EnumBlockHalf) state.getValue(HALF) == EnumBlockHalf.TOP) return HALF_META_BIT;
        else return 0;
    }
	
	@Override
    public final int damageDropped(final IBlockState state) {
        return 0;
    }
	
	@Override
    public final Item getItemDropped(final IBlockState blockState, final java.util.Random random, final int unused) {
        String blockId = this.innerGetId(false);
        return GameRegistry.findItem("voidcraft", blockId);
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public final net.minecraft.item.Item getItem(final net.minecraft.world.World world, final net.minecraft.util.BlockPos blockPos) {
        String blockId = this.innerGetId(false);
        return GameRegistry.findItem("voidcraft", blockId);
    }
	
	@Override
    protected final BlockState createBlockState() {
		if(isDouble()) return new BlockState(this, new IProperty[] {VARIANT_PROPERTY});
		else return new BlockState(this, new IProperty[] {VARIANT_PROPERTY, HALF});
	}
	
	private String innerGetId(final boolean isDoubleStacked) {
        return isDoubleStacked ? "double_"+name : name;
    }
}
