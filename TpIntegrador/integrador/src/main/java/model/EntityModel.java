package model;


	import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;

import db.EntityManagerHelper;
import entities.Cuenta;
import entities.Empresa;
import entities.Indicador;
import entities.Metodologia;
import entities.Periodo;
import entities.TipoElemento;
import entities.Usuario;
	


	public class EntityModel  {

		private EntityManager em;
		private DAOEntity dao;
		
		private static EntityModel instance = null;
		
		
		public static EntityModel getInstance() throws IOException{
			if(instance==null){
				instance = new EntityModel();
			}
			return instance;
			
		}
		
		private EntityModel() throws IOException {	
			this.boot();
			
		}
		
		public void refresh(){
			dao.refresh();
		}
		
		public void boot() throws IOException{
			em = EntityManagerHelper.getEntityManager();
			dao = new DAOEntity(em);
			System.out.println("Se creo una nueva instancia de entity");		
		}
		
		public void createElemento(TipoElemento elemento){
			this.dao.createElemento(elemento);
			//Lo comente porque me tiraba un error.(La imagen que te adjunte al mail)
			//this.dao.transaccion(elemento);
		}
		
		public <T> T find(Class<T> arg0, Object arg1){
			return this.dao.find(arg0, arg1);
			
		}
		
		public Indicador getIndicador(String indicador){
			return this.dao.getIndicador(indicador);
		}
		
		
		public List<Empresa> listaEmpresa(){
			return this.dao.listaEmpresas();
		}
		
		public Boolean verificiarExistenciaEmpresa(String elemento){
			
			return this.dao.verificiarExistenciaEmpresa(elemento);
			
		}
		public List<Usuario> listaUsuarios(){
			return this.dao.listaUsuarios();
		}
		public Usuario getUsuario(String user){
			return this.dao.getUsuario(user);
		}
		
		public Boolean verificarExistenciaUsuario(String user){
			return this.dao.verificiarExistenciaUsuario(user);
		}
		public Boolean coincidePassword(String user,String password){
			return this.dao.coincidePassword(user, password);
		}
		
		public List<Cuenta> listaCuentasDe(String empresa){
			return this.dao.listaCuentasDe(empresa);
		}
		
		public Empresa getEmpresa(String empresa){
			return this.dao.getEmpresa(empresa);
		}
		
		public List<Indicador> listaIndicadores(){
			return this.dao.listaIndicadores();
		}
		public List<Indicador> listaIndicadoresPorId(int id){
			return this.dao.listaIndicadoresPorId(id);
		}
		public List<String> listaCuentasNombresDeTodasLasEmpresas(){
			return this.dao.listaCuentasNombresDeTodasLasEmpresas();
		}
		public Cuenta getCuentaDeEmpresa(String cuenta,String empresa){
			return this.dao.getCuentaDeEmpresa(cuenta, empresa);	
		}
		
//		public List<Metodologia> listaMetodologias(){
//			return this.dao.listaMetodologias();
//		}
		
		public List<Metodologia> listaMetodologiasPorId(int id){
			return this.dao.listaMetodologiasPorId(id);			
		}
		public Metodologia getMetodologia(String metodologiaString){
			return this.dao.getMetodologia(metodologiaString);
		}
		
		public List<Periodo> listaPeriodos(){
			return this.dao.listaPeriodos();
		}
		
	
	
		
	}
