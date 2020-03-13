package main;

import logic.Array;
import logic.Network;
import logic.Primes;

public class Main {

	public static void main(String[] args) {
		// Consumes input from command line and handles it accordingly
		if(args.length == 0) {
			System.out.println("Enter *h* for help");
		}
		else if(args[0].toLowerCase().equals("h")) {
			System.out.println("Please enter the commands in the following order:");
			System.out.println("Type of Tasklet, IterationCount, BundleSize [only for Primes]");
			System.out.println("Available Tasklets are: Primes, Array, Network");
			System.out.println("e.g. Primes 16 10");
		}
		else if(args[0].toLowerCase().equals("primes") && args[1] != null && args[2] != null) {
			//call primes
			int iterations = Integer.parseInt(args[1]);
			int bundle = Integer.parseInt(args[2]);
			Primes.execute(iterations, bundle);
		}
		else if(args[0].toLowerCase().equals("array") && args[1] != null) {
			//call array
			int iterations = Integer.parseInt(args[1]);
			Array.execute(iterations);
		}
		else if(args[0].toLowerCase().equals("network") && args[1] != null) {
			//call network
			int iterations = Integer.parseInt(args[1]);
			Network.execute(iterations);
		}
		else {
			System.out.println("Input incorrect, try *h* for help");
		}
		
	}

}
