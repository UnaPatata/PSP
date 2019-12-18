package src;

import java.io.IOException;
import java.net.*;

public class MultiCastClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		int serverPort = 10101;
		String serverIp = "225.0.0.24";
		byte[] bufferIn = new byte[1024];//1KiB
		String sData;
		InetAddress server = InetAddress.getByName(serverIp);
		MulticastSocket s = new MulticastSocket(serverPort);
		s.joinGroup(server);
		DatagramPacket in = new DatagramPacket(bufferIn,bufferIn.length);
		System.out.println("Client running...");
		while (true) {
			s.receive(in);
			sData = new String(in.getData(),0,in.getData().length).trim();
			System.out.println("Client> " + sData);
			Thread.sleep(1000);
		}
	}
}
