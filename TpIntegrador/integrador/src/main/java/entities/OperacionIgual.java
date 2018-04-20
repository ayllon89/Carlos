package entities;

public class OperacionIgual implements Operacion{

	@Override
	public boolean operar(double a, double b) {
		return a==b;
	}

}
