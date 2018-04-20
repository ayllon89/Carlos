//package test;
//
//import static org.junit.Assert.*;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Calendar;
////import java.util.Date;
//import java.sql.Date;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.swing.JOptionPane;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.xml.sax.helpers.ParserAdapter;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//
//import db.EntityManagerHelper;
//import entities.*;
//import interfaz.Config;
//import model.*;
//import javacc.*;
//
//public class TestMetodologias {
//	public ArrayList<Empresa> empresas;
//	public Metodologia metodologia;
//	Date fechaSiete = java.sql.Date.valueOf("2017-1-1");
//	Date fechaSeis = java.sql.Date.valueOf("2016-1-1");
//	
//	@Before
//	public void init() throws IOException{
//		//Calendar calendario = Calendar.getInstance();
//		//calendario.set(2017, 0, 1, 0, 0);  
//		//calendario.set(2016, 0, 1, 0, 0);  
//		
//		////////////////////////////////////////////////////////////
//		Empresa empresaA= new Empresa();
//		empresaA.setNombreEmpresa("Facebook");
//		Empresa empresaC= new Empresa();
//		empresaC.setNombreEmpresa("Metroflog");
//		Empresa empresaB= new Empresa();
//		empresaB.setNombreEmpresa("Fotolog");
//		
//		////////////////////////////////////////////////////////////
//		Periodo periodoA = new Periodo();
//		periodoA.setFechaInicial(fechaSeis);
//		periodoA.setFechaFinal(fechaSiete);
//		periodoA.setValorCuenta(50000);
//		ArrayList<Periodo> periodosA = new ArrayList<Periodo>();
//		periodosA.add(periodoA);
//		
//		Cuenta cuentaA = new Cuenta();
//		cuentaA.setNombreCuenta("EBDTA");
//		cuentaA.setlistaDePeriodos(periodosA);
//		ArrayList<Cuenta> cuentasA = new ArrayList<Cuenta>();
//		cuentasA.add(cuentaA);
//		
//		empresaA.setListaDeCuentas(cuentasA);
//		/////////////////////////////////////////////////////////////
//		Periodo periodoB = new Periodo();
//		periodoB.setFechaInicial(fechaSeis);
//		periodoB.setFechaFinal(fechaSiete);
//		periodoB.setValorCuenta(40000);
//		ArrayList<Periodo> periodosB = new ArrayList<Periodo>();
//		periodosB.add(periodoB);
//		
//		Cuenta cuentaB = new Cuenta();
//		cuentaB.setNombreCuenta("EBDTA");
//		cuentaB.setlistaDePeriodos(periodosB);
//		ArrayList<Cuenta> cuentasB = new ArrayList<Cuenta>();
//		cuentasB.add(cuentaB);
//		
//		empresaB.setListaDeCuentas(cuentasB);
//		/////////////////////////////////////////////////////////////
//		Periodo periodoC = new Periodo();
//		periodoC.setFechaInicial(fechaSeis);
//		periodoC.setFechaFinal(fechaSiete);
//		periodoC.setValorCuenta(30000);
//		ArrayList<Periodo> periodosC = new ArrayList<Periodo>();
//		periodosC.add(periodoC);
//		
//		Cuenta cuentaC = new Cuenta();
//		cuentaC.setNombreCuenta("EBDTA");
//		cuentaC.setlistaDePeriodos(periodosC);
//		ArrayList<Cuenta> cuentasC = new ArrayList<Cuenta>();
//		cuentasC.add(cuentaC);
//		
//		empresaC.setListaDeCuentas(cuentasC);
//		/////////////////////////////////////////////////////////////
//		this.empresas=new ArrayList<Empresa>();
//		this.empresas.add(empresaA);
//		this.empresas.add(empresaC);
//		this.empresas.add(empresaB);
//		
//	}
//	
//	@Test
//	public void testMayorA() {
//		Metodologia metodologia = new Metodologia();
//		
//		Indicador indicador = new Indicador();
//		indicador.setNombreIndicador("Indicadorsito");
//		indicador.setFormula("(EBDTA+2)");
//		
//		Formula formula = new Formula();
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		nombresCuentas.add("EBDTA");
//		ArrayList<String> operadores = new ArrayList<String>();
//		operadores.add("+");
//		formula.setNombreCuentas(nombresCuentas);
//		//formula.setOperadores(operadores);
//		indicador.setFormulaObjeto(formula);
//		
//		CondicionTaxativa condicion1 = new CondicionTaxativa();
//		condicion1.setOperacion(">");
//		condicion1.setIndicador(indicador);
//		condicion1.setValor(49999);
//		 
//		metodologia.agregarCondicion(condicion1);
//		Periodo periodo = new Periodo();
//		periodo.setFechaInicial(fechaSeis);
//		periodo.setFechaFinal(fechaSiete);
//		periodo.setValorCuenta(0);
//		
//		List<Empresa> resultado = metodologia.aplicarMetodologia(empresas, periodo.getFechaInicial(),periodo.getFechaFinal(), null);
//		System.out.println(resultado.size());
//		assertTrue(resultado.size()==1);
//	}
//	
//	@Test
//	public void testOrdenar() {
//		Metodologia metodologia = new Metodologia();
//		
//		Indicador indicador = new Indicador();
//		indicador.setNombreIndicador("Indicadorsito");
//		indicador.setFormula("(EBDTA+2)");
//		
//		Formula formula = new Formula();
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		nombresCuentas.add("EBDTA");
//		ArrayList<String> operadores = new ArrayList<String>();
//		operadores.add("+");
//		formula.setNombreCuentas(nombresCuentas);
//		//formula.setOperadores(operadores);
//		indicador.setFormulaObjeto(formula);
//		
//		
//		
//		
//		CondicionDeOrden condicion1 = new CondicionDeOrden();
//		condicion1.setOperacion(">");
//		condicion1.setIndicador(indicador);
//		condicion1.setPeso(49999);
//		metodologia.agregarCondicion(condicion1);
//		Periodo periodo = new Periodo();
//		periodo.setFechaInicial(fechaSeis);
//		periodo.setFechaFinal(fechaSiete);
//		periodo.setValorCuenta(0);
//		
//		List<Empresa> resultado = metodologia.aplicarMetodologia(empresas, periodo.getFechaInicial(),periodo.getFechaFinal(), null);
//		System.out.println(resultado.size());
//		assertTrue(resultado.get(0).getNombreEmpresa()=="Facebook" &&
//				resultado.get(1).getNombreEmpresa()=="Fotolog" &&
//				resultado.get(2).getNombreEmpresa()=="Metroflog");
//	}
//	
//	@Test
//	public void testCombinado() {
//		Metodologia metodologia = new Metodologia();
//		
//		Indicador indicador = new Indicador();
//		indicador.setNombreIndicador("Indicadorsito");
//		indicador.setFormula("(EBDTA+2)");
//		
//		Formula formula = new Formula();
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		nombresCuentas.add("EBDTA");
//		ArrayList<String> operadores = new ArrayList<String>();
//		operadores.add("+");
//		formula.setNombreCuentas(nombresCuentas);
//
//		indicador.setFormulaObjeto(formula);
//		
//		CondicionTaxativa condicion1 = new CondicionTaxativa();
//		condicion1.setOperacion(">");
//		condicion1.setIndicador(indicador);
//		condicion1.setValor(35000);
//		 
//		CondicionDeOrden condicion2 = new CondicionDeOrden();
//		condicion2.setOperacion(">");
//		condicion2.setIndicador(indicador);
//		condicion2.setPeso(49999);
//		metodologia.agregarCondicion(condicion1);
//		metodologia.agregarCondicion(condicion2);
//		Periodo periodo = new Periodo();
//		periodo.setFechaInicial(fechaSeis);
//		periodo.setFechaFinal(fechaSiete);
//		periodo.setValorCuenta(0);
//		
//		List<Empresa> resultado = metodologia.aplicarMetodologia(empresas, periodo.getFechaInicial(),periodo.getFechaFinal(), null);
//		System.out.println(resultado.size());
//		assertTrue(resultado.get(0).getNombreEmpresa()=="Facebook" &&
//				resultado.get(1).getNombreEmpresa()=="Fotolog" &&
//				resultado.size()==2);
//	}
//	/*
//	@Test
//	public void testPersistirMetodologia(){
//		Metodologia metodologia = new Metodologia();
//	
//		metodologia.setNombre("Metodologia1");
//		
//		Indicador indicador = new Indicador("Indicadorsito");
//		indicador.setFormula("(EBDTA+2)");
//		
//		Formula formula = new Formula();
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		nombresCuentas.add("EBDTA");
//		ArrayList<String> operadores = new ArrayList<String>();
//		operadores.add("+");
//		formula.setNombreCuentas(nombresCuentas);
//
//		indicador.setFormulaObjeto(formula);
//		
//		CondicionTaxativa condicion1 = new CondicionTaxativa(">", indicador, 35000);
//		CondicionDeOrden condicion2 = new CondicionDeOrden(">", indicador, 49999);
//		
//		metodologia.agregarCondicion(condicion1);
//		metodologia.agregarCondicion(condicion2);
//		
//		DAOMetodologia DAO = new DAOMetodologia();
//		DAO.setFilePath(new Config("config.cfg").getMetodologias());
//
//		RepositorioDeMetodologias repo = new RepositorioDeMetodologias(DAO);
//		
//		try {
//			repo.cargarMetodologia(metodologia);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	*/
//	/*
//	@Test
//	 void levantarMetodologia(){
//		DAOMetodologia DAO = new DAOMetodologia();
//		DAO.setFilePath(new Config("config.cfg").getMetodologias());
//		RepositorioDeMetodologias repo = new RepositorioDeMetodologias(DAO);
//		ArrayList<TipoElemento> listita;
//		
//		try {
//			listita = repo.listaMetodologias();
//			Metodologia m1 = (Metodologia)listita.get(0);
//		System.out.println(m1.getNombre());
//		System.out.println(m1.getCondiciones().get(0).getClass().equals(CondicionTaxativa.class));
//			System.out.println(m1.getCondiciones().get(1).getClass().equals(CondicionDeOrden.class));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//	}
//	*/
//	/*
//	@Test
//	public void instanciarMetodologiaEnBD(){
//		
//		EntityModel em;
//		try {
//			em = EntityModel.getInstance();
//			em.refresh();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			return;
//		}
//		
//	Metodologia metodologia = new Metodologia();
//		
//		Indicador indicador = new Indicador();
//		indicador.setNombreIndicador("Indicadorsito");
//		indicador.setFormula("(EBDTA+2)");
//		indicador.setidUsuario(1);
//		
//		Formula formula = new Formula();
//		ArrayList<String> nombresCuentas = new ArrayList<String>();
//		nombresCuentas.add("EBDTA");
//		ArrayList<String> operadores = new ArrayList<String>();
//		operadores.add("+");
//		formula.setNombreCuentas(nombresCuentas);
//		//formula.setOperadores(operadores);
//		indicador.setFormulaObjeto(formula);
//
//		CondicionTaxativa condicion1 = new CondicionTaxativa();
//		condicion1.setOperacion(">");
//		condicion1.setIndicador(indicador);
//		condicion1.setValor(49999);
//		
//		metodologia.agregarCondicion(condicion1);
//		
//		Usuario user = new Usuario();
//		ArrayList<Indicador> listaIndicadores = new ArrayList<Indicador>();
//		ArrayList<Metodologia> listaMetodologias = new ArrayList<Metodologia>();
//		
//		listaIndicadores.add(indicador);
//		listaMetodologias.add(metodologia);
//		
//		user.setListaDeIndicadores(listaIndicadores);
//		user.setListaDeMetodologias(listaMetodologias);
//		user.setUser("hola");
//		user.setPassword("123");
//		
//		metodologia.setUsuario(user);
//		
//		//metodologia.setUsuario
//		
//		try {
//			em.createElemento(metodologia);
//			System.out.println("Pude cargar metodologia en la BD");
//		} catch (Exception e) {
//			System.out.println("No pude cargar metodologia en la BD");
//		}
//		
//
//	}
//	*/
//}