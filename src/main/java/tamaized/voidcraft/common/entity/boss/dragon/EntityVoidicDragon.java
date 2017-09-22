package tamaized.voidcraft.common.entity.boss.dragon;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tamaized.voidcraft.registry.VoidCraftItems;

public class EntityVoidicDragon extends EntityAbstractDragonOld {

	public EntityVoidicDragon(World p_i1700_1_) {
		super(p_i1700_1_);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250.0D);
	}

	@Override
	protected float getDamage() {
		return super.getDamage() + 5.0F;
	}

	@Override
	protected void dropItemsOnDeath() {
		entityDropItem(new ItemStack(VoidCraftItems.voidicDragonScale, world.rand.nextInt(2) + 1), 0.0F);
		if (world.isAirBlock(getPosition()))
			world.setBlockState(getPosition(), Blocks.DRAGON_EGG.getDefaultState());
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentString("Voidic Dragon");
	}

}
