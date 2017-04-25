package dataStructures;

public class Level {
	private int priority;

	public Level(int priority, Cost cost, Effect effect) {
		this.priority = priority;
		
	}

	public int getPriority() {
		return this.priority;
	}

}
