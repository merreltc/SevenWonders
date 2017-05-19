package constants;

import java.util.HashMap;

import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Wonder.WonderType;

public class WonderInfo {
	public int getNumLevels(Side side, WonderType type) {
		if (side == Side.A) {
			return 3;
		} else {
			return getSideBLevels(type);
		}
	}

	private int getSideBLevels(WonderType type) {
		switch (type) {
		case COLOSSUS:
			return 2;
		case LIGHTHOUSE:
		case TEMPLE:
		case GARDENS:
		case STATUE:
		case MAUSOLEUM:
			return 3;
		case PYRAMIDS:
			return 4;
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public String getNameByType(WonderType wonder) {
		switch (wonder) {
		case COLOSSUS:
			return "The Colossus of Rhodes";
		case LIGHTHOUSE:
			return "The Lighthouse of Alexandria";
		case TEMPLE:
			return "The Temple of Artemis in Ephesus";
		case GARDENS:
			return "The Hanging Gardens of Babylon";
		case STATUE:
			return "The Statue of Zeus in Olympia";
		case MAUSOLEUM:
			return "The Mausoleum of Halicarnassus";
		case PYRAMIDS:
			return "The Pyramids of Giza";
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public String getShortNameByType(WonderType wonder) {
		switch (wonder) {
		case COLOSSUS:
			return "Colossus";
		case LIGHTHOUSE:
			return "Lighthouse";
		case TEMPLE:
			return "Temple of Artemis";
		case GARDENS:
			return "Hanging Gardens";
		case STATUE:
			return "Statue of Zeus";
		case MAUSOLEUM:
			return "Mausoleum";
		case PYRAMIDS:
			return "Pyramids";
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}
	}

	public EntityEffect getResourceByType(WonderType type) {
		EntityType entity;
		Enum specificResource;
		Resource resource;

		switch (type) {
		case COLOSSUS:
			resource = Resource.ORE;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case LIGHTHOUSE:
			resource = Resource.GLASS;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case TEMPLE:
			resource = Resource.PRESS;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case GARDENS:
			resource = Resource.CLAY;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case STATUE:
			resource = Resource.LUMBER;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case MAUSOLEUM:
			resource = Resource.LOOM;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		case PYRAMIDS:
			resource = Resource.STONE;
			entity = getEntityType(resource);
			specificResource = getSpecificResource(resource);
			break;
		default:
			throw new IllegalArgumentException("Bad Wonder Type");
		}

		HashMap<Enum, Integer> effect = new HashMap<Enum, Integer>();
		effect.put(specificResource, 1);

		return new EntityEffect(entity, effect);
	}

	private EntityType getEntityType(Resource resource) {
		switch (resource) {
		case ORE:
		case STONE:
		case CLAY:
		case LUMBER:
			return EntityType.RESOURCE;
		case GLASS:
		case PRESS:
		case LOOM:
			return EntityType.MANUFACTUREDGOOD;
		default:
			throw new IllegalArgumentException("Bad Resource Type");
		}
	}

	private Enum getSpecificResource(Resource resource) {
		switch (resource) {
		case ORE:
			return RawResource.ORE;
		case STONE:
			return RawResource.STONE;
		case CLAY:
			return RawResource.CLAY;
		case LUMBER:
			return RawResource.LUMBER;
		case GLASS:
			return Good.GLASS;
		case PRESS:
			return Good.PRESS;
		case LOOM:
			return Good.LOOM;
		default:
			throw new IllegalArgumentException("Bad Resource Type");
		}
	}
}
