package rede;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente implements IntComunicacao {

	Socket sock;
	final int port = 2000;

	public Cliente() {}

	public void conectar() throws IOException {
		sock = new Socket("localhost", port);
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
