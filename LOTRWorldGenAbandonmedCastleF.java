package lotr.common.world.structure2;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

//put this in the lotr.common.world.structure2 package of the LOTR Mod

public class LOTRWorldGenAbandomnedCastleF extends LOTRWorldGenStructureBase2
{

	private LOTRWorldGenSmallTowerF SmallTowerGen = new LOTRWorldGenSmallTowerF(false);
	
	public LOTRWorldGenAbandomnedCastleF(boolean flag) 
	{
		super(flag);
	}

	@Override
	public boolean generateWithSetRotation(World world, Random random, int i, int j, int k, int rotation) 
	{
		if (restrictions)
		{
			if (world.getBlock(i, j-1, k) != Blocks.grass)
			{
				return false;
			}
			
			rotation = random.nextInt(4);
			
			if (!restrictions && usingPlayer != null) rotation = usingPlayerRotation();

		}
		
		setOrigin(i, j, k);
		
		int d = 1;
		
		SmallTowerGen.generateWithSetRotation(world, random, i, j, k+5, d+2);
		
		SmallTowerGen.generateWithSetRotation(world, random, i, j, k-11, d);
		
		SmallTowerGen.generateWithSetRotation(world, random, i-16, j, k+5, d+2);

		SmallTowerGen.generateWithSetRotation(world, random, i-16, j, k-11, d);
		
		
		for (int j1=-3; j1<=7; j1++) genWall(world, random, j1);
		
		for (int j1=-3; j1<= 10; j1++) genMiddleTower(world, random, j1);
		
		int j1 = -1;
		
		for (int i1=9; i1<=13; i1++)
		{
			for (int k1=-2; k1<=2; k1++)
			{
				placeRandomBrick(world, random, i1, j1, k1);
			}
		}
		
		j1 = 9;
		
		for (int i1=9; i1<=13; i1++)
		{
			for (int k1=-2; k1<=2; k1++)
			{
				placeRandomBrick(world, random, i1, j1, k1);
			}
		}
		
		return true;
	}
	
	private void genWall(World world, Random random, int j1)
	{
		for (int i1=0; i1<=22; i1++)
		{
			for (int k1=-11; k1<=11; k1++)
			{
				if ( (i1==2 || i1==20 ) && ( k1>=-4 && k1<=4 ) ) placeRandomBrick(world, random, i1, j1, k1);
				if ( ( k1==9 || k1==-9 ) && ( i1>=7 && i1<=15 ) ) placeRandomBrick(world, random, i1, j1, k1);
			}
		}

		for (int i1=0; i1<=22; i1++)
		{
			for (int k1=-11; k1<=11; k1++)
			{
				if ( (i1==2 || i1==20 ) && ( k1>=-4 && k1<=4 ) )
				{						
					for ( j1=-4; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
				else
				if ( ( k1==9 || k1==-9 ) && ( i1>=7 && i1<=15 ) )
				{
					for ( j1=-4; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
			}
		}
	}
	
	private void genMiddleTower(World world, Random random, int j1)
	{
		for (int i1=0; i1<=22; i1++)
		{
			for (int k1=-11; k1<=11; k1++)
			{
				if ( ( k1==3 || k1==-3 ) && ( i1>=8 && i1<=14 ) ) placeRandomBrick(world, random, i1, j1, k1);
				if ( ( k1>=-3 && k1<=3 ) && ( i1==8 || i1==14 ) ) placeRandomBrick(world, random, i1, j1, k1);
			}
		}
		
		for (int i1=0; i1<=22; i1++)
		{
			for (int k1=-11; k1<=11; k1++)
			{
				if ( ( k1==3 || k1==-3 ) && ( i1>=8 && i1<=14 ) )
				{
					for ( j1=-4; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
				if ( ( k1>=-3 && k1<=3 ) && ( i1==8 || i1==14 ) )
				{
					for ( j1=-4; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
			}
		}
		if ( j1==0 || j1==9 )
		{
			for (int i1=9; i1<=13; i1++)
			{
				for (int k1=-2; k1<=2; k1++)
				{
					placeRandomBrick(world, random, i1, j1, k1);
				}
			}
		}
	}
	
	private void placeRandomBrick(World world, Random random, int i, int j, int k)
	{
		int r=random.nextInt(5);
		if (r==0)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 0);
		}
		else if (r==1)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 1);
		}
		else if (r==2)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.stonebrick, 2);
		}
		else if (r==3)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.cobblestone, 0);
		}
		else if (r==4)
		{
			setBlockAndMetadata(world, i, j, k, Blocks.mossy_cobblestone, 0);
		}
	}
	
	
}
