package comp3100GroupProject;

import java.util.ArrayList;



public class Servers {
	public String type;
	public int memory;
	public int limit;
	public double rate;
	public int disk; 
	public int coreCount;
	public int bootupTime;
	

	public Servers(String type, int memory, int limit, double rate, int disk, int coreCount, int bootupTime) {

		this.type = type;
		this.memory = memory;
		this.limit = limit;
		this.rate = rate;
		this.disk = disk;
		this.coreCount = coreCount;
		this.bootupTime = bootupTime;
	}
	


}
