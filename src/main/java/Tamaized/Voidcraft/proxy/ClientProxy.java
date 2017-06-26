package Tamaized.Voidcraft.proxy;

import Tamaized.TamModized.proxy.AbstractProxy;
import Tamaized.Voidcraft.client.gui.VadeMecumGUI;
import Tamaized.Voidcraft.VoidCraft;
import Tamaized.Voidcraft.client.entity.nonliving.*;
import Tamaized.Voidcraft.client.event.ClientInfusionOverlayRender;
import Tamaized.Voidcraft.client.event.ClientRenderTicker;
import Tamaized.Voidcraft.client.layer.LayerCustomElytra;
import Tamaized.Voidcraft.client.layer.LayerVoidSpikes;
import Tamaized.Voidcraft.client.render.*;
import Tamaized.Voidcraft.common.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.client.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.client.blocks.render.RenderVoidicAnchor;
import Tamaized.Voidcraft.client.blocks.render.RenderVoidicCharger;
import Tamaized.Voidcraft.common.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.client.entity.boss.render.RenderDragonOldWithBar;
import Tamaized.Voidcraft.common.entity.boss.dragon.EntityVoidicDragon;
import Tamaized.Voidcraft.client.entity.boss.render.RenderVoidicDragon;
import Tamaized.Voidcraft.common.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.common.entity.boss.herobrine.extra.*;
import Tamaized.Voidcraft.client.entity.boss.extra.ModelHerobrineShadow;
import Tamaized.Voidcraft.client.entity.boss.extra.RenderHerobrineCreeper;
import Tamaized.Voidcraft.client.entity.boss.extra.RenderHerobrineShadow;
import Tamaized.Voidcraft.client.entity.boss.extra.RenderHerobrineTNTPrimed;
import Tamaized.Voidcraft.common.entity.boss.herobrine.render.RenderHerobrine;
import Tamaized.Voidcraft.common.entity.boss.lob.EntityLordOfBlades;
import Tamaized.Voidcraft.client.entity.boss.model.ModelLordOfBlades;
import Tamaized.Voidcraft.client.entity.boss.render.RenderLordOfBlades;
import Tamaized.Voidcraft.client.entity.boss.model.ModelCorruptedPawn;
import Tamaized.Voidcraft.client.entity.boss.model.ModelVoidBoss;
import Tamaized.Voidcraft.client.entity.boss.model.ModelVoidBossOverlay;
import Tamaized.Voidcraft.client.entity.boss.render.RenderCorruptedPawn;
import Tamaized.Voidcraft.client.entity.boss.bossbar.BossBarOverlay;
import Tamaized.Voidcraft.common.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.common.entity.boss.twins.EntityBossZol;
import Tamaized.Voidcraft.client.entity.boss.render.RenderDol;
import Tamaized.Voidcraft.client.entity.boss.render.RenderZol;
import Tamaized.Voidcraft.common.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.common.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.common.entity.boss.xia.finalphase.EntityDragonXia;
import Tamaized.Voidcraft.common.entity.boss.xia.finalphase.EntityWitherbrine;
import Tamaized.Voidcraft.common.entity.boss.xia.finalphase.EntityZolXia;
import Tamaized.Voidcraft.client.entity.boss.extra.EntityDolXia;
import Tamaized.Voidcraft.client.entity.boss.extra.RenderTwinsXia;
import Tamaized.Voidcraft.client.entity.boss.extra.RenderWitherbrine;
import Tamaized.Voidcraft.client.entity.boss.model.ModelXia2;
import Tamaized.Voidcraft.client.entity.boss.render.RenderXia;
import Tamaized.Voidcraft.client.entity.boss.render.RenderXia2;
import Tamaized.Voidcraft.common.entity.companion.EntityCompanionFireElemental;
import Tamaized.Voidcraft.common.entity.companion.EntityVoidParrot;
import Tamaized.Voidcraft.client.entity.companion.layer.LayerVoidParrotShoulder;
import Tamaized.Voidcraft.client.entity.companion.render.RenderFireElementalCompanion;
import Tamaized.Voidcraft.client.entity.companion.render.RenderVoidParrot;
import Tamaized.Voidcraft.common.entity.ghost.EntityGhostBiped;
import Tamaized.Voidcraft.common.entity.ghost.EntityGhostPlayer;
import Tamaized.Voidcraft.client.entity.ghost.RenderGhostPlayer;
import Tamaized.Voidcraft.common.entity.mob.*;
import Tamaized.Voidcraft.common.entity.mob.dalquor.EntityHashalaq;
import Tamaized.Voidcraft.client.entity.mob.model.ModelHashalaq;
import Tamaized.Voidcraft.common.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.client.entity.mob.model.ModelLich;
import Tamaized.Voidcraft.client.entity.mob.model.ModelSpectreChain;
import Tamaized.Voidcraft.client.entity.mob.model.ModelVoidWrath;
import Tamaized.Voidcraft.client.entity.mob.model.ModelWraith;
import Tamaized.Voidcraft.client.entity.mob.RenderEtherealGuardian;
import Tamaized.Voidcraft.common.entity.nonliving.*;
import Tamaized.Voidcraft.common.events.client.DebugEvent;
import Tamaized.Voidcraft.common.events.client.TextureStitch;
import Tamaized.Voidcraft.common.handlers.SkinHandler;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicAnchor;
import Tamaized.Voidcraft.common.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.registry.VoidCraftBlocks;
import Tamaized.Voidcraft.client.sound.BGMusic;
import Tamaized.Voidcraft.common.vademecum.contents.VadeMecumMainEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.RenderPlayer;
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

