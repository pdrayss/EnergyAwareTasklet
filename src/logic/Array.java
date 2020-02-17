package logic;


import enums.ResultMode;
import main.ResultList;
import main.Tasklet;
import main.TaskletBundle;
import main.TaskletParameterList;
import main.TaskletResult;
import main.TaskletResultListener;
import main.TaskletResultPool;
import main.TaskletResults;
import main.WrongDataTypeException;

public class Array {
	
	private static String pathToCMMFile = "CMMAppCode/array.cmm";

	public static void main(String[] args) {
		fillArray();
	}
	
	public static void fillArray() {
		
		Tasklet.setNumberOfRuns(1);
		Tasklet t = new Tasklet(pathToCMMFile);
		System.out.println("Tasklet ready...");
		t.start(1);
		TaskletResults results = Tasklet.getTaskletResults(ResultMode.EVERYTHING);
		System.out.println(results.size());
		ResultList resultsList = results.get(1);
		try {
			int size = resultsList.getInteger(0);
			int numberCount = resultsList.getInteger(1);
			System.out.println("Array filled with a size of: " + size);
			System.out.println("Array countains numbers which can be diveded by 42 exactly: " + numberCount + " times");
		} catch (WrongDataTypeException e) {
			e.printStackTrace();
		}
	}
}
