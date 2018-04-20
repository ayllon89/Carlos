package model;

import java.io.IOException ;
import java.util.ArrayList;
import java.util.List;

import entities.Cuenta;
import entities.Empresa;
import entities.TipoElemento;

public class RepositorioDeEmpresas {

	public DAOEmpresa daoEmpresa;

	public RepositorioDeEmpresas(DAOEmpresa dao){
		this.daoEmpresa = dao;
	}

	
	public ArrayList<TipoElemento> listaEmpresas() throws IOException{
		return this.daoEmpresa.lista();
	}

	public void cargarCuenta(String empresa,Cuenta nuevaCuenta) throws IOException{
		this.daoEmpresa.cargarCuenta(empresa, nuevaCuenta);
	}
	public List<Cuenta> consultarCuentas(Empresa empresa) throws IOException{
		return this.daoEmpresa.consultarCuentas(empresa);
	}
	
	public void cargarEmpresa(Empresa empresa) throws IOException{
		this.daoEmpresa.cargar(empresa);
	}
	public Boolean buscarEmpresa(ArrayList<TipoElemento> empresas, String empresaBuscada){
		return this.daoEmpresa.buscar(empresas, empresaBuscada);
	}
	

	/*public void update(Cuenta nuevaCuenta) throws IOException{
		this.daoCuenta.update(nuevaCuenta);
	}*/
	/*public void delete(Cuenta nuevaCuenta) throws IOException{
	this.daoCuenta.delete(nuevaCuenta);
	}*/

	/*public ArrayList<Cuenta> consultarCuentas() throws IOException{
	return this.daoCuenta.consultarCuentas();
	}*/
	
}