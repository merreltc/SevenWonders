package dataStructures;

import dataStructures.Chip.ChipValue;

public abstract class Chip {
	
	public enum ChipType{
		COIN, CONFLICTTOKEN
	}
	
	public enum ChipValue {
		ONE, THREE, NEG1
	}
	
	private int value;
	protected ChipType chipType;
	
	public int getValue() {
		return value;
	}
	
	protected void setValue(int value) {
		this.value = value;		
	}

	public ChipType getChipType() {
		return this.chipType;
	}
	
	
}
