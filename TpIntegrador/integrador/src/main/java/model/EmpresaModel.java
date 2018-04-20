package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import entities.*;
import interfaz.Config;


public class EmpresaModel {

	private ArrayList<TipoElemento> empresas;
	private static EmpresaModel instance = null;
	private RepositorioDeEmpresas repoEmpresas;
	private Empresa empresaBuscada;
	
	
	public static EmpresaModel getInstance() throws IOException{
		if(instance==null){
			instance = new EmpresaModel();
		}
		return instance;
	}
	
	private EmpresaModel() throws IOException {
		//this.empresas = new LinkedList<>();
		this.empresas = new ArrayList<TipoElemento>();
		this.boot();
	}
	
	public void boot() throws IOException{
		DAOEmpresa dao = new DAOEmpresa();
		dao.setFilePath(new Config("config.cfg").getEmpresas());
		repoEmpresas = new RepositorioDeEmpresas(dao);
		empresas = repoEmpresas.listaEmpresas();
		System.out.println("Se creo una nueva instancia");
		System.out.println(((Empresa) empresas.get(0)).getNombreEmpresa());
		
	}
	
	public List<Cuenta> dameCuentas(String nombreEmpresaBuscada){
		ArrayList<String> nombres = new ArrayList<String>();
		empresas.stream().forEach(empresa-> nombres.add(((Empresa) empresa).getNombreEmpresa()));
		int a = nombres.indexOf(nombreEmpresaBuscada);
		System.out.println(a);
		Empresa empresaBuscada = (Empresa) empresas.get(a);
		System.out.println(empresaBuscada);
		return empresaBuscada.getCuentas();
	}
	
	

	public Empresa get(String nombreEmpresaBuscada){
		ArrayList<String> nombres = new ArrayList<String>();
		empresas.stream().forEach(empresa-> nombres.add(((Empresa) empresa).getNombreEmpresa()));
		int a = nombres.indexOf(nombreEmpresaBuscada);
		System.out.println("Posicion de la empresa buscada:"+a);
		//System.out.println(a);
		empresaBuscada = (Empresa) empresas.get(a);
		return empresaBuscada;	
	}
	
	public ArrayList<TipoElemento> getAll(){
		return this.empresas;
	}
	public RepositorioDeEmpresas getRepositorioDeEmpresas(){
		return this.repoEmpresas;
	}
	//update
		public void actualizarListaEmpresas() throws IOException{
			this.empresas = repoEmpresas.listaEmpresas();
		}
		//	
		//controlar
	public Boolean verificiarExistencia(String empresa){
		ArrayList<String> nombresEmpresas = new ArrayList<String>();
		empresas.stream().forEach(e -> nombresEmpresas.add(((entities.Empresa) e).getNombreEmpresa()));
		Boolean valor = nombresEmpresas.contains(empresa);
		System.out.println("La empresa buscada: "+valor);
		return valor;
	}	
	
	
}
