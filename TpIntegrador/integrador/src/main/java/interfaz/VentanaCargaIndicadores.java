package interfaz;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.JTextField;

//import entities.Formula;
import entities.Indicador;
import entities.TipoElemento;
import javacc.EG1;
import model.DAOIndicador;
import model.RepositorioDeIndicadores;

//import java.io.ByteArrayInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class VentanaCargaIndicadores extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JFrame frame;
	private JTextField textoIngresarNombre;
	private JTextField textoIngreseFormula;
	private Indicador nuevoIndicador;
	private RepositorioDeIndicadores repoIndicadores;
	private static EG1 parser = null;
	
	public VentanaCargaIndicadores() throws IOException {
		
		
		setVisible(true);
		setSize(450,300);
		getContentPane().setLayout(null);		
		
		DAOIndicador dao = new DAOIndicador();
        repoIndicadores = new RepositorioDeIndicadores(dao);
        dao.setFilePath(new Config("config.cfg").getIndicadores());
        ArrayList<TipoElemento> indicadores = repoIndicadores.listaIndicadores();
		
		
		JLabel lblIngreseNombreDel = new JLabel("Ingrese nombre del Indicador");
		lblIngreseNombreDel.setBounds(20, 27, 146, 14);
		getContentPane().add(lblIngreseNombreDel);
		
		textoIngresarNombre = new JTextField();
		textoIngresarNombre.setBounds(172, 24, 158, 20);
		getContentPane().add(textoIngresarNombre);
		textoIngresarNombre.setColumns(10);
		
		JButton botonIngresar = new JButton("Ingresar");
		botonIngresar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				
				String nombreIndicador = textoIngresarNombre.getText();
				
				if(indicadores.stream().anyMatch(indicador -> ((Indicador) indicador).getNombreIndicador().equals(nombreIndicador))){
					JOptionPane.showMessageDialog(botonIngresar,"Indicador con ese nombre ya existe");
				}else{
					
					JOptionPane.showMessageDialog(botonIngresar,"Indicador no existe, puede seguir con los pasos");
					nuevoIndicador = new Indicador();
					nuevoIndicador.setNombreIndicador(nombreIndicador);
				
			}}
		});
		botonIngresar.setBounds(335, 23, 89, 23);
		getContentPane().add(botonIngresar);
		
		JLabel labelIngreseFormula = new JLabel("Ingrese Formula");
		labelIngreseFormula.setBounds(20, 87, 139, 14);
		getContentPane().add(labelIngreseFormula);
		
		textoIngreseFormula = new JTextField();
		textoIngreseFormula.setColumns(10);
		textoIngreseFormula.setBounds(172, 84, 158, 20);
		getContentPane().add(textoIngreseFormula);
		
		JButton botonVerificar = new JButton("Verificar");
		botonVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String formula = textoIngreseFormula.getText();
//				nuevoIndicador.parsearFormula(formula, repoIndicadores,parser);
				nuevoIndicador.parsearFormula(formula,parser);
				
				
				
			}
		});
		botonVerificar.setBounds(335, 83, 89, 23);
		getContentPane().add(botonVerificar);
		
		JLabel lblRecuerdeIngresarLa = new JLabel("Recuerde ingresar la f\u00F3rmula sin espacios");
		lblRecuerdeIngresarLa.setBounds(131, 117, 219, 14);
		getContentPane().add(lblRecuerdeIngresarLa);
		
	}
		}

