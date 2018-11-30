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
	private static final double LOSS_RATE = 0.25;
	private static final int AVERAGE_DELAY = 150; // milliseconds

	public static void main(String[] args) throws Exception {
		// Initialize common variables
		long delay = 0;
		int portNumber = 12281;
		String serverTwoMessage = "";
		String clientOneMessage = "";
		String host = "oak.ad.ilstu.edu";
		try (Socket serverTwoConnectionSocket = new Socket(host, 12282);
				DataOutputStream outToServerTwo = new DataOutputStream(serverTwoConnectionSocket.getOutputStream());
				BufferedReader inFromServerTwo = new BufferedReader(
						new InputStreamReader(serverTwoConnectionSocket.getInputStream()));) {
			System.out.println("Connected to server two");
			while (true) {
				System.out.println("Waiting for client connection");
				try (ServerSocket serverOneSocket = new ServerSocket(portNumber);
						Socket connectionSocket = serverOneSocket.accept();
						DataOutputStream outToClientOne = new DataOutputStream(connectionSocket.getOutputStream());
						BufferedReader inFromClientOne = new BufferedReader(
								new InputStreamReader(connectionSocket.getInputStream()));) {

					while (true) {

						clientOneMessage = inFromClientOne.readLine();
						outToServerTwo.writeChars(clientOneMessage);
						serverTwoMessage = inFromServerTwo.readLine();
						outToClientOne.writeChars(serverTwoMessage);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception();
				}
			}
		}
	}
}
