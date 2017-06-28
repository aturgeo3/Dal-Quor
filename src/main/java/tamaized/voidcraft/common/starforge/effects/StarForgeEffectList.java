package tamaized.voidcraft.common.starforge.effects;

import java.util.ArrayList;
import java.util.List;

import tamaized.voidcraft.common.starforge.effects.tool.tier1.StarForgeEffectHaste;
import tamaized.voidcraft.common.starforge.effects.tool.tier2.StarForgeEffectFortune;
import tamaized.voidcraft.common.starforge.effects.tool.tier2.StarForgeEffectSilkTouch;
import tamaized.voidcraft.common.starforge.effects.tool.tier3.StarForgeEffectThreeByThree;
import tamaized.voidcraft.common.starforge.effects.wep.tier1.StarForgeEffectBlindingFear;
import tamaized.voidcraft.common.starforge.effects.wep.tier1.StarForgeEffectFirstDegreeBurns;
import tamaized.voidcraft.common.starforge.effects.wep.tier1.StarForgeEffectVoidTouch;
import tamaized.voidcraft.common.starforge.effects.wep.tier2.StarForgeEffectSecondDegreeBurns;
import tamaized.voidcraft.common.starforge.effects.wep.tier2.StarForgeEffectVoidWrath;
import tamaized.voidcraft.common.starforge.effects.wep.tier2.StarForgeEffectXiaBlessing;
import tamaized.voidcraft.common.starforge.effects.wep.tier3.StarForgeEffectCripplingVoid;
import tamaized.voidcraft.common.starforge.effects.wep.tier3.StarForgeEffectMortalFear;
import tamaized.voidcraft.common.starforge.effects.wep.tier3.StarForgeEffectThirdDegreeBurns;
import tamaized.voidcraft.common.starforge.effects.wep.tier3.StarForgeEffectVorpal;

public final class StarForgeEffectList {

	private static final List<IStarForgeEffect> effects = new ArrayList<IStarForgeEffect>();

	public static IStarForgeEffect blindingFear;
	public static IStarForgeEffect firstDegreeBurns;
	public static IStarForgeEffect voidTouch;

	public static IStarForgeEffect xiaBlessing;
	public static IStarForgeEffect secondDegreeBurns;
	public static IStarForgeEffect voidWrath;

	public static IStarForgeEffect mortalFear;
	public static IStarForgeEffect thirdDegreeBurns;
	public static IStarForgeEffect voidCripple;
	public static IStarForgeEffect vorp;

	public static IStarForgeEffect haste;

	public static IStarForgeEffect silkTouch;
	public static IStarForgeEffect fortune;

	public static IStarForgeEffect threeByThree;

	private StarForgeEffectList() {

	}

	public static final int getEffectID(IStarForgeEffect effect) {
		return effects.indexOf(effect) + 1;
	}

	public static final IStarForgeEffect getEffect(int id) {
		return id < 1 ? null : id > effects.size() ? null : effects.get(id - 1);
	}

	public static final void register() {

		// Swords

		// Tier 1
		effects.add(blindingFear = new StarForgeEffectBlindingFear());
		effects.add(firstDegreeBurns = new StarForgeEffectFirstDegreeBurns());
		effects.add(voidTouch = new StarForgeEffectVoidTouch());

		// Tier 2
		effects.add(xiaBlessing = new StarForgeEffectXiaBlessing());
		effects.add(secondDegreeBurns = new StarForgeEffectSecondDegreeBurns());
		effects.add(voidWrath = new StarForgeEffectVoidWrath());

		// Tier 3
		effects.add(mortalFear = new StarForgeEffectMortalFear());
		effects.add(thirdDegreeBurns = new StarForgeEffectThirdDegreeBurns());
		effects.add(voidCripple = new StarForgeEffectCripplingVoid());
		effects.add(vorp = new StarForgeEffectVorpal());

		// Tools

		// Tier 1
		effects.add(haste = new StarForgeEffectHaste());

		// Tier 2
		effects.add(silkTouch = new StarForgeEffectSilkTouch());
		effects.add(fortune = new StarForgeEffectFortune());

		// Tier 3
		effects.add(threeByThree = new StarForgeEffectThreeByThree());

	}

}
