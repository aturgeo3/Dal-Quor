package Tamaized.Voidcraft.common.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import Tamaized.Voidcraft.Addons.thaumcraft.Spells.VoidSpellFocus;
import Tamaized.Voidcraft.blocks.TileEntityNoBreak;
import Tamaized.Voidcraft.blocks.render.ItemRenderHeimdall;
import Tamaized.Voidcraft.blocks.render.RenderHeimdall;
import Tamaized.Voidcraft.blocks.render.RenderNoBreak;
import Tamaized.Voidcraft.common.voidCraft;
import Tamaized.Voidcraft.common.handlers.VoidCraftClientPacketHandler;
import Tamaized.Voidcraft.common.server.VoidCraftCommonProxy;
import Tamaized.Voidcraft.events.client.OverlayEvent;
import Tamaized.Voidcraft.items.VoidRecord;
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
import Tamaized.Voidcraft.projectiles.RenderVoidChain;
import Tamaized.Voidcraft.projectiles.VoidChain;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class VoidCraftClientProxy extends VoidCraftCommonProxy {
	
	@SideOnly(Side.CLIENT)
	public static Minecraft mc = Minecraft.getMinecraft();
	@SideOnly(Side.CLIENT)
	public static int renderPass;
	@SideOnly(Side.CLIENT)
	public static int OreRenderType;

@Override
public void registerRenders(){
	
	//Events
	MinecraftForge.EVENT_BUS.register(new OverlayEvent());
	MinecraftForge.EVENT_BUS.register(new BossBarOverlay());
	//MinecraftForge.EVENT_BUS.register(new MusicEvent());
	//FMLCommonHandler.instance().bus().register(new MusicEvent()); 
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
	RenderingRegistry.registerEntityRenderingHandler(VoidChain.class, new RenderVoidChain());
	RenderingRegistry.registerEntityRenderingHandler(AcidBall.class, new RenderAcidBall());
	RenderingRegistry.registerEntityRenderingHandler(HerobrineFireball.class, new RenderFireball(2.0F));
	
	//Blocks
	OreRenderType = RenderingRegistry.getNextAvailableRenderId();
	RenderingRegistry.registerBlockHandler(new OreRenderer());
	
	RenderHeimdall renderHeimdall = new RenderHeimdall();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHeimdall.class, renderHeimdall);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(voidCraft.Heimdall), new ItemRenderHeimdall(renderHeimdall, new TileEntityHeimdall()));
	
	RenderNoBreak renderNoBreak = new RenderNoBreak();
	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNoBreak.class, renderNoBreak);
	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(voidCraft.blockNoBreak), new ItemRenderNoBreak(renderNoBreak, new TileEntityNoBreak()));
	
}

