package Tamaized.Voidcraft.common.entity.boss;

import net.minecraft.util.text.ITextComponent;


public interface IVoidBossData {
    float getMaxHealthForBossBar();

    float getHealthForBossBar();
	
	public float getPercentHPForBossBar();

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getNameForBossBar();


}
