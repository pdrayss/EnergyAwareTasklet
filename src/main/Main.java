package main;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import logic.Array;
import logic.Network;
import logic.Primes;

public class Main {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

	public static void main(String[] args) throws Exception {
		
		FileWriter csvWriter;
		
		if(args[0].toLowerCase().equals("primes") && args[1] != null && args[2] != null) {
			
			csvWriter = new FileWriter("logs/primes_" + System.currentTimeMillis() + ".csv");
			
			//call primes
			int iterations = Integer.parseInt(args[1]);
			int bundle = Integer.parseInt(args[2]);
			long start = System.currentTimeMillis();
			String csvReturn = Primes.execute(iterations, bundle);
			long end = System.currentTimeMillis();
			
			//output
			long diff = (end - start ) / 1000;
			
	    	Timestamp startDate = new Timestamp(start);
	    	Timestamp endDate = new Timestamp(end);
			
			//write CSV File with general information	
			csvWriter.append("General Information:" + System.lineSeparator());
			csvWriter.append("start: " + startDate + System.lineSeparator());
			csvWriter.append("end: " + endDate + System.lineSeparator());
			csvWriter.append("iterations: " + iterations + System.lineSeparator());
			csvWriter.append("bundleSize: " + bundle + System.lineSeparator());
			csvWriter.append("duration: " + diff + System.lineSeparator());
			//add information provided by actual execution
			csvWriter.append("Execution information:" + System.lineSeparator());
			csvWriter.append(csvReturn);
	    	csvWriter.flush();
			
	    	System.out.println("***********************************************************");
			System.out.println("Execution of Prime Tasklet took: " + diff + " seconds.");
			System.out.println("Start: " + startDate);
			System.out.println("End: " + endDate);
			System.out.println("***********************************************************");
			System.out.println("Ctrl + c to start new taskelt run...");
		}
		else if(args[0].toLowerCase().equals("array") && args[1] != null) {
			
			csvWriter = new FileWriter("logs/array_" + System.currentTimeMillis() + ".csv");
			
			//call array
			long start = System.currentTimeMillis();
			int iterations = Integer.parseInt(args[1]);
			String csvReturn = Array.execute(iterations);
			long end = System.currentTimeMillis();
			
			//output
			long diff = (end - start ) / 1000;	
			
	    	Timestamp startDate = new Timestamp(start);
	    	Timestamp endDate = new Timestamp(end);
			
			//write CSV File with general information	
			csvWriter.append("General Information:" + System.lineSeparator());
			csvWriter.append("start: " + startDate + System.lineSeparator());
			csvWriter.append("end: " + endDate + System.lineSeparator());
			csvWriter.append("iterations: " + iterations + System.lineSeparator());
			csvWriter.append("duration: " + diff + System.lineSeparator());
			//add information provided by actual execution
			csvWriter.append("Execution information:" + System.lineSeparator());
			csvWriter.append(csvReturn);
	    	csvWriter.flush();
			
	    	System.out.println("***********************************************************");
			System.out.println("Execution of Array Tasklet took: " + diff + " seconds.");
			System.out.println("Start: " + startDate);
			System.out.println("End: " + endDate);
			System.out.println("***********************************************************");
			System.out.println("Ctrl + c to start new taskelt run...");
			
		}
		else if(args[0].toLowerCase().equals("network") && args[1] != null) {
			
			csvWriter = new FileWriter("logs/network_" + System.currentTimeMillis() + ".csv");
			
			//call network
			long start = System.currentTimeMillis();
			int iterations = Integer.parseInt(args[1]);
			String csvReturn = Network.execute(iterations);
			long end = System.currentTimeMillis();
			
			//output
			long diff = (end - start ) / 1000;	
			
	    	Timestamp startDate = new Timestamp(start);
	    	Timestamp endDate = new Timestamp(end);
	    	
			//write CSV File with general information	
			csvWriter.append("General Information:" + System.lineSeparator());
			csvWriter.append("start: " + startDate + System.lineSeparator());
			csvWriter.append("end: " + endDate + System.lineSeparator());
			csvWriter.append("iterations: " + iterations + System.lineSeparator());
			csvWriter.append("duration: " + diff + System.lineSeparator());
			//add information provided by actual execution
			csvWriter.append("Execution information:" + System.lineSeparator());
			csvWriter.append(csvReturn);
	    	csvWriter.flush();
			
	    	System.out.println("***********************************************************");
			System.out.println("Execution of Network Tasklet took: " + diff + " seconds.");
			System.out.println("Start: " + startDate);
			System.out.println("End: " + endDate);
			System.out.println("***********************************************************");
			System.out.println("Ctrl + c to start new taskelt run...");
		}
		else {
			
			csvWriter = new FileWriter("logs/error_" + System.currentTimeMillis() + ".csv");
			
			System.out.println("Input incorrect, try *h* for help");
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread() 
	    { 
	      public void run()
	      { 
	    	System.out.println("Application Terminating ..."); 
	        try {
				csvWriter.flush();
		        csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      } 
	    }); 
		
	}

}
