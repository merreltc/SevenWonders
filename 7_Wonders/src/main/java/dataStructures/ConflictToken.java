package dataStructures;

import guiMain.Translate;

public class ConflictToken extends Chip {
	
	public ConflictToken() {
		super();
		this.setValue(1);
		this.chipType = ChipType.CONFLICTTOKEN;
	}

	public ConflictToken(int value) {
		super();
		if(value <= -2 || (value % 2) == 0 || value >= 6){
			String msg = Translate.prepareStringTemplateWithIntArg(value, "improperConflictTokenValue", Translate.getNewResourceBundle());
			throw new IllegalArgumentException(msg);
		}
		
		this.setValue(value);
		this.chipType = ChipType.COIN;
	}	
}
