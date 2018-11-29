import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerOne {

	
	/*
	 * Connect to server as soon as the 
	 */
	
	
	public static void main(String[] args) throws IOException {
		final int portNumber = 81;
		System.out.println("Creating server socket on port " + portNumber);
		ServerSocket serverSocket = new ServerSocket(portNumber);
		String host = "otherServer";
		while (true) {
			Socket clientSocket = serverSocket.accept();
			Socket socketToServer = new Socket(host, portNumber);
			
			BufferedReader brFromServer = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
			
			DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("What's you name?");

			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String str = br.readLine();

			pw.println("Hello, " + str);
			pw.close();
			clientSocket.close();

			System.out.println("Just said hello to:" + str);
		}
	}

}
