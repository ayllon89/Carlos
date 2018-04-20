package test;

import static org.junit.Assert.*;

import entities.TipoElemento;
import entities.Usuario;
import interfaz.Config;
import model.DAOUsuario;
import model.RepositorioDeUsuarios;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestUsuarios {
	
	ArrayList<TipoElemento> usuarios;
	RepositorioDeUsuarios repo;
	
	@Before
	public void init() throws IOException{
		
		
		DAOUsuario dao = new DAOUsuario();
		dao.setFilePath(new Config("config.cfg").getUsuarios());
		this.repo = new RepositorioDeUsuarios(dao);
		usuarios = repo.listaUsuarios();
	}
	@Test
	public void testCargarUsuario() throws IOException {
		
		//Usuario user = new Usuario("Pablo","loro",10);
		//repo.cargarUsuario(user);
		
	}
	
	@Test
	public void testMostrar(){
		usuarios.stream().forEach(e->System.out.print(((Usuario) e).getUser()));
	}

}
