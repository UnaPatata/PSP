package src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

	public static void main(String[] args) {
		try {
			int puerto = 4020;
			ServerSocket servidor = new ServerSocket(puerto);
			System.out.println("Esperando clientes...");
			while(true) {
				Socket cliente = servidor.accept();
				ServicioCliente servicio = new ServicioCliente(cliente);
				servicio.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
