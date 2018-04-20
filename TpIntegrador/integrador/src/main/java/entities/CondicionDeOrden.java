package entities;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import com.google.gson.annotations.SerializedName;
import javacc.Calculator;
import javacc.EG1;
import javacc.ParseException;
import model.EntityModel;

@Entity
public class CondicionDeOrden extends Condicion implements TipoElemento{
	@SerializedName("Tipo")
	private String tipo="ORDEN";
	@SerializedName("Operacion")
	private String operacion;
	@SerializedName("Indicador")
	@ManyToOne(cascade = CascadeType.ALL)
	private Indicador indicador;
	private String cuenta;
	@Transient
	private static Calculator parser = null;
	@Transient
	private static EG1 eg1 = null;
	@Transient
	private List<String> nombresCuentas = new LinkedList<String>();
	@Transient
	int i=0;
	@Transient
	int j=1;
	
	public CondicionDeOrden(){	
	}
	
	
	public List<Empresa> operar(List<Empresa> empresas, Date fechaInicial, Date fechaFinal,EntityModel entityModel)  {
		//List<Empresa> resultado = new LinkedList<>();
		if(cuenta==null){
			try {
				empresas = this.filtrameEmpresas(empresas, fechaInicial, fechaFinal, entityModel);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//System.out.print("se evaluan empresas por indicador"+"\n");
		empresas = this.menorAMayor(empresas, fechaInicial, fechaFinal, entityModel);
		}else{
			empresas = empresas.stream().filter(e -> e.tenesCuentaLlamada(cuenta)).collect(Collectors.toList());
			
			//System.out.print("\n"+"Se evaluan las empresas "+ empresas.size()+" \n"+"se evaluan empresas por cuenta"+"\n");
			Collections.sort(empresas, Comparator.comparing(e -> e.dameValorCuentaEnPeriodo(cuenta, fechaInicial, fechaFinal)));
			//empresas.forEach(e ->System.out.print("\n"+e.getNombreEmpresa()+" \n"));
		}
		//System.out.print("\n"+this.operacion+"\n");
		if(this.operacion.equals("Mayor")){
			java.util.Collections.reverse(empresas);
		}
		//empresas.stream().forEach(e->System.out.print("RESULTADO ------->"+e.getNombreEmpresa()+"\n"));
//		empresas.removeAll(empresas);
//		System.out.print("RESULTADO ------->"+empresas+"\n");
//		empresas.addAll(resultado);
//		System.out.print("RESULTADO ------->"+resultado+"\n");
//		return resultado;
		return empresas;
	}
	
	
	
	
	private List<Empresa> filtrameEmpresas(List<Empresa> empresas,Date fechaInicial, Date fechaFinal,EntityModel entityModel) throws ParseException{
		nombresCuentas = indicador.dameCadaUnaDeLasCuentas(eg1, entityModel);
		return empresas.stream().filter(e -> indicador.sePuedeAplicarA2(e, fechaInicial, fechaFinal,nombresCuentas)).collect(Collectors.toList());
		
	}
	
		private List<Empresa> menorAMayor(List<Empresa> empresas,  Date fechaInicial, Date fechaFinal,EntityModel entityModel){
		
		double resultadoI, resultadoJ;
		List<Empresa> resultado = new LinkedList<>();
		System.out.print("\n" + " EN CONDICION LAS FECHAS SON"+fechaInicial+fechaFinal+"\n");
		try {
			
			nombresCuentas = indicador.dameCadaUnaDeLasCuentas(eg1, entityModel);
			System.out.print("\n" + nombresCuentas+"\n");
			 while(empresas.size()!=1){
				 
				 resultadoI = this.indicador.aplicarA2(empresas.get(i), fechaInicial, fechaFinal,parser,nombresCuentas);
				 
				resultadoJ = this.indicador.aplicarA2(empresas.get(j), fechaInicial, fechaFinal,parser,nombresCuentas);
				System.out.print("\n " +"reI y reJ "+resultadoI+resultadoJ+"\n");
				
				 if(resultadoI<resultadoJ){
					 if(i==(empresas.size()-1)){
						 if(empresas.size()>2){
							 
							 i=0;	 
						 }else{
							 resultado.add(empresas.get(i));
							 empresas.remove(i);
							 j=(empresas.size()-1);
							 i=0;
						 }
						 
					 }else{
						 j++;
					 }
				 }else{
					 if(j==(empresas.size()-1)){
						 resultado.add(empresas.get(j));
						 empresas.remove(j);
						 i=(empresas.size()-1);
						 j=0;
					 }else{
						 if(i==(empresas.size()-1)){ //u
							 resultado.add(empresas.get(j));
							 empresas.remove(j);
							 i=(empresas.size()-1);
							 j=0; 
						 }
						 else{
							 i++;
						 }
					 }
				 }
				 resultado.stream().forEach(e->System.out.print("RESULTADO -------->   "+e.getNombreEmpresa()+"\n"));
			 }
			 resultado.add(empresas.get(0));
			 empresas.remove(0);
			 
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	
	
	
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public static Calculator getParser() {
		return parser;
	}

	public static void setParser(Calculator parser) {
		CondicionDeOrden.parser = parser;
	}

	public String getTipo() {
		return tipo;
	}


	public String getCuenta() {
		return cuenta;
	}


	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
}
