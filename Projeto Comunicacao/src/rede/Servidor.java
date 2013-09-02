package rede;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements IntComunicacao{
	
	Socket sock;
	ServerSocket ws;
	final int port = 2000;
	
	public Servidor(ServerSocket ws){
		this.ws = ws;
	}
	public void conectar() throws IOException {
		sock = ws.accept();
	}

	public void desconectar() throws IOException {
		sock.close();
	}

	public void enviar(Object obj) throws IOException {
		ObjectOutputStream dos = null;
		dos = new ObjectOutputStream(sock.getOutputStream());
		dos.writeObject(obj);

	}

	public Object receber() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		Object obj = null;

		ois = new ObjectInputStream(sock.getInputStream());
		obj = ois.readObject();

		return obj;
	}
}
