import java.util.concurrent.locks.Lock;

public class ServicioBancario extends Thread {

	Cuenta miCuenta;
	Lock candado;

	public ServicioBancario(Cuenta miCuenta, Lock candado) {
		this.miCuenta = miCuenta;
		this.candado = candado;
	}

	public void run() {
		hacerReintegro(50);
		if (miCuenta.getSaldo() < 0) {
			System.out.println(Thread.currentThread().getName() +  " está en numernos rojos.");
		}
	}

	private void hacerReintegro(int cantidad) {
		int re;
		while(true) {
		if (candado.tryLock()) {
			if (miCuenta.getSaldo() >= cantidad) {
				//System.out.println(Thread.currentThread().getName());
				System.out.println("Toma tu mierda de dinero, " + Thread.currentThread().getName());
				re = miCuenta.reintegro(cantidad);	
				if (re > 0) {
					System.out.println(Thread.currentThread().getName() + " +" + cantidad + "$");
				} else {
					System.out.println("F por " + Thread.currentThread().getName());
				}
			} else {
				System.out.println(Thread.currentThread().getName() + " no tiene tanta pasta");
			}
			break;
		} else {
			System.out.println(Thread.currentThread().getName() + " no tienes la llave del cerrojo, perro, jajá");
		}
		}
		candado.unlock();
	}
}
