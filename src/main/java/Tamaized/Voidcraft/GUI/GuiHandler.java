package Tamaized.Voidcraft.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import Tamaized.Voidcraft.GUI.client.HeimdallGUI;
import Tamaized.Voidcraft.GUI.client.VoidBoxGUI;
import Tamaized.Voidcraft.GUI.client.voidInfuserGUI;
import Tamaized.Voidcraft.GUI.client.voidMaceratorGUI;
import Tamaized.Voidcraft.GUI.server.HeimdallContainer;
import Tamaized.Voidcraft.GUI.server.VoidBoxContainer;
import Tamaized.Voidcraft.GUI.server.VoidInfuserContainer;
import Tamaized.Voidcraft.GUI.server.VoidMaceratorContainer;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler(){
	}
	
	
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        	TileEntity tileEntity = world.getTileEntity(x, y, z);
            
            if(tileEntity != null){
            	switch(id){
            		case voidCraft.guiIdMacerator:
            			if(tileEntity instanceof TileEntityVoidMacerator){
            				return new VoidMaceratorContainer(player.inventory, (TileEntityVoidMacerator) tileEntity);
            			}
            		case voidCraft.guiIdBox:
            			if(tileEntity instanceof TileEntityVoidBox){
            				return new VoidBoxContainer(player.inventory, (TileEntityVoidBox) tileEntity);
            			}
            		case voidCraft.guiIdInfuser:
            			if(tileEntity instanceof TileEntityVoidInfuser){
            				return new VoidInfuserContainer(player.inventory, (TileEntityVoidInfuser) tileEntity);
            			}
            		case voidCraft.guiIdHeimdall:
            			if(tileEntity instanceof TileEntityHeimdall){
            				return new HeimdallContainer(player.inventory, (TileEntityHeimdall) tileEntity);
            			}
            	}
            	
            
            }
            
            return null;

    }
        

        
        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                
                if(tileEntity != null){
                	switch(id){
                		case voidCraft.guiIdMacerator:
                			if(tileEntity instanceof TileEntityVoidMacerator){
                				return new voidMaceratorGUI(player.inventory, (TileEntityVoidMacerator) tileEntity);
                			}
                		case voidCraft.guiIdBox:
                			if(tileEntity instanceof TileEntityVoidBox){
                				return new VoidBoxGUI(player.inventory, (TileEntityVoidBox) tileEntity);
                			}
                		case voidCraft.guiIdInfuser:
                			if(tileEntity instanceof TileEntityVoidInfuser){
                				return new voidInfuserGUI(player.inventory, (TileEntityVoidInfuser) tileEntity);
                			}
                		case voidCraft.guiIdHeimdall:
                			if(tileEntity instanceof TileEntityHeimdall){
                				return new HeimdallGUI(player.inventory, (TileEntityHeimdall) tileEntity);
                			}
                	}
                	
                
                }
                
                return null;

        }
}