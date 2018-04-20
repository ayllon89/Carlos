package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import db.EntityManagerHelper;
import entities.Empresa;
import entities.Indicador;
import entities.Metodologia;
import entities.TipoElemento;

public abstract class DAOAbstract {

	public String filePath;
	public Gson myGson;
	public BufferedReader bufferToReader;
	public BufferedWriter bufferToWrite;
	private ArrayList<Empresa> empresas ;
	private ArrayList<Indicador> indicadores;
	private ArrayList<Metodologia> metodologias;
	private Empresa empresa;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	public DAOAbstract() {
		super();
		this.myGson = new Gson();
	}
	

	
	public abstract void cargar(TipoElemento tipo) throws IOException;
	
	//consultar
	 
	public abstract ArrayList<TipoElemento> lista() throws FileNotFoundException, IOException;
	//buscar
	
	public abstract Boolean buscar(ArrayList<TipoElemento> listaElementos, String nombreElementoBuscado);
	
	//transaccion
	public void transaccion(TipoElemento elemento){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		//em.persist(elemento);
		this.operacion(elemento);
		em.flush();
		EntityManagerHelper.commit();
		em.close();
	}
	
	public abstract void operacion(TipoElemento elemento);
	//Json

	public void writeJson(String cuentaSerialized) throws IOException{
		this.bufferToWrite = new BufferedWriter(new FileWriter(this.filePath, false));
		bufferToWrite.write(cuentaSerialized+"\n");
		
		this.bufferToWrite.close();
	}
	
	public String getJson() throws IOException{
		StringBuilder cuentaSerializada = new StringBuilder();
		String linea;
		while( (linea=this.bufferToReader.readLine()) != null){
			cuentaSerializada.append(linea);
		};
		this.bufferToReader.close();
		return cuentaSerializada.toString();
	}
	
	
	   
}
	


	

