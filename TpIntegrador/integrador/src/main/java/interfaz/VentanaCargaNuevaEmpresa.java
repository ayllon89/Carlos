//package interfaz;
//
//import javax.swing.JFrame; 
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
//import entities.Cuenta;
//import entities.Empresa;
//import entities.Periodo;
//import model.DAOEmpresa;
//import model.RepositorioDeEmpresas;
//
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.awt.Event;
//import java.awt.event.ActionEvent;
//
//public class VentanaCargaNuevaEmpresa extends JFrame
//{
//	private JTextField textoNombreEmpresa;
//	private JTextField textoNombreCuenta;
//	private JTextField textoValorCuenta;
//	private JTextField textoFechaInicial;
//	private JTextField textoFechaFinal;
//
//	public VentanaCargaNuevaEmpresa() throws IOException
//	{
//		setVisible(true);
//		setSize(450,300);
//		getContentPane().setLayout(null);
//
//		JLabel tituloNombreEmpresa = new JLabel("Nombre de la empresa");
//		tituloNombreEmpresa.setBounds(10, 11, 140, 29);
//		getContentPane().add(tituloNombreEmpresa);
//
//		textoNombreEmpresa = new JTextField();
//		textoNombreEmpresa.setBounds(160, 15, 169, 20);
//		getContentPane().add(textoNombreEmpresa);
//		textoNombreEmpresa.setColumns(10);
//
//		JLabel tituloNombreCuenta = new JLabel("Nombre de la cuenta");
//		tituloNombreCuenta.setBounds(10, 51, 118, 29);
//		getContentPane().add(tituloNombreCuenta);
//
//		JLabel tituloValorCuenta = new JLabel("Valor de cuenta");
//		tituloValorCuenta.setBounds(10, 91, 118, 29);
//		getContentPane().add(tituloValorCuenta);
//
//		textoNombreCuenta = new JTextField();
//		textoNombreCuenta.setBounds(160, 55, 169, 20);
//		getContentPane().add(textoNombreCuenta);
//		textoNombreCuenta.setColumns(10);
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
//		JButton botonCargar = new JButton("Cargar");
//		botonCargar.setBounds(154, 215, 89, 23);
//		getContentPane().add(botonCargar);
//
//		botonCargar.addActionListener(new ActionListener()
//		{
//
//			public void actionPerformed(ActionEvent e)
//			{	//creo variables fechaInicial y fechaFinal
//				
//				int fechaInicial =  Integer.parseInt(textoFechaInicial.getText());
//				int fechaFinal =	Integer.parseInt(textoFechaFinal.getText());
//				int valorCuenta = Integer.parseInt(textoValorCuenta.getText());
//				Empresa unaEmpresa = new Empresa();
//				unaEmpresa.setNombreEmpresa(textoNombreEmpresa.getText());
//				Cuenta unaCuenta = new Cuenta();
//				unaCuenta.setNombreCuenta(textoNombreCuenta.getText());
//				Periodo unPeriodo = new Periodo();
//				unPeriodo.setFechaInicial(fechaInicial);
//				unPeriodo.setFechaFinal(fechaFinal);
//				unPeriodo.setValorCuenta(valorCuenta);
//				// obtener nombre de la empresa
//
//				// obtener info de la cuenta
//				
//				unaCuenta.addPeriodo(unPeriodo);
//				try
//				{
//
//					ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
//					listaCuentas.add(unaCuenta);
//					unaEmpresa.setListaDeCuentas(listaCuentas);
//					repoEmpresas.cargarEmpresa(unaEmpresa);
//					JOptionPane.showMessageDialog(botonCargar, "Nueva Empresa cargada");
//
//				} 
//				catch (IOException e1)
//				{
//					e1.printStackTrace();
//					JOptionPane.showMessageDialog(botonCargar, "Hubo un error. Vuelva a ingresar los datos");
//				}
//				finally
//				{
//					textoNombreEmpresa.setText("");
//					textoNombreCuenta.setText("");
//					textoValorCuenta.setText("");
//					textoFechaInicial.setText("");
//					textoFechaFinal.setText("");
//				}
//			}
//		});
//	}
//}
