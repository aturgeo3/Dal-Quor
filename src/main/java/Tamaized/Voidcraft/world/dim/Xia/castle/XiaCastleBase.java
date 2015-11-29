package Tamaized.Voidcraft.world.dim.Xia.castle;

public abstract class XiaCastleBase {
	
	protected final XiaCastleHandler handler;
	
	public XiaCastleBase(XiaCastleHandler handler){
		this.handler = handler;
	}
	
	public abstract void update();

}
