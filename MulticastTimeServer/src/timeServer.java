package src;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class timeServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		int serverPort = 4245;
		String serverIp = "225.0.0.20";
		int hh,mm,ss;
		String sMensaje = null;
		InetAddress server = InetAddress.getByName(serverIp);
		DatagramSocket s = new DatagramSocket();
		DatagramPacket out = null;
		System.out.println("Server funcionando en : " + serverIp + ":" + serverPort);
		Calendar miCalendario = new GregorianCalendar();
		while (true) {
			hh = miCalendario.get(Calendar.HOUR_OF_DAY);
			mm = miCalendario.get(Calendar.MINUTE);
			ss = miCalendario.get(Calendar.SECOND);
			sMensaje = "Clock " + hh + ":" + mm + ":" + ss;
			out = new DatagramPacket(sMensaje.getBytes(),sMensaje.getBytes().length,server,serverPort);
			s.send(out);
			System.out.println("Server>" + sMensaje);
			Thread.sleep(1000);
		}
		
		
	}

}
