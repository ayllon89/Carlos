package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import db.EntityManagerHelper;
import entities.Usuario;
import interfaz.Config;
import entities.TipoElemento;


public class UsuarioModel {

	
	private ArrayList<TipoElemento> usuarios;
	private RepositorioDeUsuarios repoUsuarios;
	private static UsuarioModel instance = null;
	
	
	public static UsuarioModel getInstance() throws IOException{
		if(instance==null){
			instance = new UsuarioModel();
		}
		return instance;
	}
	
	private UsuarioModel() throws IOException {
	
		this.usuarios = new ArrayList<TipoElemento>();
		//this.boot();
	}
	
	public void boot() throws IOException{
		DAOUsuario dao = new DAOUsuario();
		dao.setFilePath(new Config("config.cfg").getUsuarios());
		repoUsuarios = new RepositorioDeUsuarios(dao);
		usuarios = repoUsuarios.listaUsuarios();
		System.out.println("Se creo una nueva instancia");
		System.out.println("Primer nombre de usuario: "+((Usuario) usuarios.get(0)).getUser());
		
	}
	
	public Usuario get(String nombreUsuarioBuscada){
		ArrayList<String> nombres = new ArrayList<String>();
		usuarios.stream().forEach(usuario-> nombres.add(((Usuario) usuario).getUser()));
		int a = nombres.indexOf(nombreUsuarioBuscada);
		System.out.println("Posicion de el usuario buscada:"+a);
		//System.out.println(a);
		Usuario usuarioBuscada = (Usuario) usuarios.get(a);
		
		System.out.println("Usuario encontrado: "+usuarioBuscada);
		return usuarioBuscada;	
	}
	
	public ArrayList<TipoElemento> getAll(){
		return this.usuarios;
	}
	
	public RepositorioDeUsuarios getRepositorioDeUsuarios(){
		return this.repoUsuarios;
	}
	
	//update
	public void actualizarListaUsuarios() throws IOException{
		this.usuarios = repoUsuarios.listaUsuarios();
	}
	
	//
	
	//controlar
	
	public List<Usuario> findWithName(String name) {
		EntityManager em = EntityManagerHelper.getEntityManager();
	    return em.createQuery(
	        "SELECT user FROM Usuarios u WHERE u.user LIKE ?1")
	        .setParameter(1, name)
	        .getResultList();
	    
	}
	
	 
	
//	public Boolean verificiarExistenciaDeUser(String user){
//		
//		List<Usuario> lista = findWithName(user);
//		System.out.println("cantidad de usuarios con el nombre" + lista.size());
//		
////		ArrayList<String> nombresUsuarios = new ArrayList<String>();
////		usuarios.stream().forEach(i -> nombresUsuarios.add(((entities.Usuario) i).getUser()));
////		Boolean valor = nombresUsuarios.contains(user);
//		System.out.println("El user buscado existe "+(lista.size() > 0));
//		return lista.size() > 0;		
//	}
	
//	public Boolean coincidePassword(String user,String password){
//		
//		Boolean valor = this.get(user).getPassword().equals(password);
//		 
//		
//		System.out.println("la contraseña coincide "+valor);
//		return valor;		
//	}
	
}
