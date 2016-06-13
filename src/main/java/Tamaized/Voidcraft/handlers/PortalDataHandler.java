package Tamaized.Voidcraft.handlers;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.DataOutputStream;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.Teleporter;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.world.dim.TheVoid.TeleporterVoid;
import Tamaized.Voidcraft.world.dim.Xia.TeleporterXia;

public class PortalDataHandler {
	
	public static final int PORTAL_VOID = voidCraft.dimensionIdVoid;
	public static final int PORTAL_XIA = voidCraft.dimensionIdXia;
	
	private final UUID id;
	public int type;
	public float tick;
	public boolean hasTeleported;
	public int lastDim;
	public boolean active = false;
	
	public PortalDataHandler(UUID uuid, int dim){
		id = uuid;
		type = 0;
		tick = 0.0F;
		hasTeleported = false;
		lastDim = dim;
	}
	
	public UUID getID(){
		return id;
	}
	
	public Teleporter getTeleporter(EntityPlayerMP player){
		if(player.dimension == PORTAL_VOID){
			return new TeleporterVoid(player.mcServer.worldServerForDimension(lastDim)); //From Void to ?
		}else if(player.dimension == PORTAL_XIA){
			return new TeleporterXia(player.mcServer.worldServerForDimension(lastDim)); //From Xia to ?
		}else if(type == PORTAL_VOID){
			return new TeleporterVoid(player.mcServer.worldServerForDimension(PORTAL_VOID)); //From ? to Void
		}else if(type == PORTAL_XIA){
			return new TeleporterXia(player.mcServer.worldServerForDimension(PORTAL_XIA)); //From ? to Xia
		}else{
			return new TeleporterVoid(player.mcServer.worldServerForDimension(0)); //Shouldnt happen
		}
	}
}
