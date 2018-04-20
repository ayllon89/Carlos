//package interfaz;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//
//import entities.Cuenta;
//import entities.Empresa;
//import entities.TipoElemento;
//import model.DAOEmpresa;
//import model.RepositorioDeEmpresas;
//
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//
//public class VentanaMostrar extends JFrame
//{
//
//	public VentanaMostrar() throws IOException
//	{
//
//		setVisible(true);
//		setSize(450, 300);
//		getContentPane().setLayout(null);
//
//		DAOEmpresa dao = new DAOEmpresa();
//		dao.setFilePath(new Config("config.cfg").getEmpresas());
//		RepositorioDeEmpresas repoEmpresas = new RepositorioDeEmpresas(dao);
//		ArrayList<TipoElemento> empresas = repoEmpresas.listaEmpresas();
//
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(10, 11, 414, 240);
//		getContentPane().add(scrollPane);
//
//		JTable table = new JTable(new DefaultTableModel(new String[]
//		{ "Empresa", "Cuenta", "Valor", "Fecha inicial", "Fecha final" }, 0));
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//		scrollPane.setViewportView(table);
//		String datos[] = new String[5];
//
//		// LE PASO AL ARRAY LOS DATOS DEL ARRAYLIST
//		
//		for (int i = 0; i < empresas.size(); i++)
//		{
//			Empresa unaEmpresa = (Empresa) empresas.get(i);
//			List<Cuenta> cuentas = unaEmpresa.getCuentas();
//			
//			datos[0] = unaEmpresa.getNombreEmpresa();
//
//			for (int j = 0; j < cuentas.size(); j++)
//			{
//				Cuenta unaCuenta = cuentas.get(j);
//				for(int z=0; z<unaCuenta.getlistaDePeriodos().size();z++){
//					datos[1] = unaCuenta.getnombreCuenta();
//					datos[2] = Integer.toString(unaCuenta.getlistaDePeriodos().get(z).getValorCuenta());
//					datos[3] = Integer.toString(unaCuenta.getlistaDePeriodos().get(z).getFechaInicial());
//					datos[4] = Integer.toString(unaCuenta.getlistaDePeriodos().get(z).getFechaFinal());
//					model.addRow(datos);
//			}
//
//		}
//	}
//}
//}