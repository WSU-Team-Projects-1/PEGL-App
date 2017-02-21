package application.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import application.GpsLocation;

/**
 * 
 * Substitute for PEGL Proxy during testing.
 *
 */
public class TestClient {

	public static void main(String[] args) throws IOException {
		// 39.781361, -84.067882

		String hostName = "localhost";
		int portNumber = 1605;

		System.out.println("client start");

		try (Socket kkSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));) {

			String fromServer;

			// while connection is alive
			while ((fromServer = in.readLine()) != null) {
				// System.out.println("Server: " + fromServer);

				GpsLocation gpsLocation = new GpsLocation(39.781361, 84.067882);

				out.println(gpsLocation.toString());
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}
