package Tamaized.Voidcraft.common.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.capabilities.CapabilityList;
import Tamaized.Voidcraft.capabilities.IVoidicInfusionCapability;
import Tamaized.Voidcraft.common.client.VoidCraftClientProxy;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

@SideOnly(Side.CLIENT)
public class VoidCraftClientPacketHandler{
	
	public static final int TYPE_UNUSED1 = 0;
	public static final int TYPE_UNUSED2 = 1;
	public static final int TYPE_INFUSION_UPDATE = 2;
	public static final int TYPE_INFUSION_UPDATE_ALL = 3;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientPacket(ClientCustomPacketEvent event) {
		try {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ByteBufInputStream bbis = new ByteBufInputStream(event.getPacket().payload());
			processPacketOnClient(event.getPacket().payload(), Side.CLIENT);
			bbis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void processPacketOnClient(ByteBuf parBB, Side parSide) throws IOException{
		World theWorld = Minecraft.getMinecraft().theWorld;
		if (parSide == Side.CLIENT && theWorld != null){
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType = bbis.readInt();
			if(pktType == TYPE_INFUSION_UPDATE){
				VoidCraftClientProxy.infusionHandler.amount = bbis.readInt();
				VoidCraftClientProxy.infusionHandler.maxAmount = bbis.readInt();
			}
			else if(pktType == TYPE_INFUSION_UPDATE_ALL){
				int id = bbis.readInt();
				int amount = bbis.readInt();
				int maxAmount = bbis.readInt();
				Entity e = Minecraft.getMinecraft().theWorld.getEntityByID(id);
				if(e.hasCapability(CapabilityList.VOIDICINFUSION, null)){
					IVoidicInfusionCapability cap = e.getCapability(CapabilityList.VOIDICINFUSION, null);
					cap.setInfusion(amount);
					cap.setMaxInfusion(maxAmount);
				}
			}
			bbis.close();   
		}
	}
}


