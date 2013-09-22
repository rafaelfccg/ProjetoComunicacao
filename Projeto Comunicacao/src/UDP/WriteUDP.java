package UDP;

import rede.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class WriteUDP extends Thread{

	DatagramSocket socket;
	InetAddress ip;
	String s;
	int port;
	int n;
	char c;
	ByteArrayOutputStream bs;
	ObjectOutputStream out;
	Scanner in;
	int nextSeqNum, base;
	
	public WriteUDP(DatagramSocket socket, InetAddress ip, int port) throws IOException {
		this.socket = socket;
		bs = new ByteArrayOutputStream();
		out = new ObjectOutputStream(bs);
		this.ip = ip;
		this.port = port;
		in = new Scanner(System.in);
		this.nextSeqNum = 0;
		this.base = 0;
	}
	
	private DatagramPacket criarPacoteEnvio(Object o) throws IOException {
		DatagramPacket pacote;
		out.writeObject(o);
		byte[] data = bs.toByteArray();
		pacote = new DatagramPacket(data, data.length, ip, port);
		
		return pacote;
	}
	
	public void enviarDatagrama(Object o) throws IOException {
		 DatagramPacket sendPacket = criarPacoteEnvio(o);
    	 socket.send(sendPacket);
	}	
	
}