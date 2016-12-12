package Tamaized.Voidcraft.registry;

import java.util.ArrayList;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import Tamaized.TamModized.registry.ITamModel;
import Tamaized.TamModized.registry.ITamRegistry;
import Tamaized.Voidcraft.voidCraft;

public class VoidCraftAchievements implements ITamRegistry {

	public static Achievement voidCrystal;
	public static Achievement enterVoid;
	public static Achievement demonsCall;
	public static Achievement artifact;
	public static Achievement godsSleep;
	public static Achievement tooFar;
	public static Achievement Ascension;
	public static Achievement worldsEnd;

	public static Achievement betterThanDiamond;
	public static Achievement angelicPower;

	public static Achievement theChains;
	public static Achievement thirdDegreeBurns;
	public static Achievement Legendary;
	public static Achievement demonicPower;

	public static Achievement infuser;
	public static Achievement macerator;
	public static Achievement heimdall;

	public static Achievement generator;
	public static Achievement charger;
	public static Achievement suppressor;

	@Override
	public void preInit() {

	}

	@Override
	public void init() {
		voidCrystal = new Achievement("achievement.voidCrystal", "voidCrystal", 0, 0, voidCraft.items.voidcrystal, (Achievement) null);
		voidCrystal.setSpecial().initIndependentStat().registerStat();
		enterVoid = new Achievement("achievement.enterVoid", "enterVoid", 0, 3, voidCraft.blocks.blockVoidcrystal, voidCrystal);
		enterVoid.setSpecial().registerStat();
		demonsCall = new Achievement("achievement.demonsCall", "demonsCall", 0, 6, voidCraft.items.ChainedSkull, enterVoid);
		demonsCall.registerStat();
		artifact = new Achievement("achievement.artifact", "artifact", 0, 9, voidCraft.items.voidStar, demonsCall);
		artifact.setSpecial().registerStat();
		godsSleep = new Achievement("achievement.godsSleep", "godsSleep", 2, 9, voidCraft.blocks.blockNoBreak, artifact);
		godsSleep.registerStat();
		tooFar = new Achievement("achievement.tooFar", "tooFar", 4, 9, voidCraft.blocks.blockPortalXia, godsSleep);
		tooFar.registerStat();
		Ascension = new Achievement("achievement.Ascension", "Ascension", 6, 9, voidCraft.armors.xiaHelmet, tooFar);
		Ascension.setSpecial().registerStat();
		worldsEnd = new Achievement("achievement.worldsEnd", "worldsEnd", 8, 9, voidCraft.blocks.realityHole, Ascension);
		worldsEnd.setSpecial().registerStat();

		betterThanDiamond = new Achievement("achievement.betterThanDiamond", "betterThanDiamond", 3, 0, voidCraft.tools.voidSword, voidCrystal);
		betterThanDiamond.registerStat();
		angelicPower = new Achievement("achievement.angelicPower", "angelicPower", 6, 0, voidCraft.tools.angelicSword, betterThanDiamond);
		angelicPower.setSpecial().registerStat();

		theChains = new Achievement("achievement.theChains", "theChains", 6, 3, voidCraft.tools.chainSword, betterThanDiamond);
		theChains.registerStat();
		thirdDegreeBurns = new Achievement("achievement.thirdDegreeBurns", "thirdDegreeBurns", 6, 6, voidCraft.tools.moltenSword, theChains);
		thirdDegreeBurns.registerStat();
		Legendary = new Achievement("achievement.Legendary", "Legendary", 4, 6, voidCraft.tools.archSword, thirdDegreeBurns);
		Legendary.setSpecial().registerStat();
		demonicPower = new Achievement("achievement.demonicPower", "demonicPower", 2, 6, voidCraft.tools.demonSword, Legendary);
		demonicPower.setSpecial().registerStat();

		infuser = new Achievement("achievement.infuser", "infuser", -2, 9, voidCraft.blocks.voidInfuser, artifact);
		infuser.registerStat();
		macerator = new Achievement("achievement.macerator", "macerator", -2, 6, voidCraft.blocks.voidMacerator, infuser);
		macerator.registerStat();
		heimdall = new Achievement("achievement.heimdall", "heimdall", -2, 3, voidCraft.blocks.Heimdall, macerator);
		heimdall.registerStat();

		generator = new Achievement("achievement.generator", "generator", -4, 9, voidCraft.blocks.voidicGen, infuser);
		generator.registerStat();
		charger = new Achievement("achievement.charger", "charger", -4, 6, voidCraft.blocks.voidicCharger, generator);
		charger.setSpecial().registerStat();
		suppressor = new Achievement("achievement.suppressor", "suppressor", -4, 3, voidCraft.items.voidicSuppressor, charger);
		suppressor.registerStat();
	}

	@Override
	public void postInit() {
		Achievement[] achArray = {

				voidCrystal, enterVoid, demonsCall, artifact, godsSleep, tooFar, Ascension, worldsEnd,

				betterThanDiamond, angelicPower,

				theChains, thirdDegreeBurns, Legendary, demonicPower,

				infuser, macerator, heimdall,

				generator, charger, suppressor

		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));
	}

	@Override
	public ArrayList<ITamModel> getModelList() {
		return new ArrayList<ITamModel>();
	}

	@Override
	public String getModID() {
		return voidCraft.modid;
	}

	@Override
	public void clientPreInit() {

	}

	@Override
	public void clientInit() {

	}

	@Override
	public void clientPostInit() {

	}

}
