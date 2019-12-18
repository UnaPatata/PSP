package src;

import java.net.*;

public class MensaServerRec {
	static int conectados;
	
	public static void main(String[] args) throws Exception {
		final int CLIENTES_MAX = 3;
		final int SERVER_PORT = 4020;
		final String MULTICAST_NET = "225.0.0.20";
		final int MULTICAST_PORT = 4245;
		int cliente  = 0;
		MultiMensaRec multicast = new MultiMensaRec(MULTICAST_NET,MULTICAST_PORT);
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Esperando conexiones de clientes");
		while(true){
			Socket s = server.accept();
			if (conectados < CLIENTES_MAX) {
//				FileService service = new FileService(s,cliente,multicast);
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
