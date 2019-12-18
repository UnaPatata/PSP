package src;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Comedor extends Thread{
	int nComedor;
	CyclicBarrier inicioComer;
	CountDownLatch esperaTodosTerminen;
	Caja cajaManzanas;
	int comidas = 0;
	
	public Comedor(int nComedor, 
			CyclicBarrier inicioComer, 
			CountDownLatch esperaTodosTerminen, 
			Caja cajaManzanas) {
		this.nComedor = nComedor;
		this.inicioComer = inicioComer;
		this.esperaTodosTerminen = esperaTodosTerminen;
		this.cajaManzanas = cajaManzanas;
	}
	
	public void run(){
		try {
			//Todos los comedores inicial a la vez
			inicioComer.wait();
			while (!cajaManzanas.vacia()) {
				comidas += cajaManzanas.getManzanas();
			}
			//Notifica que el hilo ha terminado
			esperaTodosTerminen.countDown();
			System.out.println("El comedor " + nComedor + " ha comido " + comidas + " manzanas.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
