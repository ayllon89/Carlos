package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
//import javax.transaction.Transactional;

import db.EntityManagerHelper;
import entities.Condicion;
import entities.CondicionDeOrden;
import entities.CondicionTaxativa;
import entities.Cuenta;
import entities.Empresa;
import entities.Formula;
import entities.Indicador;
import entities.Periodo;
import entities.Usuario;
import entities.Metodologia;
import javacc.EG1;
import javacc.ParseException;
import model.EntityModel;
//import model.IndicadorModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class CreacionController {
	
	
	private Map<String, Object> model=new HashMap<>();
	private String tip;
	
	private static EG1 parser = null;
	private String respuesta;
	
	//private Boolean booleano;
	private String nombreCuenta;
	private String empresaGlobal;
	private String cuentaGlobal;
	private List<String> nombres = new LinkedList<>();
	private List<Condicion> condiciones = new LinkedList<>();
	
	//opciones de carga
	public ModelAndView elegir(Request req, Response res){
		return new ModelAndView(model, "CREACIONindicador_o_metodologia.hbs");
	}
	
	public ModelAndView cargarEmpresa(Request req, Response res) throws IOException{ 
		return new ModelAndView(model, "CREACIONempresas.hbs");
	}

	//empresa
	
	public ModelAndView empresaCreada(Request req, Response res) throws IOException, ParseException{
		EntityModel entityModel = EntityModel.getInstance();
		String nombreEmpresa = req.queryParams("nombreEmpresa");
		nombreEmpresa = nombreEmpresa.replace(" ", "_");
		if(entityModel.verificiarExistenciaEmpresa(nombreEmpresa)){
			respuesta = "Empresa ya existe";
		}else{
			Empresa empresaNueva = new Empresa();
			empresaNueva.setNombreEmpresa(nombreEmpresa);
			entityModel.createElemento(empresaNueva);
			respuesta = "Empresa "+nombreEmpresa+" cargada";
		}
		model.clear();
		model.put("empresas",entityModel.listaEmpresa());
		model.put("respuesta", respuesta);
		return new ModelAndView(model, "CREACIONconfirmarCargaEmpresa.hbs");
	}
	
	//cuenta
	
	public ModelAndView seleccionEmpresa(Request req, Response res) throws IOException{
		EntityModel entitymodel = EntityModel.getInstance();
		
		model.clear();
		model.put("empresas",entitymodel.listaEmpresa());
		return new ModelAndView(model, "CREACION_CREARseleccionEmpresa.hbs");
	}
	
	public ModelAndView cuentaCreada(Request req, Response res) throws IOException, ParseException{

		EntityModel entityModel = EntityModel.getInstance();
		//EntityManager em = EntityManagerHelper.getEntityManager();
		
		String nombreEmpresa = req.queryParams("empresaSeleccionaParaCuenta");
		System.out.print(req.queryParams("empresaSeleccionaParaCuenta"));
		nombreCuenta = req.queryParams("nombreCuentaNueva");
		System.out.println("Creando cuenta para: " + nombreEmpresa);
		System.out.println("Nombre de la cuenta ingresada: "+nombreCuenta);
		
		entityModel.refresh();
		nombres.clear();
		entityModel.listaCuentasDe(nombreEmpresa).stream().forEach(c -> nombres.add(c.getnombreCuenta()));
		System.out.println("nombres cuentas " + nombres);
		
		
		if(nombres.contains(nombreCuenta)){
		//no existe la cuenta entonces la creo
			respuesta = "Ya existe la cuenta " + nombreCuenta +" en la empresa seleccionada";

		}else{
			Cuenta cuenta = new Cuenta();
			nombreCuenta = nombreCuenta.replaceAll(" ", "_");
			cuenta.setNombreCuenta(nombreCuenta);
			
			Empresa empresa = entityModel.getEmpresa(nombreEmpresa);
			empresa.agregarCuenta(cuenta);
			cuenta.set_empresa(empresa);
			
			//ahora cuenta tambien es tipo elemento
			
			entityModel.createElemento(cuenta);
	
			
			respuesta= "Cuenta " + nombreCuenta + " creada.";
			
			
		}	
		
		model.put("respuesta", respuesta);
		
		
		return new ModelAndView(model, "CREACIONconfirmarCargaCuenta.hbs");
		
	}
	
	
	//Indicador
	public ModelAndView cargarIndicador(Request req, Response res) throws IOException{
		
		EntityModel entitymodel = EntityModel.getInstance();
		nombres.clear();
		entitymodel.listaCuentasNombresDeTodasLasEmpresas().stream().forEach(c-> nombres.add(c));
		ArrayList<String> cuentas = new ArrayList<String>(new HashSet<String>(nombres));
		
		model.clear();
		model.put("cuentas", cuentas);
		return new ModelAndView(model, "CREACIONcrearIndicador.hbs");
	}
	public ModelAndView indicadorCreado(Request req, Response res) throws IOException, ParseException{
		EntityModel entitymodel = EntityModel.getInstance();
		String nombreIndicador = req.queryParams("nombre");
		System.out.print("\nnombre del indicador ingresado por el usuario"+nombreIndicador);
		String formula = req.queryParams("formula");
		System.out.print("\nformula del indicador ingresado por el usuario"+formula);
		
		Indicador indicadorACrear = new Indicador();
		indicadorACrear.setNombreIndicador(nombreIndicador.replaceAll(" ", "_"));
		indicadorACrear.setFormula(formula);
		
		Formula formulaNueva = new Formula();
		indicadorACrear.setFormulaObjeto(formulaNueva);
		entitymodel.refresh();
		Usuario usuario = entitymodel.find(Usuario.class,LogInController.idUsuario);
		System.out.println("\nusuario actual " + usuario.getUser());
		indicadorACrear.setUsuario(usuario);
		nombres.clear();
		entitymodel.listaIndicadoresPorId(LogInController.idUsuario).stream().forEach(i->nombres.add(i.getNombreIndicador()));
		
		if(nombres.contains(nombreIndicador)){
			respuesta = "Indicador ya existe";
		}else{
			parser = null;
			entitymodel.refresh();
			respuesta = indicadorACrear.parsearFormula2(parser,entitymodel.listaCuentasNombresDeTodasLasEmpresas(),entitymodel);
			System.out.print("termino parsear formula2");
			nombres.add(nombreIndicador);

		
		}
		
		model.clear();
		model.put("indicadores",nombres);
		model.put("respuesta", respuesta);
		
		return new ModelAndView(model, "CREACIONindicadorCreado.hbs");
	}

	//Periodo

	public ModelAndView PeriodoSeleccionEmpresa(Request req, Response res) throws IOException{
		EntityModel entityModel = EntityModel.getInstance();
		model.clear();
		model.put("empresas", entityModel.listaEmpresa());
		return new ModelAndView(model, "CREACIONPeriodoseleccionEmpresa.hbs");
		
	}
	
	
	
	public ModelAndView PeriodoSeleccionCuenta(Request req, Response res) throws IOException{
		EntityModel entityModel = EntityModel.getInstance();
		
		String nombreEmpresa = req.queryParams("empresaSeleccionParaPeriodo");
		empresaGlobal = nombreEmpresa;
		
		System.out.println("Buscando cuentas de " + nombreEmpresa);
		nombres.clear();
		entityModel.listaCuentasDe(nombreEmpresa).stream().forEach(c-> nombres.add(c.getnombreCuenta()));
		
		model.clear();	
		model.put("cuentas",nombres);
		return new ModelAndView(model, "CREACIONPeriodoseleccionCuenta.hbs");
		
	}

	public ModelAndView cargarPeriodo(Request req, Response res) throws IOException{
		
		String nombreCuenta = req.queryParams("PeriodoCuentaSelecciona");
		System.out.println("cargando periodo para empresa: " + empresaGlobal + " en cuenta " + nombreCuenta);
		cuentaGlobal = nombreCuenta;
		model.clear();
		model.put("nombreCuenta", nombreCuenta);
		model.put("empresa", empresaGlobal);
		return new ModelAndView(model, "CREACIONcrearPeriodo.hbs");
	}
		
	@SuppressWarnings("deprecation")
	public ModelAndView periodoCreado(Request req, Response res) throws IOException, java.text.ParseException{
			
			EntityModel entityModel = EntityModel.getInstance();
			String mesInicioString = req.queryParams("mesInicio");
			System.out.print(mesInicioString);
			String mesFinalString = req.queryParams("mesFin");
			String anioInicioString = req.queryParams("FechaInicio");
			String anioFinalString = req.queryParams("FechaFin");
			SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/yyyy");
			SimpleDateFormat formatoAnio = new SimpleDateFormat("yyyy");
			String fechaInicioString;
			java.util.Date fechaInicio;
			String fechaFinalString;
			java.util.Date fechaFin;
			
			if(mesInicioString.equals("Todos")){
				
				fechaInicioString = anioInicioString;
				fechaInicio = formatoAnio.parse(fechaInicioString);
				
			}else{
				fechaInicioString= mesInicioString +"/"+anioInicioString;
				fechaInicio = formatoFecha.parse(fechaInicioString);
			}
			
			if(mesFinalString.equals("Todos")){
				fechaFinalString = anioFinalString;
				fechaFin = formatoAnio.parse(fechaFinalString);
				
			}else{
				fechaFinalString= mesFinalString +"/"+anioFinalString;
				fechaFin = formatoFecha.parse(fechaFinalString);
			}
			
		
			
			
			
		
			Periodo periodo = new Periodo();
						
//			int fechaInicio = new Integer(fechaInicioString);	
//			int fechaFinal = new Integer(fechaFinalString);
			
			
			
			
			String valorString = req.queryParams("Valor");
			int valor = Integer.parseInt(valorString);
			
			java.sql.Date sqlfechaInicio = new java.sql.Date(fechaInicio.getYear(),fechaInicio.getMonth(),1);
			java.sql.Date sqlfechaFin = new java.sql.Date(fechaFin.getYear(),fechaFin.getMonth(),1);
			periodo.setFechaFinal(sqlfechaFin);
			periodo.setFechaInicial(sqlfechaInicio);
			periodo.setValorCuenta(valor);
			//funcion que dada nombre cuenta y nombre empresa devuelva id
			//find id cuenta
			//setear cuenta
			
			entityModel.refresh();
			Empresa empresa = entityModel.getEmpresa(empresaGlobal);
			Cuenta cuenta = empresa.dameCuentaConNombre(cuentaGlobal);
			periodo.setCuenta(cuenta);
			//committ periodo. Lo hago asi porque no es un tipo elemento
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(periodo);
			em.flush();
			EntityManagerHelper.commit();
			em.close();
			
			respuesta = "Periodo cargado correctamente";
			model.clear();		
			model.put("respuesta", respuesta);
			model.put("nombreCuenta", cuentaGlobal);
			model.put("fechaInicial",fechaInicioString);
			model.put("fechaFinal",fechaFinalString);
			model.put("valorCuenta",valorString);
			//model.put("periodos", cuenta.getlistaDePeriodos());
			return new ModelAndView(model, "CREACIONconfirmarCargaPeriodo.hbs");
		}


	//Metodologia

	public ModelAndView cuentaOIndicador(Request req, Response res) throws IOException
	{
		return new ModelAndView(model,"CREACION_METODOLOGIAindicadorOcuenta.hbs");
	}

	
	public ModelAndView cargarCuentasMetodologia(Request req, Response res) throws IOException
	{
		//muestro los indicadores
		EntityModel entityModel = EntityModel.getInstance();

		String nombreMetodologia = req.queryParams("nombreMetodologia");
		ArrayList<String> cuentas = new ArrayList<String>(new HashSet<String>(entityModel.listaCuentasNombresDeTodasLasEmpresas()));
		
		model.put("cuentas",cuentas);
		model.put("nombreMetodologia",nombreMetodologia);
		return new ModelAndView(model,"CREACIONcrearMetodologiaConCuentas.hbs");
	}

	
	public ModelAndView cargarIndicadoresMetodologia(Request req, Response res) throws IOException
	{
		// indicadoresNoRepetidos = new LinkedList<Indicador>();
		//muestro los indicadores
		EntityModel entityModel = EntityModel.getInstance();

		String nombreMetodologia = req.queryParams("nombreMetodologia");
		ArrayList<Indicador>indicadoresNoRepetidos = new ArrayList<Indicador>(new HashSet<Indicador>(entityModel.listaIndicadoresPorId(LogInController.idUsuario)));
		model.put("indicadores",indicadoresNoRepetidos);
		model.put("nombreMetodologia",nombreMetodologia);
		return new ModelAndView(model,"CREACIONcrearMetodologia.hbs");
	}
	
	
	public ModelAndView crearMetodologia(Request req, Response res) throws IOException
	{		
		String nombreMetodologia = req.queryParams("nombreMetodologia");
		EntityModel entityModel = EntityModel.getInstance();
		
		if(validar(nombreMetodologia,null,null,"metodologia"))
		{
			Metodologia unaMetodologia=new Metodologia();
			nombreMetodologia = nombreMetodologia.replace(" ", "_");
			unaMetodologia.setIdUsuario(LogInController.idUsuario);
			unaMetodologia.setNombreMetodologia(nombreMetodologia);
			unaMetodologia.setT(tip);
			unaMetodologia.setCondiciones(this.condiciones);
			
			
			//unaMetodologia.setUsuario(entityModel.find(Usuario.class, LogInController.idUsuario));
			unaMetodologia.setIdUsuario(LogInController.idUsuario);
			
			
			model.put("indicadores",entityModel.listaIndicadores());
			entityModel.createElemento(unaMetodologia);
			//limpio las condiciones para la proxima carga
			this.condiciones.clear();
			
			model.put("validacion","se creo la metodologia con exito");

		}
		
		return new ModelAndView(model,"CREACIONcrearMetodologia.hbs");
	}
	
	public ModelAndView crearCondicionTaxativaCuentas(Request req, Response res) throws IOException
	{
		
		//obtengo los datos que ingreso el usuario
		String nombreCuenta = req.queryParams("CuentaTaxativa");
		String operador = req.queryParams("operadorTaxativo");
		String valor=req.queryParams("valor");
		
		//hago las validaciones
		if(validar(nombreCuenta,operador,valor,"taxativa"))
		{
			EntityModel entityModel = EntityModel.getInstance();
			model.put("cuentas",entityModel.listaCuentasNombresDeTodasLasEmpresas());
			
			
			CondicionTaxativa condicion=new CondicionTaxativa();
			condicion.setOperacion(operador);
			condicion.setCuenta(nombreCuenta);
			condicion.setValor(Double.parseDouble(valor));;
			entityModel.createElemento(condicion);	
			this.condiciones.add(condicion);
			model.put("validacion","se agrego la condicion taxativa");
		
		}
		
		
		return new ModelAndView(model,"CREACIONcrearMetodologiaConCuentas.hbs");
	}
	public ModelAndView crearCondicionDeOrdenCuentas(Request req, Response res) throws IOException
	{
		//obtengo los datos que ingreso el usuario
		String nombreCuenta = req.queryParams("CuentaOrden");
		String operador = req.queryParams("operadorOrden");
		
		//hago las validaciones
		if(validar(nombreCuenta,operador,null,"orden"))
		{
			EntityModel entityModel = EntityModel.getInstance();
			model.put("cuentas",entityModel.listaCuentasNombresDeTodasLasEmpresas());
			
			
			CondicionDeOrden condicion=new CondicionDeOrden();	
			condicion.setOperacion(operador);
			condicion.setCuenta(nombreCuenta);
			entityModel.createElemento(condicion);	
			this.condiciones.add(condicion);

			model.put("validacion","se agrego la condicion de orden");
		}
		
		return new ModelAndView(model,"CREACIONcrearMetodologiaConCuentas.hbs");
	}

	
	public ModelAndView crearCondicionTaxativa(Request req, Response res) throws IOException
	{
		
		//obtengo los datos que ingreso el usuario
		String nombreIndicador = req.queryParams("indicadorTaxativo");
		String operador = req.queryParams("operadorTaxativo");
		String valor=req.queryParams("valor");
		
		//hago las validaciones
		if(validar(nombreIndicador,operador,valor,"taxativa"))
		{
			EntityModel entityModel = EntityModel.getInstance();
			model.put("indicadores",entityModel.listaIndicadores());
			
			Indicador unIndicador=entityModel.getIndicador(nombreIndicador);	
			CondicionTaxativa condicion=new CondicionTaxativa();
			condicion.setOperacion(operador);
			condicion.setIndicador(unIndicador);
			condicion.setValor(Double.parseDouble(valor));;
			entityModel.createElemento(condicion);	
			tip=condicion.getTipo();
			this.condiciones.add(condicion);
			model.put("validacion","se agrego la condicion taxativa");
		
		}
		
		
		return new ModelAndView(model,"CREACIONcrearMetodologia.hbs");
	}
	
	public ModelAndView crearCondicionDeOrden(Request req, Response res) throws IOException
	{
		//obtengo los datos que ingreso el usuario
		String nombreIndicador = req.queryParams("indicadorOrden");
		String operador = req.queryParams("operadorOrden");
		
		//hago las validaciones
		if(validar(nombreIndicador,operador,null,"orden"))
		{
			EntityModel entityModel = EntityModel.getInstance();
			model.put("indicadores",entityModel.listaIndicadores());
			
			Indicador unIndicador=entityModel.getIndicador(nombreIndicador);
			CondicionDeOrden condicion=new CondicionDeOrden();	
			condicion.setOperacion(operador);
			tip=condicion.getTipo();
			condicion.setIndicador(unIndicador);
			entityModel.createElemento(condicion);	
			this.condiciones.add(condicion);

			model.put("validacion","se agrego la condicion de orden");
		}
		
		return new ModelAndView(model,"CREACIONcrearMetodologia.hbs");
	}
		

		
	private boolean esNumerico(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	private boolean validar(String nombreIndicador,String operador,String valor,String tipo)
	{
		//borro validaciones anteriores
		model.clear();
		boolean estado=true;
		
		if(tipo.equals("taxativa"))
		{	
			if(nombreIndicador.equals("Indicador") )
			{
				model.put("validacion","Por favor elija un indicador");
				estado=false;
			}
			else
			{
				if(operador.equals("Operador") )
				{
					model.put("validacion","Por favor elija un operador");
					estado=false;
				}
				else
				{
					if(valor.equals(""))
					{
						model.put("validacion","Por favor complete un valor");
						estado=false;
					}
					else
					{
						if(!this.esNumerico(valor))
						{
							model.put("validacion","Por favor elija un valor numerico");
							estado=false;
						}
					}
				}
			}
		}
		
		if(tipo.equals("orden"))
		{	
			if(nombreIndicador.equals("Indicador") )
			{
				model.put("validacion","Por favor elija un indicador");
				estado=false;
			}
			else
			{
				if(operador.equals("Operador") )
				{
					model.put("validacion","Por favor elija un operador");
					estado=false;
				}
				
			}
		}
		
		if(tipo.equals("metodologia"))
		{	
			if(nombreIndicador.equals("") )
			{
				model.put("validacion","Por favor elija un nombre para la metodologia");
				estado=false;
			}
		}
		
		return estado;
	}

	
	}