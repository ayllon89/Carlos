package entities;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.JOptionPane;

import controllers.LogInController;
import db.EntityManagerHelper;
import javacc.Calculator;
import javacc.EG1;
import javacc.ParseException;
import model.EntityModel;

@Entity
@Table(name ="Indicadores")
public class Indicador implements TipoElemento {
	
	@Id
	@GeneratedValue
	private int idIndicador;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	//@JoinColumn(name="idUsuario")
	private Usuario usuario;
	//si no anda agregarle a usuario una lista de cuentas...
	
	private int idUsuario;
	private String nombreIndicador;
	private String formula;
	
	@Transient
	private Formula formulaObjeto;
	
	@Transient
	private EG1 eg1 ;
	
	@Transient
	private Calculator calculadora ;
	
	@Transient
	public static int contadorCalculadora ;
	@Transient
	public static int contadorEG1 ;
		
		
		
		
		//constructor
		
		public Indicador(){
		}
		
		//getters
		
		
		public int getIdIndicador() {
			return idIndicador;
		}
		
		public Usuario getUsuario(){
			return usuario;
		}

		public String getNombreIndicador() {
			return nombreIndicador;
		}
		
		public String getFormula() {
			return formula;
		}
		
		public Formula getFormulaObjeto() {
			return formulaObjeto;
		}
		//setters

		
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		public void setIdIndicador(int idIndicador) {
			this.idIndicador = idIndicador;
		}
		
		public void setNombreIndicador(String nombreIndicador) {
			this.nombreIndicador = nombreIndicador;
		}

		public void setFormula(String formula) {
			this.formula = formula;
		}

		public void setFormulaObjeto(Formula formulaObjeto) {
			this.formulaObjeto = formulaObjeto;
		}
		public void setidUsuario(int id) {
			this.idUsuario = id;
		}
//		public void setListaUsuarios(ArrayList<Usuario> lista){
//			this.listaUsuarios=lista;
//		}
		
		//add
//		public void addUsuario(Usuario usuario){
//			this.listaUsuarios.add(usuario);
//		}
		
		//AplicarA

//		public double aplicarA(Empresa empresa, Date fechaInicial,Date fechaFinal, Calculator parser) throws ParseException {
//			ArrayList<String> valores = new ArrayList<String>();
//			
//			//analizar si puedo aplicar o no el indicador
//			
//			if(this.sePuedeAplicarA(empresa, fechaInicial, fechaFinal)){
//				
//				//agarro lista y voy concatenando
//				
//				this.formulaObjeto.getNombreCuentas().stream().forEach(nombreCuenta -> valores.add(empresa.dameValorCuentaEnPeriodo(nombreCuenta, fechaInicial, fechaFinal)));
//				String nuevaFormula = this.getFormula();
//						for(int i=0;i<formulaObjeto.getNombreCuentas().size();i++){
//							for(int j=0;j<valores.size();j++){
//						nuevaFormula= nuevaFormula.replace(formulaObjeto.getNombreCuentas().get(i), valores.get(j));
//						 i++;
//							}
//						}
//				
//				
//				InputStream is = new ByteArrayInputStream(nuevaFormula.getBytes());
////				if(this.calculadora == null) this.calculadora = new Calculator(is);
////                else this.calculadora.ReInit(is);
//				System.out.print("contadorCalculadora:  "+contadorCalculadora);
//						if(contadorCalculadora==0) {
//							 this.calculadora = new Calculator(is);
//					        	contadorCalculadora= contadorCalculadora+1;
//					        	System.out.print("contadorCalculadora -> "+contadorCalculadora);
//					        	}
//			        
//				 else 
//					 {
//					 if(this.calculadora == null) {
//						 this.calculadora = new Calculator(is);
//					 }
//					 
//						else{
//							this.calculadora.ReInit(is);
//						}
//							
//				double resultado = this.calculadora.calculate();
//				return resultado;
//					 }}
//			else{
//				 //No se puede aplicar indicador
//				return -1;
//			}
//			return -1;
//			
//		}
//		
		
