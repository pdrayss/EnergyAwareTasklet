package logic;

import main.Tasklet;
import main.TaskletParameterList;
import main.TaskletResult;

public class Array {
	
	private static String pathToCMMFile = "CMMAppCode/array.cmm";
	private static String pathToCMMFile1gb = "CMMAppCode/array2.cmm";

	public static void main(String[] args) {
		//fillArray();
		fillArray1gb();
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
	}
}
