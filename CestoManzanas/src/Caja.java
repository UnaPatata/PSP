package src;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

public class Caja {

	private int manzanas;
	private Semaphore accesoComer;
	private Lock candado = new ReentrantLock();
	private Random rand = new Random();
	
	public Caja(int maxManzanas, Semaphore accesoComer) {
		this.manzanas = maxManzanas;
		this.accesoComer = accesoComer;
	}

	public boolean vacia() {
		boolean quedanManzanas;
		candado.lock();
		if (manzanas > 0) {
			quedanManzanas = true;
		} else {
			quedanManzanas = false;
		}
		candado.unlock();
		return quedanManzanas;
	}

	public int getManzanas() throws InterruptedException {
		accesoComer.acquire();
		int intentoComer = rand.nextInt(5);
		candado.lock();
		if (manzanas >= intentoComer) {
			manzanas -= intentoComer;
		} else {
			intentoComer = 0;
		}
		candado.unlock();
		accesoComer.release();
		return intentoComer;
	}

}
