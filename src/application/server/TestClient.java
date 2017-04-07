package application.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import application.GpsLocation;

/**
 * 
 * Substitute for PEGL Proxy during testing.
 *
 */
public class TestClient extends Thread {

	private double gpsLat = 39.781361;
	private double gpsLng = -84.067882;
	@Override
	public void run() {
		// 39.781361, -84.067882
		

		String hostName = "localhost";
		int portNumber = 1605;

		System.out.println("client start");

		try (Socket clientSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

			String fromServer;

			// while connection is alive
			while ((fromServer = in.readLine()) != null) {
				// System.out.println("Server: " + fromServer);

				GpsLocation gpsLocation = new GpsLocation(gpsLat + (Math.random() / 1000), gpsLng   + (Math.random() / 1000));

				out.println(gpsLocation.toString());

				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		new TestClient().start();
	}
}
