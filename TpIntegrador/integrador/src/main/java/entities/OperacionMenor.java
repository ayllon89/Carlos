package entities;

public class OperacionMenor implements Operacion{
	
	@Override
	public boolean operar(double a, double b) {
		return a<b;
	}
}