@Override
public void registerItems(){
				// items (Name)
				LanguageRegistry.addName(voidCraft.ectoplasm, "Ectoplasm");
				LanguageRegistry.addName(voidCraft.voidcrystal, "Void Crystal");				
				LanguageRegistry.addName(voidCraft.voidBurner, "Void Crystal and Steel");	
				LanguageRegistry.addName(voidCraft.burnBone, "Charred Bone");	
				LanguageRegistry.addName(voidCraft.voidStar, "Void Star");	
				LanguageRegistry.addName(voidCraft.ChainedSkull, "Chained Skull");
				
				for(Item item : voidCraft.voidDiscs){
					VoidRecord disc = (VoidRecord) item;
					LanguageRegistry.addName(item, disc.recordName.split("voidCraft:")[1]);
				}
				
				LanguageRegistry.addName(voidCraft.voidCloth, "Void Infused Cloth");
				LanguageRegistry.addName(voidCraft.voidCrystalBucket, "Crystal in a Bucket");
				LanguageRegistry.addName(voidCraft.voidBucket, "Liquid Void Bucket");
				
				LanguageRegistry.addName(voidCraft.ironDust, "Void Infused Iron Dust");
				LanguageRegistry.addName(voidCraft.goldDust, "Void Infused Gold Dust");
				LanguageRegistry.addName(voidCraft.diamondDust, "Void Infused Diamond Dust");
				LanguageRegistry.addName(voidCraft.coalDust, "Void Infused Coal Dust");
				LanguageRegistry.addName(voidCraft.copperDust, "Void Infused Copper Dust");
				LanguageRegistry.addName(voidCraft.tinDust, "Void Infused Tin Dust");
				LanguageRegistry.addName(voidCraft.leadDust, "Void Infused Lead Dust");
				LanguageRegistry.addName(voidCraft.quartzDust, "Void Infused Quartz Dust");
				
				LanguageRegistry.addName(voidCraft.debugger, "Debug Tool");

				// Armor (Name)
				//void
				LanguageRegistry.addName(voidCraft.voidHelmet, "Void Helmet");
				LanguageRegistry.addName(voidCraft.voidChest, "Void Chestplate");
				LanguageRegistry.addName(voidCraft.voidLegs, "Void Leggings");
				LanguageRegistry.addName(voidCraft.voidBoots, "Void Boots");
				//demon
				LanguageRegistry.addName(voidCraft.demonHelmet, "Demon Helmet");
				LanguageRegistry.addName(voidCraft.demonChest, "Demon Chestplate");
				LanguageRegistry.addName(voidCraft.demonLegs, "Demon Leggings");
				LanguageRegistry.addName(voidCraft.demonBoots, "Demon Boots");
				//xia
				LanguageRegistry.addName(voidCraft.xiaChest, "Xia's Chestplate");

				// Tools (Name)
				LanguageRegistry.addName(voidCraft.voidPickaxe, "Void Pickaxe");
				LanguageRegistry.addName(voidCraft.voidSpade, "Void Shovel");
				LanguageRegistry.addName(voidCraft.voidAxe, "Void Axe");
				LanguageRegistry.addName(voidCraft.voidSword, "Void Sword");
				LanguageRegistry.addName(voidCraft.voidHoe, "Void Hoe");

				LanguageRegistry.addName(voidCraft.spectrePickaxe, "Spectre Pickaxe");
				LanguageRegistry.addName(voidCraft.spectreAxe, "Spectre Axe");
				LanguageRegistry.addName(voidCraft.angelicSword, "Angelic Sword");
				LanguageRegistry.addName(voidCraft.chainSword, "Sword of Binding");
				LanguageRegistry.addName(voidCraft.moltenSword, "Molten Sword");
				LanguageRegistry.addName(voidCraft.archSword, "ArchAngelic Sword");
				LanguageRegistry.addName(voidCraft.demonSword, "Demonic Sword");
				
				// Projectiles
				LanguageRegistry.instance().addStringLocalization("entity.VoidCraft.voidChain.name", "Void Chain");
				LanguageRegistry.addName(voidCraft.voidChain, "Void Chain");
				LanguageRegistry.addName(voidCraft.MoltenvoidChainPart, "Molten Void Chain Part");
				LanguageRegistry.addName(voidCraft.MoltenvoidChain, "Molten Void Chain");
				
				if(voidCraft.thaumcraftIntegration!=null){
					
					//Research Tab
					LanguageRegistry.instance().addStringLocalization("tc.research_category.VoidCraft", "en_US", "VoidCraft");	
					
					//Research Pages
					LanguageRegistry.instance().addStringLocalization("tc.research_name.vc.CorruptedSword", "en_US", "Corrupted Sword");
					LanguageRegistry.instance().addStringLocalization("tc.research_text.vc.CorruptedSword", "en_US", "Evil side of things");
					LanguageRegistry.instance().addStringLocalization("tc.research_name.vc.VoidCrystal", "en_US", "Crystallized Void");
					LanguageRegistry.instance().addStringLocalization("tc.research_text.vc.VoidCrystal", "en_US", "Do not fear the unknown");
					
					//Spell Names
					if(voidCraft.thaumcraftIntegration.spells != null){
						for(VoidSpellFocus i : voidCraft.thaumcraftIntegration.spells.getItems()){
							LanguageRegistry.addName(i, i.getMyName());
						}
					}
					
				}
	}

	@Override
	public void registerBlocks(){
		// blocks (Name)
		LanguageRegistry.addName(voidCraft.blockVoidFluid, "Liquid Void");
		LanguageRegistry.addName(voidCraft.blockVoidcrystal, "Void Crystal Block");
		LanguageRegistry.addName(voidCraft.blockFakeBedrock, "Soft Bedrock");
		LanguageRegistry.addName(voidCraft.blockNoBreak, EnumChatFormatting.OBFUSCATED+"Limbo");
		LanguageRegistry.addName(voidCraft.blockVoidbrick, "Void Brick");
		LanguageRegistry.addName(voidCraft.blockVoidfence, "Void Brick Fence");
		LanguageRegistry.addName(voidCraft.blockVoidstairs, "Void Brick Stairs");
		LanguageRegistry.addName(voidCraft.blockVoidBrickSlab, "Void Brick Slab");
		LanguageRegistry.addName(voidCraft.fireVoid, "Void Fire");
		LanguageRegistry.addName(voidCraft.oreVoidcrystal, "Void Crystal Ore");
		LanguageRegistry.addName(voidCraft.voidBox, "Void Music Box");
		LanguageRegistry.addName(voidCraft.voidInfuserInert, "Inert Void Infusion Altar");
		LanguageRegistry.addName(voidCraft.voidInfuser, "Void Infusion Altar");
		LanguageRegistry.addName(voidCraft.Heimdall, "Heimdall");
		LanguageRegistry.addName(voidCraft.voidMacerator, "Void Infused Macerator");
		//LanguageRegistry.addName(voidCraft.voidMaceratorOn, "voidCraft.voidMacerator.on");
		
		LanguageRegistry.addName(voidCraft.blockTeleporterVoid, "Portal to the Void");
		LanguageRegistry.addName(voidCraft.blockTeleporterXia, "Portal to Xia");				
	}
	
	@Override
	public void registerAchievements(){
		// blocks (Name)
		LanguageRegistry.instance().addStringLocalization("achievement.achM_1", "en_US", "Void?");		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_1.desc", "en_US", "Discover an unknown crystal located in The End");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_2", "en_US", "We Need to Go Even Deeper");		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_2.desc", "en_US", "Build a portal to the Void");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_3", "en_US", "Become one with the Void");		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_3.desc", "en_US", "Craft a Void Infuser");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achM_4", "en_US", "It's worth how much?!");
		LanguageRegistry.instance().addStringLocalization("achievement.achM_4.desc", "en_US", "Infuse a furnace with the power of the Void");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS1_1", "en_US", "Better Than Diamond");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS1_1.desc", "en_US", "Craft a Sword with the new crystal");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS1_2", "en_US", "Godly Power");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS1_2.desc", "en_US", "Cover your new sword in ectoplasm");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_1", "en_US", "The Chains don't let go");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_1.desc", "en_US", "Cover your new sword in chains");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_2", "en_US", "Third Degree Burns");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_2.desc", "en_US", "Add even more chains to the Sword! But molten chains!");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_3", "en_US", "Legendary");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_3.desc", "en_US", "Combine the Power of the Gods, the Chains, and the Flames to create a legendary sword!");
		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_4", "en_US", "You're a Demon Harry.");		
		LanguageRegistry.instance().addStringLocalization("achievement.achS2_4.desc", "en_US", "Infuse the Legendary sword with the power of the Void!");
	}

@Override
public void registerMISC(){
	
}

@Override
public void registerNetwork() {
	voidCraft.channel.register(new VoidCraftClientPacketHandler());
}

}
