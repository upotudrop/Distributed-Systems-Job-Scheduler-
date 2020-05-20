package comp3100stage3;

import java.util.ArrayList;

public class Jobs {
	
	//JOB Format -> JOBN 24 0 1566 1 200 1200 
	//Type JOBN     
	//ID = 24 
	//SUBMIT = 0 
	//EST = 1566
	//Core = 1
	//Memory 200
	//disk 1200 
	private static ArrayList<Jobs> jobs ;
	public String type;
	public static int submitTime;
	public static int id;
	public static int estRunTime; 
	public static int cores;
	public static int memory;
	public static int disk;
	
	
	public Jobs(int submitTime, int id, int estRunTime, int cores, int memory, int disk) {
		this.submitTime = submitTime;
		this.id = id;
		this.estRunTime = estRunTime;
		this.cores = cores;
		this.memory = memory;
		this.disk = disk;
	}
	
	public Jobs(String jobs) { //Takes jobs as a string in messageFromServer and splits into job elements
		String[] msg = jobs.split(" ");
		this.type = msg[0];
		this.submitTime = Integer.parseInt(msg[1]);
		this.id = Integer.parseInt(msg[2]);
		this.estRunTime = Integer.parseInt(msg[3]);
		this.cores = Integer.parseInt(msg[4]);
		this.memory = Integer.parseInt(msg[5]);
		this.disk = Integer.parseInt(msg[6]);
		
	}
	
	public static void addJobs(Jobs j) {
		jobs.add(j);
	}

	
	public static ArrayList<Jobs> getJobs() {
		return jobs;
	}
	
	
	
	public static int getCores() {
		return cores;
	}


	public void setCores(int cores) {
		Jobs.cores = cores;
	}


	public static int getMemory() {
		return memory;
	}


	public void setMemory(int memory) {
		this.memory = memory;
	}


	public static int getDisk() {
		return disk;
	}


	public void setDisk(int disk) {
		this.disk = disk;
	}
	
	public static int getEstRunTime() {
		return estRunTime;
	}
	
	public static int getJobID() {
		return id; 
	}
	
	public static int getSubmitTime() {
		return submitTime; 
	}




}
