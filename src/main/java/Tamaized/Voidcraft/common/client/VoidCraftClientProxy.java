package Tamaized.Voidcraft.common.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderHeimdall;
import Tamaized.Voidcraft.blocks.render.RenderItemStack;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.blocks.render.RenderVoidicCharger;
import Tamaized.Voidcraft.client.ClientRenderTicker;
import Tamaized.Voidcraft.client.LayerVoidSpikes;
import Tamaized.Voidcraft.client.RenderVoidicInfusion;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.common.server.VoidCraftCommonProxy;
import Tamaized.Voidcraft.events.client.BakeEventHandler;
import Tamaized.Voidcraft.events.client.DebugEvent;
import Tamaized.Voidcraft.events.client.OverlayEvent;
import Tamaized.Voidcraft.items.entity.EntityHookShot;
import Tamaized.Voidcraft.machina.tileentity.TileEntityHeimdall;
import Tamaized.Voidcraft.machina.tileentity.TileEntityVoidicCharger;
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
import Tamaized.Voidcraft.registry.IBasicVoid;
import Tamaized.Voidcraft.registry.RegistryBase;
import Tamaized.Voidcraft.sound.client.BGMusic;
import Tamaized.Voidcraft.voidicInfusion.ClientInfusionHandler;

public class VoidCraftClientProxy extends VoidCraftCommonProxy {
	
	@SideOnly(Side.CLIENT)
	public static Minecraft mc = Minecraft.getMinecraft();
	@SideOnly(Side.CLIENT)
	public static int renderPass;
	
	public static ClientInfusionHandler infusionHandler = new ClientInfusionHandler();
	
	@Override
	public void preInit(){
		voidCraft.fluids.preInitRender();
		
		//ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.Heimdall), 0, TileEntityHeimdall.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.Heimdall), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.Heimdall).getName(), "normal"));
	
		//ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, TileEntityNoBreak.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.blockNoBreak).getName(), "normal"));
		
		//ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, TileEntityVoidicCharger.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.voidicCharger).getName(), "normal"));

        MinecraftForge.EVENT_BUS.register(BakeEventHandler.instance);
	}
	
	@Override
	public void init() {
        
        TileEntityItemStackRenderer.instance = new RenderItemStack();

        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(voidCraft.blocks.Heimdall), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.Heimdall).getName(), "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.blockNoBreak).getName(), "inventory"));
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.voidicCharger).getName(), "inventory"));
        
		RenderHeimdall renderHeimdall = new RenderHeimdall();
		RenderNoBreak renderNoBreak = new RenderNoBreak();
		RenderVoidicCharger renderCharger = new RenderVoidicCharger();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHeimdall.class, renderHeimdall);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, renderNoBreak);
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVoidicCharger.class, renderCharger);
		
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.Heimdall), 0, TileEntityHeimdall.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.Heimdall), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.Heimdall).getName(), "inventory"));
	
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, TileEntityNoBreak.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.blockNoBreak), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.blockNoBreak).getName(), "inventory"));
		
		ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, TileEntityVoidicCharger.class);
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(voidCraft.blocks.voidicCharger), 0, new ModelResourceLocation(voidCraft.modid+":"+((IBasicVoid) voidCraft.blocks.voidicCharger).getName(), "inventory"));

        //MinecraftForge.EVENT_BUS.register(BakeEventHandler.instance);

        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().registerBuiltInBlocks(voidCraft.blocks.Heimdall);
        //System.out.println( Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().getModelForState(voidCraft.blocks.Heimdall.getDefaultState()).isBuiltInRenderer());
	}
	
	@Override
	public void registerRenders(){
	
		//Events
		MinecraftForge.EVENT_BUS.register(new OverlayEvent());
		MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
		MinecraftForge.EVENT_BUS.register(new BGMusic());
		MinecraftForge.EVENT_BUS.register(new DebugEvent());
		MinecraftForge.EVENT_BUS.register(infusionHandler);
		MinecraftForge.EVENT_BUS.register(new RenderVoidicInfusion());
		MinecraftForge.EVENT_BUS.register(new ClientRenderTicker());
	
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
	
		RenderPlayer playerRenderer = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
		playerRenderer.addLayer(new LayerVoidSpikes(playerRenderer));
		
		RenderPlayer playerRendererSlim = (Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
		playerRendererSlim.addLayer(new LayerVoidSpikes(playerRendererSlim));
	}
	
	@Override
	public void registerInventoryRender() {
		for(RegistryBase reg : voidCraft.registry) reg.setupRender();
	}

	@Override
	public void registerNetwork() {
		voidCraft.channel.register(new VoidCraftClientPacketHandler());
	}

}
