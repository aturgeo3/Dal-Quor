package Tamaized.Voidcraft.world.dim.Xia.castle.logic.battle.twins.messages;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TwinsMessages05 {
	
	public static int childPhase = 0;
	public static int childPhaseModulate = 20*5;
	
	public static boolean run(World worldObj, BlockPos pos, int tick){
		if(tick % childPhaseModulate == 0){
			for(EntityPlayer p : worldObj.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(pos.add(-50, -50, -50), pos.add(50, 50, 50)))){
				switch(childPhase){
					case 0:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] What did this player dream?"));
						break;
					case 1:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] This player dreamed of sunlight and trees. Of fire and water. It dreamed it created. And it dreamed it destroyed. It dreamed it hunted, and was hunted. It dreamed of shelter."));
						break;
					case 2:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] Hah, the original interface. A million years old, and it still works. But what true structure did this player create, in the reality behind the screen?"));
						break;
					case 3:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] It worked, with a million others, to sculpt a true world in a fold of the "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.AQUA+", and created a "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.AQUA+" for "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.AQUA+", in the "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.AQUA+"."));
						break;
					case 4:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] It cannot read that thought."));
						break;
					case 5:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] It has not yet achieved the highest level. That, it must achieve in the long dream of life, not the short dream of a game."));
						break;
					case 6:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.GREEN+"[Dol] Sometimes I wish to tell them, this world you take for truth is merely "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.GREEN+" and "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.GREEN+", I wish to tell them that they are "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.GREEN+" in the "+TextFormatting.OBFUSCATED+"qwertyui"+TextFormatting.RESET+TextFormatting.GREEN+". They see so little of reality, in their long dream."));
						break;
					case 7:
						p.addChatMessage(new TextComponentTranslation(TextFormatting.AQUA+"[Zol] "+p.getGameProfile().getName()+", go.. defeat Xia, only then will you achieve this so called \"highest level\"."));
						break;
					case 8:
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
