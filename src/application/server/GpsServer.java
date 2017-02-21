package application.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import application.GpsLocation;

/**
 * 
 * Socket server that receives GPS locations as `latitude + ", " + longitude`.
 *
 */
public class GpsServer {

	private int port = 1605; // TODO configure from settings

	GpsLocation loc;

	/**
	 * Use default port value.
	 */
	public GpsServer() {
	}

	public GpsServer(int port) {
		this.port = port;
	}

	public void run() {
		System.out.println("Server start");

		try (ServerSocket serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

			String inputLine;

			// respond to client
			out.println();

			// While connection is alive
			while ((inputLine = in.readLine()) != null) {
				// System.err.println(inputLine);

				loc = new GpsLocation(inputLine);

				System.out.println(loc);

				TimeUnit.SECONDS.sleep(1);

				out.println();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Available for server testing. Not application main.
	 */
	public static void main(String[] args) {

		GpsServer gpsServer = new GpsServer();
		gpsServer.run();
	}

}
