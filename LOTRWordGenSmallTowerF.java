package lotr.common.world.structure2;
import java.util.Random;

import com.google.common.base.Function;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

//put this in the structure2 package of the LOTRMod

public class LOTRWorldGenSmallTowerF extends LOTRWorldGenStructureBase2
{
	
	static int depth = 0;
	
	public LOTRWorldGenSmallTowerF(boolean flag) 
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
				findDepth(world, i, j, k);
				j = j-depth;
			}
		}
		
		if (!restrictions && usingPlayer != null) rotation = usingPlayerRotation();
				
		setOrigin(i, j, k);
		
		genBasis(world, random);
		
		for (int j1=-2; j1<=20; j1++)
		{
			genCircle(world, random, j1);
			if (j1 % 6 == 0 && j1 !=0)
			{
				genFloor(world, random, j1);
			}
			if ( j1>=1 && j1 !=20 )
			{
				genLadder(world, random, j1, rotation);
			}
		}
		

		
		setDToO();

		return true;
	}
	
	//end of original Generator
	
	private void genCircle(World world, Random random, int j1)
	{
		for (int i1=0; i1<=6; i1++)
		{
			for (int k1=0; k1<=6; k1++)
			{	
				if ( i1==0 && ( k1==2 || k1==3 || k1==4  ) )
				{
					placeRandomBrick(world, random, i1, j1, k1);
				}
				else
				if ( i1==6 && ( k1==2 || k1==3 || k1==4  ) )
				{
					placeRandomBrick(world, random, i1, j1, k1);
				}
				else 
				if ( (i1==1 || i1 ==5) && ( k1==1 || k1==5 ) )
				{
					placeRandomBrick(world, random, i1, j1, k1);
				}
				else if ( ( i1==2 || i1==3 || i1==4 ) && ( k1==0 || k1==6 ) )
				{
					placeRandomBrick(world, random, i1, j1, k1);
				}
			}
		}
	}
	
	private void genFloor(World world, Random random, int j1)
	{
		for (int i1=0; i1<=6; i1++)
		{
			for (int k1=0; k1<=6; k1++)
			{
				if ( (i1==1 || i1==5 ) && ( k1==2 || k1==3 || k1==4 ) )
				{
					placeStoneSlab(world, random, i1, j1, k1);
				}
				else
				if ( ( i1>=2 && i1<=4) && ( k1>=1 && k1<=5))
				{
					placeStoneSlab(world, random, i1, j1, k1);
				}
			}
		}
	}
	
	private void genLadder(World world, Random random, int j1, int rotation)
	{
		for (int i1=0; i1<=6; i1++)
		{
			for (int k1=0; k1<=6; k1++)
			{
				if ( rotation==0 )
				{
					if ( i1==1 && k1==3) setBlockAndMetadata(world, i1, j1, k1, Blocks.ladder, 4);
				}
				else
				if ( rotation==1 )
				{
					if ( i1==3 && k1==1) setBlockAndMetadata(world, i1, j1, k1, Blocks.ladder, 3);
				}
				else
				if ( rotation==2 )
				{
					if ( i1==5 && k1==3) setBlockAndMetadata(world, i1, j1, k1, Blocks.ladder, 5);
				}
				else
				if ( rotation==3 )
				{
					if ( i1==3 && k1==5) setBlockAndMetadata(world, i1, j1, k1, Blocks.ladder, 2);
				}
			}
		}
	}
	
	private void genBasis(World world, Random random)
	{
		for (int i1=0; i1<=6; i1++)
		{
			for (int k1=0; k1<=6; k1++)
			{
				if ( i1==0 && ( k1==2 || k1==3 || k1==4  ) )
				{
					for (int j1 = -3; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
				else
				if ( i1==6 && ( k1==2 || k1==3 || k1==4  ) )
				{
					for (int j1 = -3; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)				
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
				else 
				if ( (i1==1 || i1 ==5) && ( k1==1 || k1==5 ) )
				{
					for (int j1 = -3; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
				}
				else if ( ( i1==2 || i1==3 || i1==4 ) && ( k1==0 || k1==6 ) )
				{
					for (int j1 = -3; !isOpaque(world, i1, j1, k1) && getY(j1) >= 0; j1--)
					{
						placeRandomBrick(world, random, i1, j1, k1);
					}
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
	
	private void placeStoneSlab(World world, Random random, int i, int j, int k)
	{
		setBlockAndMetadata(world, i, j, k, Blocks.stone_slab, 8);
	}
	
	private void findDepth (World world, int i, int j, int k)
	{
		if (world.getBlock(i, j-1, k) != Blocks.grass)
		{
			if (depth != -1)
			{	
				j--;
				goDown();
				findDepth(world, i, j, k);
			}
			else
			{
				j += 22;
				findDepthUp(world, i, j, k);
				return;
			}
		}
	}
	
	private void findDepthUp(World world, int i, int j, int k)
	{
		if (world.getBlock(i, j, k) != Blocks.grass)
		{
			if (depth >= -10)
			{
				j++;
				goUp();
				findDepthUp(world, i, j, k);
			}
			else return;	
		}
	}
	
	static int goDown()
	{
		if (depth <= 20)
		{
			depth++;
			return depth;
		}
		else
		{
			depth = -1;
			return depth;
		}
	}
	
	static int goUp()
	{
		depth--;
		return depth;
	}
	
	static int setDToO()
	{
		depth = 0;
		return depth;
	}
	
}
