package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entities.Cuenta;
import entities.Empresa;
import entities.Formula;
import entities.Indicador;
import entities.TipoElemento;
import interfaz.Config;
import javacc.EG1;
import javacc.ParseException;

public class IndicadorModel {
	private ArrayList<TipoElemento> indicadores;
	private static IndicadorModel instance = null;
	private Indicador indicador;
	private RepositorioDeIndicadores repoIndicadores;
	
	
	//constructor
	private IndicadorModel() throws IOException {
		this.indicadores = new ArrayList<TipoElemento>();
		//this.boot();
	}
	
	public static IndicadorModel getInstance() throws IOException{
		if(instance==null){
			instance = new IndicadorModel();
		}
		return instance;
	}
	//
	
	//configuracion
	
//	public void boot() throws IOException{
//		DAOIndicador dao = new DAOIndicador();
//		dao.setFilePath(new Config("config.cfg").getIndicadores());
//		repoIndicadores = new RepositorioDeIndicadores(dao);
//		indicadores = repoIndicadores.listaIndicadores();
//		System.out.println("Se creo una nueva instancia de indicadores");
//		System.out.println(((Indicador) indicadores.get(0)).getNombreIndicador());
//		
//	}
	//
	
	//getters
	
	public RepositorioDeIndicadores getRepositorioDeIndicadores(){
		return this.repoIndicadores;
	}
	
	public Indicador get(String nombreIndicadorBuscado){
		ArrayList<String> nombres = new ArrayList<String>();
		indicadores.stream().forEach(indicador-> nombres.add(((Indicador) indicador).getNombreIndicador()));
		nombres.forEach(i -> System.out.println(i));
		int a = nombres.indexOf(nombreIndicadorBuscado);
		System.out.println("Posicion del indicador buscada:"+a);
		//System.out.println(a);
		Indicador indicadorBuscado = (Indicador) indicadores.get(a);
		return indicadorBuscado;	
	}
	
	public ArrayList<TipoElemento> getAll(){
		return this.indicadores;
	}
	//
	
	
	//update
	public void actualizarListaIndicadores() throws IOException{
		this.indicadores = repoIndicadores.listaIndicadores();
	}
	//
	
	//controlar
	public Boolean verificiarExistencia(String indicador){
		ArrayList<String> nombresIndicadores = new ArrayList<String>();
		indicadores.stream().forEach(i -> nombresIndicadores.add(((entities.Indicador) i).getNombreIndicador()));
		Boolean valor = nombresIndicadores.contains(indicador);
		System.out.println("El indicador buscado:"+valor);
		return valor;		
	}
	//

}
