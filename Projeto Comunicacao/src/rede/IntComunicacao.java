package rede;

public interface IntComunicacao {
	public void enviar(Object obj);
	public Object receber();
	public void conectar();
	public void desconectar();
}
