package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import controllers.LogInController;
import db.EntityManagerHelper;
import entities.Cuenta;
import entities.Empresa;
import entities.Indicador;
import entities.Metodologia;
import entities.Periodo;
import entities.TipoElemento;
import entities.Usuario;


public class DAOEntity extends DAOAbstract{
	EntityManager em;// = EntityManagerHelper.getEntityManager();
	List<Usuario> elementos = new LinkedList<>();
	List<String> nombres = new LinkedList<>();
	
	public DAOEntity(EntityManager em){
		this.em = em;
	}

	@Override
	public void cargar(TipoElemento tipo) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<TipoElemento> lista() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean buscar(ArrayList<TipoElemento> listaElementos, String nombreElementoBuscado) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void operacion(TipoElemento elemento){
		em.persist(elemento);
	}
	
	
	public TipoElemento findElemento(int idElemento,TipoElemento tipo){
		return em.find(TipoElemento.class,idElemento);
	}
	
	
	
	public <T> T find(Class<T> arg0, Object arg1){
		return em.find(arg0, arg1); 
	}
	
	public void refresh(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		em.getEntityManagerFactory().getCache().evictAll();
		//em.close();
	}
	
	//general
	@Transactional
		public void createElemento(TipoElemento elemento){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		em.persist(elemento);
		em.flush();
		EntityManagerHelper.commit();
	//	em.close();
		}
	
	
	//Empresa
	
	public List<Empresa> listaEmpresas(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		
		
		CriteriaBuilder constructor = em.getCriteriaBuilder();
		CriteriaQuery<Empresa> criteria = constructor.createQuery(Empresa.class);
		criteria.from(Empresa.class);
		List <Empresa> empresas =em.createQuery(criteria).getResultList();
	//	em.close();
		return empresas;
		
		
	}
	
	public List<Cuenta> listaCuentasDe(String empresa){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		
		nombres.clear();
		this.listaEmpresas().stream().forEach(e -> nombres.add(e.getNombreEmpresa()));
		System.out.print("listacuentasde "+nombres);
		System.out.print("listacuentasde "+empresa);
		int i = nombres.indexOf(empresa);
		
		System.out.print(this.listaEmpresas().get(i).getCuentas());
		List<Cuenta> cuentas =this.listaEmpresas().get(i).getCuentas(); 
		//em.close();
		return cuentas;
	}//esto me parece q esta de mas
	
	public List<String> listaCuentasNombresDeTodasLasEmpresas(){
		
		
		nombres.clear();
		//this.listaEmpresas().stream().forEach(e-> this.listaCuentasDe(e.getNombreEmpresa()).stream().forEach(c-> nombres.add(c.getnombreCuenta()) ));
		this.listaEmpresas().stream().forEach(e-> e.getCuentas().stream().forEach(c->nombres.add(c.getnombreCuenta())));
		//.stream().forEach(c-> System.out.print(c.getnombreCuenta())));
		//System.out.print(nombres);
		ArrayList<String> cuentas = new ArrayList<String>(new HashSet<String>(nombres));
		System.out.print("lista cuentas de todas las empresas"+cuentas);
		return cuentas;
		//Me esta dando el nombre de la empresa?
	}
	
	public Empresa getEmpresa(String empresa){
		nombres.clear();
		this.listaEmpresas().stream().forEach(e -> nombres.add(e.getNombreEmpresa()));
		System.out.print("nombre de las empresas "+nombres);
		int i = nombres.indexOf(empresa);
		return this.listaEmpresas().get(i);
	}
	
	public Cuenta getCuentaDeEmpresa(String cuenta,String empresa){
		
		
		List<Cuenta> cuentas = this.listaCuentasDB().stream().
				filter(c -> c.getnombreCuenta().equals(cuenta) && c.get_empresa().getNombreEmpresa().equals(empresa)).collect(Collectors.toList());;
		System.out.print(cuentas);
		return cuentas.get(0);
		
	}
	
	public List<Cuenta> listaCuentasDB(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		CriteriaBuilder constructor = em.getCriteriaBuilder();
		CriteriaQuery<Cuenta> criteria = constructor.createQuery(Cuenta.class);
		criteria.from(Cuenta.class);
		return em.createQuery(criteria).getResultList();
	}
	
	
	
	
	public Boolean verificiarExistenciaEmpresa(String elemento){
		
		return this.listaEmpresas().stream().anyMatch(e -> e.getNombreEmpresa().equals(elemento));				
	}
	
