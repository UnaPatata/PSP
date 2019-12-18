import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Caballo extends Thread{
	CyclicBarrier barrera;
	int pista;
	int posicion = 0;
	int dorsal;
	int avance;
	int ganador;
	boolean fin = false;
	public void run() {
		Random rand = new Random();
		try {
			barrera.await();
			//mientras que no llega al final simula la carrera
			while ((posicion < pista) && (!fin)) {
				avance = (int) (Math.random()*5);
				if ( (posicion+avance) < pista ) {
				      posicion += avance;
				}
				else {
					  posicion = pista;
				}
				long aleatorio = rand.nextInt(300);
				Thread.sleep(aleatorio);
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			//e.printStackTrace();
			System.out.println("Finalizado caballo " + dorsal);
		}
	}
	public Caballo(int i, int pista, CyclicBarrier barrera) {
		this.barrera = barrera;
		this.pista = pista;
		this.posicion = 0;
		this.dorsal = i;
		this.ganador = 99;
	}
    public int getPosicion() {
    	return posicion;
    }
	public void finalizar() {
//		fin = true;
//		Thread t = this.currentThread();
		interrupt();
	}
}