		public double aplicarA2(Empresa empresa, Date fechaInicial,Date fechaFinal, Calculator parser,List<String> nombres) throws ParseException {
			ArrayList<String> valores = new ArrayList<String>();
			System.out.print("dentro de se aplicarA2\n");
			
			//analizar si puedo aplicar o no el indicador
			
			if(this.sePuedeAplicarA2(empresa, fechaInicial, fechaFinal,nombres)){
				System.out.print("dentro de se puede aplicarA2\n");
				//agarro lista y voy concatenando
				
				System.out.print("NOMBRES"+nombres);
				//nombres.clear();
				nombres.stream().forEach(nombreCuenta -> valores.add(empresa.dameValorCuentaEnPeriodo(nombreCuenta, fechaInicial, fechaFinal)));
					if(valores.contains("-1")){
					return -1;
					}
				else{
				System.out.print(nombres);
				String nuevaFormula = this.getFormula();
				
								for(int i=0;i<nombres.size();i++){
								for(int j=0;j<valores.size();j++){
							nuevaFormula= nuevaFormula.replaceFirst(nombres.get(i), valores.get(j));
							System.out.print("\nnomnbre de la cuenta que se esta cambiando "+nombres.get(i));
							System.out.print("\nvalores de la cuenta que se esta cambiando "+valores.get(j));
							System.out.print("\nnueva foomula "+nuevaFormula);
							 i++;
								}
//								System.out.print("dentro de se puede aplicarA2\n me tiro  false \n");
							}
				
				System.out.print("\nnueva Formula "+nuevaFormula);
				InputStream is = new ByteArrayInputStream(nuevaFormula.getBytes());
//				if(this.calculadora == null) this.calculadora = new Calculator(is);
//                else this.calculadora.ReInit(is);
//				
				
				 if(contadorCalculadora==0) {
					 
					 this.calculadora = new Calculator(is);
			        	contadorCalculadora= contadorCalculadora+1;
			        	System.out.print("\n"+"contadorCalculadora -> "+contadorCalculadora+"\n");
			        	}
			        
				 else {
					 if(this.calculadora==null){
						 this.calculadora = new Calculator(is);
					 }else{
					 this.calculadora.ReInit(is);
					 //this.calculadora = new Calculator(is);
					 }}
					
				double resultado = this.calculadora.calculate();
				return resultado;
				}
			
				 //No se puede aplicar indicador
				
			}
			return -1;
				}
			
		
		public Boolean sePuedeAplicarA2(Empresa empresa ,Date fechaInicial,Date fechaFinal,List<String> nombres){
			
			return nombres.stream().allMatch(cuenta -> empresa.tenesCuentaYValor(cuenta,fechaInicial,fechaFinal));
			
		}
		
//		public Boolean sePuedeAplicarA(Empresa empresa ,Date fechaInicial,Date fechaFinal){
//			
//			return formulaObjeto.getNombreCuentas().stream().allMatch(cuenta -> empresa.tenesCuentaYValor(cuenta,fechaInicial,fechaFinal));
//			
//		}
//		
//		//contadores
//		
//		public static  void reiniciarEg1(){
//			contadorEG1 = 0;
//		}
//		public void reiniciarCalculadora(){
//			this.contadorCalculadora = 0;
//		}
		
		
		
		//Parser
		
