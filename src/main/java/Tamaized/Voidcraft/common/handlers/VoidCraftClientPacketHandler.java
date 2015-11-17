package Tamaized.Voidcraft.common.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Tamaized.Voidcraft.blocks.tileentity.TileEntityAIBlock;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class VoidCraftClientPacketHandler{
	
	public static final int TYPE_TE_UPDATE = 0;
	public static final int TYPE_VOIDBOX_UPDATE = 1;
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onClientPacket(ClientCustomPacketEvent event) {
		try {
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
			processPacketOnClient(event.packet.payload(), Side.CLIENT);
			bbis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void processPacketOnClient(ByteBuf parBB, Side parSide) throws IOException{
		World theWorld = Minecraft.getMinecraft().theWorld;
		if (parSide == Side.CLIENT){
			ByteBufInputStream bbis = new ByteBufInputStream(parBB);
			int pktType = bbis.readInt();
			if(pktType == TYPE_TE_UPDATE){
				TileEntity te = theWorld.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
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
		        	theWorld.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
		        }
		        
		        if(te instanceof TileEntityHeimdall){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		    		TileEntityHeimdall tet = (TileEntityHeimdall)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		        	theWorld.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
		        }
		        
		        if(te instanceof TileEntityVoidInfuser){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		    		TileEntityVoidInfuser tet = (TileEntityVoidInfuser)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		        	theWorld.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
		        }
		        
		        if(te instanceof TileEntityAIBlock){
		        	
		        	int state = bbis.readInt();
		        	TileEntityAIBlock tet = (TileEntityAIBlock)te;
		        	tet.state = state;
		        	theWorld.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
		        }
			}
			else if(pktType == TYPE_VOIDBOX_UPDATE){
				TileEntity te = theWorld.getTileEntity(bbis.readInt(), bbis.readInt(), bbis.readInt());
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
				theWorld.markBlockForUpdate(vbox.xCoord, vbox.yCoord, vbox.zCoord);
			}
			bbis.close();   
		}
	}
	

/*
	@Override
	public void onPacketbbis(NetworkManager manager, Packet250CustomPayload payload, Player player){
	bbisInputStream bbis = new bbisInputStream(new ByteArrayInputStream(payload.bbis)); //Handles incoming bbis
	EntityPlayer sender = (EntityPlayer) player;

	if (payload.channel.equals("VoidCraft")) {
		
		try{
			
			int x = bbis.readInt();
			int y = bbis.readInt();
			int z = bbis.readInt();
		    TileEntity te = sender.worldObj.getBlockTileEntity(x, y, z);
		    
		    if(te != null){
		        if(te instanceof TileEntityVoidMacerator){
		        	
		        	int burnTime = bbis.readInt();
		    		int cookTime = bbis.readInt();
		    		
		        	TileEntityVoidMacerator tet = (TileEntityVoidMacerator)te;
		        	tet.burnTime = burnTime;
		        	tet.cookTime = cookTime;
		            sender.worldObj.markBlockForUpdate(x, y, z);//this could also be the code to make a custom packet to send to all players
		        }
		        
		        if(te instanceof TileEntityVoidBox){
		        	
		        	boolean isPlaying = bbis.readBoolean();
		    		int colorI = bbis.readInt();
		    		int oldRecord = bbis.readInt();
		    		int LoopTime = bbis.readInt();
		    		boolean loop = bbis.readBoolean();
		    		
		        	TileEntityVoidBox tet = (TileEntityVoidBox)te;
		        	tet.isPlaying = isPlaying;
		        	tet.colorI = colorI;
		        	tet.oldRecord = oldRecord;
		        	tet.loopTime = LoopTime;
		        	tet.loop = loop;
		        	
		            sender.worldObj.markBlockForUpdate(x, y, z);//this could also be the code to make a custom packet to send to all players
		        }
		    }
		    
			}catch(IOException e){
				e.printStackTrace();
			}
		
		 //handleRandom(payload, player);
		 //(EntityPlayerMP)player.worldObj
	}


	}
	
	
	
	private void handleRandom(Packet250CustomPayload packet,Player player ) {
		 bbisInputStream inputStream = new bbisInputStream(new ByteArrayInputStream(packet.bbis));
		 Entity playerEntity = (Entity)player;
		 TileEntityVoidBox thisTileEntity;
		 int i;
		 int oldrecord;
		 int xcoord;
		 int ycoord;
		 int zcoord;
		 boolean isPlaying;
		 int colorI;
		 try {
				 i = inputStream.readInt();
				 oldrecord = inputStream.readInt();
				 xcoord = inputStream.readInt();
				 ycoord = inputStream.readInt();
				 zcoord = inputStream.readInt();
				 isPlaying = inputStream.readBoolean();
				 colorI = inputStream.readInt();
				 
				 
				
		 } catch (IOException e) {
				 e.printStackTrace();
				 return;
		 }
		 thisTileEntity =(TileEntityVoidBox) playerEntity.worldObj.getBlockTileEntity(xcoord, ycoord, zcoord);
		 if (thisTileEntity != null) {
			 try{
				 thisTileEntity=(TileEntityVoidBox)thisTileEntity;
			 if(i==1){
				 thisTileEntity.colorI = (colorI);
				 thisTileEntity.oldRecord = (thisTileEntity.getIDStackInSlot(0));
				 thisTileEntity.isPlaying = (isPlaying);
			 }
			 
			
			 }catch(Exception e){
			
			 }
		 }else{
		 }
			

		
		
	}

*/
}


