package comp3100GroupProject;

import java.util.ArrayList;


public class Servers {
	public String type;
	public int memory;
	public int limit;
	public double hourlyRate;
	public int disk; 
	public int coreCount;
	public int bootupTime;
	
	private static ArrayList<Servers> servers;
	private int randomSeed;

	public Servers(String type, int memory, int limit, double hourlyRate, int disk, int coreCount, int bootupTime) {

		this.type = type;
		this.memory = memory;
		this.limit = limit;
		this.hourlyRate = hourlyRate;
		this.disk = disk;
		this.coreCount = coreCount;
		this.bootupTime = bootupTime;
	}
	

	public static void addServer(Servers s) {
		servers.add(s);
	}

	
	public static ArrayList<Servers> getServers() {
		return servers;
	}



	public Servers(int randomSeed) {
		this.randomSeed = randomSeed;
		servers = new ArrayList<Servers>();
	}
}
