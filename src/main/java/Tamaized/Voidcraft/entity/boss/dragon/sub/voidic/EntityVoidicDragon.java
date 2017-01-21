package Tamaized.Voidcraft.entity.boss.dragon.sub.voidic;

import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.entity.boss.dragon.EntityDragonOld;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityVoidicDragon extends EntityDragonOld {

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
		entityDropItem(new ItemStack(VoidCraft.items.voidicDragonScale, world.rand.nextInt(2) + 1), 0.0F);
	}

}
