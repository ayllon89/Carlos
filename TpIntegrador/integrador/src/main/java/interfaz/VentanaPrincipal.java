//package interfaz;
//
//import javax.swing.JFrame;
//
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.Font;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.File;
//import java.io.IOException;
//import javax.swing.SwingConstants;
//
//
//import javax.swing.JLabel;
//
//public class VentanaPrincipal extends JFrame
//{
//
//	
//	
//
//	public VentanaPrincipal()
//	{
//		super("aplicacion");
//		addWindowListener(new EscuchaVentana());
//		setSize(450, 336);
//		setVisible(true);
//		getContentPane().setLayout(null);
//
//		JButton botonConsultar = new JButton("Consultar Valores");
//		botonConsultar.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonConsultar.addActionListener(new BotonConsultarActionListener());
//		botonConsultar.setBounds(21, 173, 199, 51);
//		getContentPane().add(botonConsultar);
//
//		JButton botonCargarEmpresa = new JButton("Cargar Empresa");
//		botonCargarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonCargarEmpresa.addActionListener(new BotonCargarEmpresaActionListener());
//		botonCargarEmpresa.setBounds(226, 21, 198, 51);
//		getContentPane().add(botonCargarEmpresa);
//
//		JButton botonCargarCuenta = new JButton("Cargar Cuenta\r\n de Empresa");
//		botonCargarCuenta.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonCargarCuenta.addActionListener(new BotonCargarCuentaActionListener());
//		botonCargarCuenta.setBounds(225, 97, 199, 51);
//		getContentPane().add(botonCargarCuenta);
//		
//
//
//		JLabel lblBienvenidos = new JLabel("Bienvenido");
//		lblBienvenidos.setFont(new Font("Tahoma", Font.BOLD, 18));
//		lblBienvenidos.setHorizontalAlignment(SwingConstants.CENTER);
//		lblBienvenidos.setBounds(21, 77, 166, 60);
//		getContentPane().add(lblBienvenidos);
//		
//
//		JLabel lblquDeseaHacer = new JLabel("\u00BFQu\u00E9 desea hacer?");
//		lblquDeseaHacer.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblquDeseaHacer.setHorizontalAlignment(SwingConstants.CENTER);
//		lblquDeseaHacer.setBounds(32, 113, 140, 35);
//		getContentPane().add(lblquDeseaHacer);
//		
//		JButton botonCargarIndicador = new JButton("Cargar Indicador");
//		botonCargarIndicador.setFont(new Font("Tahoma", Font.PLAIN, 11));		
//		botonCargarIndicador.addActionListener(new BotonCargarIndicadoresActionListener());
//		botonCargarIndicador.setBounds(230, 173, 194, 51);
//		getContentPane().add(botonCargarIndicador);
//		
//		JButton botonAplicarIndicadorAEmpresa = new JButton("Aplicar Indicador a Empresa");
//		botonAplicarIndicadorAEmpresa.addActionListener(new BotonAplicarIndicadorAEmpresaActionListener());
//		botonAplicarIndicadorAEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonAplicarIndicadorAEmpresa.setBounds(21, 21, 199, 51);
//		getContentPane().add(botonAplicarIndicadorAEmpresa);
//		
//
//		JButton botonCargarMetodologia = new JButton("Cargar Metodologia");
//		botonCargarMetodologia.addActionListener(new BotonCargarMetodologiaActionListener());
//		botonCargarMetodologia.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonCargarMetodologia.setBounds(21, 235, 199, 51);
//		getContentPane().add(botonCargarMetodologia);
//		
//		JButton botonAplicarMetodologia = new JButton("Aplicar Metodologia");
//		botonAplicarMetodologia.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		botonAplicarMetodologia.setBounds(226, 235, 199, 51);
//		getContentPane().add(botonAplicarMetodologia);
//	}
//
//	private class BotonConsultarActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				new VentanaMostrar();
//
//			} 
//			catch (IOException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//	private class BotonAplicarIndicadorAEmpresaActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				new VentanaAplicarIndicadorAEmpresa();
//
//			} 
//			catch (IOException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
//	
//	private class BotonCargarIndicadoresActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				new VentanaCargaIndicadores();
//
//			} 
//			catch (IOException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//	}
//
//	private class BotonCargarEmpresaActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				new VentanaCargaNuevaEmpresa();
//
//			} 
//			catch (IOException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//	}
//
//	private class BotonCargarCuentaActionListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			try
//			{
//				new VentanaCargaNuevaCuentaDeEmpresa();
//
//			} 
//			catch (IOException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		}
//	}
//	
//	private class BotonCargarMetodologiaActionListener implements ActionListener 
//	{
//		public void actionPerformed(ActionEvent e) 
//		{
//			new VentanaCargarMetodologia();
//		}
//	}
//	
//
//
//	private class EscuchaVentana extends WindowAdapter
//	{
//		public void windowClosing(WindowEvent e)
//		{
//			setVisible(false);
//			dispose();
//			System.exit(0);
//		}
//	}
//
//	public static void main(String[] args)
//	{
//		File f = new File("config.cfg");
//		System.out.println("Verifico si existe config...");
//		if(!f.exists() && !f.isDirectory()) { 
//			System.out.println("No existe config, se debe crear una nueva");
//			VentanaConfig ventanaConfig = new VentanaConfig();
//			ventanaConfig.setVisible(true);
//		}
//		VentanaPrincipal ventana = new VentanaPrincipal();
//	}
//}