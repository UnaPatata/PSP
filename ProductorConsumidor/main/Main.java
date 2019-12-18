package main;

import domain.Cola;
import domain.Consumidor;
import domain.Productor;

public class Main {

	public static void main(String[] args) {
		Cola cola = new Cola();
		Productor p = new Productor(cola);
		Consumidor c = new Consumidor(cola);
		
		p.start();
		c.start();
	}

}
