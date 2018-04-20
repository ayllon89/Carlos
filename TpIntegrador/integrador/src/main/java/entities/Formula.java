package entities;

import java.util.ArrayList;

public class Formula {
	
	
	private ArrayList<String> nombreCuentas;
	public Formula(){
		this.nombreCuentas = new ArrayList<String>(); 
	}

	public ArrayList<String> getNombreCuentas() {
		return nombreCuentas;
	}

	public void setNombreCuentas(ArrayList<String> nombreCuentas) {
		this.nombreCuentas = nombreCuentas;
	}
	public void agregarCuenta(String cuenta){
		nombreCuentas.add(cuenta);
	}	
}
