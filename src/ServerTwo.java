import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTwo {

	/*
	 * Connect to server as soon as the
	 */
	private static final double LOSS_RATE = 0.25;
	private static final int AVERAGE_DELAY = 150; // milliseconds

	public static void main(String[] args) throws Exception {
		// Initialize common variables
		long delay = 0;
		int portNumber = 12282;
		String clientMessage = "";
		String serverOneMessage = "";

		while (true) {
			try (ServerSocket serverTwoSocket = new ServerSocket(portNumber);
					Socket serverOneConnectionSocket = serverTwoSocket.accept();
					DataOutputStream outToServerOne = new DataOutputStream(serverOneConnectionSocket.getOutputStream());
					BufferedReader inFromServerOne = new BufferedReader(
							new InputStreamReader(serverOneConnectionSocket.getInputStream()));)
			{

				while (true) {
					Socket clientTwoConnectionSocket = serverTwoSocket.accept();
					System.out.println("Received client two connection");
					DataOutputStream outToClientTwo = new DataOutputStream(clientTwoConnectionSocket.getOutputStream());
					BufferedReader inFromClientTwo = new BufferedReader(
							new InputStreamReader(clientTwoConnectionSocket.getInputStream()));
					clientMessage = inFromClientTwo.readLine();
					System.out.println(clientMessage);

				}
			} catch (Exception e) {
				throw new Exception();
			}
		}
	}
}
