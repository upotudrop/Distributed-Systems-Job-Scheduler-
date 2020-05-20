package comp3100stage3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Servers {
	private static ArrayList<Servers> servers;
	private static ArrayList<String> serverTypes;
	public String type;
	public int sID; 
	public int sState;
	public int sAvailTime;
	public int sCoreCount;
	public int sMemory;
	public int sDiskSpace;
	static long jobEndTime;
	
	public Servers() {
		
	}

	public Servers(String type, int sID, int sState, int sAvailTime, int sCoreCount, int sMemory, int sDiskSpace) {
		this.type = type;
		this.sID = sID;
		this.sState = sState;
		this.sAvailTime = sAvailTime;
		this.sCoreCount = sCoreCount;
		this.sMemory = sMemory;
		this.sDiskSpace = sDiskSpace;
	}

	public Servers(String servers) { //Splits (RESC ALL) String down into server elements
		String[] msg = servers.split(" ");
		this.type = msg[0];
		this.sID = Integer.parseInt(msg[1]);
		this.sState = Integer.parseInt(msg[2]);
		this.sAvailTime = Integer.parseInt(msg[3]);
		this.sCoreCount = Integer.parseInt(msg[4]);
		this.sMemory = Integer.parseInt(msg[5]);
		this.sDiskSpace = Integer.parseInt(msg[6]);		
		
	}
	
	public static ArrayList<String> addType(String type) {
		String[] msg = type.split(" ");
		type = msg[0];
		serverTypes.add(type);
		
		return serverTypes;
	}
	
	

	public void addServer(Servers s) {
		servers.add(s);
	}

	
	public ArrayList<Servers> getServers() {
		return servers;
	}
	
	

	public String scheduleJob(Jobs j) throws IOException {
		Client.messageServer("SCHD " + Jobs.id + " " + this.type + " " + this.sID + "\n");
		String scheduleResponse = Client.messageFromServer();
//		sCoreCount -= Jobs.getCores(); 
//		sMemory -= Jobs.getMemory();
//		sDiskSpace -= Jobs.getDisk();
//		sAvailTime += Jobs.getEstRunTime();
		
		return scheduleResponse;
	}
	
	

}