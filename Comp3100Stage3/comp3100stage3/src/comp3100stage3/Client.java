package comp3100stage3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @desc this is the main class for the COMP3100 assignment client code.
 * @author Michael Parker, 45663968
 * @required The other files in client directory, i.e. Jobs.java,Servers.java, Algorithms.java
 */

public class Client {

	private static Socket socket;
	private static InputStream in = null; 
	private static OutputStream out = null;
	static String scheduleResponse;


	public static void main(String[] args)throws IOException{

		System.out.println(args.length);

		socket = new Socket("127.0.0.1", 50000); //Server address

		String scheduleMessage = "";

		Algorithms algoList = new Algorithms(); 

		messageServer("HELO\n");
		messageFromServer();

		messageServer("AUTH root\n"); //root to pass all tests given in ./tests1 
		messageFromServer();


		while(true) {
			messageServer("REDY\n"); //While there is a job to get 
			String getJobs = messageFromServer();

			if(getJobs.equals("NONE")) { //if no jobs left break; 
				messageServer("QUIT\n"); //Quit and show total statistics
				messageFromServer();
				break;
			}
			
			Jobs j = new Jobs(getJobs); //Split getJobs string into new job object
			
			/**
			 * @desc every new job the servers from the previos job will be deleted.
			 */
			
			algoList.removeServers(algoList);
			messageServer("RESC Capable " + Jobs.getCores() + " " + Jobs.getMemory() + " " + Jobs.getDisk() + "\n");
			//Replies with all the servers initalling capable of running a job with the following required resources
			messageFromServer();  

			while(true) {

				messageServer("OK\n");
				String collectServers  = messageFromServer();

				if(collectServers.equals(".")) { //If no more servers, break back into main loop 
					break;
				}

				algoList.addServer(collectServers);

			}
			
			/**
			 * @desc under the case "cea" runs costEfficient algorithm 
			 * @default If nothing is selected the client will run the allToLargest Algorithm 
			 */

			switch(args[1]) {
			case "cea": Algorithms.costEfficient().scheduleJob(j);
			break;
			
			default: Algorithms.getLargestServer().scheduleJob(j);
			break;
			}
			
			Algorithms.costEfficient().scheduleJob(j);
			
		}


	}




	public static void messageServer(String message) throws IOException {

		OutputStreamWriter toServer = new OutputStreamWriter(out = socket.getOutputStream());
		BufferedWriter wts = new BufferedWriter(toServer);
		wts.write(message);
		wts.flush();

	}

	public static String messageFromServer() throws IOException {

		InputStreamReader inputWriterFromServer = new InputStreamReader(in = socket.getInputStream(), "UTF-8");
		BufferedReader readFromServer = new BufferedReader(inputWriterFromServer);
		String message = readFromServer.readLine();

		return message;
	}

}
