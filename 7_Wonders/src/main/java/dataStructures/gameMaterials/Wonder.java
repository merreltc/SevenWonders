package dataStructures.gameMaterials;

import java.util.HashSet;

import backend.factories.LevelFactory;
import constants.GeneralEnums.Side;
import constants.WonderInfo;
import exceptions.CannotBuildWonderException;

public class Wonder {
	private WonderType type;
	private String name;
	private EntityEffect resource;
	private Side side;
	private int numBuiltLevels = 0;
	private HashSet<Level> levels;
	private WonderInfo info;
	private LevelFactory levelFactory;

	public enum WonderType {
		COLOSSUS, LIGHTHOUSE, TEMPLE, GARDENS, STATUE, MAUSOLEUM, PYRAMIDS
	}

	public Wonder(Side side, WonderType type) {
		this.info = new WonderInfo();
		initializeData(side, type);
		setLevels(new HashSet<Level>());
		this.levelFactory = new LevelFactory(this);
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

	public Level buildNextLevel() {
		this.numBuiltLevels++;
		if (this.numBuiltLevels > this.getNumLevels()) {
			throw new CannotBuildWonderException("Player has built max number of levels.");
		} else {
			Level temp = this.levelFactory.getNextLevel(this.numBuiltLevels);
			this.levels.add(temp);
			return temp;
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

	public void setLevels(HashSet<Level> levels) {
		this.levels = levels;
	}
	
	public LevelFactory getLevelFactory(){
		return this.levelFactory;
	}

	public HashSet<Level> getLevels() {
		return this.levels;
	}
}
