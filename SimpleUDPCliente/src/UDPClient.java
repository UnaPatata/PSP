package src;

import java.io.*;
import java.net.*;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		int serverPort = 10101;
		String serverIP = "127.0.0.1";
		String sData;
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket s = new DatagramSocket();
		InetAddress server = InetAddress.getByName(serverIP);
		DatagramPacket out = null;
		DatagramPacket in = null;
		byte[] bufferOut = null;
		byte[] bufferIn = new byte[1024];//1KiB
		while (true) {
			System.out.println("msg > ");
			sData = cin.readLine();
			if (sData.contains("quit")) {
				System.out.println("Parando la conexión...");
				break;
			}
			bufferOut = sData.getBytes();
			out = new DatagramPacket(bufferOut, bufferOut.length,server,serverPort);
			s.send(out);
			in = new DatagramPacket(bufferIn,bufferIn.length);
			s.receive(in);
			String rData = new String(in.getData(),0,in.getData().length).trim();
			System.out.println(rData);
		}//end-whileTrue
		s.close();
	}
	
	private static void borraBuffer(byte[] buffer) {
		if (buffer != null) {
			for (int i = 0 ; i < buffer.length ; i++) {
				buffer[i] = 0;
			}
		}
	}//end-borraBuffer
}//end-main
