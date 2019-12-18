package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServicioCliente extends Thread{
	
	Socket s;
	
	public ServicioCliente(Socket cliente) {
		this.s = cliente;
	}
	
	public void run() {
		System.out.println("Cliente conectado "  + s.getRemoteSocketAddress());
		try {
			ObjectOutputStream sendObject = new ObjectOutputStream(s.getOutputStream());
			//Creamos y enviamos un objeto
			Persona alumno = new Persona("Pipo", "Programación de servicios", 0);
			sendObject.writeObject(alumno);
			System.out.println("Server enviando " + alumno.getNombre() + " "
					+ alumno.getMateria() + " " 
					+ alumno.getCalificacion() + " ...");
			
			//Creamos el inputStream para leer el objeto de vuelta
			ObjectInputStream receivedObject = new ObjectInputStream(s.getInputStream());
			Persona alumnoRekt = (Persona) receivedObject.readObject();
			System.out.println("Server recibiendo " + alumnoRekt.getNombre() + " "
					+ alumnoRekt.getMateria() + " " 
					+ alumnoRekt.getCalificacion() + " ...");
			
			sendObject.close();
			receivedObject.close();
			s.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
