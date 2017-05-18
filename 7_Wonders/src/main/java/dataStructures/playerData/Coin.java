package dataStructures.playerData;

import utils.Translate;
import utils.TranslateWithTemplate;

public class Coin extends Chip {

	public Coin() {
		super();
		this.setValue(1);
		this.chipType = ChipType.COIN;
	}	
	
	public Coin(int value) {
		super();
		if(value <= 0 || value == 2 || value >= 4) {
			String msg = TranslateWithTemplate.prepareStringTemplateWithIntArg(value, "improperCoinValue", Translate.getNewResourceBundle());
			throw new IllegalArgumentException(msg);
		}
		
		this.setValue(value);
		this.chipType = ChipType.COIN;
	}
}
