package Tamaized.Voidcraft.GUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import Tamaized.Voidcraft.GUI.client.HeimdallGUI;
import Tamaized.Voidcraft.GUI.client.VoidBoxGUI;
import Tamaized.Voidcraft.GUI.client.VoidicChargerGUI;
import Tamaized.Voidcraft.GUI.client.VoidicPowerGenGUI;
import Tamaized.Voidcraft.GUI.client.voidInfuserGUI;
import Tamaized.Voidcraft.GUI.client.voidMaceratorGUI;
import Tamaized.Voidcraft.GUI.server.HeimdallContainer;
import Tamaized.Voidcraft.GUI.server.VoidBoxContainer;
import Tamaized.Voidcraft.GUI.server.VoidInfuserContainer;
import Tamaized.Voidcraft.GUI.server.VoidMaceratorContainer;
import Tamaized.Voidcraft.GUI.server.VoidicChargerContainer;
import Tamaized.Voidcraft.GUI.server.VoidicPowerGenContainer;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidBox;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidInfuser;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidMacerator;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicPowerGen;

public class GuiHandler implements IGuiHandler {

	public static final int guiIdMacerator = 0;
	public static final int guiIdBox = 1;
	public static final int guiIdInfuser = 2;
	public static final int guiIdHeimdall = 3;
	public static final int guiIdGen = 4;
	public static final int guiIdCharger = 5;
	
	public GuiHandler(){}
	
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(tileEntity != null){
			switch(id){
				case guiIdMacerator:
					if(tileEntity instanceof TileEntityVoidMacerator){
						return new VoidMaceratorContainer(player.inventory, (TileEntityVoidMacerator) tileEntity);
					}
				case guiIdBox:
					if(tileEntity instanceof TileEntityVoidBox){
						return new VoidBoxContainer(player.inventory, (TileEntityVoidBox) tileEntity);
					}
				case guiIdInfuser:
					if(tileEntity instanceof TileEntityVoidInfuser){
						return new VoidInfuserContainer(player.inventory, (TileEntityVoidInfuser) tileEntity);
					}
				case guiIdHeimdall:
					if(tileEntity instanceof TileEntityHeimdall){
						return new HeimdallContainer(player.inventory, (TileEntityHeimdall) tileEntity);
					}
				case guiIdGen:
					if(tileEntity instanceof TileEntityVoidicPowerGen){
						return new VoidicPowerGenContainer(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
					}
				case guiIdCharger:
					if(tileEntity instanceof TileEntityVoidicCharger){
						return new VoidicChargerContainer(player.inventory, (TileEntityVoidicCharger) tileEntity);
					}
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
		if(tileEntity != null){
			switch(id){
				case guiIdMacerator:
					if(tileEntity instanceof TileEntityVoidMacerator){
						return new voidMaceratorGUI(player.inventory, (TileEntityVoidMacerator) tileEntity);
					}
				case guiIdBox:
					if(tileEntity instanceof TileEntityVoidBox){
						return new VoidBoxGUI(player.inventory, (TileEntityVoidBox) tileEntity);
					}
				case guiIdInfuser:
					if(tileEntity instanceof TileEntityVoidInfuser){
						return new voidInfuserGUI(player.inventory, (TileEntityVoidInfuser) tileEntity);
					}
				case guiIdHeimdall:
					if(tileEntity instanceof TileEntityHeimdall){
						return new HeimdallGUI(player.inventory, (TileEntityHeimdall) tileEntity);
					}
				case guiIdGen:
					if(tileEntity instanceof TileEntityVoidicPowerGen){
						return new VoidicPowerGenGUI(player.inventory, (TileEntityVoidicPowerGen) tileEntity);
					}
				case guiIdCharger:
					if(tileEntity instanceof TileEntityVoidicCharger){
						return new VoidicChargerGUI(player.inventory, (TileEntityVoidicCharger) tileEntity);
					}
			}
		}
		return null;
	}
}