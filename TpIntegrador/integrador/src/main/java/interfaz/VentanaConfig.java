package interfaz;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.List;
//import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class VentanaConfig extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dirIndicadores;
	private JTextField dirEmpresas;
	private JButton btnCargar;
	private JTextField dirMetodologias;
	private JTextField dirUsuarios;

	/**
	 * Launch the application.
	 */
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfig frame = new VentanaConfig();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	
	public VentanaConfig() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpresas = new JLabel("Empresas:");
		lblEmpresas.setBounds(10, 45, 81, 14);
		contentPane.add(lblEmpresas);
		
		JLabel lblIndicadores = new JLabel("Indicadores:");
		lblIndicadores.setBounds(10, 100, 81, 14);
		contentPane.add(lblIndicadores);
		
		dirIndicadores = new JTextField();
		dirIndicadores.setBounds(101, 97, 323, 20);
		contentPane.add(dirIndicadores);
		dirIndicadores.setColumns(10);
		
		dirEmpresas = new JTextField();
		dirEmpresas.setBounds(101, 42, 323, 20);
		contentPane.add(dirEmpresas);
		dirEmpresas.setColumns(10);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setBounds(335, 227, 89, 23);
		contentPane.add(btnCargar);
		
		JLabel lblMetodologias = new JLabel("Metodologias:");
		lblMetodologias.setBounds(10, 155, 81, 14);
		contentPane.add(lblMetodologias);
		
		dirMetodologias = new JTextField();
		dirMetodologias.setBounds(101, 152, 323, 20);
		contentPane.add(dirMetodologias);
		dirMetodologias.setColumns(10);
		
		JLabel lblUsuarios = new JLabel("Usuarios:");
		lblUsuarios.setBounds(10, 202, 81, 14);
		contentPane.add(lblUsuarios);
		
		dirUsuarios = new JTextField();
		dirUsuarios.setColumns(10);
		dirUsuarios.setBounds(101, 196, 323, 20);
		contentPane.add(dirUsuarios);
		
		this.btnCargar.addActionListener(new CargarListener());
	}
	
	public String getDireccionEmpresas(){
		return this.dirEmpresas.getText();
	}
	
	public String getDireccionIndicadores(){
		return this.dirIndicadores.getText();
	}
	
	public String getDireccionMetodologias(){
		return this.dirMetodologias.getText();
	}
	public String getDireccionuUsuarios(){
		return this.dirUsuarios.getText();
	}
	
	class CargarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Config config = new Config("config.cfg");
			config.setEmpresas(VentanaConfig.this.getDireccionEmpresas());
			config.setIndicadores(VentanaConfig.this.getDireccionIndicadores());
			config.setMetodologias(VentanaConfig.this.getDireccionMetodologias());
			config.setUsuarios(VentanaConfig.this.getDireccionuUsuarios());
			config.saveConfig();
			VentanaConfig.this.borrarVentana();
		  }
		}

	public void borrarVentana() {
		setVisible(false);
		dispose();
	}
}
