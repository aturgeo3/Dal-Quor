package tamaized.dalquor.proxy;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderLightningBolt;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderParrot;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderWitherSkull;
import net.minecraft.client.renderer.entity.layers.LayerEntityOnShoulder;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tamaized.tammodized.client.entity.render.RenderDragonOld;
import tamaized.dalquor.DalQuor;
import tamaized.dalquor.client.blocks.render.RenderNoBreak;
import tamaized.dalquor.client.entity.boss.bossbar.BossBarOverlay;
import tamaized.dalquor.client.entity.boss.extra.EntityDolXia;
import tamaized.dalquor.client.entity.boss.extra.ModelHerobrineShadow;
import tamaized.dalquor.client.entity.boss.extra.RenderHerobrineCreeper;
import tamaized.dalquor.client.entity.boss.extra.RenderHerobrineShadow;
import tamaized.dalquor.client.entity.boss.extra.RenderHerobrineTNTPrimed;
import tamaized.dalquor.client.entity.boss.extra.RenderTwinsXia;
import tamaized.dalquor.client.entity.boss.extra.RenderWitherbrine;
import tamaized.dalquor.client.entity.boss.model.ModelCorruptedPawn;
import tamaized.dalquor.client.entity.boss.model.ModelLordOfBlades;
import tamaized.dalquor.client.entity.boss.model.ModelVoidBoss;
import tamaized.dalquor.client.entity.boss.model.ModelVoidBossOverlay;
import tamaized.dalquor.client.entity.boss.model.ModelXia2;
import tamaized.dalquor.client.entity.boss.render.RenderCorruptedPawn;
import tamaized.dalquor.client.entity.boss.render.RenderDol;
import tamaized.dalquor.client.entity.boss.render.RenderHerobrine;
import tamaized.dalquor.client.entity.boss.render.RenderLordOfBlades;
import tamaized.dalquor.client.entity.boss.render.RenderVoidicDragon;
import tamaized.dalquor.client.entity.boss.render.RenderXia;
import tamaized.dalquor.client.entity.boss.render.RenderXia2;
import tamaized.dalquor.client.entity.boss.render.RenderZol;
import tamaized.dalquor.client.entity.companion.layer.LayerVoidParrotShoulder;
import tamaized.dalquor.client.entity.companion.render.RenderFireElementalCompanion;
import tamaized.dalquor.client.entity.companion.render.RenderVoidParrot;
import tamaized.dalquor.client.entity.ghost.RenderGhostPlayer;
import tamaized.dalquor.client.entity.mob.RenderEtherealGuardian;
import tamaized.dalquor.client.entity.mob.model.ModelHashalaq;
import tamaized.dalquor.client.entity.mob.model.ModelLich;
import tamaized.dalquor.client.entity.mob.model.ModelSpectreChain;
import tamaized.dalquor.client.entity.mob.model.ModelVoidWrath;
import tamaized.dalquor.client.entity.mob.model.ModelWraith;
import tamaized.dalquor.client.entity.nonliving.RenderAcidBall;
import tamaized.dalquor.client.entity.nonliving.RenderBlockSpell;
import tamaized.dalquor.client.entity.nonliving.RenderChainedSkull;
import tamaized.dalquor.client.entity.nonliving.RenderObsidianFlask;
import tamaized.dalquor.client.entity.nonliving.RenderSpellImplosion;
import tamaized.dalquor.client.entity.nonliving.RenderSpellRune;
import tamaized.dalquor.client.entity.nonliving.RenderVoidChain;
import tamaized.dalquor.client.event.ClientInfusionOverlayRender;
import tamaized.dalquor.client.event.ClientRenderTicker;
import tamaized.dalquor.client.gui.VadeMecumGUI;
import tamaized.dalquor.client.layer.LayerCustomElytra;
import tamaized.dalquor.client.layer.LayerVoidSpikes;
import tamaized.dalquor.client.render.RenderGeneric;
import tamaized.dalquor.client.render.RenderNull;
import tamaized.dalquor.client.render.RenderSheathe;
import tamaized.dalquor.client.sound.BGMusic;
import tamaized.dalquor.common.blocks.TileEntityNoBreak;
import tamaized.dalquor.common.entity.boss.EntityBossCorruptedPawn;
import tamaized.dalquor.common.entity.boss.dragon.EntityVoidicDragon;
import tamaized.dalquor.common.entity.boss.herobrine.EntityBossHerobrine;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineFireball;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineShadow;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import tamaized.dalquor.common.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import tamaized.dalquor.common.entity.boss.lob.EntityLordOfBlades;
import tamaized.dalquor.common.entity.boss.twins.EntityBossDol;
import tamaized.dalquor.common.entity.boss.twins.EntityBossZol;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia;
import tamaized.dalquor.common.entity.boss.xia.EntityBossXia2;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityDragonXia;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityWitherbrine;
import tamaized.dalquor.common.entity.boss.xia.finalphase.EntityZolXia;
import tamaized.dalquor.common.entity.companion.EntityCompanionFireElemental;
import tamaized.dalquor.common.entity.companion.EntityVoidParrot;
import tamaized.dalquor.common.entity.ghost.EntityGhostPlayer;
import tamaized.dalquor.common.entity.ghost.EntityGhostPlayerSlim;
import tamaized.dalquor.common.entity.mob.EntityMobEtherealGuardian;
import tamaized.dalquor.common.entity.mob.EntityMobLich;
import tamaized.dalquor.common.entity.mob.EntityMobSpectreChain;
import tamaized.dalquor.common.entity.mob.EntityMobVoidWrath;
import tamaized.dalquor.common.entity.mob.EntityMobWraith;
import tamaized.dalquor.common.entity.mob.dalquor.EntityHashalaq;
import tamaized.dalquor.common.entity.mob.lich.EntityLichInferno;
import tamaized.dalquor.common.entity.nonliving.EntityAcidBall;
import tamaized.dalquor.common.entity.nonliving.EntityBlockSpell;
import tamaized.dalquor.common.entity.nonliving.EntityCasterLightningBolt;
import tamaized.dalquor.common.entity.nonliving.EntityChainedSkull;
import tamaized.dalquor.common.entity.nonliving.EntityObsidianFlask;
import tamaized.dalquor.common.entity.nonliving.EntitySpellImplosion;
import tamaized.dalquor.common.entity.nonliving.EntitySpellRune;
import tamaized.dalquor.common.entity.nonliving.EntityVoidChain;
import tamaized.dalquor.common.entity.nonliving.ProjectileDisintegration;
import tamaized.dalquor.common.events.client.DebugEvent;
import tamaized.dalquor.common.events.client.TextureStitch;
import tamaized.dalquor.common.vademecum.contents.VadeMecumMainEntry;
import tamaized.dalquor.registry.ModBlocks;

