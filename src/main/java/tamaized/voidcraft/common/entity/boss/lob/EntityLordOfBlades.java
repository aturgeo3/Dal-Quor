package tamaized.voidcraft.common.entity.boss.lob;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import tamaized.voidcraft.client.entity.animation.AnimationRegistry;
import tamaized.voidcraft.client.entity.animation.IAnimation;
import tamaized.voidcraft.client.entity.boss.model.ModelLordOfBlades;
import tamaized.voidcraft.common.entity.EntityVoidBoss;
import tamaized.voidcraft.common.xiacastle.logic.battle.EntityVoidNPCAIBase;
import tamaized.voidcraft.common.xiacastle.logic.battle.IBattleHandler;
import tamaized.voidcraft.network.IVoidBossAIPacket;

import java.util.ArrayList;

public class EntityLordOfBlades extends EntityVoidBoss<IBattleHandler> {

	public static final int animations = AnimationRegistry.register(AnimationTest.class);

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		AnimationTest.play(this, AnimationTest.Type.Idle);
		return super.processInteract(player, hand);
	}

	public static class AnimationTest implements IAnimation<EntityLordOfBlades, ModelLordOfBlades> {

		public static enum Type {
			Idle, Attack, Spell, Spin, Fly, Land, Charge
		}

		public static void play(EntityLordOfBlades entity, Type type) {
			AnimationTest animation = ((AnimationTest) entity.constructAnimation(animations));
			entity.setAnimation(animation);
			entity.playAnimation();
		}

		// Degrees
		private float leftArmYaw = 0.0f;
		private float leftArmPitch = 0.0f;
		private float rightArmYaw = 0.0f;
		private float rightArmPitch = 0.0f;

		private Type type;
		private int phase = 0;

		private void init(Type type) {
			this.type = type;
			switch (type) {
				default:
				case Idle:
					break;
			}
		}

		@Override
		public boolean update(EntityLordOfBlades e) {
			return false;
		}

		@Override
		public void render(EntityLordOfBlades e, ModelLordOfBlades model) {
			float laP = (float) Math.toDegrees(model.armLeft.rotateAngleX);
			float raP = (float) Math.toDegrees(model.armRight.rotateAngleX);
			float laY = (float) Math.toDegrees(model.armLeft.rotateAngleY);
			float raY = (float) Math.toDegrees(model.armRight.rotateAngleY);
			model.setAnimations(move(laP, leftArmPitch), move(raP, rightArmPitch), move(laY, leftArmYaw), move(raY, rightArmYaw));
			if (isDone(model)) {

			}
		}

		private float move(float o, float r) {
			float speed = 1;
			float m = 0;
			if (o > r)
				m = -speed;
			else if (o < r)
				m = speed;
			return Math.min(m, m > 0 ? (r - o) : (o - r));
		}

		private boolean isDone(ModelLordOfBlades model) {
			return model.armLeft.rotateAngleY == leftArmPitch && model.armRight.rotateAngleY == rightArmPitch && model.armLeft.rotateAngleX == leftArmYaw && model.armRight.rotateAngleX == rightArmYaw;
		}

		@Override
		public void encodePacket(ByteBuf stream) {
			stream.writeFloat(leftArmYaw);
			stream.writeFloat(leftArmPitch);
			stream.writeFloat(rightArmYaw);
			stream.writeFloat(rightArmPitch);
		}

		@Override
		public void decodePacket(ByteBuf stream) {
			leftArmYaw = stream.readFloat();
			leftArmPitch = stream.readFloat();
			rightArmYaw = stream.readFloat();
			rightArmPitch = stream.readFloat();
		}

	}

	public EntityLordOfBlades(World world) {
		super(world, new IBattleHandler() {

			@Override
			public void update() {
				// TODO Auto-generated method stub

			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}

			@Override
			public void start(World worldObj, BlockPos pos) {

			}

			@Override
			public void setDone() {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isRunning() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}
		}, true);
	}

	@Override
	protected void deathHook() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initPhase(int phase) {
		switch (phase) {
			case 1:
				addAI(new EntityVoidNPCAIBase<EntityLordOfBlades>(this, getFilters()) {

					@Override
					protected void preInit() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void postInit() {
						// TODO Auto-generated method stub

					}

					@Override
					protected void update() {
						// TODO Auto-generated method stub

					}

					@Override
					public void doAction(BlockPos pos) {
						// TODO Auto-generated method stub

					}

					@Override
					public void readPacket(IVoidBossAIPacket packet) {
						// TODO Auto-generated method stub

					}
				});
				break;
		}
	}

	@Override
	protected void updatePhase(int phase) {
		// TODO Auto-generated method stub

	}

	@Override
	protected ArrayList getFilters() {
		return new ArrayList<Class>();
	}

	@Override
	protected boolean immuneToFire() {
		return true;
	}

	@Override
	protected float sizeWidth() {
		return 1.0F;
	}

	@Override
	protected float sizeHeight() {
		return 1.65F;
	}

	@Override
	protected int maxPhases() {
		return 1;
	}

	@Override
	protected void triggerOnDamage(int phase, DamageSource source, float amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("null");
	}

	@Override
	protected void encodePacketData(ByteBuf stream) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void decodePacketData(ByteBuf stream) {
		// TODO Auto-generated method stub

	}

}
