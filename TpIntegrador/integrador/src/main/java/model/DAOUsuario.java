package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

import entities.Empresa;
import entities.TipoElemento;
import entities.Usuario;

public class DAOUsuario extends DAOAbstract{

	private ArrayList<TipoElemento> usuarios;
	
	
	public DAOUsuario(){
			
		}
	@Override
	public void cargar(TipoElemento tipo) throws IOException {
		this.usuarios = this.lista();
		this.usuarios.add(tipo);
		String cuentasSerializadas = myGson.toJson(usuarios.toArray());
		this.writeJson(cuentasSerializadas);
		
	}

	@Override
	public ArrayList<TipoElemento> lista() throws IOException {
		FileReader reader = new	FileReader(this.filePath);
		this.bufferToReader = new BufferedReader(reader);
		ArrayList<TipoElemento> listaDeUsuarios = this.myGson.fromJson(getJson(), new TypeToken<ArrayList<Usuario>>(){}.getType());
		return listaDeUsuarios;
	}

	@Override
	public Boolean buscar(ArrayList<TipoElemento> listaElementos, String nombreElementoBuscado) {
		Boolean resultadoBusqueda = listaElementos.stream().anyMatch(empresa -> ((Empresa) empresa).getNombreEmpresa().equals(nombreElementoBuscado));
		return resultadoBusqueda;
	}
	@Override
	public void operacion(TipoElemento elemento) {
		// TODO Auto-generated method stub
		
	}

}
