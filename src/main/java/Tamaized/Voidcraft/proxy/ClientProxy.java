package Tamaized.Voidcraft.proxy;

import Tamaized.TamModized.proxy.AbstractProxy;
import Tamaized.Voidcraft.voidCraft;
import Tamaized.Voidcraft.GUI.client.VadeMecumGUI;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderVoidicCharger;
import Tamaized.Voidcraft.client.LayerCustomElytra;
import Tamaized.Voidcraft.client.RenderNull;
import Tamaized.Voidcraft.entity.boss.EntityBossCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.herobrine.EntityBossHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineFireball;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.EntityHerobrineWitherSkull;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineCreeper;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineShadow;
import Tamaized.Voidcraft.entity.boss.herobrine.extra.render.RenderHerobrineTNTPrimed;
import Tamaized.Voidcraft.entity.boss.herobrine.model.ModelHerobrine;
import Tamaized.Voidcraft.entity.boss.herobrine.render.RenderHerobrine;
import Tamaized.Voidcraft.entity.boss.model.ModelCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.render.RenderCorruptedPawn;
import Tamaized.Voidcraft.entity.boss.render.bossBar.BossBarOverlay;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossDol;
import Tamaized.Voidcraft.entity.boss.twins.EntityBossZol;
import Tamaized.Voidcraft.entity.boss.twins.model.ModelTwins;
import Tamaized.Voidcraft.entity.boss.twins.render.RenderDol;
import Tamaized.Voidcraft.entity.boss.twins.render.RenderZol;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia;
import Tamaized.Voidcraft.entity.boss.xia.EntityBossXia2;
import Tamaized.Voidcraft.entity.boss.xia.model.ModelXia;
import Tamaized.Voidcraft.entity.boss.xia.model.ModelXia2;
import Tamaized.Voidcraft.entity.boss.xia.render.RenderXia;
import Tamaized.Voidcraft.entity.boss.xia.render.RenderXia2;
import Tamaized.Voidcraft.entity.ghost.EntityGhostBiped;
import Tamaized.Voidcraft.entity.ghost.EntityGhostPlayer;
import Tamaized.Voidcraft.entity.ghost.render.RenderGhostPlayer;
import Tamaized.Voidcraft.entity.mob.EntityMobLich;
import Tamaized.Voidcraft.entity.mob.EntityMobSpectreChain;
import Tamaized.Voidcraft.entity.mob.EntityMobVoidWrath;
import Tamaized.Voidcraft.entity.mob.EntityMobWraith;
import Tamaized.Voidcraft.entity.mob.lich.EntityLichInferno;
import Tamaized.Voidcraft.entity.mob.model.ModelLich;
import Tamaized.Voidcraft.entity.mob.model.ModelSpectreChain;
import Tamaized.Voidcraft.entity.mob.model.ModelVoidWrath;
import Tamaized.Voidcraft.entity.mob.model.ModelWraith;
import Tamaized.Voidcraft.entity.mob.render.RenderLich;
import Tamaized.Voidcraft.entity.mob.render.RenderSpectreChain;
import Tamaized.Voidcraft.entity.mob.render.RenderVoidWrath;
import Tamaized.Voidcraft.entity.mob.render.RenderWraith;
import Tamaized.Voidcraft.entity.nonliving.AcidBall;
import Tamaized.Voidcraft.entity.nonliving.EntityHookShot;
import Tamaized.Voidcraft.entity.nonliving.EntityObsidianFlask;
import Tamaized.Voidcraft.entity.nonliving.VoidChain;
import Tamaized.Voidcraft.entity.nonliving.render.RenderAcidBall;
import Tamaized.Voidcraft.entity.nonliving.render.RenderHook;
import Tamaized.Voidcraft.entity.nonliving.render.RenderObsidianFlask;
import Tamaized.Voidcraft.entity.nonliving.render.RenderVoidChain;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.events.client.OverlayEvent;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
import Tamaized.Voidcraft.network.ClientPacketHandler;
import Tamaized.Voidcraft.sound.client.BGMusic;
import Tamaized.Voidcraft.vadeMecum.VadeMecumEntry;
import Tamaized.Voidcraft.vadeMecum.contents.VadeMecumMainEntry;
import Tamaized.Voidcraft.voidicInfusion.client.ClientInfusionHandler;
import Tamaized.Voidcraft.voidicInfusion.client.ClientRenderTicker;
import Tamaized.Voidcraft.voidicInfusion.client.LayerVoidSpikes;
import Tamaized.Voidcraft.voidicInfusion.client.RenderVoidicInfusion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends AbstractProxy {

	@SideOnly(Side.CLIENT)
	public static Minecraft mc = Minecraft.getMinecraft();
	@SideOnly(Side.CLIENT)
	public static int renderPass;

	public static ClientInfusionHandler infusionHandler = new ClientInfusionHandler();
	public static VadeMecumGUI vadeMecum;
	public static VadeMecumMainEntry vadeMecumEntryList;

	@Override
	public void preInit() {
		voidCraft.instance.clientPreInit();
		vadeMecumEntryList = new VadeMecumMainEntry();
		vadeMecumEntryList.preLoadObject();
	}

	@Override
	public void init() {
		voidCraft.instance.clientInit();
		RenderNoBreak renderNoBreak = new RenderNoBreak();
		RenderVoidicCharger renderCharger = new RenderVoidicCharger();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, renderNoBreak);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicCharger.class, renderCharger);

		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, TileEntityNoBreak.class);
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, TileEntityVoidicCharger.class);
	}

	@Override
	public void postInit() {
		voidCraft.instance.clientPostInit();
		// Events
		MinecraftForge.EVENT_BUS.register(new OverlayEvent());
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new BGMusic());
		MinecraftForge.EVENT_BUS.register(new DebugEvent());
		MinecraftForge.EVENT_BUS.register(infusionHandler);
		MinecraftForge.EVENT_BUS.register(new RenderVoidicInfusion());
		MinecraftForge.EVENT_BUS.register(new ClientRenderTicker());

		float shadowSize = 0.5F;
		// MOBS
		RenderingRegistry.registerEntityRenderingHandler(EntityMobWraith.class, new RenderWraith(new ModelWraith(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobLich.class, new RenderLich(new ModelLich(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobSpectreChain.class, new RenderSpectreChain(new ModelSpectreChain(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidWrath.class, new RenderVoidWrath(new ModelVoidWrath(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossCorruptedPawn.class, new RenderCorruptedPawn(new ModelCorruptedPawn(), shadowSize));
		// npcs
		RenderingRegistry.registerEntityRenderingHandler(EntityBossHerobrine.class, new RenderHerobrine(new ModelHerobrine(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossDol.class, new RenderDol(new ModelTwins(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossZol.class, new RenderZol(new ModelTwins(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia.class, new RenderXia(new ModelXia(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityBossXia2.class, new RenderXia2(new ModelXia2(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostPlayer.class, new RenderGhostPlayer(new ModelPlayer(0.0F, false)));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhostBiped.class, new RenderGhostPlayer(new ModelBiped(0.0F)));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineCreeper.class, new RenderHerobrineCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineWitherSkull.class, new RenderWitherSkull(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineTNTPrimed.class, new RenderHerobrineTNTPrimed(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineShadow.class, new RenderHerobrineShadow(new ModelHerobrine()));

		// Projectiles and MISC.
		RenderingRegistry.registerEntityRenderingHandler(VoidChain.class, new RenderVoidChain(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(AcidBall.class, new RenderAcidBall(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityHookShot.class, new RenderHook(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityHerobrineFireball.class, new RenderFireball(Minecraft.getMinecraft().getRenderManager(), 2.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityLichInferno.class, new RenderNull());
		RenderingRegistry.registerEntityRenderingHandler(EntityObsidianFlask.class, new RenderObsidianFlask(Minecraft.getMinecraft().getRenderManager(), voidCraft.items.obsidianFlask, Minecraft.getMinecraft().getRenderItem()));

		RenderPlayer playerRenderer = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
		playerRenderer.addLayer(new LayerVoidSpikes(playerRenderer));
		playerRenderer.addLayer(new LayerCustomElytra(playerRenderer));

		RenderPlayer playerRendererSlim = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
		playerRendererSlim.addLayer(new LayerVoidSpikes(playerRendererSlim));
		playerRendererSlim.addLayer(new LayerCustomElytra(playerRendererSlim));

		voidCraft.channel.register(new ClientPacketHandler());
	}

}
