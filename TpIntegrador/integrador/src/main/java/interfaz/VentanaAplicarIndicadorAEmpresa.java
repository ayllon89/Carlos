//package interfaz;
//
//
//
//
//import entities.Empresa;
//import entities.Indicador;
//import entities.TipoElemento;
//import javacc.Calculator;
//import javacc.ParseException;
//import model.DAOEmpresa;
//import model.DAOIndicador;
//import model.RepositorioDeEmpresas;
//import model.RepositorioDeIndicadores;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JOptionPane;
//import javax.swing.JComboBox;
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.JTextField;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//import javax.swing.SwingConstants;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//public class VentanaAplicarIndicadorAEmpresa extends JFrame {
//
//	private JPanel contentPane;
//	private RepositorioDeIndicadores repoIndicadores;
//	private RepositorioDeEmpresas repoEmpresas;
//	private Empresa unaEmpresa;
//	private Indicador indicador;
//	private JTextField textFieldFechaInicio;
//	private JTextField textFieldFechaFinal;
//	private static Calculator parser = null;
//
//		public VentanaAplicarIndicadorAEmpresa() throws IOException {
//		
//		setVisible(true);
//		setSize(450,300);
//		getContentPane().setLayout(null);
//		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblIngreseIndicador = new JLabel("Nombre del Indicador");
//		lblIngreseIndicador.setHorizontalAlignment(SwingConstants.CENTER);
//		lblIngreseIndicador.setBounds(52, 58, 108, 14);
//		contentPane.add(lblIngreseIndicador);
//		
//		JLabel lblIngreseEmpresa = new JLabel("Nombre de la Empresa");
//		lblIngreseEmpresa.setHorizontalAlignment(SwingConstants.LEFT);
//		lblIngreseEmpresa.setBounds(52, 33, 108, 14);
//		contentPane.add(lblIngreseEmpresa);
//		
////		JButton botonVerificarEmpresa = new JButton("Verificar");
////		botonVerificarEmpresa.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				String nombreEmpresa = textFieldEmpresa.getText();
////				
////				if(empresas.stream().anyMatch(empresa -> empresa.getNombreEmpresa().equals(nombreEmpresa))){
////					JOptionPane.showMessageDialog(botonVerificarEmpresa,"Se verifica existencia de Empresa \n Ahora puede presionar Aplicar");
////				}else{
////					
////					JOptionPane.showMessageDialog(botonVerificarIndicador,"Empresa no existe");
////					}
////			}
////		});
//		//botonVerificarEmpresa.setBounds(242, 93, 89, 23);
//		//contentPane.add(botonVerificarEmpresa);
//		
//		JButton botonAplicar = new JButton("Aplicar");
//		botonAplicar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				try {
//					int valor =indicador.aplicarA(unaEmpresa, Integer.parseInt(textFieldFechaInicio.getText()),Integer.parseInt(textFieldFechaFinal.getText()),parser);
//					if(valor!=-1){
//					JOptionPane.showMessageDialog(botonAplicar, "El valor del indicador para la empresa es: " + valor);
//					}else{JOptionPane.showMessageDialog(botonAplicar, "El valor del indicador para la empresa no existe");}
//				    
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					JOptionPane.showMessageDialog(botonAplicar, "No se puede aplicar");
//				}
//				
//				
//			}
//		});
//		botonAplicar.setBounds(176, 189, 89, 23);
//		contentPane.add(botonAplicar);
//		
//		JLabel lblLuegoDeVerificar = new JLabel("Luego de completar todo los campos puede presionar el boton");
//		lblLuegoDeVerificar.setHorizontalAlignment(SwingConstants.CENTER);
//		lblLuegoDeVerificar.setBounds(37, 164, 361, 14);
//		contentPane.add(lblLuegoDeVerificar);
//		//dao
//		DAOIndicador dao = new DAOIndicador();
//	    repoIndicadores = new RepositorioDeIndicadores(dao);
//	    dao.setFilePath(new Config("config.cfg").getIndicadores());
//	    repoIndicadores.listaIndicadores();
//	    
//	    DAOEmpresa dao2 = new DAOEmpresa();
//	    repoEmpresas = new RepositorioDeEmpresas(dao2);
//	    dao2.setFilePath(new Config("config.cfg").getEmpresas());
//	    repoEmpresas.listaEmpresas();
//	    
//	    this.crearCombo(repoEmpresas);
//	    this.crearComboIndicadores(repoIndicadores);
//	    
//	    JLabel labelFechaInicial = new JLabel("Ingrese Fecha de incio");
//	    labelFechaInicial.setHorizontalAlignment(SwingConstants.CENTER);
//	    labelFechaInicial.setBounds(52, 84, 120, 14);
//	    contentPane.add(labelFechaInicial);
//	    
//	    JLabel labelFechaFinal = new JLabel("Ingrese Fecha final");
//	    labelFechaFinal.setHorizontalAlignment(SwingConstants.CENTER);
//	    labelFechaFinal.setBounds(52, 109, 108, 14);
//	    contentPane.add(labelFechaFinal);
//	    
//	    textFieldFechaInicio = new JTextField();
//	    textFieldFechaInicio.setBounds(194, 81, 169, 20);
//	    contentPane.add(textFieldFechaInicio);
//	    textFieldFechaInicio.setColumns(10);
//	    
//	    
//	    textFieldFechaFinal = new JTextField();
//	    textFieldFechaFinal.setColumns(10);
//	    textFieldFechaFinal.setBounds(194, 106, 169, 20);
//	    contentPane.add(textFieldFechaFinal);
//	    
//	    
//	    
//	    
//	    
//	    
//		
//		
//	}
//		
//		
//		//ComboBox
//		
//		
//		
//		public JComboBox<String> crearCombo(RepositorioDeEmpresas unRepositorio) throws IOException
//		{
//			JComboBox<String> comboEmpresas = new JComboBox<String>();
//			comboEmpresas.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Empresa"}));
//			comboEmpresas.setBounds(194, 30, 169, 20);
//			getContentPane().add(comboEmpresas);
//			
//			ArrayList<TipoElemento> listaEmpresas=unRepositorio.listaEmpresas();
//			
//			for (int i = 0; i < listaEmpresas.size(); i++)
//			{
//				comboEmpresas.addItem(((Empresa) listaEmpresas.get(i)).getNombreEmpresa());
//			}
//			
//			comboEmpresas.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					//Obtengo la empresa que selecciono el usuario
//					if(e.getSource()==comboEmpresas)
//					{
//						int posicion=comboEmpresas.getSelectedIndex();
//						//Aca es -1 dado que en el array del combo la posicion cero es ocupada por "Seleccione empresa"
//						unaEmpresa=(Empresa) listaEmpresas.get(posicion-1);
//					}
//
//				}
//			});
//			
//			return comboEmpresas;
//		}
//		public JComboBox<String> crearComboIndicadores(RepositorioDeIndicadores unRepositorio) throws IOException
//		{
//			JComboBox<String> comboIndicadores = new JComboBox<String>();
//			comboIndicadores.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Indicador"}));
//			comboIndicadores.setBounds(194, 55, 169, 20);
//			getContentPane().add(comboIndicadores);
//			
//			ArrayList<TipoElemento> listaIndicadores=unRepositorio.listaIndicadores();
//			
//			for (int i = 0; i < listaIndicadores.size(); i++)
//			{
//				comboIndicadores.addItem(((Indicador) listaIndicadores.get(i)).getNombreIndicador());
//			}
//			
//			comboIndicadores.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					//Obtengo la empresa que selecciono el usuario
//					if(e.getSource()==comboIndicadores)
//					{
//						int posicion=comboIndicadores.getSelectedIndex();
//						//Aca es -1 dado que en el array del combo la posicion cero es ocupada por "Seleccione empresa"
//						indicador=(Indicador) listaIndicadores.get(posicion-1);
//					}
//
//				}
//			});
//			
//			return comboIndicadores;
//		}
//		
//	}
//
//
//
