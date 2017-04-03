package dataStructures;

public class Card {
	public enum Cost{
		NONE
	}
	
	public enum Effect{
		NONE
	}

	public String getName() {
		return "Default Card";
	}

	public int getMinNumPlayers() {
		return 3;
	}

	public Cost getCostType() {
		return Cost.NONE;
	}

	public Effect getEffectType() {
		return Effect.NONE;
	}

}
