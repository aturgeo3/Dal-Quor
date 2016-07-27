package Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages;

import net.minecraft.block.BlockLever;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobDol;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobZol;

public class TwinsMessages04 {
	
	public static int childPhase = 0;
	public static int childPhaseModulate = 20*5;
	
	public static boolean run(World worldObj, BlockPos pos, int tick){
		if(tick % childPhaseModulate == 0){
			for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))){
				switch(childPhase){
					case 0:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] ..."));
						break;
					case 1:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] How about this one?"));
						break;
					case 2:
						worldObj.setBlockState(pos.add(0, 0, -3), Blocks.CHEST.getDefaultState().withProperty(Blocks.CHEST.FACING, EnumFacing.SOUTH));
						TileEntityChest te = (TileEntityChest) worldObj.getTileEntity(pos.add(0, 0, -3));
						te.setInventorySlotContents(0, new ItemStack(Items.SIGN));
						worldObj.setBlockState(pos.add(1, 0, -2), Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X));
						worldObj.setBlockState(pos.add(1, 0, -3), voidCraft.blocks.blockNoBreak.getDefaultState());
						worldObj.setBlockState(pos.add(-1, 0, -3), voidCraft.blocks.blockNoBreak.getDefaultState());
						worldObj.setBlockState(pos.add(0, 1, -3), Blocks.STANDING_SIGN.getDefaultState());
						worldObj.setBlockState(pos.add(1, 1, -3), Blocks.STANDING_SIGN.getDefaultState());
						worldObj.setBlockState(pos.add(-1, 1, -3), Blocks.STANDING_SIGN.getDefaultState());
						TileEntitySign s1 = (TileEntitySign) worldObj.getTileEntity(pos.add(-1, 1, -3));
						TileEntitySign s2 = (TileEntitySign) worldObj.getTileEntity(pos.add(0, 1, -3));
						TileEntitySign s3 = (TileEntitySign) worldObj.getTileEntity(pos.add(1, 1, -3));
						s1.signText[0] = new TextComponentString("In your future");
						s1.signText[1] = new TextComponentString("and in your past");
						s1.signText[2] = new TextComponentString("I come and go");
						s1.signText[3] = new TextComponentString("so senseless and fast");
						s2.signText[0] = new TextComponentString("My purpose is");
						s2.signText[1] = new TextComponentString("unknown to all");
						s2.signText[2] = new TextComponentString("Remembrance seems");
						s2.signText[3] = new TextComponentString("to drift then fall");
						s3.signText[0] = new TextComponentString("I travel by night");
						s3.signText[1] = new TextComponentString("and fade by day");
						s3.signText[2] = new TextComponentString("Because that is");
						s3.signText[3] = new TextComponentString("my common way");
						return true;
					default:
						break;
				}
				childPhase++;
			}
		}
		return false;
	}

}
