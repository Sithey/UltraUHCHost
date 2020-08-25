package fr.sithey.ultrauhchost.lib;

import net.minecraft.server.v1_8_R3.BiomeBase;

import java.lang.reflect.Field;

public class BiomeReplacer {
	
	public static void init() {
		try {
			Field biomeF = BiomeBase.class.getDeclaredField("biomes");
			biomeF.setAccessible(true);
			
			if (biomeF.get(null) instanceof BiomeBase[]) {
				
				BiomeBase[] biomes = (BiomeBase[]) biomeF.get(null);
				
				swap(biomes, BiomeBase.JUNGLE, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.OCEAN, BiomeBase.PLAINS);
			    swap(biomes, BiomeBase.JUNGLE_HILLS, BiomeBase.FOREST_HILLS);
			    swap(biomes, BiomeBase.JUNGLE_EDGE, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.DEEP_OCEAN, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.DESERT, BiomeBase.SAVANNA);
			    swap(biomes, BiomeBase.MESA, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.MESA_PLATEAU, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.MESA_PLATEAU_F, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.DESERT_HILLS, BiomeBase.FOREST_HILLS);
			    swap(biomes, BiomeBase.ICE_MOUNTAINS, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.ICE_PLAINS, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.BEACH, BiomeBase.SAVANNA);
			    swap(biomes, BiomeBase.COLD_BEACH, BiomeBase.SMALL_MOUNTAINS);
			    swap(biomes, BiomeBase.STONE_BEACH, BiomeBase.SAVANNA_PLATEAU);
			    swap(biomes, BiomeBase.SWAMPLAND, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.FROZEN_OCEAN, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.MEGA_TAIGA, BiomeBase.PLAINS);
			    swap(biomes, BiomeBase.MEGA_TAIGA_HILLS, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.TAIGA, BiomeBase.PLAINS);
			    swap(biomes, BiomeBase.TAIGA_HILLS, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.EXTREME_HILLS, BiomeBase.FOREST);
			    swap(biomes, BiomeBase.EXTREME_HILLS_PLUS, BiomeBase.FOREST_HILLS);

			    biomeF.set(null, biomes);
			}
			biomeF.setAccessible(false);
			
		} catch (Exception e) {}
	}
	
	private static void swap(BiomeBase[] biomes, BiomeBase from, BiomeBase to) {
		biomes[from.id] = to;
	}
}
