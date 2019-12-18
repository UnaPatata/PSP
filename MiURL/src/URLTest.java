package src;

import java.io.*;
import java.net.*;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL miURL = new URL("http://172.16.6.43/cgi-bin/backwards.pl");
			URLConnection connect = miURL.openConnection();
			connect.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connect.getOutputStream());
			String msgToReverse = URLEncoder.encode("Prueba dam18-20","UTF-8");
			
			System.out.println(msgToReverse);
			
			out.write("string=" + msgToReverse);
			out.close();
			
			InputStreamReader in = new InputStreamReader(connect.getInputStream());
			BufferedReader flujoE = new BufferedReader(in);
			String linea;
			
			do {
				linea = flujoE.readLine();
				System.out.println(linea);
			} while (linea != null);
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
