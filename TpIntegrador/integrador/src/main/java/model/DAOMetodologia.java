package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import entities.Condicion;
import entities.CondicionDeOrden;
//import entities.CondicionDeserializador;
import entities.CondicionTaxativa;
import entities.Metodologia;
import entities.TipoElemento;

public class DAOMetodologia extends DAOAbstract
{
	
	
	private ArrayList<TipoElemento> metodologias;
	//public void aplicarA(Empresa empresa,Periodo periodo);
	
	public DAOMetodologia(){
		//this.myGson  = new GsonBuilder().registerTypeAdapter(Condicion.class,new CondicionDeserializador()).create();
	}
	
	
	@Override
	public void cargar(TipoElemento tipo) throws IOException {
	this.metodologias = this.lista();
	this.metodologias.add(tipo);
	String metodologiasSerializadas = myGson.toJson(metodologias.toArray());
	this.writeJson(metodologiasSerializadas);
	}
		
	

	@Override
	public ArrayList<TipoElemento> lista() throws IOException {
	FileReader reader = new	FileReader(this.filePath);
	this.bufferToReader = new BufferedReader(reader);
	ArrayList<TipoElemento> listaDeMetodologias = this.myGson.fromJson(getJson(), new TypeToken<ArrayList<Metodologia>>(){}.getType());
	if(listaDeMetodologias==null)
		listaDeMetodologias=new ArrayList<TipoElemento>();
	return listaDeMetodologias;
	}

	@Override
	public Boolean buscar(ArrayList<TipoElemento> listaElementos, String nombreElementoBuscado) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void operacion(TipoElemento elemento) {
		// TODO Auto-generated method stub
		
	}
	
}