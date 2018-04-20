package entities;

public class FabricaOperaciones
{
	public Operacion crearOperacion(String operacion)
	{
		if(operacion=="<")
		{
			return new OperacionMenor();
		}
		else if (operacion==">")
		{
			return new OperacionMayor();
		}
		else if (operacion=="=")
		{
			return new OperacionIgual();
		}
		else if (operacion=="=/")
		{
			return new OperacionDistinto();
		}
		return null;
		
	}

}
