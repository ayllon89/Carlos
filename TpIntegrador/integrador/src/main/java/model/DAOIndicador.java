package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import entities.Indicador;
import entities.TipoElemento;

public class DAOIndicador extends DAOAbstract{
	
	private ArrayList<TipoElemento> indicadores;
	
	public DAOIndicador(){
		
	}
	
	@Override
	public void cargar(TipoElemento elemento) throws IOException{
	this.indicadores = this.lista();
	this.indicadores.add(elemento);
	String indicadoresSerializados = myGson.toJson(indicadores.toArray());
	this.writeJson(indicadoresSerializados);
	}
	
	@Override
	public ArrayList<TipoElemento> lista() throws JsonSyntaxException, IOException{
	FileReader reader = new	FileReader(this.filePath);
	this.bufferToReader = new BufferedReader(reader);
	ArrayList<TipoElemento> listaDeIndicadores = this.myGson.fromJson(getJson(), new TypeToken<ArrayList<Indicador>>(){}.getType());
	return listaDeIndicadores;
	}

	@Override
	public Boolean buscar(ArrayList<TipoElemento> listaElementos, String nombreElementoBuscado) {
		Boolean resultadoBusqueda = listaElementos.stream().anyMatch(indicador -> ((Indicador) indicador).getNombreIndicador().equals(nombreElementoBuscado));
		return resultadoBusqueda;
	}

	@Override
	public void operacion(TipoElemento elemento) {
		// TODO Auto-generated method stub
		
	}
	
}
