package entities;

public class OperacionDistinto implements Operacion{

	@Override
	public boolean operar(double a, double b) {
		return a!=b;
	}

}
