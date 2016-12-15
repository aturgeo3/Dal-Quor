package Tamaized.Voidcraft.starforge.effects;

import java.util.ArrayList;
import java.util.List;

import Tamaized.Voidcraft.starforge.effects.tool.tier2.StarForgeEffectFortune;
import Tamaized.Voidcraft.starforge.effects.wep.tier1.StarForgeEffectBlindingFear;
import Tamaized.Voidcraft.starforge.effects.wep.tier1.StarForgeEffectFirstDegreeBurns;

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
		effects.add(voidTouch = new StarForgeEffectBlindingFear());

		effects.add(xiaBlessing = new StarForgeEffectBlindingFear());
		effects.add(secondDegreeBurns = new StarForgeEffectBlindingFear());
		effects.add(voidWrath = new StarForgeEffectBlindingFear());

		effects.add(mortalFear = new StarForgeEffectBlindingFear());
		effects.add(thirdDegreeBurns = new StarForgeEffectBlindingFear());
		effects.add(voidCripple = new StarForgeEffectBlindingFear());
		

		effects.add(haste = new StarForgeEffectBlindingFear());

		effects.add(silkTouch = new StarForgeEffectBlindingFear());
		effects.add(fortune = new StarForgeEffectFortune());

		effects.add(threeByThree = new StarForgeEffectBlindingFear());
		
	}

}
