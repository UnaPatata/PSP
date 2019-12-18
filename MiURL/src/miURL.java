package src;

import java.io.*;
import java.net.URL;

public class miURL {
	public static void main(String[] args) {
		try {
			URL miURL = new URL("https://www.iescierva.net:443");
			System.out.println("Protocolo " + miURL.getProtocol()
					+ "\nPuerto " + miURL.getPort());
			InputStreamReader sr = new InputStreamReader(miURL.openStream());
			BufferedReader flujoE = new BufferedReader(sr);
			String linea;
			do {
				linea = flujoE.readLine();
				System.out.println(linea);
			} while (linea != null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
