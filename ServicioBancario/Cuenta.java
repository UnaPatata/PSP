
public class Cuenta {
	
	private int saldo;
	
	public Cuenta(int saldo) {
		this.saldo = saldo;
	}
	
	int getSaldo() {
		return saldo;
	}
	
	synchronized int reintegro(int cantidad) {
		int re;
		if (saldo >= cantidad) {
			saldo -= cantidad;
			re = cantidad;
		} else {
			re = 0;
		}
		return re;
	}
	
}
