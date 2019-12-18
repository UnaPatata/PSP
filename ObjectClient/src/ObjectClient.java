package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObjectClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		String server = "127.0.0.1";
		int puerto = 4020;
		Socket cliente = new Socket(server,puerto);
		//Flujo de entrada de objetos
		ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
		//Recibimos objeto
		Persona alumno = (Persona) ois.readObject();
		//Imprimo objeto
		System.out.println("Recibido > " + alumno.getNombre() + " >"
				+ alumno.getMateria() + " >" 
				+ alumno.getCalificacion() + " ...");
		alumno.setCalificacion(10);
		
		//enviamos el objeto de vuelta
		ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
		oos.writeObject(alumno);
		ois.close();
		oos.close();
		cliente.close();
	}
}
