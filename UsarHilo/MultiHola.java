import java.util.concurrent.CyclicBarrier;

public class MultiHola {
	public static void main(String [] args) {
		
		int maxHilos = 1000;
		CyclicBarrier barrera = new CyclicBarrier(maxHilos);
		
		
		HiloSimple [] arrayHilos = new HiloSimple[maxHilos];
		//crear hilos
		for (int i = 0 ; i < maxHilos ; i++) {
			arrayHilos[i] = new HiloSimple("Hilo " + i, (int)Math.random()*2000, barrera);
		}
		//Inicia los hilos
		for (int i = 0 ; i < maxHilos ; i++) {
			arrayHilos[i].start();
		}
		
		for (int i = 0 ; i < maxHilos ; i++) {
			System.out.println("Fuera del hilo");
		}
	}
}