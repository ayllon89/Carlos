package server;

import java.io.IOException;

import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder.In;

import db.EntityManagerHelper;
import entities.Indicador;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) throws IOException {
		
		
		Indicador.contadorCalculadora=0;
		Indicador.contadorEG1=0;
		EntityManager entityManager= EntityManagerHelper.getEntityManager();
		entityManager.close();
		
//		
		Spark.port(8886);
		DebugScreen.enableDebugScreen();
		Router.configure();
		
		}
}
