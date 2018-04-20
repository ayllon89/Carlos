package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import org.hibernate.jpa.internal.EntityManagerImpl;

import entities.Usuario;
import model.EntityModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LogInController {
	
private Map<String, Object> model=new HashMap<>();
private String respuesta;
private Boolean booleano;
public static int idUsuario;


//opciones
	public ModelAndView registro_o_logueo(Request req, Response res){
		return new ModelAndView(model, "LOGINregistro_o_logueo.hbs");
	}

//registro
	public ModelAndView registro(Request req, Response res){
	
	return new ModelAndView(model, "LOGINregistro.hbs");
}
	
	public ModelAndView confirma_registro(Request req, Response res) throws IOException{		
	EntityModel entityModel = EntityModel.getInstance();
	String userIngresado = req.queryParams("user");
	String passwordIngresada = req.queryParams("password");
	if(entityModel.verificarExistenciaUsuario(userIngresado)){
		respuesta = "Este nombre de usuario ya esta siendo utilizada, por favor intente con otro.";
		booleano=false;
	}else{
		
		Usuario usuario = new Usuario();
		usuario.setUser(userIngresado);
		usuario.setPassword(passwordIngresada);
		entityModel.createElemento(usuario);
		
		
		LogInController.idUsuario = usuario.getIdUsuario();
		System.out.print(" id usuario actual"+LogInController.idUsuario);
		respuesta = "Nuevo usuario registrado";
		booleano=true;
	}
	
	model.clear();
	model.put("respuesta", respuesta);
	model.put("booleano",booleano);
	return new ModelAndView(model, "LOGINconfirmarRegistro.hbs");
}
	
	


	public ModelAndView logueo(Request req, Response res) throws IOException{
	
	return new ModelAndView(model, "LOGINlogueo.hbs");
}
	
	public ModelAndView confirma_logueo(Request req, Response res) throws IOException{

		EntityModel entityModel = EntityModel.getInstance();
		//System.out.print("En confirma logueo");
		
		String userIngresado = req.queryParams("user");
		String passwordIngresada = req.queryParams("password");
		
		if(entityModel.verificarExistenciaUsuario(userIngresado)){
			System.out.print("Se verifica la existencia de user");
			if(entityModel.coincidePassword(userIngresado, passwordIngresada)){
				respuesta="Usuario y contraseña correctos. Puede ir al menu principal";
				LogInController.idUsuario = entityModel.getUsuario(userIngresado).getIdUsuario();
				System.out.print(" id usuario actual"+LogInController.idUsuario);

				booleano= true;
			}else{
				respuesta="Contraseña incorrecta";
				booleano= false;
			}}else{
				
				System.out.print("N verifica la existencia de user");
			respuesta = "Este usuario no se encuentra registrado";
			booleano = false;
			
			//si no existe lo tiene que refistrar y si existe se tiene q fijar que la pass coincida.
		}
		
		model.clear();
		model.put("respuesta", respuesta);
		model.put("booleano", booleano);
	return new ModelAndView(model, "LOGINconfirmarLogueo.hbs");
	}
	



}
