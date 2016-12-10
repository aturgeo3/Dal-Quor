package Tamaized.Voidcraft.entity.boss.xia.finalphase.render;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.xia.finalphase.EntityTwinsXia;
import Tamaized.Voidcraft.entity.nonliving.ProjectileDisintegration;
import Tamaized.Voidcraft.xiaCastle.logic.battle.Xia2.phases.EntityAIXia2Phase3;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo.Color;
import net.minecraft.world.World;

public class EntityDolXia extends EntityTwinsXia {

	private int actionTick = 20 * 3;

	public EntityDolXia(World worldIn) {
		super(worldIn);
		ignoreFrustumCheck = true;
	}

	public EntityDolXia(World world, EntityAIXia2Phase3 entityAIXia2Phase3) {
		super(world, entityAIXia2Phase3);
	}

	@Override
	protected void update() {
		if (!world.isRemote) {
			if (getActivePotionEffect(voidCraft.potions.acidSheath) == null) {
				clearActivePotions();
				addPotionEffect(new PotionEffect(voidCraft.potions.acidSheath, 100));
			}
			if (ticksExisted % actionTick == 0 && watchedEntity != null && watchedEntity instanceof EntityLivingBase) {
				ProjectileDisintegration disint = new ProjectileDisintegration(world, this, (EntityLivingBase) watchedEntity, 6.0F);
				world.spawnEntity(disint);
			}
		}
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentString("Dol");
	}

	@Override
	protected Color getBossBarColor() {
		return Color.GREEN;
	}

}
