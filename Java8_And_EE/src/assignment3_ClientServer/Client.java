package assignment3_ClientServer;

//import java packages
import java.util.*;
import java.io.*;
import java.net.*;

//Create client class
public class Client {
	
	// Create main method, throw exception if Socket not valid
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		//Create socket connecting to local ip, port 1337
		Socket cs = new Socket("localhost", 1337);
		
		//Create in/out streams to pass requests/response between client and server
		InputStream ins= cs.getInputStream();
		OutputStream outs= cs.getOutputStream();
		Scanner in = new Scanner(ins);
		PrintWriter out = new PrintWriter(outs);
		Scanner userIn = new Scanner(System.in);
		
		//Created as condition for while loop
		String answer = "";
		
		//Loop until user enters Quit
		while(answer != "Quit") {
		
			//Get input from user
			System.out.println("Enter request and token: ");
			String req = userIn.nextLine();
			
			//Added string to stop errors
			if(req.contains("Quit")){
				req = req +" lol \n";
			}
			else {
				req = req +" \n";
			}
			
			//Send request to server
			out.print(req);
			//Empty buffer
			out.flush();
			
			//Exit while loop
			if(req.contains("Quit")) {
				answer = "Quit";	
			}
			
			else if(req.contains("Submit")) {
			//Get response from server
			String res = in.nextLine();
			//Print response to console
			System.out.println("Response: " + res);
			}
			
			else if(req.contains("Remove")) {
				//Get response from server
				String res = in.nextLine();
				//Print response to console
				System.out.println("Response: " + res);}
			
		}
		//Close Scanner and Socket
		in.close();
		cs.close();
		userIn.close();			
	}
}
