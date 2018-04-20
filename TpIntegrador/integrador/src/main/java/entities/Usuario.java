package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="Usuarios")
public class Usuario implements TipoElemento {
	
	
	@Id
	@GeneratedValue
	public int idUsuario;
	
	public String user;
	public String password;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE,mappedBy="usuario")//le puse merge ni idea
	public List<Indicador> listaDeIndicadores;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="usuario")
	public List<Metodologia> listaDeMetodologias;
	
	
	//constructor
	//public Usuario(String user, String password, int idUsuario){
	public Usuario(){
//		this.user = user;
//		this.password = password;
	}
	
	//add
	
	public void addIndicador(Indicador indicador){
		this.listaDeIndicadores.add(indicador);
	}
	//getters
	
	public int getIdUsuario(){
		return idUsuario;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	//setters
	
	

	public void setUser(String user) {
		this.user = user;
	}



	public void setPassword(String password) {
		this.password = password;
	}


	public List<Indicador> getListaDeIndicadores() {
		return listaDeIndicadores;
	}


	public void setListaDeIndicadores(List<Indicador> listaDeIndicadores) {
		this.listaDeIndicadores = listaDeIndicadores;
	}


	public List<Metodologia> getListaDeMetodologias() {
		return listaDeMetodologias;
	}


	public void setListaDeMetodologias(List<Metodologia> listaDeMetodologias) {
		this.listaDeMetodologias = listaDeMetodologias;
	}
	
	
}
