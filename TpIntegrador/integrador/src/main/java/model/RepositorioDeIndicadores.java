package model;

import java.io.IOException;
import java.util.ArrayList;

import entities.Indicador;
import entities.TipoElemento;

public class RepositorioDeIndicadores {

	public DAOIndicador daoIndicador;

	public RepositorioDeIndicadores(DAOIndicador dao){
		this.daoIndicador = dao;
	}
	
	public void cargarIndicador(Indicador indicador) throws IOException{
		this.daoIndicador.cargar(indicador);
	}
	public ArrayList<TipoElemento> listaIndicadores() throws IOException{
		return this.daoIndicador.lista();
	}
	
	public Boolean buscarIndicador(ArrayList<TipoElemento> indicadores, String nombreIndicadorBuscado){
		return this.daoIndicador.buscar(indicadores, nombreIndicadorBuscado);
	}
	

}
