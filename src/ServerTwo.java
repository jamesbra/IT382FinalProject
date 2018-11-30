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
		int portNumber = 12281;
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
					DataOutputStream outToClientTwo = new DataOutputStream(serverOneConnectionSocket.getOutputStream());
					BufferedReader inFromClientTwo = new BufferedReader(
							new InputStreamReader(serverOneConnectionSocket.getInputStream()));
					clientMessage = inFromClientTwo.readLine();
					System.out.println(clientMessage);
					// message = inFromClient.readLine();
//					if (message == null) {
//						break;
//					}
//								if (random.nextDouble() < LOSS_RATE)
//								{
//					outToClient.writeBoolean(false);
//
//					System.out.println(message + "  ACTION: not sent");
//
////								}
////								else
////								{
//					// delay = (int) (random.nextDouble() * 2* AVERAGE_DELAY);
//					Thread.sleep(delay);
//					outToClient.writeBoolean(true);
//
//					System.out.println(message + "  ACTION: delayed: " + delay + " ms");
////								}

				}
			} catch (Exception e) {
				throw new Exception();
			}
		}
	}
}
