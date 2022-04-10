package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import Conexion.Conexion;
import entidad.Estudiante;
import entidad.Modelos;

public class ListaEstudiantes extends JFrame {
	
	
	private Connection con;
	private Statement st;
	private String consultausuario = "CALL PROC_consultausuario()";
	private JTable tblContactos = null;
	Modelos modelo = new Modelos();

	public ListaEstudiantes() {
		
		//conectados la base de datos
		Conexion a = Conexion.getobj();
		con = a.getConexion();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loggin.class.getResource("/Recursos/user.png")));
		setTitle("Lista Estudiantil");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 482);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 255));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(205, 11, 552, 333);
		scrollPane.setViewportView(getTblContactos());
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Informacion de los estudiantes");
		lblNewLabel.setBackground(Color.MAGENTA);
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 23));
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JPanel panel_2_1 = new JPanel();
		panel_2.add(panel_2_1);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agregar();
			}
		});
		btnNuevo.setForeground(Color.MAGENTA);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNuevo.setBackground(new Color(255, 204, 255));
		panel_2_1.add(btnNuevo);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
			}
		});
		btnActualizar.setForeground(Color.MAGENTA);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnActualizar.setBackground(new Color(255, 204, 255));
		panel_2_1.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar();
			}
		});
		btnEliminar.setForeground(Color.MAGENTA);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(255, 204, 255));
		panel_2_1.add(btnEliminar);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cerrarsesion();
			}
		});
		btnCerrarSesion.setForeground(Color.MAGENTA);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrarSesion.setBackground(new Color(255, 204, 255));
		panel_2_1.add(btnCerrarSesion);
		Border border = BorderFactory.createLineBorder(null);
		
		setLocationRelativeTo(null);
	}
	
	
	private JTable getTblContactos() {
		if (tblContactos == null) {
			tblContactos = new JTable();
			tblContactos.setModel(modelo);
		}
		return tblContactos;
	}
	
	void Agregar() {
		
		Registro ventanaRegistro = new Registro();
		ventanaRegistro.setLocationRelativeTo(null);
		ventanaRegistro.setVisible(true);
		this.dispose();
		
		
	}
	
	String datoscolumna(int a ){
		
		return tblContactos.getValueAt(tblContactos.getSelectedRow(), a).toString();
		
	}

	void Eliminar() {
		
		int temp = tblContactos.getSelectedRow();
		if (temp == -1) {
			return;
		}
		
		String consulta = "CALL PROC_eliminando(?)";
		try {
			PreparedStatement sentencia = con.prepareStatement(consulta);
			sentencia.setString(1, datoscolumna(0));
			sentencia.executeUpdate();
			
			this.dispose();
			
			new ListaEstudiantes().setVisible(true);;
			

		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
		



	void Actualizar() {
		
		int temp = tblContactos.getSelectedRow();
		if (temp == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione el estudiante que desea actualizar");
			return;
		}
		
		
		Estudiante.usuario = datoscolumna(0);
		Estudiante.nombre = datoscolumna(1);
		Estudiante.apellido = datoscolumna(2);
		Estudiante.telefono = datoscolumna(3);
		Estudiante.email = datoscolumna(4);
		
		new Actualizador().setVisible(true);;
		this.dispose();
		

	}

	void Cerrarsesion() {
		Loggin login = new Loggin();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		this.dispose();

	}
	
}
