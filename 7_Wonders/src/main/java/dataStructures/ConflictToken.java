package dataStructures;

public class ConflictToken extends Chip {
	
	public ConflictToken() {
		super();
		this.setValue(1);
	}

	public ConflictToken(int value) {
		super();
		if(value <= -2 || (value % 2) == 0 || value >= 6){
			throw new IllegalArgumentException("Cannot have a conflict token whose's value is " + value);
		}
		
		this.setValue(value);
	}	
}
