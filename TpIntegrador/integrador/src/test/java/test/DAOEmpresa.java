//package test;
//
//import java.io.IOException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import entities.Cuenta;
//import entities.Empresa;
//import entities.Indicador;
//import entities.Periodo;
//import entities.TipoElemento;
//import interfaz.Config;
//import javacc.Calculator;
//import javacc.ParseException;
//import model.DAOIndicador;
//import model.RepositorioDeEmpresas;
//import model.RepositorioDeIndicadores;
//
//
//public class DAOEmpresa {
//	
//	private List<Cuenta> cuentas;
//	private RepositorioDeEmpresas repoEmpresas;
//	
//	private ArrayList<TipoElemento> empresas;
//	private Empresa empresa;
//	
//	private RepositorioDeIndicadores repoIndicadores;
//	private ArrayList<TipoElemento> indicadores;
//	private Indicador indicador;
//	
//	private static Calculator parser = null;
//	
//	
//		
//	@Before
//	public void init() throws IOException{
//		
//		DAOIndicador dao2 = new DAOIndicador();
//		model.DAOEmpresa dao = new model.DAOEmpresa();
//		dao2.setFilePath(new Config("config.cfg").getIndicadores());
//		dao.setFilePath(new Config("config.cfg").getEmpresas());
//		this.repoIndicadores = new RepositorioDeIndicadores(dao2);
//		this.repoEmpresas = new RepositorioDeEmpresas(dao);
//	
//		indicadores = repoIndicadores.listaIndicadores();
//		empresas = repoEmpresas.listaEmpresas();
//	
//		
//	
//	}
//		
//	@Test
//	
//	public void sePuedeAplicarIndicador(){
//		
//		//String cuenta = "JJ";
//		indicador  = (Indicador) indicadores.get(0);
//		empresa = (Empresa) empresas.get(0);
//		
//		
//		
//		Assert.assertTrue(indicador.sePuedeAplicarA(empresa, 2015, 2016));
//	}
//	
//	
//	@Test
//	public void leoCuentaDeEmpresaYEsCorrecto() throws IOException{
//		Empresa empresaBuscada = new Empresa();
//		empresaBuscada.setNombreEmpresa("IG");
//		Cuenta cuentaBuscada = new Cuenta();
//		cuentaBuscada.setNombreCuenta("JJJ");
//		if(repoEmpresas.buscarEmpresa(empresas, empresaBuscada.getNombreEmpresa())){
//			cuentas = repoEmpresas.consultarCuentas(empresaBuscada);
//			Assert.assertTrue(cuentas.stream().anyMatch(cuenta -> cuenta.getnombreCuenta().equals(cuentaBuscada.getnombreCuenta())));
//		}else{
//			Assert.assertTrue(false);
//		}
//	}
//	
//	
//	@Test//verAssert
//	public void cargarEmpresa() throws IOException{
//		
//		Empresa nuevaEmpresa = new Empresa();
//		nuevaEmpresa.setNombreEmpresa("FACEBOOK");
//		ArrayList<String> nombres = new ArrayList<String>();
//		empresas.stream().forEach(e -> nombres.add(((Empresa) e).getNombreEmpresa()));
//		int index = nombres.indexOf(nuevaEmpresa.getNombreEmpresa());
//		
//		if(repoEmpresas.buscarEmpresa(empresas, nuevaEmpresa.getNombreEmpresa())){
//			//Empresa ya existe
//			Assert.assertEquals(nuevaEmpresa.getNombreEmpresa(), ((Empresa) empresas.get(index)).getNombreEmpresa());
//		}else{
//			//Como empresa no existe se carga
//			this.repoEmpresas.cargarEmpresa(nuevaEmpresa);
//			Assert.assertEquals(nuevaEmpresa.getNombreEmpresa(), ((Empresa) empresas.get((empresas.size())-1)).getNombreEmpresa());		}		
//		
//	}
//
//	@Test
//		public void VerificarExistenciaDeEmpresayCargarCuenta() throws IOException{
//		
//		Empresa empresaBuscada = new Empresa();
//		empresaBuscada.setNombreEmpresa("IG");
//		Cuenta nuevaCuenta = new Cuenta();
//		nuevaCuenta.setNombreCuenta("JJJ");
//		Periodo periodo = new Periodo();
//		periodo.setFechaInicial(2013);
//		periodo.setFechaFinal(2014);
//		periodo.setValorCuenta(9999);
//		nuevaCuenta.addPeriodo(periodo);
//		ArrayList<String> nombres = new ArrayList<String>();
//		if(repoEmpresas.buscarEmpresa(empresas, empresaBuscada.getNombreEmpresa())){
//			empresas.stream().forEach(e -> nombres.add(((Empresa) e).getNombreEmpresa()));
//			int index = nombres.indexOf(empresaBuscada.getNombreEmpresa());
//			if(((Empresa) empresas.get(index)).tenesCuentaLlamada(nuevaCuenta.getnombreCuenta())){
//			//la empresa ya tiene la cuenta
//				Assert.assertTrue(false);
//			}else{
//				repoEmpresas.cargarCuenta(empresaBuscada.getNombreEmpresa(), nuevaCuenta);
//			Assert.assertEquals(empresaBuscada.getNombreEmpresa(), ((Empresa) empresas.get(index)).getNombreEmpresa());
//			
//			}
//	}else{
//		Assert.assertTrue(false);
//	}
//
//	}
//	
//
//	@Test
//	
//		public void aplicarA() throws ParseException{
//		indicador  = (Indicador) indicadores.get(0);
//		empresa = (Empresa) empresas.get(0);
//		
//		
//		System.out.println(indicador.aplicarA(empresa, 2015, 2016,parser));
//		
//	}
//}
//	