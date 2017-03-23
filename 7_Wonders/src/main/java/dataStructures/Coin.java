package dataStructures;

public class Coin extends Chip {

	public Coin() {
		super();
		this.setValue(1);
	}	
	
	public Coin(int value) {
		super();
		if(value == -1 || value == 0 || value == 2 || value == 4 || value == 5) {
			throw new IllegalArgumentException();
		}
		
		this.setValue(value);
	}
}
