package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import entities.Cuenta;
import entities.Empresa;
import entities.FabricaOperaciones;
import entities.Indicador;
//import entities.Operacion;
import entities.TipoElemento;
import model.DAOEmpresa;
import model.DAOIndicador;
import model.RepositorioDeEmpresas;
import model.RepositorioDeIndicadores;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

public class VentanaCargarMetodologia  extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textoValorCuenta;
	private JTextField textoValorIndicador;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboOperador;
	private JComboBox<String> comboOperador2;
	private JComboBox<String> comboOrdenar;
	private JComboBox<String> comboOrdenar2;
	private JComboBox<String> comboIndicadores;
	private JComboBox<String> comboCuentas;
	private JComboBox<String> comboTipoMetodologia;
	private JComboBox<String> comboPeso;
	private JComboBox<String> comboPeso2;
	//private ArrayList<CondicionDeOrden> listaDeCondicionDeOrden;
	//private ArrayList<CondicionTaxativa> listaDeCondicionTaxativa;

	
	public VentanaCargarMetodologia() 
	{
		setVisible(true);
		setSize(939, 353);
		getContentPane().setLayout(null);
		
		JLabel lblNombreMetodologia = new JLabel("Nombre metodologia");
		lblNombreMetodologia.setBounds(10, 37, 214, 29);
		getContentPane().add(lblNombreMetodologia);
				
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(143, 41, 142, 20);
		getContentPane().add(textField);
		
		JLabel lblCondicion = new JLabel("Cuenta");
		lblCondicion.setBounds(10, 91, 48, 29);
		getContentPane().add(lblCondicion);
		
		JLabel lblIndicador = new JLabel("Indicador");
		lblIndicador.setBounds(10, 131, 58, 29);
		getContentPane().add(lblIndicador);
		
		this.comboOperador = new JComboBox<String>();
		comboOperador.setModel(new DefaultComboBoxModel<String>(new String[] {"",">","<","=/","="}));
		comboOperador.setBounds(213, 95, 42, 20);
		getContentPane().add(comboOperador);
		
		this.comboOperador2 = new JComboBox<String>();
		comboOperador2.setModel(new DefaultComboBoxModel<String>(new String[] {"",">","<","=/","="}));
		comboOperador2.setBounds(213, 135, 42, 20);
		getContentPane().add(comboOperador2);
		
		textoValorCuenta = new JTextField();
		textoValorCuenta.setColumns(10);
		textoValorCuenta.setBounds(275, 95, 58, 20);
		getContentPane().add(textoValorCuenta);
		
		textoValorIndicador = new JTextField();
		textoValorIndicador.setColumns(10);
		textoValorIndicador.setBounds(275, 135, 58, 20);
		getContentPane().add(textoValorIndicador);
		
		JButton botonAgregarCondicionIndicador = new JButton("Agregar");
		botonAgregarCondicionIndicador.setBounds(795, 134, 89, 23);
		getContentPane().add(botonAgregarCondicionIndicador);
		botonAgregarCondicionIndicador.addActionListener(new botonAgregarCondicionIndicadorActionListener());
		
		JButton botonAgregarCondicionCuenta = new JButton("Agregar");
		botonAgregarCondicionCuenta.setBounds(795, 91, 89, 23);
		getContentPane().add(botonAgregarCondicionCuenta);
		botonAgregarCondicionCuenta.addActionListener(new botonAgregarCondicionCuentaActionListener());
		
		JLabel lblUltimos = new JLabel("Ultimos Periodos");
		lblUltimos.setBounds(360, 91, 110, 29);
		getContentPane().add(lblUltimos);
		
		JLabel lblUltimosPeriodos = new JLabel("Ultimos Periodos");
		lblUltimosPeriodos.setBounds(360, 131, 110, 29);
		getContentPane().add(lblUltimosPeriodos);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(459, 95, 27, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(459, 135, 27, 20);
		getContentPane().add(textField_2);
		
		JLabel lblOrdenar = new JLabel("Ordenar");
		lblOrdenar.setBounds(510, 91, 51, 29);
		getContentPane().add(lblOrdenar);
		
		JLabel lblOrdenar_1 = new JLabel("Ordenar");
		lblOrdenar_1.setBounds(510, 131, 51, 29);
		getContentPane().add(lblOrdenar_1);
		
		this.comboOrdenar = new JComboBox<String>();
		comboOrdenar.setModel(new DefaultComboBoxModel<String>(new String[] {"Creciente","Decreciente","Ninguno"}));
		comboOrdenar.setBounds(571, 95, 87, 20);
		getContentPane().add(comboOrdenar);
		
		this.comboOrdenar2 = new JComboBox<String>();
		comboOrdenar2.setModel(new DefaultComboBoxModel<String>(new String[] {"Creciente","Decreciente","Ninguno"}));
		comboOrdenar2.setBounds(571, 135, 87, 20);
		getContentPane().add(comboOrdenar2);
				
		JButton crearMetodologia = new JButton("Aceptar");
		crearMetodologia.setBounds(755, 219, 142, 36);
		getContentPane().add(crearMetodologia);
		
		DAOEmpresa daoEmpresas = new DAOEmpresa();
		daoEmpresas.setFilePath(new Config("config.cfg").getEmpresas());
		RepositorioDeEmpresas repoEmpresas = new RepositorioDeEmpresas(daoEmpresas);
		
		DAOIndicador daoIndicadores = new DAOIndicador();
		daoIndicadores.setFilePath(new Config("config.cfg").getIndicadores());
		RepositorioDeIndicadores repoIndicadores = new RepositorioDeIndicadores(daoIndicadores);
		
		//empiezo a crear y llenar los combos con las opciones
		
		this.crearComboCuentas(repoEmpresas);
		this.crearComboIndicadores(repoIndicadores);
		
		this.comboTipoMetodologia = new JComboBox<String>();
		comboTipoMetodologia.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione","Taxativa","Ordenamiento"}));
		comboTipoMetodologia.setBounds(328, 41, 110, 20);
		getContentPane().add(comboTipoMetodologia);	
		comboTipoMetodologia.addActionListener(new comboTipoMetodologiaActionListener());

	}
	
	private void crearComboIndicadores(RepositorioDeIndicadores unRepositorio)
	{
	    try
		{
	    	//creo el combo de indicadores
			this.comboIndicadores = new JComboBox<String>();
			comboIndicadores.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione"}));
			comboIndicadores.setBounds(78, 135, 102, 20);
			getContentPane().add(comboIndicadores);
	    	
			ArrayList<TipoElemento> listaIndicadores = unRepositorio.listaIndicadores();
			
			for(TipoElemento unIndicador: listaIndicadores)
			{
					comboIndicadores.addItem(((Indicador) unIndicador).getNombreIndicador());
			}
			
		} 
	    catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	private void crearComboCuentas(RepositorioDeEmpresas unRepositorio)
	{
		try
		{
			//creo el combo de cuentas
			this.comboCuentas = new JComboBox<String>();
			comboCuentas.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione"}));
			comboCuentas.setBounds(78, 95, 102, 20);
			getContentPane().add(comboCuentas);
			
			ArrayList<TipoElemento> listaEmpresas=unRepositorio.listaEmpresas();
			
			for(TipoElemento unaEmpresa: listaEmpresas)
			{
				List<Cuenta> listaCuentas=unRepositorio.consultarCuentas((Empresa) unaEmpresa); 
				
				for(Cuenta unaCuenta: listaCuentas)
				{
					comboCuentas.addItem(unaCuenta.getnombreCuenta());
				}				
			}
			
			comboCuentas.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//Obtengo la empresa que selecciono el usuario
					if(e.getSource()==comboCuentas)
					{
						comboCuentas.getSelectedIndex();
					}

				}
			});
			
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private class comboTipoMetodologiaActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Obtengo la metodologia que selecciono el usuario
			if (e.getSource() == comboTipoMetodologia)
			{
				int posicion = comboTipoMetodologia.getSelectedIndex();

				if (posicion == 2)
				{
					JLabel lblPrioridad = new JLabel("Peso");
					lblPrioridad.setBounds(681, 91, 39, 29);
					getContentPane().add(lblPrioridad);

					JLabel lblPrioridad_1 = new JLabel("Peso");
					lblPrioridad_1.setBounds(681, 131, 39, 29);
					getContentPane().add(lblPrioridad_1);

					comboPeso = new JComboBox<String>();
					comboPeso.setModel(new DefaultComboBoxModel<String>(new String[]
					{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
					comboPeso.setBounds(730, 95, 42, 20);
					getContentPane().add(comboPeso);

					comboPeso2 = new JComboBox<String>();
					comboPeso2.setModel(new DefaultComboBoxModel<String>(new String[]
					{ "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
					comboPeso2.setBounds(730, 135, 42, 20);
					getContentPane().add(comboPeso2);
				}

			}
		}
	}

	private class botonAgregarCondicionIndicadorActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		
			

		}
		
	}

	private class botonAgregarCondicionCuentaActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			FabricaOperaciones fabricaOperaciones=new FabricaOperaciones();
			fabricaOperaciones.crearOperacion((String)comboOperador.getSelectedItem());
			
			
			if(comboTipoMetodologia.getSelectedIndex()==2)
			{
				
				
			}

		}
		
	}
	
	
	
}