
package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import entities.Cuenta;
import entities.Empresa;
import entities.TipoElemento;

public class DAOEmpresa extends DAOAbstract {

	public Empresa empresa;
	public ArrayList<TipoElemento> empresas;
	
	public DAOEmpresa(){
		
	}
	
	public List<Cuenta> consultarCuentas(Empresa empresa) throws IOException{
	List<Cuenta> listaDeCuentas = empresa.getCuentas();
	return listaDeCuentas;
    }
	 

	public void cargarCuenta(String nombreEmpresa,Cuenta nuevaCuenta) throws IOException{
	this.empresas = this.lista();
	ArrayList<String> nombres = new ArrayList<String>();
	empresas.stream().forEach(e -> nombres.add(((Empresa) e).getNombreEmpresa()));
	int index = nombres.indexOf(nombreEmpresa);
	((Empresa) empresas.get(index)).getCuentas().add(nuevaCuenta);
	String cuentasSerializadas = myGson.toJson(empresas.toArray());
	this.writeJson(cuentasSerializadas);		
	}
	
//	public void cargarPeriodo(String nombreEmpresa,String nombreCuenta, Periodo periodo){
//		this.empresas = this.lista();
//		ArrayList<String> nombres = new ArrayList<String>();
//		empresas.stream().forEach(e -> nombres.add(((Empresa) e).getNombreEmpresa()));
//		int index = nombres.indexOf(nombreEmpresa);
//		
//		
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		this.consultarCuentas((Empresa) empresas.get(index)).stream().forEach(c -> nombresCuentas.add(c.getnombreCuenta()));
//		int indexcuenta = nombresCuentas.indexOf(nombreCuenta);
//		
//		
//	}
	
	@Override
	public ArrayList<TipoElemento> lista() throws IOException{
	FileReader reader = new	FileReader(this.filePath);
	this.bufferToReader = new BufferedReader(reader);
	ArrayList<TipoElemento> listaDeEmpresas = this.myGson.fromJson(getJson(), new TypeToken<ArrayList<Empresa>>(){}.getType());
	return listaDeEmpresas;
	}

	@Override
	public void cargar(TipoElemento tipo) throws IOException {
	this.empresas = this.lista();
	this.empresas.add(tipo);
	String cuentasSerializadas = myGson.toJson(empresas.toArray());
	this.writeJson(cuentasSerializadas);
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