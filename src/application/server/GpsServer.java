package application.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import application.GpsLocation;
import application.MainModel;

/**
 * 
 * Socket server that receives GPS locations as `latitude + ", " + longitude`.
 * 
 * The server accepts only one active client. Multiple clients could be
 * supported by mapping connected IPs to GpsLocations.
 *
 */
public class GpsServer extends Thread {

	private int port = 1605; // TODO configure from settings

	private MainModel model;

	/**
	 * Use default port value.
	 */
	public GpsServer() {
		model = MainModel.getInstance();
	}

	public GpsServer(int port) {
		this();
		this.port = port;
	}

	@Override
	public void run() {
		System.out.println("Server start");

		try (ServerSocket serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

			String inputLine;

			// respond to client to let it know communication is ready
			out.println();

			// While connection is alive
			while ((inputLine = in.readLine()) != null) {
				// System.err.println(inputLine);

				model.setLocation( GpsLocation.parse(inputLine));

				out.println();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Available for server testing. Not application main.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		GpsServer gpsServer = new GpsServer();
		gpsServer.start();

		System.out.println("server testing:");
		while (true) {
			System.out.println(gpsServer.model.getLocation());
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
