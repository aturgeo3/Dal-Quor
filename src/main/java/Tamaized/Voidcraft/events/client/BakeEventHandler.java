package Tamaized.Voidcraft.events.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import Tamaized.Voidcraft.voidCraft;

public class BakeEventHandler {
	
	public static final BakeEventHandler instance = new BakeEventHandler();

    private BakeEventHandler() {};

    @SubscribeEvent
    public void onModelBakeEvent(ModelBakeEvent event){
    	voidCraft.logger.info("Baking TESR Blocks");
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(voidCraft.blocks.Heimdall);
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(voidCraft.blocks.blockNoBreak);
        event.getModelManager().getBlockModelShapes().registerBuiltInBlocks(voidCraft.blocks.voidicCharger);
    }

}
