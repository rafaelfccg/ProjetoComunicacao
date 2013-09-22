package UDP;

import rede.*;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DadosCliente {

	int port;
	DatagramSocket socket;
	InetAddress ip;
	String nome;
	int time;
	
	public DadosCliente(int port, DatagramSocket socket, InetAddress ip, String nome, int time) {
		this.port = port;
		this.socket = socket;
		this.ip = ip;
		this.nome = nome;
		this.time = time;
	}
	

}
