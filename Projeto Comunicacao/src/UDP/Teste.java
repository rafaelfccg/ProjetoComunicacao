package UDP;

import rede.*;
import java.io.Serializable;

public class Teste implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5107085265372716105L;
	int n;
	String s;
	
	public Teste(int n, String s) {
		this.n = n;
		this.s = s;
	}

}
