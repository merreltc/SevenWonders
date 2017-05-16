package backend.factories;

import java.util.ArrayList;

import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.NoMoreWondersException;

public class WonderFactory {
	private ArrayList<WonderType> remainingWonders;
	private int numRemainingWonders;

	public WonderFactory() {
		this.remainingWonders = new ArrayList<WonderType>();
		buildWonderSet(this.remainingWonders);
		this.numRemainingWonders = 7;
	}

	public WonderType getWonder() {
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

	public WonderType removeAtIndex(int index) {
		return (WonderType) this.remainingWonders.remove(index);
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