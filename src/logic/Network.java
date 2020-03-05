package logic;

import java.util.Random;

import main.Tasklet;
import main.TaskletParameterList;
import main.TaskletResult;

public class Network {
	
	private static String pathToCMMFile = "CMMAppCode/networkArrays.cmm";

	public static void main(String[] args) {
		networkArrays();

	}
	
	private static void networkArrays() {
		Random random = new Random();
		int[] array1 = random.ints(10000000, 10,10000000).toArray();
		int[] array2 = random.ints(10000000, 10,10000000).toArray();
		int[] array3 = random.ints(10000000, 10,10000000).toArray();
		int[] array4 = random.ints(10000000, 10,10000000).toArray();
		int[] array5 = random.ints(10000000, 10,10000000).toArray();
		
		//networkArrays.cmm returns number of entries being able to divide by 42
		Tasklet t = Tasklet.fromFile(pathToCMMFile);
		TaskletParameterList p = t.getParameterList();
		p.addIntArray("importArrayA", array1);
		p.addIntArray("importArrayB", array2);
		p.addIntArray("importArrayC", array3);
		p.addIntArray("importArrayD", array4);
		p.addIntArray("importArrayE", array5);
		System.out.println("Tasklet ready...");
		t.start();
		TaskletResult allResults = t.waitForResult();
		
		System.out.println("NetworkArrays - Results received: " + allResults.size());
		System.out.println("Number of entries being able to devide by 42: " + allResults.getInt(0));		
	}

}
