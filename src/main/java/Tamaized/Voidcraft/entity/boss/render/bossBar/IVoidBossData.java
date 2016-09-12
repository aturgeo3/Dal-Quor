package Tamaized.Voidcraft.entity.boss.render.bossBar;

import net.minecraft.util.text.ITextComponent;


public interface IVoidBossData {
    float getMaxHealth();

    float getHealth();
	
	public float getPercentHP();

    /**
     * Get the formatted ChatComponent that will be used for the sender's username in chat
     */
    public ITextComponent getNameForBossBar();


}
