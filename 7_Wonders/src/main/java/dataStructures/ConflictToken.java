package dataStructures;

public class ConflictToken extends Chip {
	
	public ConflictToken() {
		super();
		this.setValue(1);
	}

	public ConflictToken(int value) {
		super();
		if(value == -2 || value == 0 || value == 2 || value == 4){
			throw new IllegalArgumentException();
		}
		
		this.setValue(value);
	}	
}
