package atn_project2;

import java.util.ArrayList;
import java.util.Random;

public class CalculateMinimumCut {
	public static int minimumCut(int[][] Graph ,int N,float noOfEdges){
		Random r = new Random();
		int present;
		int nxt =0, maxEdges=0,c=0,remove=0,minimum=0;
		ArrayList<Integer> MAordering = new ArrayList<Integer>();
		int remGraph = 2147483647;
		
		while(N >=2) // checking if N greater than or equal to 2 for contraction
		{
			c =0;
			present = r.nextInt(N);
			present = 0;
			MAordering.add(present); //Updating the MaximumAdjacency (MAordering) ordering
			c++;
			int[][] g = new int[N][N];
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					g[i][j] = Graph[i][j];
			
			while(c < N)
			{
				maxEdges = 0;
				for(int j=0;j<N ;j++)
				{
					if(g[present][j] >= maxEdges)
					{
						maxEdges = g[present][j];
						nxt = j; // Find next node
					}
				}
				
				MAordering.add(nxt);// Adding found node
				c++;
				
				if(c == (N-2))
				{
					remove = g[present][nxt];
				}
				
				for(int j=0; j<N ;j++)
				{
					g[present][j] += g[nxt][j];
					g[j][present] += g[nxt][j];
					g[present][present] = 0;
					g[nxt][j] =0;
					g[j][nxt] =0;
				}
			}
			
			// Graph Merge
			int nodeN, nodeNminusOne;
			nodeN = MAordering.get(MAordering.size() - 1);
			nodeNminusOne = MAordering.get(MAordering.size() - 2);
			
			for (int j = 0; j < N; j++) 
			{
				Graph[nodeNminusOne][j] += Graph[nodeN][j];
				Graph[j][nodeNminusOne] += Graph[j][nodeN];
				Graph[nodeNminusOne][nodeNminusOne] = 0;
				Graph[nodeN][j] = 0;
				Graph[j][nodeN] =0;
			}
			
			if (remGraph >= remove) 
			{
				remGraph = remove;
			}
			N--;
			
			int m =0, n=0;
			int[][] newGraph = new int[N][N];
			
			for (int i = 0; i < Graph.length; i++) 
			{
				if (i != nodeN) 
				{
					n = 0;
					for (int j = 0; j < Graph.length; j++) 
					{
						if (j != nodeN) 
						{
							newGraph[m][n] = Graph[i][j];
							n++;
						}
					}
					m++;
				}
				else
					continue;
			}
			Graph = newGraph;
			minimum = remGraph;
		}
		return minimum;
	}
}
