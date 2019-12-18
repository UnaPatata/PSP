package src;

import java.net.*;

public class MensaServer {
	static int conectados;
	
	public static void main(String[] args) throws Exception {
		final int CLIENTES_MAX = 3;
		final int SERVER_PORT = 4020;
//		final String MULTICAST_NET = "225.0.0.24";
//		final int MULTICAST_PORT = 4245;
		final String MULTICAST_NET = "225.0.0.1";
		final int MULTICAST_PORT = 3333;
		int cliente  = 0;
		MultiMensa multicast = new MultiMensa(MULTICAST_NET,MULTICAST_PORT);
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Esperando conexiones de clientes");
		while(true){
			Socket s = server.accept();
			if (conectados < CLIENTES_MAX) {
				MensaService service = new MensaService(s,cliente,multicast);
				conectados++;
				cliente++;
				System.out.println("Hay " + conectados + " clientes conectados");
			} else {
				s.close();
				multicast.close();
			}
		}
	}  
}
