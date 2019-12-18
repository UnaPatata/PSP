package src;

public class Banco {
	
	int maxCuentas = 10;
	Cuenta [] cuenta = new Cuenta[maxCuentas];
	
	public Banco() {
		for (int i = 0 ; i < maxCuentas ; i++) {
			cuenta[i] = new Cuenta();
		}
	}
	
	public double saldo(int nCuenta) {
		return cuenta[nCuenta].getSaldo();
	}

	public boolean deposito(int nCuenta, double cantidad) {
		return cuenta[nCuenta].deposito(cantidad);
	}
	
	public boolean reintegro(int nCuenta, double cantidad) {
		return cuenta[nCuenta].reintegro(cantidad);
	}
}
