package atn_project2;

import java.util.Random;

public class GenerateGraph {
	public static int[][] graphGenerator(int[][] Graph, int N ,float noOfEdges){
		Random r = new Random();
		
		for(int i=0; i<N ;i++){
			for(int j=0; j<N; j++)
				Graph[i][j] = 0;
		}

		for(int i=0; i<noOfEdges; i++){
			int source = r.nextInt(N);
			int dest = r.nextInt(N);
			while(source==dest || Graph[source][dest] !=0 )
			{ 
				source = r.nextInt(N);
				dest = r.nextInt(N);
			}
			Graph[source][dest] += 1;
			Graph[dest][source] += 1;
		}
		return Graph;
	}
}
