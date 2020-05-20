package comp3100stage3;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Algorithms {

	static ArrayList<Servers> servers = new ArrayList<>();
	static ArrayList<String> serverTypes = new ArrayList<>();
	static ArrayList<Jobs> jobs = new ArrayList<>();

	static ArrayList<Servers> instantList = new ArrayList<>(); //holds first 5 of each server type
	static ArrayList<Servers> shortList = new ArrayList<>();   //holds 6-19 of each server type 
	static ArrayList<Servers> mediumList = new ArrayList<>();  // holds 11 - 15 of each server type 
	static ArrayList<Servers> longList = new ArrayList<>();    // holds 16 - 20 of each server type 


	final static int instantRunTime = 1; //max runtime 30 (varies)
	final static int shortRunTime = 11; //max runtime 180 (varies)
	final static int mediumRunTime = 301; //max runtime 900 (varies)
	final static int longRunTime = 1801; //max runtime 43200 (varies)
	final static int perminentRunTime = 3601; 
	//servers.size / numofServerTypes 
	// e.g. 80 size / 5 == 
	//loop through each server type counting the amount of servers there are, and for each type split it evenly aka 4 ways

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


	public static Servers test() {
		Servers instant = null;
		int bestFit = Integer.MAX_VALUE;
		int minAvail = Integer.MAX_VALUE;
		for(Servers s : servers) {
			int fitness = s.sCoreCount - Jobs.getCores();
			if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) {
				if(fitness < bestFit || (fitness == bestFit && s.sAvailTime < minAvail)) {
					minAvail = s.sAvailTime;
					bestFit = fitness;
					instant = s;					
				}
			}
		}
		return instant;
	}






	public static Servers scheduleInstant() {
		Servers instantRun = null; 
		//addType(serverTypes);
		int bestFit = Integer.MAX_VALUE;
		for(String types : serverTypes) {
			for(Servers s : servers) {
				if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) {

					if(Jobs.getEstRunTime() >= instantRunTime && Jobs.getEstRunTime() < shortRunTime) {
						int fitness = s.sCoreCount - Jobs.getCores(); 
						if(s.sAvailTime == Jobs.getSubmitTime()) {
							bestFit = fitness;
							instantRun =  s;
						}
						else if(fitness == 0 || fitness < bestFit) {
							if(s.sID >= 0 && s.sID <= 3) {
								bestFit = fitness;
								instantRun = s;
							}
						}
					}
				}
			} 
		}
		if(instantRun != null) {
			return instantRun; 
		} else {
			for(Servers s : servers) {
				if(s.sID >= 0 && s.sID <= 3) {
					instantRun = s;
				}
			}
		}

		return instantRun;

	}

	public static Servers scheduleShort() {
		Servers shortRun = null; 
		addType(serverTypes);
		int bestFit = Integer.MAX_VALUE;
		for(String types : serverTypes) {
			for(Servers s : servers) {
				if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) {
					if(Jobs.getEstRunTime() >= shortRunTime && Jobs.getEstRunTime() < mediumRunTime) {
						int fitness = s.sCoreCount - Jobs.getCores();
						if(s.sAvailTime == Jobs.getSubmitTime()) {
							bestFit = fitness;
							shortRun = s;
						}
						else if(fitness < bestFit || fitness < bestFit) {
							if(s.sID >= 4 && s.sID <= 10) {
								bestFit = fitness;
								shortRun = s;		
							}
						}
					}
				}
			}
		}

		if(shortRun != null) {
			return shortRun; 
		} else {
			for(Servers s : servers) {
				if(s.sID >= 4 && s.sID <= 10) {
					shortRun = s;	
				}
			}
		}

		return shortRun;

	}



	public static Servers scheduleMedium() throws IOException {
		Servers mediumRun = null; 

		int bestFit = Integer.MAX_VALUE; 
		addType(serverTypes);

		for(String types : serverTypes) {
			for(Servers s : servers) {
				if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) {
					if(Jobs.getEstRunTime() >= mediumRunTime && Jobs.getEstRunTime() < longRunTime) {
						int fitness = s.sCoreCount - Jobs.getCores(); 
						if(s.sAvailTime == Jobs.getSubmitTime()) {
							bestFit = fitness;
							mediumRun = s;
						}
						else if(fitness == 0 || fitness < bestFit) {
							if(s.sID >= 11 && s.sID <= 15) {
								bestFit = fitness;
								mediumRun = s;
							}
						}
					}

				}
			}
		} 
		if(mediumRun != null) {
			return mediumRun;
		} else {
			for(Servers s : servers) {
				if(s.sID >= 11 && s.sID <= 15) {
					mediumRun = s;
				}
			}
		}

		return mediumRun;

	}

	public static Servers scheduleLong() {
		Servers longRun = null; 
		//addType(serverTypes); 
		addType(serverTypes);
		int bestFit = Integer.MAX_VALUE;
		for(String types : serverTypes) {
			for(Servers s : servers) {
				if(Jobs.getEstRunTime() >= longRunTime) {
					if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) {
						int fitness = s.sCoreCount - Jobs.getCores();
						if(s.sAvailTime == Jobs.getSubmitTime()) {
							bestFit = fitness;
							longRun = s;
						}
						else if(fitness == 0 || fitness < bestFit) {
							if(s.sID >= 16 && s.sID <= 20) {

								bestFit = fitness;
								longRun = s;
							}
						}
					}
				}
			}
		}

		if(longRun != null) {
			return longRun; 
		} else {
			int count = 0; 
			int minTime = Integer.MIN_VALUE;
			int minAvail = Integer.MIN_VALUE;
			for(Servers s : servers) {
				if(s.sID >= 0 && s.sID <= 20) {
					int badFitness = s.sCoreCount - Jobs.getCores();
					if(badFitness > minTime || badFitness == minTime && s.sAvailTime > minAvail) {
						minTime = badFitness;
						minAvail = s.sAvailTime;
						longRun = s;

					}
				}
			}
		}

		return longRun;

	}





	public static Servers algorithm() throws IOException {

		//config_Simple 2, 4, 5, 7, 8 do not work. 

		Servers bestServer = null;
		if(Jobs.getEstRunTime() >= instantRunTime && Jobs.getEstRunTime() < shortRunTime) {
			bestServer = scheduleInstant();
		} else if(Jobs.getEstRunTime() >= shortRunTime && Jobs.getEstRunTime() < mediumRunTime) {
			bestServer = scheduleShort();
		} else if(Jobs.getEstRunTime() >= mediumRunTime && Jobs.getEstRunTime() < longRunTime) {
			bestServer = scheduleMedium();
		} else {
			bestServer = scheduleLong();
		}
		return bestServer;
	}



	//	public static Servers minimalTime() throws IOException {
	//
	//		//HashMap of servers & Jobs 
	//
	//		addType(serverTypes);
	//		Servers bestServer = null;
	//		
	//		//sort by server state, 
	//		//always keep some servers pre booted, 
	//		//only sort based on when server is available? 
	//		int maxTime = Integer.MAX_VALUE;
	//		
	//		for(Servers s : servers) {			
	//			if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory() && s.sDiskSpace >= Jobs.getDisk()) {
	//				int fitness = s.sCoreCount - Jobs.getCores();
	//				int time = s.sAvailTime - Jobs.getSubmitTime();
	//				
	//				if(fitness == 0 && time == 0 || fitness < maxTime) {
	//					bestServer = s;
	//				}
	//				
	//				
	//			}
	//			
	//		}
	//	
	//		return bestServer;	
	//		
	//		
	//	}




	public static Servers usability() throws IOException {

		Servers bestServer = null; 
		addType(serverTypes);

		for(int i=0; i < instantList.size(); i++) {
			System.out.println(instantList.get(i).sID);
		}
		int bestTime = Integer.MAX_VALUE;
		int fitnessLevel = Integer.MAX_VALUE;


		//reserves a (small) portion of the servers to run exclusively short jobs.
		//if job is small in est time schedule to a specific server based on resource requirements
		//i.e if job is small with 1 core, make sure it goes server tiny #19 -> #15 
		//if job is large with 1 core, make sure it goes server tiny #1 -> 10 
		//more evaluations required to figure it out 
		for(Servers s : servers) {
			if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory() && s.sDiskSpace >= Jobs.getDisk()) {

				int time = s.sAvailTime - Jobs.getSubmitTime();

				int fitness = s.sCoreCount - Jobs.getCores();


				if(time == 0 || (fitness < fitnessLevel)) {
					fitness = fitnessLevel;
					//bestTime = s.sAvailTime;
					bestServer = s; 
				} 
			}

		}
		if(bestServer != null) {
			return bestServer;
		} else {

			for(Servers s : servers) {
				bestServer = s;
			}
		}

		return bestServer;
	}




	public static Servers bestFit() {

		//loop through servers, assigning jobs to servers where appropriate

		//If multiple servers can run jobs always go with the lowest fitnessvalue being when the fitness value is closest to 0 
		//as an example, 

		//You have a job that requires 3 cores,
		//As medium servers have 4 cores, large have 8 and so on, it will loop through and check what the last server in the array that can run the job
		//so currently if a job requires 3 cores on config_simple1, it would be placed on a large server, which is obviously incorrect.

		//To fix this we have to make sure that jobs are always assigned based on fitness level being closest to 0


		//Create an arrayList of serverTypes 
		//Fill the types as needed, 
		//loop through from first to last server for a particular type and base job info on those 


		addType(serverTypes);
		Servers bestServer = null;
		int bestFit = Integer.MAX_VALUE;
		int minAvail = Integer.MAX_VALUE;
		int fitnessValue = 0; 

		for(String types : serverTypes) { //For all current Server types
			for(Servers s : servers) { //Servers inside those server types 
				if(s.sCoreCount >= Jobs.getCores() && s.sMemory >= Jobs.getMemory()) { //If servers can run jobs

					fitnessValue = s.sCoreCount - Jobs.getCores(); //checks fitness level, 0 is best as well as closest to 0
					//fitness value == 0 
					if(fitnessValue < bestFit || (fitnessValue == bestFit && s.sAvailTime < minAvail)) { 
						//If algo finds a better fitnessValue (closer to 0 || 0) or finds the same fitnessvalue 
						//that can run the job faster than otherwise
						bestFit = fitnessValue; //best fit == 0 
						minAvail = s.sAvailTime;
						bestServer = s;

					}
				}
			}
		}


		if(bestServer != null) {
			return bestServer; 
		}

		return firstAvailable();
	}


	public static Servers firstAvailable() { //Finds the first available server based on initial resources
		Servers firstAvailable = null; 
		for(String types : serverTypes) {
			for(Servers s : servers) {
				if(s.sCoreCount - Jobs.getCores() < 0 && s.sAvailTime == -1) {
					return s;
				}
			}
		}
		return firstAvailable;
	}




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

	public static ArrayList<String> addType(ArrayList<String> serverTypes) {
		HashSet<String> set = new HashSet<String>();
		for(int i=0; i < servers.size(); i++) {
			set.add(servers.get(i).type);
		}



		serverTypes.addAll(set);


		return serverTypes;
	}

}

