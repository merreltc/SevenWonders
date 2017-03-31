package dataStructures;

public class Coin extends Chip {

	public Coin() {
		super();
		this.setValue(1);
	}	
	
	public Coin(int value) {
		super();
		if(value <= 0 || value == 2 || value >= 4) {
			throw new IllegalArgumentException("Cannot have a coin whose's value is " + value);
		}
		
		this.setValue(value);
	}
}
