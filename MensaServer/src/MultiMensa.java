package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MultiMensa extends Thread{
	
	private String multiNet;
	private int multiPort;
	private InetAddress multiAddress;
	private DatagramSocket s;
	private DatagramPacket enviar;
	private boolean ejecuta = true;
	
	public MultiMensa(String MULTICAST_NET, int MULTICAST_PORT) throws UnknownHostException, SocketException {
		this.multiNet = MULTICAST_NET;
		this.multiPort = MULTICAST_PORT;
		this.multiAddress = InetAddress.getByName(MULTICAST_NET);
		this.s = new DatagramSocket();
		this.start();
	}
	
	public void run() {
		
		while(ejecuta) {
			//El hilo se ejecuta indefinidamente
		}
	}

	public void close() {
		this.s.close();
		this.ejecuta = false;
	}

	public synchronized void send(String mensa) throws IOException {
		enviar = new DatagramPacket(mensa.getBytes(),mensa.getBytes().length,multiAddress,multiPort);
		s.send(enviar);
	}
}
