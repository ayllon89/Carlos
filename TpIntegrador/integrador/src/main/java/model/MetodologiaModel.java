//package model;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.swing.JOptionPane;
//
//import entities.Cuenta;
//import entities.Empresa;
//import entities.Formula;
//import entities.Indicador;
//import entities.TipoElemento;
//import entities.Metodologia;
//import interfaz.Config;
//import javacc.EG1;
//import javacc.ParseException;
//
//public class MetodologiaModel {
//	private ArrayList<TipoElemento> metodologias;
//	private static MetodologiaModel instance = null;
//	private Metodologia metodologia;
//	private RepositorioDeMetodologias repoMetodologias;
//	
//	
//	//constructor
//	private MetodologiaModel() throws IOException {
//		this.metodologias = new ArrayList<TipoElemento>();
//		this.boot();
//	}
//	
//	public static MetodologiaModel getInstance() throws IOException{
//		if(instance==null){
//			instance = new MetodologiaModel();
//		}
//		return instance;
//	}
//	//
//	
//	//configuracion
//	
//	public void boot() throws IOException{
//		DAOMetodologia dao = new DAOMetodologia();
//		dao.setFilePath(new Config("config.cfg").getMetodologias());
//		repoMetodologias = new RepositorioDeMetodologias(dao);
//		metodologias = repoMetodologias.listaMetodologias();
//		System.out.println("Se creo una nueva instancia de indicadores");
//		System.out.println(((Metodologia) metodologias.get(0)).getNombre());
//		
//	}
//	//
//	
//	//getters
//	
//	public RepositorioDeMetodologias getRepositorioDeIndicadores(){
//		return this.repoMetodologias;
//	}
//	
//	public Metodologia get(String nombreMetodologiaBuscada){
//		ArrayList<String> nombres = new ArrayList<String>();
//		metodologias.stream().forEach(metodologia-> nombres.add(((Metodologia) metodologia).getNombre()));
//		nombres.forEach(i -> System.out.println(i));
//		int a = nombres.indexOf(nombreMetodologiaBuscada);
//		System.out.println("Posicion de la metodolgia buscada:"+a);
//		//System.out.println(a);
//		Metodologia metodologiaBuscada = (Metodologia) metodologias.get(a);
//		return metodologiaBuscada;	
//	}
//	
//	public ArrayList<TipoElemento> getAll(){
//		return this.metodologias;
//	}
//	//
//	
//	
//	//update
//	public void actualizarListaMetodologias() throws IOException{
//		this.metodologias = repoMetodologias.listaMetodologias();
//	}
//	//
//	
//	//controlar
//	public Boolean verificiarExistencia(String metodologia){
//		ArrayList<String> nombresMetodologias = new ArrayList<String>();
//		metodologias.stream().forEach(i -> nombresMetodologias.add(((entities.Metodologia) i).getNombre()));
//		Boolean valor = nombresMetodologias.contains(metodologia);
//		System.out.println("La metodologia buscada:"+valor);
//		return valor;		
//	}
//	//
//
//}
