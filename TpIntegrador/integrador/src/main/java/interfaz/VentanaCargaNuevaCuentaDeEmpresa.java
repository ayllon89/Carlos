//package interfaz;
//
//
//import javax.swing.JFrame; 
//
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
//import entities.Cuenta;
//import entities.Empresa;
//import entities.Periodo;
//import entities.TipoElemento;
//import model.DAOEmpresa;
//import model.RepositorioDeEmpresas;
//
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.awt.Event;
//import java.awt.event.ActionEvent;
//import javax.swing.JComboBox;
//import javax.swing.DefaultComboBoxModel;
//
//import java.util.*;
//
//public class VentanaCargaNuevaCuentaDeEmpresa extends JFrame
//{
//	private JTextField textoNombreCuenta;
//	private JTextField textoValorCuenta;
//	private JTextField textoFechaInicial;
//	private JTextField textoFechaFinal;
//	private Empresa unaEmpresa;
//
//	public VentanaCargaNuevaCuentaDeEmpresa() throws IOException
//	{
//		setVisible(true);
//		setSize(450,300);
//		getContentPane().setLayout(null);
//
//		JLabel tituloNombreEmpresa = new JLabel("Nombre de la empresa");
//		tituloNombreEmpresa.setBounds(10, 11, 140, 29);
//		getContentPane().add(tituloNombreEmpresa);
//
//		JLabel tituloNombreCuenta = new JLabel("Nombre de la cuenta");
//		tituloNombreCuenta.setBounds(10, 51, 118, 29);
//		getContentPane().add(tituloNombreCuenta);
//		
//		textoNombreCuenta = new JTextField();
//		textoNombreCuenta.setBounds(160, 55, 169, 20);
//		getContentPane().add(textoNombreCuenta);
//		textoNombreCuenta.setColumns(10);
//
//		JLabel tituloValorCuenta = new JLabel("Valor de cuenta");
//		tituloValorCuenta.setBounds(10, 91, 118, 29);
//		getContentPane().add(tituloValorCuenta);
//
//		textoValorCuenta = new JTextField();
//		textoValorCuenta.setBounds(160, 95, 169, 20);
//		getContentPane().add(textoValorCuenta);
//		textoValorCuenta.setColumns(10);
//
//		JLabel tituloFechaInicial = new JLabel("Fecha inicial");
//		tituloFechaInicial.setBounds(10, 131, 118, 29);
//		getContentPane().add(tituloFechaInicial);
//
//		textoFechaInicial = new JTextField();
//		textoFechaInicial.setBounds(160, 135, 169, 20);
//		getContentPane().add(textoFechaInicial);
//		textoFechaInicial.setColumns(10);
//
//		JLabel tituloFechaFinal = new JLabel("Fecha final");
//		tituloFechaFinal.setBounds(10, 170, 118, 29);
//		getContentPane().add(tituloFechaFinal);
//
//		textoFechaFinal = new JTextField();
//		textoFechaFinal.setColumns(10);
//		textoFechaFinal.setBounds(160, 174, 169, 20);
//		getContentPane().add(textoFechaFinal);
//		
//		DAOEmpresa dao = new DAOEmpresa();
//		dao.setFilePath(new Config("config.cfg").getEmpresas());
//		RepositorioDeEmpresas repoEmpresas = new RepositorioDeEmpresas(dao);
//		
//		this.crearCombo(repoEmpresas);
//		
//		JButton botonCargar = new JButton("Cargar");
//		botonCargar.setBounds(154, 215, 89, 23);
//		getContentPane().add(botonCargar);
//		botonCargar.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{	//creo variables fecha incial y fecha final
//				
//				int fechaInicial = Integer.parseInt(textoFechaInicial.getText());
//				int fechaFinal = Integer.parseInt(textoFechaFinal.getText());
//				int valorCuenta = Integer.parseInt(textoValorCuenta.getText());
//				Cuenta unaCuenta = new Cuenta();
//				unaCuenta.setNombreCuenta(textoNombreCuenta.getText());
//				Periodo unPeriodo = new Periodo();
//				unPeriodo.setFechaInicial(fechaInicial);
//				unPeriodo.setFechaFinal(fechaFinal);
//				unPeriodo.setValorCuenta(valorCuenta);
//				// obtener info de la cuenta
//				unaCuenta.addPeriodo(unPeriodo);
//
//				try
//				{
//
//					/*for (int i = 0; i < repoEmpresas.listaEmpresas().size(); i++)
//					{
//
//						if (repoEmpresas.listaEmpresas().get(i).getNombreEmpresa().equals(textoNombreEmpresa.getText()))
//						{
//
//							repoEmpresas.cargarCuenta(repoEmpresas.listaEmpresas().get(i), unaCuenta);
//							JOptionPane.showMessageDialog(botonCargar, "Nueva cuenta cargada");
//						} 
//						else
//						{
//							JOptionPane.showMessageDialog(botonCargar, "No");
//						}
//					}*/
//					repoEmpresas.cargarCuenta(unaEmpresa.getNombreEmpresa(), unaCuenta);
//					JOptionPane.showMessageDialog(botonCargar, "Nueva cuenta cargada");
//				} 
//				catch (IOException e1)
//				{
//
//					e1.printStackTrace();
//					JOptionPane.showMessageDialog(botonCargar, "Hubo un error. Vuelva a ingresar los datos");
//				}
//				finally
//				{
//					textoNombreCuenta.setText("");
//					textoValorCuenta.setText("");
//					textoFechaInicial.setText("");
//					textoFechaFinal.setText("");
//				}
//
//			}
//		});
//
//		
//	}
//	
//
//	public void crearCombo(RepositorioDeEmpresas unRepositorio)
//	{
//		try
//		{
//			JComboBox<String> comboEmpresas = new JComboBox<String>();
//			comboEmpresas.setModel(new DefaultComboBoxModel<String>(new String[]
//			{ "Seleccione Empresa" }));
//			comboEmpresas.setBounds(160, 15, 169, 20);
//			getContentPane().add(comboEmpresas);
//
//			ArrayList<TipoElemento> listaEmpresas = unRepositorio.listaEmpresas();
//
//			// realizo un for each
//			for (TipoElemento unaEmpresa : listaEmpresas)
//			{
//				comboEmpresas.addItem(((Empresa) unaEmpresa).getNombreEmpresa());
//			}
//
//			comboEmpresas.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					// Obtengo la empresa que selecciono el usuario
//					if (e.getSource() == comboEmpresas)
//					{
//						int posicion = comboEmpresas.getSelectedIndex();
//						// Aca es -1 dado que en el array del combo la posicion
//						// cero es ocupada por "Seleccione empresa"
//						unaEmpresa = (Empresa) listaEmpresas.get(posicion - 1);
//					}
//
//				}
//			});
//
//		} 
//		catch (IOException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}
//}