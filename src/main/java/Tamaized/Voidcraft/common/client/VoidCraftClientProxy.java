package Tamaized.Voidcraft.common.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderHeimdall;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.common.server.VoidCraftCommonProxy;
import Tamaized.Voidcraft.events.client.OverlayEvent;
import Tamaized.Voidcraft.items.entity.EntityHookShot;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.mobs.entity.EntityMobLich;
import Tamaized.Voidcraft.mobs.entity.EntityMobSpectreChain;
import Tamaized.Voidcraft.mobs.entity.EntityMobVoidWrath;
import Tamaized.Voidcraft.mobs.entity.EntityMobWraith;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobDol;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobHerobrine;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobVoidBoss;
import Tamaized.Voidcraft.mobs.entity.boss.EntityMobZol;
import Tamaized.Voidcraft.mobs.entity.boss.bar.BossBarOverlay;
import Tamaized.Voidcraft.mobs.model.ModelHerobrine;
import Tamaized.Voidcraft.mobs.model.ModelLich;
import Tamaized.Voidcraft.mobs.model.ModelSpectreChain;
import Tamaized.Voidcraft.mobs.model.ModelTwins;
import Tamaized.Voidcraft.mobs.model.ModelVoidBoss;
import Tamaized.Voidcraft.mobs.model.ModelVoidWrath;
import Tamaized.Voidcraft.mobs.model.ModelWraith;
import Tamaized.Voidcraft.mobs.render.RenderDol;
import Tamaized.Voidcraft.mobs.render.RenderHerobrine;
import Tamaized.Voidcraft.mobs.render.RenderLich;
import Tamaized.Voidcraft.mobs.render.RenderSpectreChain;
import Tamaized.Voidcraft.mobs.render.RenderVoidBoss;
import Tamaized.Voidcraft.mobs.render.RenderVoidWrath;
import Tamaized.Voidcraft.mobs.render.RenderWraith;
import Tamaized.Voidcraft.mobs.render.RenderZol;
import Tamaized.Voidcraft.mobs.xia.EntityMobXia;
import Tamaized.Voidcraft.mobs.xia.EntityMobXia2;
import Tamaized.Voidcraft.mobs.xia.ModelXia;
import Tamaized.Voidcraft.mobs.xia.ModelXia2;
import Tamaized.Voidcraft.mobs.xia.RenderXia;
import Tamaized.Voidcraft.mobs.xia.RenderXia2;
import Tamaized.Voidcraft.projectiles.AcidBall;
import Tamaized.Voidcraft.projectiles.HerobrineFireball;
import Tamaized.Voidcraft.projectiles.RenderAcidBall;
import Tamaized.Voidcraft.projectiles.RenderHook;
import Tamaized.Voidcraft.projectiles.RenderVoidChain;
import Tamaized.Voidcraft.projectiles.VoidChain;
import Tamaized.Voidcraft.registry.RegistryBase;
import Tamaized.Voidcraft.registry.VoidBlocks;
import Tamaized.Voidcraft.sound.client.BGMusic;

public class VoidCraftClientProxy extends VoidCraftCommonProxy {
	
	@SideOnly(Side.CLIENT)
	public static Minecraft mc = Minecraft.getMinecraft();
	@SideOnly(Side.CLIENT)
	public static int renderPass;
	
	@Override
	public void preInit(){
		voidCraft.fluids.preInitRender();
	}

	@Override
	public void registerRenders(){
	
		//Events
		MinecraftForge.EVENT_BUS.register(new OverlayEvent());
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new BGMusic());
		FMLCommonHandler.instance().bus().register(new BGMusic()); 
		//MinecraftForge.EVENT_BUS.register(new DebugEvent());
	
		float shadowSize = 0.5F;
		//MOBS
		RenderingRegistry.registerEntityRenderingHandler(EntityMobWraith.class, new RenderWraith(new ModelWraith(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobLich.class, new RenderLich(new ModelLich(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobSpectreChain.class, new RenderSpectreChain(new ModelSpectreChain(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidWrath.class, new RenderVoidWrath(new ModelVoidWrath(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobVoidBoss.class, new RenderVoidBoss(new ModelVoidBoss(), shadowSize));
		//npcs
		RenderingRegistry.registerEntityRenderingHandler(EntityMobHerobrine.class, new RenderHerobrine(new ModelHerobrine(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobDol.class, new RenderDol(new ModelTwins(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobZol.class, new RenderZol(new ModelTwins(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobXia.class, new RenderXia(new ModelXia(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityMobXia2.class, new RenderXia2(new ModelXia2(), shadowSize));
	
		//Projectiles
		RenderingRegistry.registerEntityRenderingHandler(VoidChain.class, new RenderVoidChain(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(AcidBall.class, new RenderAcidBall(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityHookShot.class, new RenderHook(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(HerobrineFireball.class, new RenderFireball(Minecraft.getMinecraft().getRenderManager(), 2.0F));
	
		//Blocks
		//RenderingRegistry.registerBlockHandler(new OreRenderer()); //TODO: Deal with this at a later time
	
		RenderHeimdall renderHeimdall = new RenderHeimdall();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHeimdall.class, renderHeimdall);
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(voidCraft.blocks.Heimdall), new ItemRenderHeimdall(renderHeimdall, new TileEntityHeimdall()));
	
		RenderNoBreak renderNoBreak = new RenderNoBreak();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, renderNoBreak);
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), new ItemRenderNoBreak(renderNoBreak, new TileEntityNoBreak()));
	}
	
	@Override
	public void registerInventoryRender() {
		for(RegistryBase reg : voidCraft.registry) reg.setupRender();
	}

	@Override
	public void registerItems(){
				
	}

	@Override
	public void registerBlocks(){
		
	}
	
	@Override
	public void registerAchievements(){
		
	}

	@Override
	public void registerMISC(){
		
	}

	@Override
	public void registerNetwork() {
		voidCraft.channel.register(new VoidCraftClientPacketHandler());
	}

}
