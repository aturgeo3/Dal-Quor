package Tamaized.Voidcraft.blocks.spell.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntitySpellIceSpike extends TileEntitySpell {

	private int life = 20 * 10;
	private int tick = 0;

	@Override
	protected void readNBT(NBTTagCompound nbt) {

	}

	@Override
	protected NBTTagCompound writeNBT(NBTTagCompound nbt) {
		return nbt;
	}

	@Override
	protected void onUpdate() {
		if (!world.isRemote) {
			for (Entity e : world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(getPos().add(-3, -3, -3), getPos().add(3, 3, 3)))) {
				if (!(e instanceof EntityLivingBase)) continue;
				EntityLivingBase living = ((EntityLivingBase) e);
				living.attackEntityFrom(DamageSource.magic, 2.0f);
				living.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 5 * 20, 3));
			}
			if (tick > 0 && tick % life == 0) {
				world.setBlockToAir(getPos());
			}
		}
		tick++;
	}

}