import java.util.List;

public class ClientProxy extends CommonProxy {

	public static VadeMecumGUI vadeMecum;
	public static VadeMecumMainEntry vadeMecumEntryList;

	public ClientProxy() {
		super(Side.CLIENT);
	}

	@Override
	public void preRegisters() {
		OBJLoader.INSTANCE.addDomain(DalQuor.modid);
	}

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new DebugEvent());
		MinecraftForge.EVENT_BUS.register(new ClientInfusionOverlayRender());
		MinecraftForge.EVENT_BUS.register(new tamaized.dalquor.client.render.RenderPlayer());
		MinecraftForge.EVENT_BUS.register(new tamaized.dalquor.client.render.RenderLiving());
		MinecraftForge.EVENT_BUS.register(new RenderSheathe());
		MinecraftForge.EVENT_BUS.register(new ClientRenderTicker());
		MinecraftForge.EVENT_BUS.register(new TextureStitch());
		vadeMecumEntryList = new VadeMecumMainEntry();
		vadeMecumEntryList.preLoadObject();

		float shadowSize = 0.5F;
		// MOBS
		RenderingRegistry.registerEntityRenderingHandler(EntityLordOfBlades.class, manager -> new RenderLordOfBlades(manager, new ModelLordOfBlades(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/lordofblades.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityHashalaq.class, manager -> new RenderGeneric(manager, new ModelHashalaq(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/hashalaq.png"), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobWraith.class, manager -> new RenderGeneric(manager, new ModelWraith(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/wraith.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobSpectreChain.class, manager -> new RenderGeneric(manager, new ModelSpectreChain(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/spectrechain.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobLich.class, manager -> new RenderGeneric(manager, new ModelLich(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/voidiclich.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidWrath.class, manager -> new RenderGeneric(manager, new ModelVoidWrath(), shadowSize, new ResourceLocation(DalQuor.modid, "textures/entity/voidwrath.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossCorruptedPawn.class, manager -> new RenderCorruptedPawn(manager, new ModelCorruptedPawn(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossHerobrine.class, manager -> new RenderHerobrine(manager, new ModelVoidBoss<EntityBossHerobrine>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossDol.class, manager -> new RenderDol(manager, shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossZol.class, manager -> new RenderZol(manager, new ModelVoidBoss<EntityBossZol>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia.class, manager -> new RenderXia(manager, new ModelVoidBossOverlay<EntityBossXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia2.class, manager -> new RenderXia2(manager, new ModelXia2<EntityBossXia2>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPlayer.class, manager -> new RenderGhostPlayer(manager, false));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPlayerSlim.class, manager -> new RenderGhostPlayer(manager, true));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineCreeper.class, RenderHerobrineCreeper::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineWitherSkull.class, RenderWitherSkull::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineTNTPrimed.class, RenderHerobrineTNTPrimed::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineShadow.class, manager -> new RenderHerobrineShadow(manager, new ModelHerobrineShadow<EntityHerobrineShadow>()));
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherbrine.class, RenderWitherbrine::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonXia.class, RenderDragonOld::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidicDragon.class, RenderVoidicDragon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDolXia.class, manager -> new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_DOL, new ModelVoidBoss<EntityDolXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZolXia.class, manager -> new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_ZOL, new ModelVoidBoss<EntityZolXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobEtherealGuardian.class, manager -> new RenderEtherealGuardian(manager, shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCompanionFireElemental.class, manager -> new RenderFireElementalCompanion(manager, shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidChain.class, RenderVoidChain::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidBall.class, RenderAcidBall::new);
		RenderingRegistry.registerEntityRenderingHandler(ProjectileDisintegration.class, RenderAcidBall::new);
		// RenderingRegistry.registerEntityRenderingHandler(EntityHookShot.class, new RenderHook(manager));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineFireball.class, manager -> new RenderFireball(manager, 2.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLichInferno.class, RenderNull::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianFlask.class, manager -> new RenderObsidianFlask(manager, Minecraft.getMinecraft().getRenderItem()));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellRune.class, RenderSpellRune::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityCasterLightningBolt.class, RenderLightningBolt::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpellImplosion.class, RenderSpellImplosion::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidParrot.class, RenderVoidParrot::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityChainedSkull.class, RenderChainedSkull::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityBlockSpell.class, RenderBlockSpell::new);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		ModBlocks.clientInit();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, new RenderNoBreak());

		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(ModBlocks.noBreak), 0, TileEntityNoBreak.class);

		MinecraftForge.EVENT_BUS.register(new BGMusic());
	}

	@Override
	public void postInit() {

		RenderPlayer playerRenderer = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
		try {
			for (LayerRenderer layer : (List<LayerRenderer>) ReflectionHelper.findField(RenderLivingBase.class, "layerRenderers", "field_177097_h").get(playerRenderer)) {
				if (layer instanceof LayerEntityOnShoulder) {
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftResource", "field_192869_e").set(layer, RenderVoidParrot.TEXTURE);
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightResource", "field_192873_i").set(layer, RenderVoidParrot.TEXTURE);
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftRenderer", "field_192865_a").set(layer, new RenderParrot(playerRenderer.getRenderManager()));
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightRenderer", "field_192866_b").set(layer, new RenderParrot(playerRenderer.getRenderManager()));
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftModel", "field_192868_d").set(layer, new ModelParrot());
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightModel", "field_192872_h").set(layer, new ModelParrot());
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		playerRenderer.addLayer(new LayerVoidSpikes(playerRenderer));
		playerRenderer.addLayer(new LayerCustomElytra(playerRenderer));
		playerRenderer.addLayer(new LayerVoidParrotShoulder(playerRenderer.getRenderManager()));
		// playerRenderer.addLayer(new LayerSheath(playerRenderer));

		RenderPlayer playerRendererSlim = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
		try {
			for (LayerRenderer layer : (List<LayerRenderer>) ReflectionHelper.findField(RenderLivingBase.class, "layerRenderers", "field_177097_h").get(playerRendererSlim)) {
				if (layer instanceof LayerEntityOnShoulder) {
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftResource", "field_192869_e").set(layer, RenderVoidParrot.TEXTURE);
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightResource", "field_192873_i").set(layer, RenderVoidParrot.TEXTURE);
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftRenderer", "field_192865_a").set(layer, new RenderParrot(playerRendererSlim.getRenderManager()));
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightRenderer", "field_192866_b").set(layer, new RenderParrot(playerRendererSlim.getRenderManager()));
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "leftModel", "field_192868_d").set(layer, new ModelParrot());
					ReflectionHelper.findField(LayerEntityOnShoulder.class, "rightModel", "field_192872_h").set(layer, new ModelParrot());
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		playerRendererSlim.addLayer(new LayerVoidSpikes(playerRendererSlim));
		playerRendererSlim.addLayer(new LayerCustomElytra(playerRendererSlim));
		playerRendererSlim.addLayer(new LayerVoidParrotShoulder(playerRendererSlim.getRenderManager()));
		// playerRendererSlim.addLayer(new LayerSheath(playerRendererSlim));

	}

	@Override
	public void fillProfileProperties(GameProfile profile, boolean verify) {
		net.minecraft.client.Minecraft.getMinecraft().getSessionService().fillProfileProperties(profile, verify);
	}
}
