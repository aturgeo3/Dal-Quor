package Tamaized.Voidcraft.xiaCastle.logic.battle.Xia.phases.actions;

import java.io.DataOutputStream;
import java.io.IOException;

import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.animations.AnimationXiaSwordSwing;
import Tamaized.Voidcraft.entity.boss.xia.render.EntityAnimationsXia;
import Tamaized.Voidcraft.entity.client.animation.IAnimation;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;

public class XiaPhase2ActionSword {

	private final EntityBossXia xia;
	private final Vec3d throne;

	private int tick = 1;
	private int actionTick = (int) (20f * 1.5f);
	private boolean isActing = false;
	private boolean done = true;
	private EntityAnimationsXia.Animation animation;
	private IAnimation<EntityBossXia> currAnimation;

	public XiaPhase2ActionSword(EntityBossXia entity, Vec3d pos) {
		xia = entity;
		throne = pos;
	}

	public void init() {
		xia.setPosition(throne.xCoord, throne.yCoord, throne.zCoord);
		xia.setArmRotations(0, 90, 0, 90, true);
		tick = 1;
		animation = null;
		isActing = false;
		done = false;
	}

	public void update() {
		if (done) return;
		if (!isActing) {
			xia.setArmRotations(0, 0, 0, 0, true);
			if (tick % actionTick == 0) {
				animation = EntityAnimationsXia.Animation.SWORD_SWING;
				sendPacketToClients();
				currAnimation = new AnimationXiaSwordSwing();
				currAnimation.init(throne);
				isActing = true;
				tick = 1;
			}
		} else {
			if (currAnimation != null && currAnimation.update(xia)) {
				finish();
				isActing = false;
				tick = 1;
			}
		}
		tick++;
	}

	private void sendPacketToClients() {
		ByteBufOutputStream bos = new ByteBufOutputStream(Unpooled.buffer());
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeInt(ClientPacketHandler.getPacketTypeID(ClientPacketHandler.PacketType.XIA_ANIMATIONS));
			outputStream.writeInt(xia.getEntityId());
			outputStream.writeInt(EntityAnimationsXia.getAnimationID(animation));
			FMLProxyPacket packet = new FMLProxyPacket(new PacketBuffer(bos.buffer()), voidCraft.networkChannelName);
			if (voidCraft.channel != null && packet != null) voidCraft.channel.sendToAllAround(packet, new TargetPoint(xia.worldObj.provider.getDimension(), xia.posX, xia.posY, xia.posZ, 64));
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void finish() {
		currAnimation = null;
		xia.setArmRotations(0, 0, 0, 0, true);
		done = true;
	}

	public boolean isDone() {
		return done;
	}

}
