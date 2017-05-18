package backend.factories;

import java.util.ArrayList;

import constants.GeneralEnums.GameMode;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.NoMoreWondersException;

public class WonderFactory {
	private ArrayList<WonderType> remainingWonders;
	private int numRemainingWonders;
	private GameMode mode;

	public WonderFactory(GameMode mode) {
		this.remainingWonders = new ArrayList<WonderType>();
		buildWonderSet(this.remainingWonders);
		this.numRemainingWonders = 7;
		this.mode = mode;
	}

	public Wonder getWonder() {
		if (isOutOfWonders()) {
			throw new NoMoreWondersException("There are no more unique wonders left.");
		}

		int index = getRandomIndex();
		decrementNumRemainingWonders();
		return removeAtIndex(index);
	}

	public int getRandomIndex() {
		double randomNum = Math.random();
		double correctedRandomNum = randomNum * (this.numRemainingWonders - 1);
		int index = (int) Math.round(correctedRandomNum);
		return index;
	}

	public void decrementNumRemainingWonders() {
		this.numRemainingWonders--;
	}

	public Wonder removeAtIndex(int index) {
		WonderType type = (WonderType) this.remainingWonders.remove(index);
		return new Wonder(getSide(), type);
	}

	public Side getSide() {
		if (this.mode == GameMode.EASY) {
			return Side.A;
		}

		double randomNum = Math.random();
		int sideAOrB = (int) Math.round(randomNum);
		return sideAOrB == 0 ? Side.A : Side.B;
	}

	private void buildWonderSet(ArrayList<WonderType> wonders) {
		wonders.add(WonderType.COLOSSUS);
		wonders.add(WonderType.TEMPLE);
		wonders.add(WonderType.GARDENS);
		wonders.add(WonderType.PYRAMIDS);
		wonders.add(WonderType.LIGHTHOUSE);
		wonders.add(WonderType.MAUSOLEUM);
		wonders.add(WonderType.STATUE);
	}

	public boolean isOutOfWonders() {
		return this.numRemainingWonders == 0;
	}
}