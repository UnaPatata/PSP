package src;

import java.net.*;

public class FileServer {
	static int conectados;
	
	public static void main(String[] args) throws Exception {
		final int CLIENTES_MAX = 3;
		final int SERVER_PORT = 4020;
		int cliente  = 0;
		
		ServerSocket server = new ServerSocket(SERVER_PORT);
		System.out.println("Esperando conexiones de clientes");
		while(true){
			Socket s = server.accept();
			if (conectados < CLIENTES_MAX) {
				FileService service = new FileService(s,cliente);
				conectados++;
				cliente++;
				System.out.println("Hay " + conectados + " clientes conectados");
			} else {
				s.close();
			}
		}
	}  
}
