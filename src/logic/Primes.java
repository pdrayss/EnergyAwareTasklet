package logic;


import enums.ResultMode;
import execution.TaskletBundleAwaiter;
import main.ResultList;
import main.Tasklet;
import main.TaskletBundle;
import main.TaskletParameterList;
import main.TaskletResult;
import main.TaskletResultListener;
import main.TaskletResultPool;
import main.TaskletResults;
import main.WrongDataTypeException;

public class Primes {
	
	private static String pathToCMMFile = "CMMAppCode/primes2.cmm";
	private static String pathToCMMFileIt = "CMMAppCode/primes3.cmm";
	private static int bundelSize = 16;
	private static int iterations = 16;

	public static void main(String[] args) {
		//primes();
		//primesBundle();
		primesIterarions();
	}
	
	public static void primes() {
		
		int lower = 1;
		int upper = 20000;
		
		Tasklet t = new Tasklet(pathToCMMFile);
		Tasklet.setNumberOfRuns(1);
		t.addInt(lower);
		t.addInt(upper);
		System.out.println("Tasklet ready...");
		t.start(1);
		TaskletResults results = Tasklet.getTaskletResults(ResultMode.EVERYTHING);
		System.out.println(results.size());
		ResultList resultsList = results.get(1);
		try {
			int result = resultsList.getInteger(0);
			System.out.println("Number of primes between " + lower +" and " + upper +": " + result);
		} catch (WrongDataTypeException e) {
			e.printStackTrace();
		}
	}
	
	public static void primesBundle() {
		TaskletBundle t = TaskletBundle.fromFile(pathToCMMFile);

		Tasklet.setNumberOfRuns(bundelSize);
		for (int i = 0; i < bundelSize; i++) {
			TaskletParameterList p = t.getNewParameterList();
			p.addInt("low", 1);
			p.addInt("high", 20000);
			t.addParameterizedRun(p);
		}
		t.start();
		
		TaskletResultPool allResults = t.waitForAllResults();
		System.out.println("Primes Test - Tasklets started, now waiting for all results...");
		System.out.println("Allresults keyset: " + allResults.keySet());

		for (TaskletResult nextResult : allResults.values()) {
			if (nextResult.hadTimeout()) {
				System.out.println("Result had timeout");
				continue;
			}
//			 for (Object nextPrime : nextResult.getResultItems()) {
//			 System.out.println("Found Prime Number: " + (int) nextPrime);
//			 }
		}
	}
	
	public static void primesIterarions() {
		
		int lower = 1;
		int upper = 20000;
		
		Tasklet.setNumberOfRuns(iterations);
		for (int i = 0; i < iterations; i++) {
			Tasklet t = new Tasklet(pathToCMMFileIt);
			t.setQoCReliable();
			t.addInt(lower);
			t.addInt(upper);
			t.start(i);
		}
		
		TaskletResults results = Tasklet.getTaskletResults(ResultMode.EVERYTHING);
		System.out.println("Primes Iterations - Results received: " + results.size());
		

		int result[] = new int[results.size()];
		int i = 0;
		for (ResultList resultList : results.tResults) {
			try {			
				result[i] = resultList.getInteger(0);
				System.out.println("Getting result No. " + i + ": " + resultList.getInteger(0));
				i++;
			} catch (WrongDataTypeException e) {
				e.printStackTrace();
			}
		}
	}

}