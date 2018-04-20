package controllers;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entities.Cuenta;
import entities.Empresa;
import entities.Periodo;
import model.EntityModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultarController {
	private Map<String, Object> model=new HashMap<>();
	private String cuentaNombre;
	
	private List<String> lista_de_cuentas = new LinkedList<>();
	private List<Periodo> periodos = new LinkedList<>();
		
	public ModelAndView mostar_menu(Request req, Response res) throws IOException{
		
		EntityModel entityModel = EntityModel.getInstance();
		entityModel.refresh();
		model.put("empresas", entityModel.listaEmpresa());
		return new ModelAndView(model, "CONSULTAconsultar.hbs");
	}
	public ModelAndView listarCuentas(Request req, Response res) throws IOException{
		String respuesta;
		Boolean booleano;
		EntityModel entityModel = EntityModel.getInstance();
		entityModel.refresh();
		String empresaBuscada = req.queryParams("empresaBuscada");
		System.out.println("Buscando cuentas de: " + empresaBuscada);
		lista_de_cuentas.clear();
		entityModel.refresh();
		if(entityModel.getEmpresa(empresaBuscada).listaDeCuentas.size() == 0){
			respuesta = "Esta empresa no tiene cuentas";
			booleano=false;
		}else{
			respuesta = "";
			entityModel.refresh();
			entityModel.listaCuentasDe(empresaBuscada).stream().forEach(c -> lista_de_cuentas.add(c.getnombreCuenta()));
			booleano = true;
		}
		
		model.clear();
		model.put("empresa", empresaBuscada);
		model.put("cuentas", lista_de_cuentas);
		model.put("respuesta", respuesta);
		model.put("booleano", booleano);

		return new ModelAndView(model,"CONSULTAdetalleCuentas.hbs");
	}

		public ModelAndView verdetalleCuenta(Request req, Response res) throws IOException{
			String respuesta;
			Boolean booleano;
			EntityModel entityModel = EntityModel.getInstance();
			entityModel.refresh();
			String empresaBuscada = req.queryParams("nombreEmpresa");
			String nombreCuenta = req.queryParams("nombreCuenta");
			
			Empresa empresa = entityModel.getEmpresa(empresaBuscada);
			Cuenta cuenta = empresa.dameCuentaConNombre(nombreCuenta);
			periodos.clear();
			if(cuenta.getlistaDePeriodos().size() == 0){
				respuesta = "Esta cuenta no tiene periodos";
				periodos = cuenta.getlistaDePeriodos();
				booleano = false;
			}else{
				periodos = cuenta.getlistaDePeriodos();
				respuesta = "";
				booleano = true;
			}
						
			model.clear();
			model.put("cuenta", cuenta);
			model.put("periodos", periodos);
			model.put("booleano", booleano);
			model.put("respuesta", respuesta);
			
			
			return new ModelAndView(model, "CONSULTAdetalleCuentaEspecifica.hbs");	
		}
		
		public ModelAndView verdetalleCuenta2(Request req, Response res) throws IOException{
			String respuesta;
			Boolean booleano;
			EntityModel entityModel = EntityModel.getInstance();
			String nombreEmpresa = req.params(":empresa");
			String nombreCuenta = req.params(":cuentaNombre");
			
			
			entityModel.refresh();
			Empresa empresa = entityModel.getEmpresa(nombreEmpresa);
			System.out.print("nombre de la empresa: "+empresa.getNombreEmpresa()+
					"\n cuentas de la empresa: "+empresa.getCuentas());
			//Cuenta cuenta = empresa.dameCuentaConNombre(nombreCuenta);
			Cuenta cuenta = entityModel.getCuentaDeEmpresa(nombreCuenta, nombreEmpresa);
			System.out.print("\n lista de cuentas de la empresa"+entityModel.listaCuentasDe(empresa.getNombreEmpresa()));
			
			periodos.clear();
			if(cuenta.getlistaDePeriodos().size() == 0){
				respuesta = "Esta cuenta no tiene periodos";
				periodos = cuenta.getlistaDePeriodos();
				booleano = false;
			}else{
				periodos = cuenta.getlistaDePeriodos();
				respuesta = "";
				booleano = true;
			}
			model.clear();
			model.put("cuenta", cuenta);
			model.put("periodos", periodos);
			model.put("cuentaNombre", cuentaNombre);
			model.put("booleano", booleano);
			model.put("respuesta", respuesta);
			return new ModelAndView(model, "CONSULTAdetalleCuentaEspecifica.hbs");
		}
	}


	
	
	
	
		



	
	
	
	
		

