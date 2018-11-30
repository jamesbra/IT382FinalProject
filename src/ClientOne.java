import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientOne {
	public static void main(String[] args) throws IOException {
		String hostName = "oak.ad.ilstu.edu";
		int portNumber = 12281;


		Socket clientSocket = null;
		try {
			// Initialize socket - checking hostname
			clientSocket = new Socket(hostName, portNumber);
		} catch (UnknownHostException e) {
			System.out.println("ERR - arg 1");
			System.exit(1);
		} catch (ConnectException e) {
			System.out.println("ERR - arg 2");
			System.exit(1);
		}
		// Create output/input streams
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		System.out.println("Trying to write to server");
		outToServer.writeChars("Fuck");
		System.out.println("Wrote to server");
	}
}
