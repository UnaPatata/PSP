package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankService extends Thread{

	Socket s;
	Banco banco;
	Scanner in;
	PrintWriter out;

	public BankService(Socket s, Banco banco) {
		this.s = s;
		this.banco = banco;
		this.start();
	}

	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			out.print("Bienvenido a su banco online\n" + 
					"----------------------------\n" +
					"> ");
			out.flush();
			doService();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}//end-run	

	private void doService() {
		while(true) {
			if (!in.hasNext()) {
				return;
			}
			String comando = in.next();
			if (comando.equals("quit")) {
				return;
			}
			procesaComando(comando);
		}//end-while
	}//end-doService

	private void procesaComando(String comando) {
		
		boolean errorCmd = true;
		
		if (comando.contains("saldo")) {
			int cuenta = in.nextInt();
			double saldo = banco.saldo(cuenta);
			out.println("Saldo: " + saldo + "\n");
			errorCmd = false;
		}//end-comando saldo
		
		if (comando.contains("dep")) {
			int cuenta = in.nextInt();
			double cantidad = in.nextDouble();
			boolean op = banco.deposito(cuenta,cantidad);
			if (op) {
				out.println("Deposito realizado con éxito, saldo: " + banco.saldo(cuenta));
			} else {
				out.println("ERROR:\n"
						+ "No se ha podido realizar el deposito\n"
						+ "Consulte con el administrador del sistema\n");
			}
			errorCmd = false;
		}//end-comando dep
		
		if (comando.contains("ret")) {
			int cuenta = in.nextInt();
			double cantidad = in.nextDouble();
			boolean op = banco.reintegro(cuenta,cantidad);
			if (op) {
				out.println("Reintegro realizado con éxito, saldo: " + banco.saldo(cuenta));
			} else {
				out.println("ERROR:\n"
						+ "No hay saldo suficiente\n");
			}
			errorCmd = false;
		}//end-comando ret
		
		if (errorCmd) {
			out.print("ERROR:\n"
					+ "Comando desconocido\n");
		}
		out.print("> ");
		out.flush();
	}//end-procesaComando
}
