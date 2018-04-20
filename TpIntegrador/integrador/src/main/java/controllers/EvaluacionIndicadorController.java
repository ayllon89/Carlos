package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import entities.*;
import javacc.Calculator;
import javacc.EG1;
import javacc.ParseException;
import model.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EvaluacionIndicadorController {
	private Map<String, Object> model=new HashMap<>();
	private Empresa empresa;
	private Indicador indicador;
	
	private static Calculator parser = null;
	private static EG1 eg1 ;
	private String  respuesta;
	private List<String> nombres = new LinkedList<String>();
	private List<String> nombresCuentas = new LinkedList<String>();
	
	public ModelAndView seleccionarEmpresa(Request req, Response res) throws IOException{
		
		EntityModel em = EntityModel.getInstance();
		em.refresh();
		model.clear();
		model.put("empresas", em.listaEmpresa());
		return new ModelAndView(model, "EVALUAR_INDICADORseleccionar_Empresa.hbs");
	}
	public ModelAndView seleccionarIndicador(Request req, Response res) throws IOException{
		EntityModel em = EntityModel.getInstance();
		String empresaBuscada = req.queryParams("empresaBuscada");
		
		empresa = em.getEmpresa(empresaBuscada);
		em.refresh();//no se esta refrescando
		
		model.clear();
		model.put("indicadores", em.listaIndicadoresPorId(LogInController.idUsuario));
		model.put("empresa", empresa);
		return new ModelAndView(model, "EVALUAR_INDICADORseleccionar_Indicador.hbs");
	}

	public ModelAndView ingresarPeriodo(Request req, Response res) throws IOException, ParseException{
		
		EntityModel entitymodel = EntityModel.getInstance();
		String indicadorBuscado = req.queryParams("indicadorBuscado");
		indicador = (entitymodel.getIndicador(indicadorBuscado));
		System.out.println("Formula indicador = " + indicador.getFormula());
		nombres.clear();
		entitymodel.listaCuentasDe(empresa.getNombreEmpresa()).stream().forEach(c -> nombres.add(c.getnombreCuenta()));
		
		nombresCuentas.clear();
		nombresCuentas = indicador.dameCadaUnaDeLasCuentas(eg1, entitymodel); 
				//indicador.getFormulaObjeto().getNombreCuentas();
		for (int i = 0; i < nombresCuentas.size(); i++) {
			System.out.println("Nombre " + i + ": " + nombresCuentas.get(i));
		}
		
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
		ArrayList<Date> fechasInicialesRepetidas = new ArrayList<Date>();
		ArrayList<Date> fechasFinalesRepetidas = new ArrayList<Date>();
		ArrayList<Boolean> booleanos = new ArrayList<Boolean>();
		nombresCuentas.stream().forEach(cuenta -> booleanos.add(empresa.tenesCuentaLlamada(cuenta)));
		System.out.print("BOOLEANOS"+booleanos);
		Boolean booleano;
		if(empresa.listaDeCuentas.size()!=0){
		
		if(booleanos.contains(false)){
			respuesta ="Esta empresa no posee las cuentas necesarias para evaluar este indicador";
			booleano = false;
		}else{
			respuesta = "";
			nombresCuentas.stream().forEach(cuenta -> cuentas.add(empresa.dameCuentaConNombre(cuenta)));
			booleano = true;
		}
		}else{
			respuesta = "Esta empresa no tiene cuentas";
			booleano = false;
		}
		
		cuentas.stream().forEach(cuenta -> cuenta.getlistaDePeriodos().stream().forEach(o -> fechasInicialesRepetidas.add(o.getFechaInicial())));
		cuentas.stream().forEach(cuenta -> cuenta.getlistaDePeriodos().stream().forEach(o -> fechasFinalesRepetidas.add(o.getFechaFinal())));
		
		ArrayList<Date>fechasIniciales = new ArrayList<Date>(new HashSet<Date>(fechasInicialesRepetidas));//Asi no aparecen duplicados los años
		ArrayList<Date>fechasFinales = new ArrayList<Date>(new HashSet<Date>(fechasFinalesRepetidas));

		model.clear();
		model.put("indicador", indicador);
		model.put("empresas", empresa);
		model.put("fechasIniciales", fechasIniciales);
		model.put("fechasFinales", fechasFinales);
		model.put("respuesta", respuesta);
		model.put("booleano", booleano);
		return new ModelAndView(model, "EVALUAR_INDICADORingresar_Periodo.hbs");
	}
	
	
	@SuppressWarnings("deprecation")
	public ModelAndView evaluarIndicador(Request req, Response res) throws IOException, ParseException, java.text.ParseException{
		
	
//		int fechaIncial = Integer.parseInt(req.queryParams("fechaInicial"));
//		int fechaFinal = Integer.parseInt(req.queryParams("fechaFinal"));
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
		Date fechaInicial;
		Date fechaFinal;
		
		String fechaInicialString = req.queryParams("fechaInicial");
		System.out.print(" fechaInicialstring " +fechaInicialString);
		String fechaFinalString = req.queryParams("fechaFinal");
		
		if(fechaInicialString.contains("-")){
			fechaInicial = formatoFecha.parse(fechaInicialString);
			System.out.print(" fechaInicialcon/ " +fechaInicial);
			
		}else{
			fechaInicial = formatoAnio.parse(fechaInicialString);
			System.out.print(" fechaInicial sin/ " +fechaInicial);
		}
		if(fechaFinalString.contains("-")){
			fechaFinal = formatoFecha.parse(fechaFinalString);
		}else{
			fechaFinal = formatoAnio.parse(fechaFinalString);
		}
		
		java.sql.Date sqlfechaInicio = new java.sql.Date(fechaInicial.getYear(),fechaInicial.getMonth(),1);
		java.sql.Date sqlfechaFin = new java.sql.Date(fechaFinal.getYear(),fechaFinal.getMonth(),1);
		
		
		System.out.print(nombresCuentas+"nombre empresa"+empresa.getNombreEmpresa());
		System.out.print("fecha inicial y fecha final "+sqlfechaInicio+sqlfechaFin);
		System.out.print("nombre del indicador"+indicador.getNombreIndicador());
	
//		parser= null;
//		System.out.print("parser esta en null");
		double valor =indicador.aplicarA2(empresa, sqlfechaInicio,sqlfechaFin,parser,nombresCuentas);
		if(valor!=-1){
		respuesta = ("El valor del indicador para la empresa es: " + valor);
		}
		else{
		respuesta =	"El valor del indicador para la empresa no existe";
		}
		
		model.clear();
		model.put("empresa", empresa);
		model.put("indicador", indicador);
		model.put("respuesta", respuesta);
		return new ModelAndView(model, "EVALUAR_INDICADORevaluarIndicador.hbs");
	}
	
	}

