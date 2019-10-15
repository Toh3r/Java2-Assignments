package assignment3_ClientServer;

//Import packages
import java.io.*;
import java.net.*;
import java.util.*;

//Create server class
public class Server implements Runnable {
	
	//Create socket, in/out Streams
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	//Create synchronized list
	private static List<String> tokens = Collections.synchronizedList(new ArrayList<>());
	
	public Server(Socket socket) {
		this.socket = socket;
	}
	
	public Server() {}

	public static void main(String[] args) throws Exception {
		//Create new server socket
		ServerSocket server = new ServerSocket(1337);
		//Server serverInstance = new Server();
		System.out.println("Server listening for client...");
		
		while (true) {
			//Accept client
			Socket soc = server.accept();
			System.out.println("Client connected to server");
			//Create new thread for each client
			new Thread(new Server(soc)).start();
		}
	}

	//Create run method
	@Override
	public void run() {
		//Create try catch block to catch IO exceptions
		try {
			try {
				//Create streams to receive/send information to/from client
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				//Call request method
				getRequest();
			} finally { 
				//close socket
				socket.close();
			}
		}catch (IOException e) {
				System.err.println(e);
		}
	}
	//Create method to receive request
	public void getRequest() throws IOException{
		//Return if no input
		while(true) {
			if(!in.hasNext())
				return;
		//Create variable for request type and token	
		String req = in.next();
		
		System.out.println("Request received: " + req);
		//call method deal with request
		handleRequest(req);
		}
	}
	//Create method to handle request
	public void handleRequest(String req) throws IOException {
		//create token variable
		String tok = in.next();
		//Synchronize tokens so it can only be accessed by one thread at a time
		synchronized(tokens) {
			//If request is to submit token
			if(req.contains("Submit")) {
				//add token to array if array is less than 10 and doesn't already contain token
				if(tokens.size() < 10 && !tokens.contains(tok)) {
					tokens.add(tok);
					System.out.println("Token added");
					out.println("OK");
				}
				//else just print error 
				else {
					System.out.println("Error");
					out.println("Error");
				}
			}
			//If request is to remove token
			else if(req.contains("Remove")) {
				//If token in array, remove token
				if(tokens.contains(tok)) {
					tokens.remove(tok);
					System.out.println("Removed token");
					out.println("OK");
				}
				//else print error
				else {
					System.out.println("Error");
					out.println("Error");
				}
			}
			//If quit request
			else if(req.contains("Quit")) {
				
				//close connection
				socket.close();
			}
			//Sort tokens array in alphabetical order, print to console
			tokens.sort(String::compareToIgnoreCase);
	        System.out.println(tokens);
	        out.flush();
		}
	}	
}
