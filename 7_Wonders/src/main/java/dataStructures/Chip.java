package dataStructures;

public abstract class Chip {
	public enum ChipType {
		ONE, THREE
	}
	
	private int value;
	
	public int getValue() {
		return value;
	}
	
	protected void setValue(int value) {
		this.value = value;		
	}
	
	
}
