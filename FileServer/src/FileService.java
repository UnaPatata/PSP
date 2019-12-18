package src;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class FileService extends Thread{
	Socket s;
	int cliente;
	Scanner in;
	PrintWriter out;
	File ficheroEnviar;
	FileInputStream fis;
	OutputStream os;
	BufferedInputStream bis;
	
	public FileService(Socket s, int cliente) {
		this.s = s;
		this.cliente = cliente;
//		this.ficheroEnviar = new File("/home/dam18-20/eclipse-workspace/PSP/FileServer/file2send.txt");
		this.ficheroEnviar = new File("/home/dam18-20/eclipse-workspace/PSP/FileServer/megumin_wallpaper.jpg");
		this.start();
	}
	
	public void run() {
		System.out.println("El cliente " + cliente + " se ha conectado");
		try {
			byte[] sDatos = new byte[(int) ficheroEnviar.length()];
			fis = new FileInputStream(ficheroEnviar);
			bis = new BufferedInputStream(fis);
			bis.read(sDatos);
			os = s.getOutputStream();
			System.out.println("Enviando archivo...");
			os.write(sDatos);
			os.flush();
			os.close();
			s.close();
			FileServer.conectados--;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
