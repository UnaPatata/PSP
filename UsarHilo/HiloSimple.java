import java.lang.Thread;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
public class HiloSimple extends Thread{

	String nombre;
	int retardo;
	CyclicBarrier barrera;

	public HiloSimple( String nombre ,int retardo, CyclicBarrier barrera ) {
		this.nombre = nombre;
		this.retardo = retardo;  
		this.barrera = barrera;
	}

	public void run() {
		try {
			barrera.await();
			for (int i = 0 ; i < 10 ; i++) {
				System.out.println("En el hilo " + nombre + "...");
				Thread.sleep( retardo );
			}
		} catch( InterruptedException | BrokenBarrierException e ) {
			e.printStackTrace();        
		}//end-try-catch
	}//end-run
}//end-class