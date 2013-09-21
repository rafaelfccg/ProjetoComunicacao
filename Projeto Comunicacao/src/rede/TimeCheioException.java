package rede;

public class TimeCheioException extends Exception{
	private static final long serialVersionUID = 1L;

	public TimeCheioException(){
		super("Time cheio! Tente novamente");
	}
}
