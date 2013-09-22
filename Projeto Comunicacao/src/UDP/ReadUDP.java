package UDP;

import rede.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReadUDP extends Thread{
	
	DatagramSocket socket;
	String s;
	ByteArrayInputStream bi;
	ObjectInput in;
	
	public ReadUDP(DatagramSocket socket) {
		this.socket = socket;
	}
	
	public DatagramPacket criarPacoteLeitura() {
		byte[] receiveData = new byte[1024];
		DatagramPacket pacote = new DatagramPacket(receiveData, receiveData.length);
		return pacote;
	}
	
	public Object lerDatagrama() throws IOException, ClassNotFoundException {
		DatagramPacket receivePacket = criarPacoteLeitura();
		socket.receive(receivePacket);
		bi = new ByteArrayInputStream(receivePacket.getData());
		in = new ObjectInputStream(bi);
		return in.readObject();
	}

}
