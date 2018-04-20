package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Cuentas")

public class Cuenta implements Serializable,TipoElemento {

	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int idCuenta;
	
	private String nombreCuenta;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idEmpresa")
	private Empresa empresa;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="cuenta")
	private List<Periodo> listaDePeriodos;
	
	
	public Cuenta(){
		
//		this.nombreCuenta =nombreCuenta;
//		this.empresa = empresa;
//		this.empresa = empresa;
//		this.empresa. = id_empresa;
		this.listaDePeriodos = new LinkedList<>();
		
	}
	//getters
	public String getnombreCuenta() {
		return nombreCuenta;
	}
	
	public void set_empresa(Empresa empresa){
		this.empresa = empresa;
	}
	public Empresa get_empresa(){
		return this.empresa;
	}
	
//	public int getValorCuenta() {
//		return valorCuenta;
//	}

	public List<Periodo> getlistaDePeriodos() {
		return listaDePeriodos;
	}
	
	//setters
	
		
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}
	
	
//	public void setValorCuenta(int valorCuenta) {
//		this.valorCuenta = valorCuenta;
//	}


	public void setlistaDePeriodos(ArrayList<Periodo> listaDePeriodos) {
		this.listaDePeriodos = listaDePeriodos;
	}
	public void addPeriodo(Periodo periodo){
		this.listaDePeriodos.add(periodo);
	}
	
	@SuppressWarnings("deprecation")
	public Boolean tienePeriodo(Date fechaInicial, Date fechaFinal){
		Boolean bool = this.getlistaDePeriodos().stream().anyMatch(periodo-> periodo.getFechaInicial().getYear()==fechaInicial.getYear() 
				&& periodo.getFechaFinal().getYear()==fechaFinal.getYear() && periodo.getFechaInicial().getMonth()==fechaInicial.getMonth() 
				&& periodo.getFechaFinal().getMonth()==fechaFinal.getMonth()
				);
		System.out.print(bool);
		return bool;
		
	}


}