		public String parsearFormula2(EG1 parser,List<String> list,EntityModel em) throws ParseException{
			// Put parens around sentence so that parser knows scope
	        formula = "(" + formula + ")";
	        InputStream is = new ByteArrayInputStream(formula.getBytes());
	        System.out.print("\ncontadorEg1 "+contadorEG1);
	        
//	        if(this.eg1 == null) {
//	        	System.out.print("parser es null");
//	        	this.eg1 = new EG1(is);
//	        	}
//	        
//	        else{EG1.ReInit(is);}
	        if(contadorEG1==0) {
	        	this.eg1= new EG1(is);
	        	contadorEG1 =contadorEG1+1;
	        	System.out.print("\ncontadorEG1 -> "+contadorEG1);
	        	}
	        	else
	        	{
	        		EG1.ReInit(is);
	        	}
				switch (EG1.start())
				  {
				    case 0 :
				    	
				    	Formula formulaObjeto = new Formula();
				    	formulaObjeto.setNombreCuentas(EG1.cuenta());
				    	System.out.print("\ncuentas: " + formulaObjeto.getNombreCuentas());
				    	this.setFormulaObjeto(formulaObjeto);
				    	if(this.coinciden(list,formulaObjeto)==0){
				    		return "Cuentas ingresadas no se encuentran en la base de datos."
				    				+ "Verifique que los nombres esten escritos igual a los de la tabla";
				    	}
				    	else
				    	{
				    		Usuario usuario=em.find(Usuario.class, LogInController.idUsuario);
				    		usuario.addIndicador(this);
				    		this.setUsuario(usuario);
				    		
				    		this.setidUsuario(LogInController.idUsuario);
				    		System.out.print("el id usuario es "+LogInController.idUsuario);
				    		System.out.println("Dentro de parsearFormula2 el usuario actual es: "+LogInController.idUsuario);
				    		
			    			EntityManagerHelper.beginTransaction();
			    			EntityManager ayudante = EntityManagerHelper.getEntityManager();
			    			ayudante.persist(this);
			    			ayudante.flush();
			    			EntityManagerHelper.commit();
			    			ayudante.close();
			    			
			    			EntityManagerHelper.beginTransaction();
			    			EntityManager ayudante2 = EntityManagerHelper.getEntityManager();
			    			ayudante2.merge(usuario);
			    			ayudante2.flush();
			    			EntityManagerHelper.commit();
			    			ayudante2.close();
				    		return "Indicador creado";
				    	}
				    	
				    	
				    	default :
				    break;
				  
			} 
			return "Error. Comience de nuevo por favor";
	          }
		
		
		public List<String> dameCadaUnaDeLasCuentas(EG1 parser,EntityModel em) throws ParseException{
			// Put parens around sentence so that parser knows scope
	        this.formula = "(" + this.formula + ")";
	        InputStream is = new ByteArrayInputStream(this.formula.getBytes());
	        //System.out.print("\n dentro de damecadaunadelascuentas la formula es"+this.formula);
	        
	        
//	        if(this.eg1 == null) {
//	        	this.eg1 = new EG1(is);
//	        	}
//	        
//	        else{EG1.ReInit(is);}
	        
	        if(contadorEG1==0) {
	        	this.eg1= new EG1(is);
	        	contadorEG1 =contadorEG1+1;
	        	//System.out.print("contadorEG1 -> "+contadorEG1);
	        	}
	        
	        else{EG1.ReInit(is);}
			switch (EG1.start())
				  {
				    case 0 :
				    	Formula formulaObjeto = new Formula();
				    	formulaObjeto.setNombreCuentas(EG1.cuenta());
				    	return formulaObjeto.getNombreCuentas();
				    default :
				    break;
				  }
				return formulaObjeto.getNombreCuentas();
			
		}
	        
	        

		
		public int coinciden(List<String> nombreCuentas,Formula formulaObjeto){
			ArrayList<Boolean> bool = new ArrayList<Boolean>(); 
			formulaObjeto.getNombreCuentas().stream().forEach(nombre -> bool.add(nombreCuentas.contains(nombre)));
			System.out.print("booleanos "+bool);
			if(bool.stream().anyMatch(c -> c==false)){
				return 0;
			}return 1;
			
		
		}

	
		
		public void parsearFormula(String formula,EG1 parser){

	        // Put parens around sentence so that parser knows scope
	        formula = "(" + formula + ")";
	        InputStream is = new ByteArrayInputStream(formula.getBytes());
	        
//	        if(this.eg1 == null) {
//	        	this.eg1= new EG1(is);
//	        	}
//	        
//	        else{EG1.ReInit(is);}
	        if(contadorEG1==0) {
	        	this.eg1= new EG1(is);

	        	contadorEG1 =contadorEG1+1;
	        	System.out.print("contadorEG1 -> "+contadorEG1);
	        	}
	        
	        else{EG1.ReInit(is);}
			
	        try
	        {
	          switch (EG1.start())
	          {
	            case 0 :
	            	this.setFormula(formula);
	            	Formula formulaObjeto = new Formula();
	            	formulaObjeto.setNombreCuentas(EG1.cuenta());
	            	this.setFormulaObjeto(formulaObjeto);

	    			EntityManagerHelper.beginTransaction();
	    			EntityManager ayudante = EntityManagerHelper.getEntityManager();
	    			ayudante.persist(this);
	    			ayudante.flush();
	    			EntityManagerHelper.commit();
	    			ayudante.close();
	            	
	            	
	            break;
	            default :
	            break;
	          }
	        }
	        catch (Exception e)
	        {
	         JOptionPane.showMessageDialog(null, "Error al ingresar formula, vuelva a intentarlo");
	        }
	        catch (Error e)
	        {
	        JOptionPane.showMessageDialog(null, "Error al ingresar formula, vuelva a intentarlo");
	        }
	        finally
	        {
	          
	        }
		}



		
		
		
}
