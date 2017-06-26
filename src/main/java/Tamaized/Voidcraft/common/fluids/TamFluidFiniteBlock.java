package Tamaized.Voidcraft.common.fluids;

import Tamaized.TamModized.client.MeshDefinitionFix;
import Tamaized.TamModized.registry.ITamRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

public class TamFluidFiniteBlock extends BlockFluidFinite implements ITamRegistry { // TODO: MOVE THIS INTO TAMMODIZED

	private final String name;
	private final DamageSource damageSource;
	private final int damage;

	public TamFluidFiniteBlock(CreativeTabs tab, Fluid fluid, Material material, String name, DamageSource source, int dmg) {
		super(fluid, material);
		this.name = name;
		damageSource = source;
		damage = dmg;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(tab);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
	}

	public String getModelDir() {
		return "fluids";
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
		entityIn.attackEntityFrom(damageSource, damage);
	}

	@Override
	public void registerBlock(RegistryEvent.Register<Block> e) {
		e.getRegistry().register(this);
	}

	@Override
	public void registerItem(RegistryEvent.Register<Item> e) {
		e.getRegistry().register(new ItemBlock(this).setRegistryName(name));
	}

	@Override
	public void registerModel(ModelRegistryEvent e) {
		final Item item = Item.getItemFromBlock(this);
		ModelBakery.registerItemVariants(item);
		String domain = getRegistryName() == null ? "minecraft" : getRegistryName().getResourceDomain();
		ModelResourceLocation modelResourceLocation = new ModelResourceLocation(domain + ":blocks/fluids", getFluid().getName());
		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> modelResourceLocation));
		ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return modelResourceLocation;
			}
		});
	}
}
