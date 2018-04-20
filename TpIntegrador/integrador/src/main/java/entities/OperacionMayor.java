package entities;

public class OperacionMayor implements Operacion {

	@Override
	public boolean operar(double a, double b) {
		return a>b;
	}
	
}
