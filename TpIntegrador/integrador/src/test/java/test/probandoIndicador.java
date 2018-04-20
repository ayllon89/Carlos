package test;

 


import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.Indicador;
import entities.TipoElemento;
import interfaz.Config;
import javacc.EG1;
import model.DAOEmpresa;
import model.DAOIndicador;
import model.RepositorioDeEmpresas;
import model.RepositorioDeIndicadores;


public class probandoIndicador {

	private RepositorioDeIndicadores repoIndicadores;
	private ArrayList<TipoElemento> indicadores;
	private RepositorioDeEmpresas repoEmpresas;
	private static EG1 parser;
	
	
	@Before
	public void init() throws IOException{
		
		DAOIndicador dao = new DAOIndicador();
		DAOEmpresa dao2 = new DAOEmpresa();
		dao.setFilePath(new Config("config.cfg").getIndicadores());
		dao2.setFilePath(new Config("config.cfg").getEmpresas());
		this.repoIndicadores = new RepositorioDeIndicadores(dao);
		this.repoEmpresas = new RepositorioDeEmpresas(dao2);
	
		indicadores = repoIndicadores.listaIndicadores();
		repoEmpresas.listaEmpresas();
		
	}
		
		

	
	@Test
	public void VerificarExistenciaIndicador() throws IOException {
		 
		Indicador indicadorBuscado = new Indicador();
		indicadorBuscado.setNombreIndicador("ROE");
		Boolean resultado = repoIndicadores.buscarIndicador(indicadores, indicadorBuscado.getNombreIndicador());
		
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void CargaIndicadorSiEsteNoExiste() throws IOException{
		Indicador indicadorNuevo = new Indicador();
		indicadorNuevo.setNombreIndicador("ROE");
		String formula = "(IngresoNeto-Dividendos)/CapitalTotal";
		
		if(repoIndicadores.buscarIndicador(indicadores, indicadorNuevo.getNombreIndicador())){
			//no hacer nada
		}else{
			indicadorNuevo.parsearFormula(formula, parser);
		}
		
		Assert.assertTrue((indicadores.stream().anyMatch(indicador -> ((Indicador) indicador).getNombreIndicador().equals(indicadorNuevo.getNombreIndicador()))));
	}
	
	
	
}
