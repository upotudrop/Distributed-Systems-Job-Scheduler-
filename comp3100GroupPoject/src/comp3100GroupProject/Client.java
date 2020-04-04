package comp3100GroupProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Client {
	private static InputStream in = null; 
	private static OutputStream out = null;
	private static Socket socket = null;	
	

	public static void main(String[] args) throws IOException, VerifyError, ParserConfigurationException, SAXException{
		
		socket = new Socket("localhost", 50000);
		
		File inputFile = new File("/root/Downloads/ds-sim/system.xml");
        
		messageServer("HELO");
		messageFromServer();
		
		messageServer("AUTH COMP3100"); //gets system.xml 
		messageFromServer();
		
		messageServer("REDY"); //Gets job information 
		String jobs = messageFromServer();
		System.out.println(jobs + "\n");
		
		
//		ArrayList<Servers> servers = new ArrayList<Servers>();
//		servers.addAll(Servers.getServers()); //get all servers from system.xml 
		
		long currentTime = System.currentTimeMillis(); //Start time 
		
	}
	public static void messageServer(String message) throws IOException {

		OutputStreamWriter toServer = new OutputStreamWriter(out = socket.getOutputStream());
		BufferedWriter wts = new BufferedWriter(toServer);
		wts.write(message + "\n");
		wts.flush();
		
	}
	
	public static String messageFromServer() throws IOException {
	
		InputStreamReader inputWriterFromServer = new InputStreamReader(in = socket.getInputStream(), "UTF-8");
		BufferedReader readFromServer = new BufferedReader(inputWriterFromServer);
		String message = null; 
		if(readFromServer.ready()) { //checks to see if buffer is available 
			message = readFromServer.readLine();
		}
		
		return message;
			
	}
		//Finds largest server based on its core count 
	public static Servers getLargestServer() {

		int curCoreCount = 0; 
		Servers largestServer = null;
		for(int i=0; i < Servers.getServers().size(); i++) {

			if(Servers.getServers().get(i).coreCount > curCoreCount) {
				curCoreCount = Servers.getServers().get(i).coreCount;
				largestServer = Servers.getServers().get(i);
			}
		}
		return largestServer;

	}

}
