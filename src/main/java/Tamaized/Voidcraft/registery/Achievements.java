package Tamaized.Voidcraft.registery;

import Tamaized.Voidcraft.common.voidCraft;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

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
	
	public static Achievement voidCraftAchSideLine2_1;
	public static Achievement voidCraftAchSideLine2_2;
	public static Achievement voidCraftAchSideLine2_3;
	public static Achievement voidCraftAchSideLine2_4;
	public static Achievement voidCraftAchSideLine2_5;

	@Override
	public void preInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		voidCraftAchMainLine_1 = new Achievement("achievement.achM_1", "achM_1", 0, 0, voidCraft.items.voidcrystal, (Achievement) null).setSpecial().initIndependentStat().registerStat();
		voidCraftAchMainLine_2 = new Achievement("achievement.achM_2", "achM_2", 0, 3, voidCraft.blocks.blockVoidcrystal, voidCraftAchMainLine_1).setSpecial().registerStat();
		voidCraftAchMainLine_3 = new Achievement("achievement.achM_3", "achM_3", 0, 6, voidCraft.blocks.voidInfuser, voidCraftAchMainLine_2).registerStat();
		voidCraftAchMainLine_4 = new Achievement("achievement.achM_4", "achM_4", 0, 9, voidCraft.blocks.voidMacerator, voidCraftAchMainLine_3).registerStat();
		
		voidCraftAchSideLine1_1 = new Achievement("achievement.achS1_1", "achS1_1", 3, 0, voidCraft.tools.voidSword, voidCraftAchMainLine_1).registerStat();
		voidCraftAchSideLine1_2 = new Achievement("achievement.achS1_2", "achS1_2", 6, 0, voidCraft.tools.angelicSword, voidCraftAchSideLine1_1).setSpecial().registerStat();
		
		voidCraftAchSideLine2_1 = new Achievement("achievement.achS2_1", "achS2_1", 6, 3, voidCraft.tools.chainSword, voidCraftAchSideLine1_1).registerStat();
		voidCraftAchSideLine2_2 = new Achievement("achievement.achS2_2", "achS2_2", 6, 6, voidCraft.tools.moltenSword, voidCraftAchSideLine2_1).registerStat();
		voidCraftAchSideLine2_3 = new Achievement("achievement.achS2_3", "achS2_3", 4, 6, voidCraft.tools.archSword, voidCraftAchSideLine2_2).setSpecial().registerStat();
		voidCraftAchSideLine2_4 = new Achievement("achievement.achS2_4", "achS2_4", 2, 6, voidCraft.tools.demonSword, voidCraftAchSideLine2_3).setSpecial().registerStat();
	}

	@Override
	public void postInit() {
		Achievement[] achArray = {
				voidCraftAchMainLine_1,
				voidCraftAchMainLine_2,
				voidCraftAchMainLine_3,
				voidCraftAchMainLine_4,
				voidCraftAchSideLine1_1,
				voidCraftAchSideLine1_2,
				voidCraftAchSideLine2_1,
				voidCraftAchSideLine2_2,
				voidCraftAchSideLine2_3,
				voidCraftAchSideLine2_4
		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));
	}

}
