package controllers;

import java.util.HashMap;
import java.util.Map;

//import model.SupermecadoModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res){
		
		return new ModelAndView(model, "inicio.hbs");
		
	}
}
