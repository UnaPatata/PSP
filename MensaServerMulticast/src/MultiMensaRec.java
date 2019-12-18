package src;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MultiMensaRec extends Thread{

	private String multiNet;
	private int multiPort;
	private InetAddress multiAddress;
	private MulticastSocket s;
	private DatagramPacket entrada;
	private boolean ejecuta = true;
	private byte[] bufferIn = new byte[1024];//1KiB
	private String sData;


	public MultiMensaRec(String MULTICAST_NET, int MULTICAST_PORT) throws UnknownHostException, SocketException {
		this.multiNet = MULTICAST_NET;
		this.multiPort = MULTICAST_PORT;
		this.multiAddress = InetAddress.getByName(MULTICAST_NET);

		try {
			this.s = new MulticastSocket(MULTICAST_PORT);
			s.joinGroup(multiAddress);
			this.entrada = new DatagramPacket(bufferIn,bufferIn.length);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(ejecuta) {
			//El hilo se ejecuta indefinidamente
			try {
				s.receive(entrada);
				sData = new String(entrada.getData(),0,entrada.getLength()).trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		this.s.close();
		this.ejecuta = false;
	}

	public synchronized String receive() throws IOException {
		return sData;
	}
}
