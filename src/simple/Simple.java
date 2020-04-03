package simple;

import main.Tasklet;
import main.TaskletParameterList;
import main.TaskletResult;

public class Simple {

	public static void main(String[] args) {
			
		int lower = 1;
		int upper = 20000;
		
		Tasklet t = Tasklet.fromFile("CMMAppCode/primes2.cmm");
		TaskletParameterList p = t.getParameterList();
		p.addInt("low", lower);
		p.addInt("high", upper);
		t.setParameterList(p);

		//TEST
		t.getQoCList().setReliable();
		t.getQoCList().setRemote();
		
		t.start();
		
		TaskletResult allResults = t.waitForResult();
		
		System.out.println("Primes - Results received: " + allResults.size());
		System.out.println("Number of primes between " + lower +" and " + upper +": " + allResults.getInt(0));
	}

}
