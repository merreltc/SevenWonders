package backend.factories;

import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Wonder;

public class LevelFactory {
	LevelBuilder builder;

	public LevelFactory(Wonder wonder) {
		this.builder = new LevelBuilder(wonder);
	}

	public Level getNextLevel(int priority) {
		return this.builder.buildLevel(priority);
	}
}
