import java.util.concurrent.locks.*;

public class Banco {
	
	private static Cuenta miCuenta = new Cuenta(80);
//	private static Object candado = new Object();
	
 public static void main(String [] args) {
	 Lock candado = new ReentrantLock();
	 
	 ServicioBancario uno = new ServicioBancario(miCuenta,candado);
	 ServicioBancario dos = new ServicioBancario(miCuenta,candado);
	 uno.setName("Ana");
	 dos.setName("Pedro");
	 uno.start();
	 dos.start();
 }
}
