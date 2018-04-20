package server;

import controllers.ConsultarController;
import controllers.CreacionController;
import controllers.EvaluacionIndicadorController;
import controllers.EvaluarMetodologiaController;
import controllers.InicioController;
import controllers.LogInController;
//import controllers.VentasController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure(){
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		
		//creo controllers
		InicioController inicioController = new InicioController();
		EvaluacionIndicadorController indicadorController = new EvaluacionIndicadorController();
		EvaluarMetodologiaController metodologiaController = new EvaluarMetodologiaController();
		ConsultarController consultarController = new ConsultarController();
		CreacionController creacionController = new CreacionController();
		LogInController logincontroller = new LogInController();
		
		
		
		//LogIn
		Spark.get("/",logincontroller::registro_o_logueo,engine );
		Spark.post("/",logincontroller::registro_o_logueo,engine );
		Spark.post("/registro",logincontroller::registro,engine );
		Spark.post("/confirmaRegistro",logincontroller::confirma_registro,engine );
		Spark.post("/logueo",logincontroller::logueo,engine );
		Spark.post("/confirmaLogueo",logincontroller::confirma_logueo,engine );
		
		//menu principal
		Spark.post("/inicio", inicioController::inicio, engine);
		
		//consulta
		Spark.post("/consulta", consultarController::mostar_menu, engine);
		Spark.post("/cuentas", consultarController::listarCuentas,engine);
		Spark.get("/detalleEspecificoCuenta/:empresa/:cuentaNombre", consultarController::verdetalleCuenta2,engine);
		Spark.post("/detalleEspecificoCuenta", consultarController::verdetalleCuenta, engine);
		
		//evaluarIndicador
		Spark.post("/seleccionarEmpresa", indicadorController::seleccionarEmpresa, engine);
		Spark.post("/seleccionarIndicador", indicadorController::seleccionarIndicador, engine);
		Spark.post("/seleccionarPeriodo", indicadorController::ingresarPeriodo, engine);
		Spark.post("/evaluarIndicador", indicadorController::evaluarIndicador, engine);
		
		//evaluarMetodologia
		Spark.post("/seleccionar_Metodologia", metodologiaController::seleccionarMetodologia, engine);
		Spark.post("/evaluarMetodologia", metodologiaController::AplicarMetodologia, engine);
		
		//crear
		
		Spark.post("/crearindicadorometodologia",creacionController::elegir,engine);
		
		Spark.post("/crearIndicador",creacionController::cargarIndicador,engine);
		Spark.post("/indicadorCreado",creacionController::indicadorCreado,engine);
		
		Spark.post("/cuentaOIndicador",creacionController::cuentaOIndicador,engine);
		Spark.post("/cargarIndicadoresMetodologia",creacionController::cargarIndicadoresMetodologia,engine);
		Spark.post("/crearCondicionTaxativa",creacionController::crearCondicionTaxativa,engine);
		Spark.post("/crearCondicionDeOrden",creacionController::crearCondicionDeOrden,engine);
		Spark.post("/crearMetodologia",creacionController::crearMetodologia,engine);
		
		Spark.post("/crearEmpresa",creacionController::cargarEmpresa,engine);
		Spark.post("/empresaCreada",creacionController::empresaCreada,engine);
		
		
		Spark.post("/CREARselecciomEmpresa",creacionController::seleccionEmpresa,engine);
		Spark.post("/cuentaCreada",creacionController::cuentaCreada,engine);
		
		

		Spark.post("/PeriodoSeleccionEmpresa",creacionController::PeriodoSeleccionEmpresa,engine);
		Spark.post("/PeriodoSeleccionCuenta",creacionController::PeriodoSeleccionCuenta,engine);
		Spark.post("/cargaPeriodo",creacionController::cargarPeriodo,engine);
		Spark.post("/periodoCreado",creacionController::periodoCreado,engine);
		
		Spark.post("/cargaCuentasMetodologia",creacionController::cargarCuentasMetodologia,engine);
		Spark.post("/crearCondicionTaxativaCuentas",creacionController::crearCondicionTaxativaCuentas,engine);
		Spark.post("/crearCondicionDeOrdenCuentas",creacionController::crearCondicionDeOrdenCuentas,engine);
		
		
	}
}
