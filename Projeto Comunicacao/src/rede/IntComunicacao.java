

package rede;

import java.io.IOException;

public interface IntComunicacao {
	public void conectar() throws IOException;
	public void desconectar() throws IOException;
	public void enviar(Object obj) throws IOException;
	public Object receber() throws IOException, ClassNotFoundException;
}