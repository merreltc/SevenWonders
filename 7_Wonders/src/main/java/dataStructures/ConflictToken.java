package dataStructures;

public class ConflictToken extends Chip {
	
	public ConflictToken() {
		super();
		this.setValue(1);
	}

	public ConflictToken(int value) {
		super();
		if(value == -2){
			throw new IllegalArgumentException();
		}
		
		this.setValue(value);
	}	
}
