package comp3100GroupProject;

public class Jobs {
	
	//JOB Format -> JOBN 24 0 1566 1 200 1200 
	//Type JOBN     
	//ID = 24 
	//SUBMIT = 0 
	//EST = 1566
	//Core = 1
	//Memory 200
	//disk 1200 
	public String type;
	public int submitTime;
	public int id;
	public int estRunTime; 
	public int cores;
	public int memory;
	public int disk;
	
	
	public Jobs(int submitTime, int id, int estRunTime, int cores, int memory, int disk) {
		this.submitTime = submitTime;
		this.id = id;
		this.estRunTime = estRunTime;
		this.cores = cores;
		this.memory = memory;
		this.disk = disk;
	}

	
	public Jobs(String jobs) {
		String[] msg = jobs.split(" ");
		this.type = msg[0];
		this.submitTime = Integer.parseInt(msg[1]);
		this.id = Integer.parseInt(msg[2]);
		this.estRunTime = Integer.parseInt(msg[3]);
		this.cores = Integer.parseInt(msg[4]);
		this.memory = Integer.parseInt(msg[5]);
		this.disk = Integer.parseInt(msg[6]);
		
	}

}
