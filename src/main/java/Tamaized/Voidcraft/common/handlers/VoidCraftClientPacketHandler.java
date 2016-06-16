package Tamaized.Voidcraft.common.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.common.client.VoidCraftClientProxy;
import Tamaized.Voidcraft.handlers.ClientPortalDataHandler;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;

@SideOnly(Side.CLIENT)
public class VoidCraftClientPacketHandler{
	
	public static final int TYPE_TE_UPDATE = 0;
	public static final int TYPE_VOIDBOX_UPDATE = 1;
	public static final int TYPE_INFUSION_UPDATE = 2;
	
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
			if(pktType == TYPE_TE_UPDATE){
				TileEntity te = theWorld.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
				if(te == null){
					bbis.close();
					return;
				}
				if(te instanceof TileEntityVoidMacerator){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		        	TileEntityVoidMacerator tet = (TileEntityVoidMacerator)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		        	te.markDirty();
		        }
		        
		        if(te instanceof TileEntityHeimdall){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		    		TileEntityHeimdall tet = (TileEntityHeimdall)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		        	te.markDirty();
		        }
		        
		        if(te instanceof TileEntityVoidInfuser){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		    		TileEntityVoidInfuser tet = (TileEntityVoidInfuser)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		        	te.markDirty();
		        }
		        
		        if(te instanceof TileEntityAIBlock){
		        	
		        	int state = bbis.readInt();
		        	TileEntityAIBlock tet = (TileEntityAIBlock)te;
		        	tet.state = state;
		        	te.markDirty();
		        }
			}
			else if(pktType == TYPE_VOIDBOX_UPDATE){
				TileEntity te = theWorld.getTileEntity(new BlockPos(bbis.readInt(), bbis.readInt(), bbis.readInt()));
				if(te == null || !(te instanceof TileEntityVoidBox)){
					bbis.close();
					return;
				}
				TileEntityVoidBox vbox = (TileEntityVoidBox) te;
				vbox.isPlaying = bbis.readBoolean();
				boolean flag = bbis.readBoolean();
				if(flag) vbox.oldRecord = Item.getItemById(bbis.readInt());
				vbox.loopTime = bbis.readInt();
				vbox.maxLoopTime = bbis.readInt();
				vbox.loop = bbis.readBoolean();
				vbox.autoFill = bbis.readBoolean();
				vbox.markDirty();
			}
			else if(pktType == TYPE_INFUSION_UPDATE){
				VoidCraftClientProxy.infusionHandler.amount = bbis.readInt();
				VoidCraftClientProxy.infusionHandler.maxAmount = bbis.readInt();
			}
			bbis.close();   
		}
	}
}


