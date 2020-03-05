package logic;


import main.Tasklet;
import main.TaskletBundle;
import main.TaskletParameterList;
import main.TaskletResult;
import main.TaskletResultPool;

public class Primes {
	
	private static String pathToCMMFile = "CMMAppCode/primes2.cmm";
	private static int bundelSize = 16;

	public static void main(String[] args) {
		//singlePrimes();
		primesBundle();
	}
	
	private static void singlePrimes() {
		
		int lower = 1;
		int upper = 20000;
		
		Tasklet t = Tasklet.fromFile(pathToCMMFile);
		TaskletParameterList p = t.getParameterList();
		p.addInt("low", lower);
		p.addInt("high", upper);
		t.setParameterList(p);
		t.start();
		
		TaskletResult allResults = t.waitForResult();
		
		System.out.println("Primes - Results received: " + allResults.size());
		System.out.println("Number of primes between " + lower +" and " + upper +": " + allResults.getInt(0));
		
	}
	
	private static void primesBundle() {
		
		int lower = 1;
		int upper = 20000;
		
		TaskletBundle taskletBundle = TaskletBundle.fromFile(pathToCMMFile);
		for (int i = 0; i < bundelSize; i++) {
			TaskletParameterList p = taskletBundle.getNewParameterList();
			p.addInt("low", lower);
			p.addInt("high", upper);
			taskletBundle.addParameterizedRun(p);
		}
		taskletBundle.start();
		
		TaskletResultPool allResults = taskletBundle.waitForAllResults();
		System.out.println("Primes Test - Tasklets started, now waiting for all results...");
		System.out.println("Allresults keyset: " + allResults.keySet());

		for (TaskletResult nextResult : allResults.values()) {
			if (nextResult.hadTimeout()) {
				System.out.println("Result had timeout");
				continue;
			}
			else {
				System.out.println("Number of primes between " + lower +" and " + upper +": " + nextResult.getInt(0));
			}
		}
	}
}