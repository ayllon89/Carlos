package entities;

import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedList;
//import java.util.Optional;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name ="Empresas")
public class Empresa implements TipoElemento {

	@Id
	@GeneratedValue
	private int idEmpresa;
	
	
	private String nombreEmpresa;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="empresa")
	public List<Cuenta> listaDeCuentas;
	
	
	
	public void set_idEmpresa(int id){
		this.idEmpresa = id;
	}
	
	public Empresa(){//constructor
		
//		this.nombreEmpresa = nombreEmpresa;
		this.listaDeCuentas = new LinkedList<>();
		
		//nose si hace falta poner algo sobre lo de formula
		
	}
	
	
	
	//getters
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	
	public List<Cuenta> getCuentas() {
		return listaDeCuentas;
	}
	//setters
		
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public void setListaDeCuentas(ArrayList<Cuenta> listaDeCuentas) {
		this.listaDeCuentas = listaDeCuentas;
	}
	public void agregarCuenta(Cuenta cuenta){
		listaDeCuentas.add(cuenta);
	}
//	public boolean equals(Object o)
//	{
//		Empresa unaEmpresa=(Empresa) o;
//		return unaEmpresa.getNombreEmpresa().equals(this.getNombreEmpresa());
//	}
	
	public Boolean tenesCuentaLlamada(String cuentaBuscada){
		return listaDeCuentas.stream().anyMatch(cuenta -> cuenta.getnombreCuenta().equals(cuentaBuscada));
		
	}
//	public Boolean tenesValorDeCuentaEn(Cuenta cuenta, Periodo periodo){
	public Boolean tenesValorDeCuentaEn(Cuenta cuenta, Date fechaInicial, Date fechaFinal){
		if(this.tenesCuentaLlamada(cuenta.getnombreCuenta())){
			 return cuenta.getlistaDePeriodos().stream().anyMatch(periodo -> periodo.getFechaInicial() == fechaInicial && periodo.getFechaFinal() == fechaFinal );
		}
		return false;
	}
	
	public Cuenta dameCuentaConNombre(String nombreCuenta){
	
	ArrayList<String> nombreCuentas = new ArrayList<String>();
	this.getCuentas().stream().forEach(c -> nombreCuentas.add(c.getnombreCuenta()));
	
	System.out.println("nombre de la cuentas en dameCuentaConNombre: "+nombreCuentas.toString());
	Boolean n = nombreCuentas.contains(nombreCuenta);
	System.out.print(n);
	int i = nombreCuentas.indexOf(nombreCuenta);
	Cuenta cuenta = this.getCuentas().get(i);
	//ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) this.listaDeCuentas.stream().filter(cuenta -> cuenta.getnombreCuenta().equals(nombreCuenta)).collect(Collectors.toList());
	//Cuenta cuenta = cuentas.get(0);
		
	return cuenta;
	}
	
	
	public Boolean tenesCuentaYValor(String cuenta, Date fechaInicial, Date fechaFinal){
		if(this.tenesCuentaLlamada(cuenta)){
			Boolean bool = this.getCuentas().stream().anyMatch(cuenta1 -> cuenta1.tienePeriodo(fechaInicial, fechaFinal));
			System.out.print("tiene el periodo"+bool);
			return bool;
		}
		System.out.print("tiene cuenta llamada "+cuenta);
		return false;
	}
	
	
	@SuppressWarnings("deprecation")
	public String dameValorCuentaEnPeriodo(String cuenta, Date fechaInicial, Date fechaFinal){
		
		ArrayList<Cuenta> cuentasDeIndicador = (ArrayList<Cuenta>) this.listaDeCuentas.stream().filter(cuenta1 -> cuenta1.getnombreCuenta().equals(cuenta)).collect(Collectors.toList());
		System.out.print("cuentas dl indicador" + cuentasDeIndicador);
		System.out.print("periodos"  + cuentasDeIndicador.get(0).getlistaDePeriodos());
		ArrayList<Periodo> valorCuenta = (ArrayList<Periodo>) cuentasDeIndicador.get(0).getlistaDePeriodos().stream().filter(periodo1 -> 
				(periodo1.getFechaInicial().getYear() == fechaInicial.getYear())
				&&(periodo1.getFechaFinal().getYear() == fechaFinal.getYear()) 
				&& (periodo1.getFechaInicial().getMonth() == fechaInicial.getMonth()) &&
				(periodo1.getFechaFinal().getMonth() == fechaFinal.getMonth())).collect(Collectors.toList());
		
		if(valorCuenta.size()==0){
			return "-1";
		}else{
			return valorCuenta.get(0).getValorCuenta().toString();
		}
		
		
		
	}

	
	
	
}