import java.util.List;

public class ClientProxy extends AbstractProxy {

	private static final ResourceLocation WHITESPACE = new ResourceLocation(VoidCraft.modid, "textures/entity/whitespace.png");
	public static VadeMecumGUI vadeMecum;
	public static VadeMecumMainEntry vadeMecumEntryList;

	public ClientProxy() {
		super(Side.CLIENT);
	}

	@Override
	public void preRegisters() {
		OBJLoader.INSTANCE.addDomain(VoidCraft.modid);
	}

	@Override
	public void preInit() {
		MinecraftForge.EVENT_BUS.register(SkinHandler.instance);
		vadeMecumEntryList = new VadeMecumMainEntry();
		vadeMecumEntryList.preLoadObject();

		float shadowSize = 0.5F;
		// MOBS
		RenderingRegistry.registerEntityRenderingHandler(EntityLordOfBlades.class, manager -> new RenderLordOfBlades(manager, new ModelLordOfBlades(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/lordofblades.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityHashalaq.class, manager -> new RenderGeneric(manager, new ModelHashalaq(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/hashalaq.png"), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobWraith.class, manager -> new RenderGeneric(manager, new ModelWraith(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/wraith.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobSpectreChain.class, manager -> new RenderGeneric(manager, new ModelSpectreChain(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/spectrechain.png"), 1.0F));

		RenderingRegistry.registerEntityRenderingHandler(EntityMobLich.class, manager -> new RenderGeneric(manager, new ModelLich(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/voidiclich.png"), 1.0F));

		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidWrath.class, manager -> new RenderGeneric(manager, new ModelVoidWrath(), shadowSize, new ResourceLocation(VoidCraft.modid, "textures/entity/voidwrath.png"), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossCorruptedPawn.class, manager -> new RenderCorruptedPawn(manager, new ModelCorruptedPawn(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossHerobrine.class, manager -> new RenderHerobrine(manager, new ModelVoidBoss<EntityBossHerobrine>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossDol.class, manager -> new RenderDol(manager, shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossZol.class, manager -> new RenderZol(manager, new ModelVoidBoss<EntityBossZol>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia.class, manager -> new RenderXia(manager, new ModelVoidBossOverlay<EntityBossXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia2.class, manager -> new RenderXia2(manager, new ModelXia2<EntityBossXia2>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPlayer.class, manager -> new RenderGhostPlayer(manager, new ModelPlayer(0.0F, false)));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostBiped.class, manager -> new RenderGhostPlayer(manager, new ModelBiped(0.0F)));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineCreeper.class, RenderHerobrineCreeper::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineWitherSkull.class, RenderWitherSkull::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineTNTPrimed.class, RenderHerobrineTNTPrimed::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineShadow.class, manager -> new RenderHerobrineShadow(manager, new ModelHerobrineShadow<EntityHerobrineShadow>()));
		RenderingRegistry.registerEntityRenderingHandler(EntityWitherbrine.class, RenderWitherbrine::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonXia.class, RenderDragonOldWithBar::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityVoidicDragon.class, RenderVoidicDragon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDolXia.class, manager -> new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_DOL, new ModelVoidBoss<EntityDolXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityZolXia.class, manager -> new RenderTwinsXia(manager, RenderTwinsXia.TEXTURE_ZOL, new ModelVoidBoss<EntityZolXia>(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobEtherealGuardian.class, manager -> new RenderEtherealGuardian(manager, shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCompanionFireElemental.class, manager -> new RenderFireElementalCompanion(manager, shadowSize));

		// Projectiles and MISC.
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
	}

	@Override
	public void init() {
		VoidCraftBlocks.clientInit();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, new RenderNoBreak());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicCharger.class, new RenderVoidicCharger());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicAnchor.class, new RenderVoidicAnchor());

		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraftBlocks.blockNoBreak), 0, TileEntityNoBreak.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraftBlocks.voidicCharger), 0, TileEntityVoidicCharger.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(VoidCraftBlocks.voidicAnchor), 0, TileEntityVoidicAnchor.class);
	}

	@Override
	public void postInit() {
		// Events
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new BGMusic());
		MinecraftForge.EVENT_BUS.register(new DebugEvent());
		MinecraftForge.EVENT_BUS.register(new ClientInfusionOverlayRender());
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.client.render.RenderPlayer());
		MinecraftForge.EVENT_BUS.register(new Tamaized.Voidcraft.client.render.RenderLiving());
		MinecraftForge.EVENT_BUS.register(new RenderSheathe());
		MinecraftForge.EVENT_BUS.register(new ClientRenderTicker());
		MinecraftForge.EVENT_BUS.register(new TextureStitch());

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

		VoidCraft.channel.register(new ClientPacketHandler());

	}

}
