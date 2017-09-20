package tamaized.voidcraft.common.entity.nonliving;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import tamaized.voidcraft.client.entity.boss.extra.EntityDolXia;
import tamaized.voidcraft.common.entity.EntityVoidNPC;

public class EntityBlockSpell extends EntityVoidNPC implements IEntityAdditionalSpawnData {

	private final EntityDolXia parent;
	private Vec3d moveTo;
	private IBlockState state = Blocks.STONE.getDefaultState();
	private Mode mode = Mode.IDLE;
	private int attackTicks;

	public EntityBlockSpell(World worldIn) {
		this(worldIn, null);
	}

	public EntityBlockSpell(World world, EntityDolXia boss) {
		super(world);
		parent = boss;
		moveHelper = new BossFlyNoclipMoveHelper(this);
		canPush = false;
		isImmuneToFire = true;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	public final Mode getMode() {
		return mode;
	}

	public final void setMode(Mode mode) {
		this.mode = mode;
		if (mode == Mode.ATTACKING)
			attackTicks = getRNG().nextInt(100) + 100;
		else
			attackTicks = 0;
	}

	public void setMoveTo(Vec3d vec) {
		moveTo = vec;
	}

	public final IBlockState getState() {
		return state;
	}

	public final void setState(IBlockState state) {
		this.state = state;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getTrueSource() instanceof EntityLivingBase) {
			EntityLivingBase attacker = (EntityLivingBase) source.getTrueSource();
			if (attacker.getHeldItemMainhand().isEmpty())
				return super.attackEntityFrom(source, amount);
			for (String tool : attacker.getHeldItemMainhand().getItem().getToolClasses(attacker.getHeldItemMainhand()))
				if (state.getBlock().isToolEffective(tool, state))
					return super.attackEntityFrom(source, getMaxHealth());
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.GENERIC, 1);
		setMode(Mode.IDLE);
		if (getRNG().nextInt(4) == 0)
			attackEntityFrom(DamageSource.GENERIC, getMaxHealth());
	}

	@Override
	public void onUpdate() {
		if (!world.isRemote && (parent == null || parent.isDead)) {
			setDead();
			return;
		}
		if (attackTicks > 0)
			attackTicks--;
		else
			setMode(Mode.IDLE);
		if (moveTo != null) {
			double dx = 0;
			double dy = 0;
			double dz = 0;
			double speed = mode == Mode.ATTACKING ? 1.5D : 1.0D;
			if (posX != moveTo.x) {
				dx = MathHelper.clamp(moveTo.x - posX, -speed, speed);
			}
			if (posY != moveTo.y) {
				dy = MathHelper.clamp(moveTo.y - posY, -speed, speed);
			}
			if (posZ != moveTo.z) {
				dz = MathHelper.clamp(moveTo.z - posZ, -speed, speed);
			}
			if (mode == Mode.IDLE) {
				dx += parent.motionX * 2;
				dy += parent.motionY * 2;
				dz += parent.motionZ * 2;
			}
			setPositionAndUpdate(posX + dx, posY + dy, posZ + dz);
		}
		super.onUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		state = Block.getStateById(nbt.getInteger("state"));
	}

	@Override
	protected void encodePacketData(ByteBuf stream) {

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("state", Block.getStateId(state));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(Block.getStateId(state));
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		state = Block.getStateById(additionalData.readInt());
	}

	public enum Mode {

		IDLE,

		ATTACKING

	}
}
