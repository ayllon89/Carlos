package entities;

import java.sql.Date;
import java.util.List;
//import java.util.ArrayList;
//import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Condiciones")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Condicion
{
	@Id
	@GeneratedValue
	private int idCondicion;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idMetodologia")
	private Metodologia metodologia;

	
	
//	public List<Empresa> operar(List<Empresa> empresas, Date fechaInicial, Date fechaFinal){
//		return empresas;
//	}
	//;
	/*public String getOperacion();
	public void setOperacion(String operacion);
	public String getTipo();*/
}
