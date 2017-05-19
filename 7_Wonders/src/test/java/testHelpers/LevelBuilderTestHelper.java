package testHelpers;

import java.util.HashMap;
import java.util.HashSet;

import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;

public class LevelBuilderTestHelper {
	private Wonder wonder;
	WonderCostGenerator costGenerator;
	WonderEffectsGenerator effectGenerator;

	public LevelBuilderTestHelper() {

	}

	public void setWonder(Wonder wonder) {
		this.wonder = wonder;
		this.costGenerator = new WonderCostGenerator(this.wonder);
		this.effectGenerator = new WonderEffectsGenerator(this.wonder);
	}

	public Level getExpectedLevel(int priority) {
		Cost cost = this.costGenerator.getExpectedCost(priority);
		HashMap<Frequency, HashSet<Effect>> effects = this.effectGenerator.getExpectedEffects(priority);
		return new Level(priority, cost, effects);
	}
}
