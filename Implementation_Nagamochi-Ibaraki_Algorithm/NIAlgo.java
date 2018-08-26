package atn_project2;

import java.util.HashMap;
import java.util.Map;

public class NIAlgo {
	
	public static void main(String[] args) {
		int N = 19;
		int noOfEdges = 20;
		int c =0;
		int[][] g = new int[N][N];
		System.out.println("NumberOfEdges   lambda ");
		float[] cutArray = new float[5];
		Map<Float,Integer> largest = new HashMap<Float,Integer>();
		Map<Float,Integer> smallest = new HashMap<Float,Integer>();
		Map<Float,Integer> stability = new HashMap<Float,Integer>();
		
		while(noOfEdges <= 168)
		{
			
			while(c<5) // Repeating 5 times for each edge value
			{
				g = GenerateGraph.graphGenerator(g, N, noOfEdges);
				
				float minimum = CalculateMinimumCut.minimumCut(g, N,noOfEdges);
				cutArray[c] = minimum;
				c++;
			}
			float sum =0;
			float avg = 0;
			for(int i=0; i<5; i++){
				sum += cutArray[i];
			}
			avg = sum/5;
			
			if(largest.containsKey(avg))
			{
				if(largest.get(avg) < noOfEdges)
					largest.put(avg, noOfEdges);
			}
			else
				largest.put(avg, noOfEdges);
			
			if(smallest.containsKey(avg))
			{
				if(smallest.get(avg) > noOfEdges)
					smallest.put(avg, noOfEdges);
			}
			else
				smallest.put(avg, noOfEdges);
			
			int Stability = largest.get(avg) - smallest.get(avg) ;
			stability.put(avg, Stability);
			System.out.println(noOfEdges + "               " + avg);
			noOfEdges += 4;
			c = 0;
		}
		System.out.println("AvgerageLambda   Stability");
		for(Float s : stability.keySet())
		{
			System.out.println(s + "                " + stability.get(s));
		}
	}
}