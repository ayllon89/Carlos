package entities;

public class OperacionCreador {
	
	public Operacion crearOperacion(String op){
		switch(op){
			case "Mayor":
				return new OperacionMayor();
			case "Menor":
				return new OperacionMenor();
			case "==":
				return new OperacionIgual();
			case "!=":
				return new OperacionDistinto();
			default:
				return new OperacionIgual();//TODO error?
		}
	}
}
