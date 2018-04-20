package entities;
import java.sql.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Periodo")
public class Periodo {

	
	@Id
	@GeneratedValue
	private int idPeriodo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idCuenta")
	private Cuenta cuenta;
	
	Date fechaInicial;
	Date fechaFinal;
	int valorCuenta;
	
	
	
	public Periodo(){
//		this.fechaInicial = fechaInicial;
//		this.fechaFinal = fechaFinal;
//		this.valorCuenta = valorCuenta;
		
	}
	
	public Integer getValorCuenta() {
		return valorCuenta;
	}

	public void setValorCuenta(int valorCuenta) {
		this.valorCuenta = valorCuenta;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}


	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
}
