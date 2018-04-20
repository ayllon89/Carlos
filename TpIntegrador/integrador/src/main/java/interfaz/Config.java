package interfaz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
	private String rutaConfig;
	private String empresas;
	private String indicadores;
	private String metodologias;
	private String usuarios;
	
	public Config(String rutaConfig){
		this.setRutaConfig(rutaConfig);
		this.getProperties();
	}
	
	public void saveConfig(){
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.cfg");

			// set the properties value
			prop.setProperty("empresas", this.empresas);
			
			prop.setProperty("indicadores", this.indicadores);
			
			prop.setProperty("metodologias", this.metodologias);
			
			prop.setProperty("usuarios", this.usuarios);

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	  public void getProperties() {

			Properties prop = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream(this.getRutaConfig());

				// load a properties file
				prop.load(input);

				// get the property value and print it out
				System.out.println(prop.getProperty("empresas"));
				System.out.println(prop.getProperty("indicadores"));
				System.out.println(prop.getProperty("metodologias"));
				System.out.println(prop.getProperty("usuarios"));
				
				this.setEmpresas(prop.getProperty("empresas"));
				this.setIndicadores(prop.getProperty("indicadores"));
				this.setMetodologias(prop.getProperty("metodologias"));
				this.setUsuarios(prop.getProperty("usuarios"));

			} catch (IOException ex) {
				//ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		  }

	public String getRutaConfig() {
		return rutaConfig;
	}

	public void setRutaConfig(String rutaConfig) {
		this.rutaConfig = rutaConfig;
	}

	public String getEmpresas() {
		return empresas;
	}

	public void setEmpresas(String empresas) {
		this.empresas = empresas;
	}

	public String getIndicadores() {
		return indicadores;
	}
	
	public String getUsuarios(){
		return usuarios;
	}

	public void setIndicadores(String indicadores) {
		this.indicadores = indicadores;
	}
	
	public String getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(String metodologias) {
		this.metodologias = metodologias;
	}
	
	public void setUsuarios(String usuarios){
		this.usuarios = usuarios;
	}
}
