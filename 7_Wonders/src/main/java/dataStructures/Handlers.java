package dataStructures;

import backend.handlers.EffectHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TradeHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;

public class Handlers{
	private SetUpPlayerHandler setUpPlayerHandler;
	private SetUpDeckHandler setUpDeckHandler;
	private TurnHandler turnHandler;
	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;
	private PlayerTurnHandler playerTurnHandler;
	private EffectHandler effectHandler;
	
	public Handlers(SetUpPlayerHandler setUpPlayerHandler){
		this.setUpPlayerHandler = setUpPlayerHandler;
	}
	
	public Handlers(GameMode mode){
		this.setUpPlayerHandler = new SetUpPlayerHandler(mode);
	}
	public SetUpPlayerHandler getSetUpPlayerHandler() {
		return setUpPlayerHandler;
	}
	
	public SetUpDeckHandler getSetUpDeckHandler() {
		return setUpDeckHandler;
	}
	
	public void setSetUpDeckHandler(SetUpDeckHandler setUpDeckHandler) {
		this.setUpDeckHandler = setUpDeckHandler;
	}
	
	public TurnHandler getTurnHandler() {
		return turnHandler;
	}
	
	public void setTurnHandler(TurnHandler turnHandler) {
		this.turnHandler = turnHandler;
	}
	
	public RotateHandler getRotateHandler() {
		return rotateHandler;
	}
	
	public void setRotateHandler(RotateHandler rotateHandler) {
		this.rotateHandler = rotateHandler;
	}
	
	public TradeHandler getTradeHandler() {
		return tradeHandler;
	}
	
	public void setTradeHandler(TradeHandler tradeHandler) {
		this.tradeHandler = tradeHandler;
	}
	
	public PlayerTurnHandler getPlayerTurnHandler() {
		return playerTurnHandler;
	}
	
	public void setPlayerTurnHandler(PlayerTurnHandler playerTurnHandler) {
		this.playerTurnHandler = playerTurnHandler;
	}

	public EffectHandler getEffectHandler() {
		return effectHandler;
	}

	public void setEffectHandler(EffectHandler effectHandler) {
		this.effectHandler = effectHandler;
	}
}
