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
import application.WarningIssuer;

/**
 * 
 * Socket server that receives GPS locations as `latitude + ", " + longitude`.
 * 
 * The server accepts only one active client. Multiple clients could be
 * supported by mapping connected IPs to GpsLocations.
 *
 */
public class GpsServer extends Thread {

	private int port = 1605;

	private final MainModel model;

	private final String conectionWarning = "Connection to PEGL Proxy lost. Please start it again.";

	public GpsServer(int port) {
		model = MainModel.getInstance();
		this.port = port;
	}


	@Override
	public void run() {
		while (true) {
			System.out.println("Server start");

			try (ServerSocket serverSocket = new ServerSocket(port);
					Socket clientSocket = serverSocket.accept();
					PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

				String inputLine;
				System.out.println("connected");
				model.setShouldIssueComunicationWarning(true);

				// respond to client to let it know communication is ready
				out.println();

				// While connection is alive
				while ((inputLine = in.readLine()) != null) {

					model.setLocation(GpsLocation.parse(inputLine));

					out.println();

				}

			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Server dissconect");

				if (model.shouldIssueComunicationWarning()) {
					model.setShouldIssueComunicationWarning(false);
					new WarningIssuer(conectionWarning);
				}
			}
		}
	}

	/**
	 * Available for server testing. Not application main.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		GpsServer gpsServer = new GpsServer(1605);
		gpsServer.start();

		System.out.println("server testing:");
		while (true) {
			GpsLocation l = gpsServer.model.getLocation();
			if (l != null)
				System.out.println(l);
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