	//Usuario
	
	public Usuario getUsuario(String user){
		nombres.clear();
		this.listaUsuarios().stream().forEach(c -> nombres.add(c.getUser()));
		int i = nombres.indexOf(user);
		return this.listaUsuarios().get(i);
	}
	public List<Usuario> listaUsuarios(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		CriteriaBuilder constructor = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = constructor.createQuery(Usuario.class);
		criteria.from(Usuario.class);
		return em.createQuery(criteria).getResultList();
	}
	
	public Boolean verificiarExistenciaUsuario(String user){
		return this.listaUsuarios().stream().anyMatch(u -> u.getUser().equals(user));				
	}
	
	
	public Boolean coincidePassword(String user,String password){
		nombres.clear();
		this.listaUsuarios().stream().forEach(e-> nombres.add(e.getUser()));
		int i = nombres.indexOf(user);
		
		//Usuario usuario = em.find(Usuario.class, user);   NO ME SIRVE PORQUE BUSCA POR ID
		Boolean valor = this.listaUsuarios().get(i).getPassword().equals(password);
		return valor;
	}
	
	//Periodos
	public List<Periodo> listaPeriodos(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();		
		CriteriaBuilder constructor = em.getCriteriaBuilder();
		CriteriaQuery<Periodo> criteria = constructor.createQuery(Periodo.class);
		criteria.from(Periodo.class);
		List<Periodo> periodos =em.createQuery(criteria).getResultList();
		//em.close();
		return periodos;
	}
	
	//Metodologias
	public List<Metodologia> listaMetodologias(){
	EntityManager em = EntityManagerHelper.getEntityManager();
	EntityManagerHelper.beginTransaction();
	em.getTransaction();		
	CriteriaBuilder constructor = em.getCriteriaBuilder();
	CriteriaQuery<Metodologia> criteria = constructor.createQuery(Metodologia.class);
	criteria.from(Metodologia.class);
	List<Metodologia> metodologias =em.createQuery(criteria).getResultList();
	//em.close();
	return metodologias;
}
	public List<Metodologia> listaMetodologiasPorId(int id){
		this.refresh();
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		
		
		List<Metodologia> metodologias = new LinkedList<>();
		metodologias = this.listaMetodologias();
		metodologias.removeIf(i -> i.getIdUsuario()!=id);
		
		return metodologias;
		
	}//cero performante pero al n estar manejando grandes volumenes sirve
	
	public Metodologia getMetodologia(String metodologiaString){
		this.refresh();
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		nombres.clear();
		this.listaMetodologiasPorId(LogInController.idUsuario).stream().forEach(i -> nombres.add(i.getNombreMetodologia()));
		System.out.print("lista de metodologias actual \n"+nombres);
		int i = nombres.indexOf(metodologiaString);
		Metodologia metodologiaObjeto = this.listaMetodologiasPorId(LogInController.idUsuario).get(i);
		//em.close();
		return metodologiaObjeto;
		
	}
	//Indicadores
	
	public List<Indicador> listaIndicadores(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		
		
		CriteriaBuilder constructor = em.getCriteriaBuilder();
		CriteriaQuery<Indicador> criteria = constructor.createQuery(Indicador.class);
		criteria.from(Indicador.class);
		List<Indicador> indicadores =em.createQuery(criteria).getResultList();
		//em.close();
		return indicadores;
	}
	
	public List<Indicador> listaIndicadoresPorId(int id){
		this.refresh();
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		
		
		List<Indicador> indicadores = new LinkedList<>();
		indicadores = this.listaIndicadores();
		indicadores.removeIf(i -> i.getUsuario().getIdUsuario()!=id);
		//indicadores.stream().forEach(i -> System.out.print(i.getUsuario().getUser()));
		//em.close();
		return indicadores;
		
	}//cero performante pero al n estar manejando grandes volumenes sirve
	
	public Indicador getIndicador(String indicador){
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		em.getTransaction();
		nombres.clear();
		this.listaIndicadoresPorId(LogInController.idUsuario).stream().forEach(i -> nombres.add(i.getNombreIndicador()));
		int i = nombres.indexOf(indicador);
		Indicador indicadorobjeto = this.listaIndicadoresPorId(LogInController.idUsuario).get(i);
		//em.close();
		return indicadorobjeto;
	}
	


}
