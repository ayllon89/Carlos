package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import entities.Empresa;
import entities.Metodologia;
import model.EntityModel;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EvaluarMetodologiaController {
	
	private Map<String, Object> model=new HashMap<>();
	private ArrayList<Date> fechasInicialesRepetidas = new ArrayList<Date>();
	private ArrayList<Date> fechasFinalesRepetidas = new ArrayList<Date>();
	private Date fechaInicial;
	private Date fechaFinal;
	private List<String> nombres = new LinkedList<String>();
	private Boolean booleano;
	
	
	
	
		public ModelAndView seleccionarMetodologia(Request req, Response res) throws IOException{
		
		EntityModel em = EntityModel.getInstance();
		
		//periodos
		
		
		fechasInicialesRepetidas.clear();
		fechasFinalesRepetidas.clear();
		nombres.clear();
		
		em.listaPeriodos().stream().forEach(periodo -> fechasInicialesRepetidas.add(periodo.getFechaInicial()));
		em.listaPeriodos().stream().forEach(periodo -> fechasFinalesRepetidas.add(periodo.getFechaFinal()));
		
		
		ArrayList<Date>fechasIniciales = new ArrayList<Date>(new HashSet<Date>(fechasInicialesRepetidas));//Asi no aparecen duplicados los años
		ArrayList<Date>fechasFinales = new ArrayList<Date>(new HashSet<Date>(fechasFinalesRepetidas));
		em.listaMetodologiasPorId(LogInController.idUsuario).stream().forEach(m -> nombres.add(m.getNombreMetodologia()));
		nombres.stream().forEach(n -> n.replaceAll(" ","_"));
		
		model.clear();
		model.put("metodologias",nombres);
		model.put("fechasIniciales", fechasIniciales);
		model.put("fechasFinales", fechasFinales);
		return new ModelAndView(model, "EVALUAR_METODOLOGIAseleccionar_Metodologia.hbs");
	}
		@SuppressWarnings("deprecation")
		public ModelAndView AplicarMetodologia(Request req, Response res) throws IOException, ParseException{
			
		EntityModel em = EntityModel.getInstance();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String metodologiaString = req.queryParams("metodologia_Buscada");
		System.out.print("metodologia buscada "+metodologiaString);
		String fechaInicialString = req.queryParams("fechaInicial");
		String fechaFinalString = req.queryParams("fechaFinal");
		
		Metodologia metodologia = em.getMetodologia(metodologiaString);
		fechaInicial = formatoFecha.parse(fechaInicialString);
		fechaFinal = formatoFecha.parse(fechaFinalString);
		
		java.sql.Date sqlfechaInicio = new java.sql.Date(fechaInicial.getYear(),fechaInicial.getMonth(),1);
		java.sql.Date sqlfechaFin = new java.sql.Date(fechaFinal.getYear(),fechaFinal.getMonth(),1);
		
		em.listaEmpresa().stream().forEach(e -> System.out.print(e.getNombreEmpresa()+"\n"));
		List<Empresa> resultado = metodologia.aplicarMetodologia(em.listaEmpresa(), sqlfechaInicio,sqlfechaFin,em);
		System.out.print("termina metodo -> Aplicar metodologia \n");
		if(resultado.size()==0){
			booleano = true;
		}else{
			booleano = false;
		}
		
		System.out.print(resultado.size()+"\n");
		System.out.print(resultado);
		model.clear();
		model.put("resultado", resultado);
		model.put("nombreMetodologia", metodologiaString);
		model.put("tamano", booleano);
		return new ModelAndView(model, "EVALUAR_METODOLOGIAevaluarMetodologia.hbs");
			
			
		}
		

}
