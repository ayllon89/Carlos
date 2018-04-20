package model;

import java.io.IOException;
import java.util.ArrayList;

import entities.Metodologia;
import entities.TipoElemento;

public class RepositorioDeMetodologias
{
	public DAOMetodologia daoMetodologia;
	
	public RepositorioDeMetodologias(DAOMetodologia dao){
		this.daoMetodologia = dao;
	}
	
	public ArrayList<TipoElemento> listaMetodologias() throws IOException{
		return this.daoMetodologia.lista();
	}
	
	public void cargarMetodologia(Metodologia metodologia) throws IOException{
		this.daoMetodologia.cargar(metodologia);
	}
}
