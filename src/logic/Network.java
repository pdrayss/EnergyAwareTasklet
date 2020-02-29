package logic;

import java.util.Random;

import enums.ResultMode;
import main.ResultList;
import main.Tasklet;
import main.TaskletResults;
import main.WrongDataTypeException;

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
		Tasklet t = new Tasklet(pathToCMMFile);
		Tasklet.setNumberOfRuns(1);
		t.addIntArray(array1);
		t.addIntArray(array2);
		t.addIntArray(array3);
		t.addIntArray(array4);
		t.addIntArray(array5);
		System.out.println("Tasklet ready...");
		t.start(1);
		TaskletResults results = Tasklet.getTaskletResults(ResultMode.EVERYTHING);
		System.out.println("Result size: " + results.size());
		ResultList resultsList = results.get(1);
		try {
			int result = resultsList.getInteger(0);
			System.out.println("Number of entries being able to devide by 42:  " + result);
		} catch (WrongDataTypeException e) {
			e.printStackTrace();
		}
		
	}

}
