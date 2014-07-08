package lotr.common.world.biome;

import lotr.common.entity.npc.LOTREntityGondorArcher;
import lotr.common.entity.npc.LOTREntityGondorSoldier;
import lotr.common.entity.npc.LOTREntityMordorOrc;
import lotr.common.entity.npc.LOTREntityMordorOrcArcher;
import lotr.common.entity.npc.LOTREntityMordorOrcBombardier;
import lotr.common.entity.npc.LOTREntityNearHaradrimArcher;
import lotr.common.entity.npc.LOTREntityNearHaradrimWarrior;
import lotr.common.world.structure.LOTRWorldGenGondorRuin;
import lotr.common.world.structure.LOTRWorldGenGondorRuins;
import lotr.common.world.structure.LOTRWorldGenGundabadCamp;
import lotr.common.world.structure.LOTRWorldGenMordorCamp;
import lotr.common.world.structure.LOTRWorldGenRuinedGondorTower;
import lotr.common.world.structure.LOTRWorldGenStoneRuin;
import lotr.common.world.structure.LOTRWorldGenStoneRuinGondor;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

/*
  put this in the lotr.common.world.biome package
  add the following in the LOTRBiome.java
  ublic static BiomeGenBase gondorBattlefield;
  add the following in the public static void initBiomes()
  gondorBattlefield = new LOTRBiomeGenGondorBattlefield(119).setTemperatureRainfall(1.5F, 0.1F).setMinMaxHeight(0.1F, 0.1F).setColor(0xADAD88).setBiomeName("gondorBattlefield");
*/
public class LOTRBiomeGenGondorBattlefield extends LOTRBiomeGenGondor
{
	public LOTRBiomeGenGondorBattlefield(int i) {
		super(i);
		
		spawnableGoodList.add(new SpawnListEntry(LOTREntityGondorSoldier.class, 20, 4, 6));
		spawnableGoodList.add(new SpawnListEntry(LOTREntityGondorArcher.class, 10, 4, 6));
		
		spawnableEvilList.clear();
		spawnableEvilList.add(new SpawnListEntry(LOTREntityMordorOrc.class, 20, 4, 6));
		spawnableEvilList.add(new SpawnListEntry(LOTREntityMordorOrcArcher.class, 7, 4, 6));
		spawnableEvilList.add(new SpawnListEntry(LOTREntityMordorOrcBombardier.class, 3, 1, 2));
		spawnableEvilList.add(new SpawnListEntry(LOTREntityNearHaradrimWarrior.class, 3, 4, 4));
		spawnableEvilList.add(new SpawnListEntry(LOTREntityNearHaradrimArcher.class, 2, 4, 4));
		
		decorator.addRandomStructure(new LOTRWorldGenGundabadCamp(), 75);
		decorator.addRandomStructure(new LOTRWorldGenGondorRuins(), 100);
		decorator.addRandomStructure(new LOTRWorldGenGondorRuin(false), 120);
		decorator.addRandomStructure(new LOTRWorldGenRuinedGondorTower(false), 100);
		decorator.addRandomStructure(new LOTRWorldGenStoneRuinGondor(5, 5), 65);
	}
	
	@Override
	public boolean canSpawnHostilesInDay()
	{
		return true;
	}

}
