package src;

import java.io.IOException;
import java.net.*;

public class echoServer {

	public static void main(String[] args) throws IOException {
		int serverPort = 10101;
		DatagramSocket s = new DatagramSocket(serverPort);

		byte[] buffer = new byte[1024]; //1KiB

		DatagramPacket in = new DatagramPacket(buffer,buffer.length);
		DatagramPacket out = null;

		byte[] datos = null;

		System.out.println("Servidor funcionando en: " + serverPort);

		while (true) {
			s.receive(in);
			datos = in.getData();
			String sData = new String(datos,0,datos.length);
			System.out.println(in.getAddress().getHostAddress() + ":" + in.getPort() + " -> " + sData);
			borraBuffer(datos);
			sData = "OK : " + sData + "\n";
			out = new DatagramPacket(sData.getBytes(),sData.length(),in.getAddress(),in.getPort());
			s.send(out);
		}
	}//end-main

	private static void borraBuffer(byte[] buffer) {
		if (buffer != null) {
			for (int i = 0 ; i < buffer.length ; i++) {
				buffer[i] = 0;
			}
		}
	}//end-borraBuffer
}//end-class
