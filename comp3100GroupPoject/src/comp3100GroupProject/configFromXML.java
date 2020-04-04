package comp3100GroupProject;

import java.util.ArrayList;

public class configFromXML {
	private static ArrayList<Servers> servers;
	private int randomSeed;
	
	public static void addServer(Servers s) {
		servers.add(s);
	}

	
	public static ArrayList<Servers> getServers() {
		return servers;
	}



	public configFromXML(int randomSeed) {
		this.randomSeed = randomSeed;
		servers = new ArrayList<>();
	}

	

}
