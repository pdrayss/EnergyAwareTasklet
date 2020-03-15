package logic;

import java.sql.Timestamp;

import main.Tasklet;
import main.TaskletParameterList;
import main.TaskletResult;

public class Array {
	
	private static String pathToCMMFile = "CMMAppCode/array.cmm";
	private static String pathToCMMFile1gb = "CMMAppCode/array2.cmm";
	private static int iterationCounter = 10;
	private static String csvReturn = "";
	private static int counter = 0;

	public static void main(String[] args) {
		//fillArray();

		for(int i = 0; i < iterationCounter; i++) {
			fillArray1gb();			
		}
	}
	

	public static String execute(int iterationCount) {
		iterationCounter = iterationCount;
		for(int i = 0; i < iterationCounter; i++) {
			fillArray1gb();			
		}
		return csvReturn;
	}
	
	private static void fillArray() {
		
		Tasklet t = Tasklet.fromFile(pathToCMMFile);
		TaskletParameterList p = t.getParameterList();
		p.addInt("current", 0);
		t.setParameterList(p);
		System.out.println("Tasklet ready..."); //consumers around ~400 Mb of memory per array
		t.start();
		
		TaskletResult allResults = t.waitForResult();
				
		System.out.println("Arrays - Results received: " + allResults.size());
		System.out.println("Array filled with a size of: " + allResults.getInt(0));
		System.out.println("Array countains numbers which can be diveded by 42 exactly: " + allResults.getInt(1) + " times");
	}
	
	private static void fillArray1gb() {
		counter++;
		
		Tasklet t = Tasklet.fromFile(pathToCMMFile1gb);
		TaskletParameterList p = t.getParameterList();
		p.addInt("current", 0);
		t.setParameterList(p);
		System.out.println("Tasklet ready...");  //consumers around ~1100 Mb of memory per array
		t.start();
		
		TaskletResult allResults = t.waitForResult();
		
		System.out.println("Arrays - Results received: " + allResults.size());
		System.out.println("Array filled with a size of: " + allResults.getInt(0));
		System.out.println("Array countains numbers which can be diveded by 42 exactly: " + allResults.getInt(1) + " times");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		//prepare CSV file output
		csvReturn = csvReturn.concat(
					now + ", " + 
					"arraySize: " + allResults.getInt(0) + ", " +
					"iteration: " + counter + System.lineSeparator());
		
	}
}
