package dataStructures.gameMaterials;

import java.util.ArrayList;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Side;
import constants.WonderInfo;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level.Frequency;
import exceptions.CannotBuiltWonderException;

public class Wonder {
	private WonderType type;
	private String name;
	private EntityEffect resource;
	private Side side;
	private int numBuiltLevels = 0;
	private ArrayList<Level> levels;
	private WonderInfo info;

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(Side side, WonderType type) {
		this.info = new WonderInfo();
		initializeData(side, type);
		setLevels(new ArrayList<Level>());
		// TODO: Parse to set up levels
	}

	private void initializeData(Side side, WonderType type) {
		this.type = type;
		this.name = this.info.getNameByType(type);
		this.resource = this.info.getResourceByType(type);
		this.side = side;
	}

	@Override
	public boolean equals(Object obj) {
		Wonder temp = (Wonder) obj;
		boolean equalType = this.type == temp.getType();
		boolean equalName = this.name == temp.getName();
		boolean equalResource = this.resource.equals(temp.getResource());
		boolean equalSide = this.side == temp.getSide();
		boolean equalLevels = this.levels.equals(temp.getLevels());
		return equalType && equalName && equalResource && equalSide && equalLevels;
	}

	public void buildNextLevel() {
		this.numBuiltLevels++;
		if (this.numBuiltLevels > this.getNumLevels()) {
			throw new CannotBuiltWonderException("Player has built max number of levels.");
		} else {
//			Level temp = new Level(this.numBuiltLevels, new Cost(CostType.NONE, 1), new Effect(EffectType.NONE),
//					Frequency.DEFAULT);
//			this.levels.add(temp);
		}
	}

	public WonderType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public EntityEffect getResource() {
		return this.resource;
	}

	public Side getSide() {
		return this.side;
	}

	public int getNumLevels() {
		return this.info.getNumLevels(this.side, this.type);
	}

	public int getNumBuiltLevels() {
		return this.levels.size();
	}

	public Level getLevel(int index) {
		return this.levels.get(index);
	}

	public void setLevels(ArrayList<Level> levels) {
		this.levels = levels;
	}

	public ArrayList<Level> getLevels() {
		return this.levels;
	}
}
