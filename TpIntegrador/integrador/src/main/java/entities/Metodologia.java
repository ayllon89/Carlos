package entities;

import java.sql.Date;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import model.EntityModel;

@Entity
@Table(name="Metodologias")

public class Metodologia implements TipoElemento
{


	@Id
	@GeneratedValue
	private int idMetodologia;
	
	private String nombreMetodologia;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	//@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	private int idUsuario;
	
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE,mappedBy="metodologia")
	private List<Condicion> condiciones = new LinkedList<Condicion>();
	
	
	private String t;
	
	@Transient
	private List<Empresa> resultado = new LinkedList<Empresa>();
	
	public Metodologia(){
		
	}
	
	public List<Condicion> getCondiciones(){
		return this.condiciones;
	}
	
	public void setCondiciones(List<Condicion> condiciones){
		this.condiciones=condiciones;
	}
	
	public String getNombreMetodologia(){
		return this.nombreMetodologia;
	}

	public void setNombreMetodologia(String nombre) {
		this.nombreMetodologia=nombre;
	}
	
//	public Usuario getUsuario(){
//		return this.usuario;
//	}
//	
//	public void setUsuario(Usuario usuario){
//		this.usuario=usuario;
//	}
	
	
	public void agregarCondicion(Condicion condicion){
		this.condiciones.add(condicion);
	}
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	
	
	
	public List<Empresa> aplicarMetodologia(List<Empresa> empresas,Date fechaInicial, Date fechaFinal,EntityModel em) {
		
		if(t.equals("TAXATIVA")){
			System.out.print("dentro de condicion taxativa");
			List<CondicionTaxativa> condicionesT = new LinkedList<>();
			this.condiciones.forEach(c -> condicionesT.add((CondicionTaxativa) c));
			condicionesT.stream().forEach(c-> resultado = c.operar(empresas, fechaInicial, fechaFinal, em));
			System.out.print(resultado);
			
			return resultado;
		}else{
			System.out.print("dentro de condicion de orden");
			List<CondicionDeOrden> condicionesO = new LinkedList<>();
			this.condiciones.forEach(condicion->condicionesO.add((CondicionDeOrden) condicion));
			condicionesO.stream().forEach(c-> resultado = c.operar(empresas, fechaInicial, fechaFinal, em));
			System.out.print(resultado);
			return resultado;
		}
		
		//System.out.println("En metodologia la lista mide: "+resultado.size());
		
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	
}