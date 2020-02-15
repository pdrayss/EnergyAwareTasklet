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

public class Primes {
	
	private static String pathToCMMFile = "CMMAppCode/primes2.cmm";

	public static void main(String[] args) {
		//testPrimes();
		primes();
	}
	
	public static void primes() {
		Tasklet.setNumberOfRuns(1);
		Tasklet t = new Tasklet(pathToCMMFile);
		t.addInt(1);
		t.addInt(20000);
		System.out.println("Tasklet ready");
		t.start(1);
		TaskletResults results = Tasklet.getTaskletResults(ResultMode.EVERYTHING);
		System.out.println(results.size());
		ResultList resultsList = results.get(1);
		try {
			int result = resultsList.getInteger(0);
			System.out.println("Result of primes: " + result + ".");
		} catch (WrongDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testPrimes() {
		TaskletBundle t = TaskletBundle.fromFile("primes.cmm");
		t.setTimeout(3000000);

		t.getQoCList().setReliable();

		for (int i = 0; i < 2; i++) {

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
			// If the Tasklet had a timeout, just skip it. We don't need to
			// re-run it...
			if (nextResult.hadTimeout()) {
				System.out.println("Result had timeout");
				continue;
			}
//			 for (Object nextPrime : nextResult.getResultItems()) {
//			 System.out.println("Found Prime Number: " + (int) nextPrime);
//			 }
		}
	}

}
