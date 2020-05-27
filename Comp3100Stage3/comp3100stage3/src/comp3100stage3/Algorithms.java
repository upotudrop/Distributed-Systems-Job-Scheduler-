package comp3100stage3;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;


public class Algorithms {

	/**
	 * @servers arrayList holding all the current servers that can run the current job.
	 * @serverTypes arrayList containing all the current server types for the current job 
	 */
	static ArrayList<Servers> servers = new ArrayList<>();
	static ArrayList<String> serverTypes = new ArrayList<>();


	public Algorithms() {

	}


	public void addServer(String s) {
		servers.add(new Servers(s));
	}

	public Servers getServer(int n) {
		return servers.get(n);
	}

	public void removeServers(Algorithms s) {
		//new arrayList
		servers.removeAll(servers);
	}

	public static Servers firstFit() {
		Servers firstServer = null;


		return firstServer;
	}

	//Counts the amount of servers per server type 
	public static int countServers(ArrayList<String> serverTypes) {
		int count = 0; 
		for(int i=1; i < servers.size(); i++) {
			if(servers.get(i).type.equals(servers.get(i-1).type)) {
				count++; 
			}
		}
		return count; 
	}

	
	/**
	 * @addType(serverTypes) calls a method to add all the current server types for the current job
	 * @curFit high number to always find the best fitness level for the job
	 * @fitness defined as cur server corecount - cur job corecount 
	 * @backupAlgorithm algorthim should not be triggered but in the even that it does, it's there to prevent any errors
	 * @costEfficient Runs the first server that could run the job at some point in time. 
	 */

	public static Servers costEfficient() {
		Servers best = null;
		addType(serverTypes);
		int curFit = Integer.MAX_VALUE;

		for(String types : serverTypes) {
			for(Servers s : servers) {
				
					int fitness = s.sCoreCount - Jobs.getCores(); 
					if(fitness < curFit) {
						curFit = fitness;
						return s; 
					
				}
			}
		}
		if(curFit == Integer.MAX_VALUE) {
			return best;
		}
		else {
			int minTime = Integer.MIN_VALUE;
			int minAvail = Integer.MIN_VALUE;

			for(Servers s : servers) {
				if(s.sID >= 0 && s.sID <= servers.size()) {
					int badFitness = s.sCoreCount - Jobs.getCores();
					if(badFitness > minTime) {
						minTime = badFitness;
						minAvail = s.sAvailTime;
						best = s;

					}
				}
			}
		}
		return best;
		
	}

	/**
	 *@desc returns the server with the largest amount of cores
	 */
	
	public static Servers getLargestServer() {

		int curCoreCount = 0; 
		Servers largestServer = null;
		for(int i=0; i < servers.size(); i++) {
			
			if(servers.get(i).sCoreCount > curCoreCount) {
				curCoreCount = servers.get(i).sCoreCount;
				largestServer = servers.get(i);
				
			}
			
		}
		return largestServer;

	}
	
	/**
	 *@set holds all the current server types in a hashset to avoid duplicates
	 *@desc will then add all the items in the set to the arrayList serverTypes
	 */
	
	public static ArrayList<String> addType(ArrayList<String> serverTypes) {
		HashSet<String> set = new HashSet<String>();
		for(int i=0; i < servers.size(); i++) {
			set.add(servers.get(i).type);
		}

		serverTypes.addAll(set);
		return serverTypes;
	}



}

