package entities;

import java.sql.Date;
import java.util.LinkedList;
//import java.util.ArrayList;
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
public class CondicionTaxativa extends Condicion implements TipoElemento {
	@SerializedName("Tipo")
	private String tipo="TAXATIVA";
	@SerializedName("Operacion")
	private String operacion;
	@SerializedName("Indicador")
	@ManyToOne(cascade = CascadeType.ALL)
	private Indicador indicador;
	@SerializedName("Cuenta")
	private String cuenta;				// OJO QUE ES UN STRING
	@SerializedName("Valor")
	private double valor;
	@Transient
	private static Calculator parser = null;
	@Transient
	private static EG1 parser2 = null;
	
	public CondicionTaxativa(){
	}
	
	
	public List<Empresa> operar(List<Empresa> empresas,Date fechaInicial, Date fechaFinal,EntityModel em) {
		List<Empresa> resultado = new LinkedList<>();
		if(cuenta==null){
		resultado=empresas.stream().filter(x->aplicarCondicion(fechaInicial, fechaFinal,x,em)).collect(Collectors.toList());
		}else{
		resultado=empresas.stream().filter(x->aplicarCondicionCuenta(fechaInicial, fechaFinal,x,em)).collect(Collectors.toList());
		}
		empresas.removeAll(empresas);
		empresas.addAll(resultado);
		return empresas;
	}
	
	private boolean aplicarCondicionCuenta(Date fechaInicial, Date fechaFinal, Empresa empresa,EntityModel em){
		double resultado = 0;
		
			resultado = new Double(empresa.dameValorCuentaEnPeriodo(cuenta, fechaInicial, fechaFinal));
			if(resultado != -1){
				Boolean valor = new OperacionCreador().crearOperacion(this.operacion).operar(resultado, this.valor);
				System.out.print("booleano "+valor+"\n");
				return valor;	
			}else{
				return false;
			}
		}


	private boolean aplicarCondicion(Date fechaInicial, Date fechaFinal, Empresa empresa,EntityModel em){
		double resultado = 0;
		try {
			List<String> nombreCuentas= new LinkedList<String>();
			nombreCuentas = indicador.dameCadaUnaDeLasCuentas(parser2, em);
			resultado = this.indicador.aplicarA2(empresa, fechaInicial, fechaFinal,parser,nombreCuentas);
			System.out.print("resultado es ->"+resultado+"\n");
			System.out.print("MI OPERACION ES "+this.operacion+"\n");
			if(resultado != -1){
				Boolean valor = new OperacionCreador().crearOperacion(this.operacion).operar(resultado, this.valor);
				System.out.print("booleano "+valor+"\n");
				return valor;	
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("\n"+"No se pudo aplicar la condicion por un error al aplicar el indicador "+"\n");
			return false;
			}
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public static Calculator getParser() {
		return parser;
	}

	public static void setParser(Calculator parser) {
		CondicionTaxativa.parser = parser;
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
