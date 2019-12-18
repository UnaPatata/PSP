package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BankServer {
	
	//telnet 127.0.0.1 10101
	
	public static void main(String[] args) {
		final int puerto = 10101;
		try {
			Banco banco = new Banco();
			ServerSocket server = new ServerSocket(puerto);
			System.out.println("Servidor esperando conexiones en " + puerto);
			while (true) {
				Socket s = server.accept();
				System.out.println("Cliente conectado desde " + s.getInetAddress() + ":" + s.getPort());
				BankService service = new BankService(s,banco);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
