

import main.Tasklet;
import main.TaskletBundle;
import main.TaskletParameterList;
import main.TaskletResult;
import main.TaskletResultPool;

public class ProcessorLoad {
	
	private final static String pathToCMMFile = "/EnergyAwareTasklet/CMMAppCode/processorLoad.cmm";

	public static void main(String[] args) {
		testPrimes();
	}
	
	public void createTasklet() {
		Tasklet.setNumberOfRuns(1);
	}
	
	public static void testPrimes() {
		TaskletBundle t = TaskletBundle.fromFile(pathToCMMFile);
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
