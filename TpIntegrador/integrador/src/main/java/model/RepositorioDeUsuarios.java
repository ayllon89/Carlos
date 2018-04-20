package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import entities.TipoElemento;
import entities.Usuario;

public class RepositorioDeUsuarios {
	public DAOUsuario daoUsuario;

	public RepositorioDeUsuarios(DAOUsuario dao){
		this.daoUsuario = dao;
	}
	
	public ArrayList<TipoElemento> listaUsuarios() throws FileNotFoundException, IOException{
		return this.daoUsuario.lista();
	}
	public Boolean tieneNombreUsuario(ArrayList<TipoElemento> listaUsuarios,String user){
		return this.daoUsuario.buscar(listaUsuarios, user);
	}
	public void cargarUsuario(Usuario user) throws IOException{
		this.daoUsuario.cargar(user);
	}

}
