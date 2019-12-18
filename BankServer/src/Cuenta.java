package src;

public class Cuenta {

	private double balance;
	
	public synchronized double getSaldo() {
		return balance;
	}

	public synchronized boolean deposito(double cantidad) {
		balance += cantidad;
		return true;
	}

	public synchronized boolean reintegro(double cantidad) {
		if (balance >= cantidad) {
			balance -= cantidad;
			return true;
		}
		return false;
	}

}
