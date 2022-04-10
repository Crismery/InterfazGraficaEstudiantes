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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import Conexion.Conexion;


public class Registro extends JFrame {

	private MyTextField txtnombre;
	private MyTextField txtusuario_1;
	private MyTextField txtusuario_1_1;
	private MyTextField txtusuario_1_2;
	private MyTextField txtusuario_1_3;
	
	private MyPassfield txtpasswd;
	private MyPassfield txtpasswd_1;
	
	
	private Connection con;
	private Statement st;
	private String consultausuario = "CALL PROC_consultausuario()";

	public Registro() {
		
		//conectados la base de datos
		Conexion a = Conexion.getobj();
		con = a.getConexion();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loggin.class.getResource("/Recursos/user.png")));
		setTitle("Registro");
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
		txtnombre.setBounds(71, 35, 365, 33);
		panel.add(txtnombre);
		
		txtusuario_1 = new MyTextField();
		txtusuario_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1.setHint("Nombre");
		txtusuario_1.setBounds(71, 79, 365, 33);
		panel.add(txtusuario_1);
		
		txtusuario_1_1 = new MyTextField();
		txtusuario_1_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_1.setHint("Apellido");
		txtusuario_1_1.setBounds(71, 123, 365, 33);
		panel.add(txtusuario_1_1);
		
		txtusuario_1_2 = new MyTextField();
		txtusuario_1_2.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_2.setHint("Telefono");
		txtusuario_1_2.setBounds(71, 167, 365, 33);
		panel.add(txtusuario_1_2);
		
		txtusuario_1_3 = new MyTextField();
		txtusuario_1_3.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario_1_3.setHint("Email");
		txtusuario_1_3.setBounds(71, 211, 365, 33);
		panel.add(txtusuario_1_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 255));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		txtpasswd = new MyPassfield();
		txtpasswd.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpasswd.setHint("Contraseņa");
		txtpasswd.setBounds(71, 255, 365, 33);
		panel.add(txtpasswd);
		
		txtpasswd_1 = new MyPassfield();
		txtpasswd_1.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpasswd_1.setHint("Confirmar Contraseņa");
		txtpasswd_1.setBounds(71, 299, 365, 33);
		panel.add(txtpasswd_1);
		
		JButton bntregistro = new JButton("Registro");
		bntregistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		bntregistro.setBounds(106, 343, 300, 33);
		bntregistro.setForeground(new Color(255, 0, 255));
		bntregistro.setBackground(new Color(255, 204, 255));
		Border border = BorderFactory.createLineBorder(null);
		bntregistro.setBorder(border);
		bntregistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrar();
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
		
		JLabel lblNewLabel_1 = new JLabel("Registrate e inicia a\r\n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 165, 324, 63);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hola, que tal?");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.BOLD, 23));
		lblNewLabel_1_1.setBounds(10, 109, 324, 63);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("\r\ntrabajar con nosotros.\r\n\r\n");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 191, 324, 63);
		panel_1.add(lblNewLabel_1_2);
	}
	
	void Registrar() {
		
		boolean cond = true;
    	String [] datos = {txtnombre.getText(),txtusuario_1.getText(),txtusuario_1_1.getText(),
    			txtusuario_1_2.getText(),txtusuario_1_3.getText(),txtpasswd.getText() };
    	
    	//validando que todos los campos esten llenos
    	for (String a : datos) {
			if (a.equals("")) {
				cond=false;
			}
		}
    	boolean condtemp = usuarioexistente();
    	if (cond && condtemp) {
    		//validar que la clave y su confirmacion sean la mismas.
    		
    		if (txtpasswd.getText().equals(txtpasswd_1.getText())) {
    			
    			InsertarRegistro(datos);
    	    	Loggin login = new Loggin();
    	    	login.setLocationRelativeTo(null);
    	    	login.setVisible(true);
    			this.dispose();
    			
			}else {
				JOptionPane.showMessageDialog(null, "Las contraseņas no coinciden.");
			}
    		
			
		}else {
			if (condtemp) {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos.");
			}
		
		}
    	
		
	}
	
	
	private boolean usuarioexistente(){
    	boolean cond = true;
    	Statement st;
    	
    	try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("CALL PROC_consultausuario()");
			while(rs.next()) {
				String usuariotemp =  rs.getString(1);
				if (txtnombre.getText().equalsIgnoreCase(usuariotemp)) {
					cond=false;
				}
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
    	if (cond == false) {
			JOptionPane.showMessageDialog(null,"El usuario "+txtnombre.getText()+" ya esta en uso.");
		}
    	return cond;
    }
	
	private void InsertarRegistro(String[] datos) {
    	
    	String consulta = "CALL PROC_insertarusuario(?,?,?,?,?,?)";
    	
    	try {
    		PreparedStatement sentencia = con.prepareStatement(consulta);
			sentencia.setString(1, datos[0]);
			sentencia.setString(2, datos[1]);
			sentencia.setString(3, datos[2]);
			sentencia.setString(4, datos[3]);
			sentencia.setString(5, datos[4]);
			sentencia.setString(6, datos[5]);
			sentencia.executeUpdate();
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
    	
    }
	
	void Salir() {
		Loggin login = new Loggin();
		login.setLocationRelativeTo(null);
		login.setVisible(true);
		this.dispose();
	}

}
