package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import Conexion.Conexion;
import entidad.Estudiante;

public class Actualizador extends JFrame {

	private MyTextField txtnombre;
	private MyTextField txtusuario_1;
	private MyTextField txtusuario_1_1;
	private MyTextField txtusuario_1_2;
	private MyTextField txtusuario_1_3;
	
	private MyPassfield txtpasswd;
	private MyPassfield txtpasswd_1;
	
	
	private Connection con;

	public Actualizador() {
		
		//conectados la base de datos
		Conexion a = Conexion.getobj();
		con = a.getConexion();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loggin.class.getResource("/Recursos/user.png")));
		setTitle("Actualizar Datos del estudiante");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 482);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(550,0));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		
		txtnombre = new MyTextField();
		txtnombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtnombre.setHint("Usuario");
		txtnombre.setText(Estudiante.usuario);
		txtnombre.setEditable(false);
		txtnombre.setBounds(71, 35, 365, 33);
		panel.add(txtnombre);
		
		txtusuario_1 = new MyTextField();
		txtusuario_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1.setHint("Nombre");
		txtusuario_1.setText(Estudiante.nombre);
		txtusuario_1.setBounds(71, 79, 365, 33);
		panel.add(txtusuario_1);
		
		txtusuario_1_1 = new MyTextField();
		txtusuario_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_1.setHint("Apellido");
		txtusuario_1_1.setText(Estudiante.apellido);
		txtusuario_1_1.setBounds(71, 123, 365, 33);
		panel.add(txtusuario_1_1);
		
		txtusuario_1_2 = new MyTextField();
		txtusuario_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_2.setHint("Telefono");
		txtusuario_1_2.setText(Estudiante.telefono);
		txtusuario_1_2.setBounds(71, 167, 365, 33);
		panel.add(txtusuario_1_2);
		
		txtusuario_1_3 = new MyTextField();
		txtusuario_1_3.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_3.setHint("Email");
		txtusuario_1_3.setText(Estudiante.email);
		txtusuario_1_3.setBounds(71, 211, 365, 33);
		panel.add(txtusuario_1_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 255));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		txtpasswd = new MyPassfield();
		txtpasswd.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpasswd.setHint("Contraseña");
		txtpasswd.setBounds(71, 255, 365, 33);
		panel.add(txtpasswd);
		
		txtpasswd_1 = new MyPassfield();
		txtpasswd_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpasswd_1.setHint("Confirmar Contraseña");
		txtpasswd_1.setBounds(71, 299, 365, 33);
		panel.add(txtpasswd_1);
		
		JButton bntregistro = new JButton("Actualizar");
		bntregistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		bntregistro.setBounds(106, 343, 300, 33);
		bntregistro.setForeground(new Color(255, 0, 255));
		bntregistro.setBackground(new Color(255, 204, 255));
		Border border = BorderFactory.createLineBorder(null);
		bntregistro.setBorder(border);
		bntregistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Actualizar();
			}
		});
		panel.add(bntregistro);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.MAGENTA);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBackground(new Color(255, 204, 255));
		btnSalir.setBounds(106, 387, 300, 33);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Salir();
			}
		});
		panel.add(btnSalir);
		
		JLabel lblNewLabel_1 = new JLabel("Cambie los campos que");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 165, 324, 63);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Actualizando...");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(10, 109, 324, 63);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("desea actualizar.\r\n");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 191, 324, 63);
		panel_1.add(lblNewLabel_1_2);
		
		setLocationRelativeTo(null);
		
	}
	
	void Actualizar() {
		
		boolean cond = true;
		String [] datos = {txtusuario_1.getText(),txtusuario_1_1.getText(),
    			txtusuario_1_2.getText(),txtusuario_1_3.getText(),txtpasswd.getText(),txtnombre.getText()};
    	
    	//validando que todos los campos esten llenos
    	for (String a : datos) {
			if (a.equals("")) {
				cond=false;
			}
		}
    	if (cond) {
    		//validar que la clave y su confirmacion sean la mismas.
    		if (txtpasswd.getText().equals(txtpasswd_1.getText())) {
    			ActulizarDatos(datos);
    	    	
			}else {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
			}
    		
			
		}else {
			JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos.");
		}
    	
		
	}
	
 private void ActulizarDatos(String[] datos) {
    	
		String consulta = "CALL PROC_ActualizarUsuario(?,?,?,?,?,?)";

		try {
			
			PreparedStatement sentencia = con.prepareStatement(consulta);
			sentencia.setString(1, datos[0]);
			sentencia.setString(2, datos[1]);
			sentencia.setString(3, datos[2]);
			sentencia.setString(4, datos[3]);
			sentencia.setString(5, datos[4]);
			sentencia.setString(6, datos[5]);
			sentencia.executeUpdate();
			
			for (String s : datos) {
				System.out.println(s);
			}
			
			this.dispose();
			new ListaEstudiantes().setVisible(true);;
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}

	}
	
	
	void Salir() {
		this.dispose();
		new ListaEstudiantes().setVisible(true);;
	}

}
