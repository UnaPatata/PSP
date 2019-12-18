package src;

import java.util.concurrent.*;

public class CestoManzanas {

	public static void main(String[] args) {
		int maxHilos = 100;
		int maxConcurrente = 5;
		int maxManzanas = 200;
		
		Comedor[] comedor = new Comedor[maxHilos];
		Semaphore accesoComer = new Semaphore(maxConcurrente);
		CyclicBarrier inicioComer = new CyclicBarrier(maxHilos);
		CountDownLatch esperaTodosTerminen = new CountDownLatch(maxHilos);
		Caja cajaManzanas = new Caja(maxManzanas,accesoComer);
		
		//Iniciamos los hilos
		for (int i = 0; i < maxHilos ; i++) {
			comedor[i] = new Comedor(i,inicioComer,esperaTodosTerminen, cajaManzanas);
			comedor[i].start();
		}
		try {
			System.out.println("Iniciados todos los hilos...");
			esperaTodosTerminen.await();
			System.out.println("Terminados todos los hilos...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
