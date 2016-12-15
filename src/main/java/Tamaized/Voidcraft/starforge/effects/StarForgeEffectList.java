package Tamaized.Voidcraft.starforge.effects;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.starforge.effects.tool.tier1.StarForgeEffectHaste;
import Tamaized.Voidcraft.starforge.effects.tool.tier2.StarForgeEffectFortune;
import Tamaized.Voidcraft.starforge.effects.tool.tier2.StarForgeEffectSilkTouch;
import Tamaized.Voidcraft.starforge.effects.tool.tier3.StarForgeEffectThreeByThree;
import Tamaized.Voidcraft.starforge.effects.wep.tier1.StarForgeEffectBlindingFear;
import Tamaized.Voidcraft.starforge.effects.wep.tier1.StarForgeEffectFirstDegreeBurns;
import Tamaized.Voidcraft.starforge.effects.wep.tier1.StarForgeEffectVoidTouch;
import Tamaized.Voidcraft.starforge.effects.wep.tier2.StarForgeEffectSecondDegreeBurns;
import Tamaized.Voidcraft.starforge.effects.wep.tier2.StarForgeEffectVoidWrath;
import Tamaized.Voidcraft.starforge.effects.wep.tier2.StarForgeEffectXiaBlessing;
import Tamaized.Voidcraft.starforge.effects.wep.tier3.StarForgeEffectCripplingVoid;
import Tamaized.Voidcraft.starforge.effects.wep.tier3.StarForgeEffectMortalFear;
import Tamaized.Voidcraft.starforge.effects.wep.tier3.StarForgeEffectThirdDegreeBurns;

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
	

	public static IStarForgeEffect haste;

	public static IStarForgeEffect silkTouch;
	public static IStarForgeEffect fortune;

	public static IStarForgeEffect threeByThree;

	private StarForgeEffectList() {

	}

	public static final int getEffectID(IStarForgeEffect effect) {
		return effects.indexOf(effect);
	}

	public static final IStarForgeEffect getEffect(int id) {
		return id < 0 ? null : id >= effects.size() ? null : effects.get(id);
	}

	public static final void register() {
		
		effects.add(blindingFear = new StarForgeEffectBlindingFear());
		effects.add(firstDegreeBurns = new StarForgeEffectFirstDegreeBurns());
		effects.add(voidTouch = new StarForgeEffectVoidTouch());

		effects.add(xiaBlessing = new StarForgeEffectXiaBlessing());
		effects.add(secondDegreeBurns = new StarForgeEffectSecondDegreeBurns());
		effects.add(voidWrath = new StarForgeEffectVoidWrath());

		effects.add(mortalFear = new StarForgeEffectMortalFear());
		effects.add(thirdDegreeBurns = new StarForgeEffectThirdDegreeBurns());
		effects.add(voidCripple = new StarForgeEffectCripplingVoid());
		

		effects.add(haste = new StarForgeEffectHaste());

		effects.add(silkTouch = new StarForgeEffectSilkTouch());
		effects.add(fortune = new StarForgeEffectFortune());

		effects.add(threeByThree = new StarForgeEffectThreeByThree());
		
	}

}
