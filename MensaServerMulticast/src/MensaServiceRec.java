package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MensaServiceRec extends Thread{
	Socket s;
	int cliente;
	Scanner in;
	PrintWriter out;
	MultiMensaRec multicast;
	
	public MensaServiceRec(Socket s, int cliente, MultiMensaRec multicast) {
		this.s = s;
		this.cliente = cliente;
		this.multicast = multicast;
		this.start();
	}
	
	public void run() {
		System.out.println("El cliente " + cliente + " se ha conectado");
		try {
			out = new PrintWriter(s.getOutputStream());
			in = new Scanner(s.getInputStream());
			out.println("Bienvenido cliente " + cliente);
			out.flush();
			doService();
			s.close();
			MensaServer.conectados--;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doService() throws IOException {
		while (true) {
			String comando = in.nextLine();
			if (comando.contains("quit")) {
				break;
			}
			procesaComando(comando);
		}
	}

	private void procesaComando(String comando) throws IOException {
		if (comando.contains("rec")) {
			String mensa = multicast.receive();
			out.println(mensa);
			out.flush();
		}
	}
}
