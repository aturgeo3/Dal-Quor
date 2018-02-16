package tamaized.dalquor.common.entity.boss.xia.finalphase;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tamaized.dalquor.common.entity.boss.dragon.EntityAbstractDragonOld;
import tamaized.dalquor.common.xiacastle.logic.battle.xia2.phases.EntityAIXia2Phase3;

@Deprecated
public class EntityDragonXia extends EntityAbstractDragonOld {

	private EntityAIXia2Phase3 ai;

	public EntityDragonXia(World p_i1700_1_) {
		super(p_i1700_1_);
	}

	public EntityDragonXia(World world, EntityAIXia2Phase3 entityAIXia2Phase3) {
		this(world);
		ai = entityAIXia2Phase3;
	}

	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (ai == null || ai.getEntity() == null || ai.getEntity().getCurrentPhase() != 3)
				setDead();
		}
		super.onLivingUpdate();
	}

	@Override
	public ITextComponent getAlternateBossName() {
		return new TextComponentString("Ender Dragon");
	}

}
