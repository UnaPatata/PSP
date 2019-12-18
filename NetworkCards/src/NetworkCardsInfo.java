package src;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class NetworkCardsInfo {

	public static void main(String[] args) {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface miInterfaz : Collections.list(interfaces)) {
				dislayInfo(miInterfaz);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private static void dislayInfo(NetworkInterface miInterfaz) throws SocketException {
		System.out.println("Interfaz: " + miInterfaz.getDisplayName());
		if (!miInterfaz.getDisplayName().contains("lo")) {
			byte [] mac = miInterfaz.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0 ; i < mac.length ; i++) {
				sb.append("");
			}
			System.out.println(mac);
		}
	}

}
