package tamaized.dalquor.registry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tamaized.dalquor.VoidCraft;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VoidCraftAdvancements { // TODO

	public static final ModTrigger voice = new ModTrigger(new ResourceLocation(VoidCraft.modid, "voice"));
	public static final ModTrigger anchor = new ModTrigger(new ResourceLocation(VoidCraft.modid, "anchor"));
	public static final ModTrigger stabilization = new ModTrigger(new ResourceLocation(VoidCraft.modid, "stabilization"));
	public static final ModTrigger empowerment = new ModTrigger(new ResourceLocation(VoidCraft.modid, "empowerment"));
	public static final ModTrigger tolerance = new ModTrigger(new ResourceLocation(VoidCraft.modid, "tolerance"));
	public static final ModTrigger totalcontrol = new ModTrigger(new ResourceLocation(VoidCraft.modid, "totalcontrol"));
	public static final ModTrigger nightmare = new ModTrigger(new ResourceLocation(VoidCraft.modid, "nightmare"));
	public static final ModTrigger familiarity = new ModTrigger(new ResourceLocation(VoidCraft.modid, "familiarity"));


	public static void register() {
		Method method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);
		try {
			method.invoke(null, voice);
			method.invoke(null, anchor);
			method.invoke(null, stabilization);
			method.invoke(null, empowerment);
			method.invoke(null, tolerance);
			method.invoke(null, totalcontrol);
			method.invoke(null, nightmare);
			method.invoke(null, familiarity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public static void init() {

		/*

		voidCrystal = new Achievement("achievement.voidCrystal", "voidCrystal", 0, 0, VoidCraft.items.voidcrystal, (Achievement) null);
		voidCrystal.setSpecial().initIndependentStat().registerStat();
		enterVoid = new Achievement("achievement.enterVoid", "enterVoid", 0, 3, VoidCraft.blocks.blockVoidcrystal, voidCrystal);
		enterVoid.setSpecial().registerStat();
		demonsCall = new Achievement("achievement.demonsCall", "demonsCall", 0, 6, VoidCraft.items.ChainedSkull, enterVoid);
		demonsCall.registerStat();
		artifact = new Achievement("achievement.artifact", "artifact", 0, 9, VoidCraft.items.voidStar, demonsCall);
		artifact.setSpecial().registerStat();
		godsSleep = new Achievement("achievement.godsSleep", "godsSleep", 2, 9, VoidCraft.blocks.blockNoBreak, artifact);
		godsSleep.registerStat();
		tooFar = new Achievement("achievement.tooFar", "tooFar", 4, 9, VoidCraft.blocks.blockPortalXia, godsSleep);
		tooFar.registerStat();
		Ascension = new Achievement("achievement.Ascension", "Ascension", 6, 9, VoidCraft.armors.xiaHelmet, tooFar);
		Ascension.setSpecial().registerStat();
		worldsEnd = new Achievement("achievement.worldsEnd", "worldsEnd", 8, 9, VoidCraft.blocks.realityHole, Ascension);
		worldsEnd.setSpecial().registerStat();

		betterThanDiamond = new Achievement("achievement.betterThanDiamond", "betterThanDiamond", 3, 0, VoidCraft.tools.voidSword, voidCrystal);
		betterThanDiamond.registerStat();
		angelicPower = new Achievement("achievement.angelicPower", "angelicPower", 6, 0, VoidCraft.tools.angelicSword, betterThanDiamond);
		angelicPower.setSpecial().registerStat();

		theChains = new Achievement("achievement.theChains", "theChains", 6, 3, VoidCraft.tools.chainSword, betterThanDiamond);
		theChains.registerStat();
		thirdDegreeBurns = new Achievement("achievement.thirdDegreeBurns", "thirdDegreeBurns", 6, 6, VoidCraft.tools.moltenSword, theChains);
		thirdDegreeBurns.registerStat();
		Legendary = new Achievement("achievement.Legendary", "Legendary", 4, 6, VoidCraft.tools.archSword, thirdDegreeBurns);
		Legendary.setSpecial().registerStat();
		demonicPower = new Achievement("achievement.demonicPower", "demonicPower", 2, 6, VoidCraft.tools.demonSword, Legendary);
		demonicPower.setSpecial().registerStat();

		infuser = new Achievement("achievement.infuser", "infuser", -2, 9, VoidCraft.blocks.voidInfuser, artifact);
		infuser.registerStat();
		macerator = new Achievement("achievement.macerator", "macerator", -2, 6, VoidCraft.blocks.voidMacerator, infuser);
		macerator.registerStat();
		heimdall = new Achievement("achievement.heimdall", "heimdall", -2, 3, VoidCraft.blocks.Heimdall, macerator);
		heimdall.registerStat();

		generator = new Achievement("achievement.generator", "generator", -4, 9, VoidCraft.blocks.voidicGen, infuser);
		generator.registerStat();
		charger = new Achievement("achievement.charger", "charger", -4, 6, VoidCraft.blocks.voidicCharger, generator);
		charger.setSpecial().registerStat();
		suppressor = new Achievement("achievement.suppressor", "suppressor", -4, 3, VoidCraft.items.voidicSuppressor, charger);
		suppressor.registerStat();*/
	}

	/*public static Achievement vademecum;
	public static Achievement theVoice;
	public static Achievement anchor; // Require Voice
	public static Achievement stabilization; // Require Voice
	public static Achievement empowerment; // Require Voice
	public static Achievement tolerance;
	public static Achievement totalcontrol;
	public static Achievement nightmare;

	public static Achievement familiarity;

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
	public static Achievement suppressor;*/

	public static void postInit() {
		/*Achievement[] achArray = {

				familiarity,

				vademecum, theVoice, anchor, stabilization, empowerment, tolerance, totalcontrol, nightmare,

				voidCrystal, enterVoid, demonsCall, artifact, godsSleep, tooFar, Ascension, worldsEnd,

				betterThanDiamond, angelicPower,

				theChains, thirdDegreeBurns, Legendary, demonicPower,

				infuser, macerator, heimdall,

				generator, charger, suppressor

		};
		AchievementPage.registerAchievementPage(new AchievementPage("VoidCraft", achArray));*/
	}

	public static class ModTrigger implements ICriterionTrigger<ModTrigger.Instance> {
		private final Map<PlayerAdvancements, ModTrigger.Listeners> listeners = Maps.newHashMap();
		private final ResourceLocation id;

		public ModTrigger(ResourceLocation id) {
			this.id = id;
		}

		public ResourceLocation getId() {
			return this.id;
		}

		public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<ModTrigger.Instance> listener) {
			ModTrigger.Listeners modtrigger$listeners = this.listeners.computeIfAbsent(playerAdvancementsIn, Listeners::new);

			modtrigger$listeners.add(listener);
		}

		public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<ModTrigger.Instance> listener) {
			ModTrigger.Listeners modtrigger$listeners = this.listeners.get(playerAdvancementsIn);

			if (modtrigger$listeners != null) {
				modtrigger$listeners.remove(listener);

				if (modtrigger$listeners.isEmpty()) {
					this.listeners.remove(playerAdvancementsIn);
				}
			}
		}

		public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
			this.listeners.remove(playerAdvancementsIn);
		}

		/**
		 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
		 */
		public ModTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
			return new ModTrigger.Instance(this.id);
		}

		public void trigger(EntityPlayerMP player) {
			ModTrigger.Listeners modtrigger$listeners = this.listeners.get(player.getAdvancements());

			if (modtrigger$listeners != null) {
				modtrigger$listeners.trigger(player);
			}
		}

		public static class Instance extends AbstractCriterionInstance {

			public Instance(ResourceLocation criterionIn) {
				super(criterionIn);
			}
		}

		static class Listeners {
			private final PlayerAdvancements playerAdvancements;
			private final Set<Listener<ModTrigger.Instance>> listeners = Sets.newHashSet();

			public Listeners(PlayerAdvancements playerAdvancementsIn) {
				this.playerAdvancements = playerAdvancementsIn;
			}

			public boolean isEmpty() {
				return this.listeners.isEmpty();
			}

			public void add(ICriterionTrigger.Listener<ModTrigger.Instance> listener) {
				this.listeners.add(listener);
			}

			public void remove(ICriterionTrigger.Listener<ModTrigger.Instance> listener) {
				this.listeners.remove(listener);
			}

			public void trigger(EntityPlayerMP player) {
				List<Listener<ModTrigger.Instance>> list = null;

				for (ICriterionTrigger.Listener<ModTrigger.Instance> listener : this.listeners) {
					if (list == null) {
						list = Lists.newArrayList();
					}
					list.add(listener);
				}

				if (list != null) {
					for (ICriterionTrigger.Listener<ModTrigger.Instance> listener1 : list) {
						listener1.grantCriterion(this.playerAdvancements);
					}
				}
			}
		}
	}

}
