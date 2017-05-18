package dataStructures.playerData;

import utils.Translate;
import utils.TranslateWithTemplate;

public class ConflictToken extends Chip {

	public ConflictToken() {
		super();
		this.setValue(1);
		this.chipType = ChipType.CONFLICTTOKEN;
	}

	public ConflictToken(int value) {
		super();
		if (value < -1 || (value % 2) == 0 || value >= 7) {
			String msg = TranslateWithTemplate.prepareStringTemplateWithIntArg(value, "improperConflictTokenValue",
					Translate.getNewResourceBundle());
			throw new IllegalArgumentException(msg);
		}

		this.setValue(value);
		this.chipType = ChipType.COIN;
	}
}