package Tamaized.Voidcraft.registry;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import Tamaized.Voidcraft.common.voidCraft;

public class Achievements extends RegistryBase {
	
	public static Achievement voidCraftAchMainLine_1;
	public static Achievement voidCraftAchMainLine_2;
	public static Achievement voidCraftAchMainLine_3;
	public static Achievement voidCraftAchMainLine_4;
	public static Achievement voidCraftAchMainLine_5;
	public static Achievement voidCraftAchMainLine_6;
	public static Achievement voidCraftAchMainLine_7;
	public static Achievement voidCraftAchMainLine_8;
	
	public static Achievement voidCraftAchSideLine1_1;
	public static Achievement voidCraftAchSideLine1_2;
	public static Achievement voidCraftAchSideLine1_3;
	public static Achievement voidCraftAchSideLine1_4;
	public static Achievement voidCraftAchSideLine1_5;
	
	public static Achievement voidCraftAchSideLine1_1_1;
	public static Achievement voidCraftAchSideLine1_1_2;
	public static Achievement voidCraftAchSideLine1_1_3;
	public static Achievement voidCraftAchSideLine1_1_4;
	
	public static Achievement voidCraftAchSideLine2_1;
	public static Achievement voidCraftAchSideLine2_2;
	public static Achievement voidCraftAchSideLine2_3;

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		voidCraftAchMainLine_1 = new Achievement("achievement.achM_1", "achM_1", 0, 0, voidCraft.items.voidcrystal, (Achievement) null);
		voidCraftAchMainLine_1.setSpecial().initIndependentStat().registerStat();
		voidCraftAchMainLine_2 = new Achievement("achievement.achM_2", "achM_2", 0, 3, voidCraft.blocks.blockVoidcrystal, voidCraftAchMainLine_1);
		voidCraftAchMainLine_2.setSpecial().registerStat();
		voidCraftAchMainLine_3 = new Achievement("achievement.achM_3", "achM_3", 0, 6, voidCraft.items.ChainedSkull, voidCraftAchMainLine_2);
		voidCraftAchMainLine_3.registerStat();
		voidCraftAchMainLine_4 = new Achievement("achievement.achM_4", "achM_4", 0, 9, voidCraft.items.voidStar, voidCraftAchMainLine_3);
		voidCraftAchMainLine_4.setSpecial().registerStat();
		voidCraftAchMainLine_5 = new Achievement("achievement.achM_5", "achM_5", 2, 9, voidCraft.blocks.blockNoBreak, voidCraftAchMainLine_4);
		voidCraftAchMainLine_5.registerStat();
		voidCraftAchMainLine_6 = new Achievement("achievement.achM_6", "achM_6", 4, 9, voidCraft.blocks.blockPortalXia, voidCraftAchMainLine_5);
		voidCraftAchMainLine_6.registerStat();
		
		voidCraftAchSideLine1_1 = new Achievement("achievement.achS1_1", "achS1_1", 3, 0, voidCraft.tools.voidSword, voidCraftAchMainLine_1);
		voidCraftAchSideLine1_1.registerStat();
		voidCraftAchSideLine1_2 = new Achievement("achievement.achS1_2", "achS1_2", 6, 0, voidCraft.tools.angelicSword, voidCraftAchSideLine1_1);
		voidCraftAchSideLine1_2.setSpecial().registerStat();
		
		voidCraftAchSideLine1_1_1 = new Achievement("achievement.achS1_1_1", "achS1_1_1", 6, 3, voidCraft.tools.chainSword, voidCraftAchSideLine1_1);
		voidCraftAchSideLine1_1_1.registerStat();
		voidCraftAchSideLine1_1_2 = new Achievement("achievement.achS1_1_2", "achS1_1_2", 6, 6, voidCraft.tools.moltenSword, voidCraftAchSideLine1_1_1);
		voidCraftAchSideLine1_1_2.registerStat();
		voidCraftAchSideLine1_1_3 = new Achievement("achievement.achS1_1_3", "achS1_1_3", 4, 6, voidCraft.tools.archSword, voidCraftAchSideLine1_1_2);
		voidCraftAchSideLine1_1_3.setSpecial().registerStat();
		voidCraftAchSideLine1_1_4 = new Achievement("achievement.achS1_1_4", "achS1_1_4", 2, 6, voidCraft.tools.demonSword, voidCraftAchSideLine1_1_3);
		voidCraftAchSideLine1_1_4.setSpecial().registerStat();


		voidCraftAchSideLine2_1 = new Achievement("achievement.achS2_1", "achS2_1", -2, 9, voidCraft.blocks.voidInfuser, voidCraftAchMainLine_4);
		voidCraftAchSideLine2_1.registerStat();
		voidCraftAchSideLine2_2 = new Achievement("achievement.achS2_2", "achS2_2", -2, 6, voidCraft.blocks.voidMacerator, voidCraftAchSideLine2_1);
		voidCraftAchSideLine2_2.registerStat();
		voidCraftAchSideLine2_3 = new Achievement("achievement.achS2_3", "achS2_3", -2, 3, voidCraft.blocks.Heimdall, voidCraftAchSideLine2_2);
		voidCraftAchSideLine2_3.registerStat();
	}

	@Override
	public void postInit() {
		Achievement[] achArray = {
				voidCraftAchMainLine_1,
				voidCraftAchMainLine_2,
				voidCraftAchMainLine_3,
				voidCraftAchMainLine_4,
				voidCraftAchMainLine_5,
				voidCraftAchMainLine_6,
				
				voidCraftAchSideLine1_1,
				voidCraftAchSideLine1_2,
				
				voidCraftAchSideLine1_1_1,
				voidCraftAchSideLine1_1_2,
				voidCraftAchSideLine1_1_3,
				voidCraftAchSideLine1_1_4,

				voidCraftAchSideLine2_1,
				voidCraftAchSideLine2_2,
				voidCraftAchSideLine2_3
		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));
	}

}
