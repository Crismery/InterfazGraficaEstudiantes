package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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


public class Loggin extends JFrame {

	private MyTextField txtusuario;
	private MyPassfield txtpasswd;
	private Connection con;
	private Statement st;
	private String consultausuario = "CALL PROC_consultausuario()";

	public Loggin() {
		
		//conectados la base de datos
		Conexion a = Conexion.getobj();
		con = a.getConexion();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loggin.class.getResource("/Recursos/user.png")));
		setTitle("Iniciar Sesion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 482);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(550,0));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Iniciar Sesion");
		lblNewLabel.setBounds(0, 77, 550, 63);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 255));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
		panel.add(lblNewLabel);
		
		
		txtusuario = new MyTextField();
		txtusuario.setPrefixIcon(new ImageIcon(getClass().getResource("/Recursos/mail.png")));
		txtusuario.setFont(new Font("Arial", Font.PLAIN, 16));
		txtusuario.setHint("Usuario");
		txtusuario.setBounds(71, 151, 365, 50);
		panel.add(txtusuario);
		
		txtpasswd = new MyPassfield();
		txtpasswd.setPrefixIcon(new ImageIcon(getClass().getResource("/Recursos/pass.png")));
		txtpasswd.setFont(new Font("Arial", Font.PLAIN, 16));
		txtpasswd.setHint("Contraseña");
		txtpasswd.setBounds(71, 212, 365, 43);
		panel.add(txtpasswd);
		
		
		
		JButton bntsesion = new JButton("Entrar");
		bntsesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		bntsesion.setBounds(71, 274, 365, 43);
		bntsesion.setForeground(new Color(255, 0, 255));
		bntsesion.setBackground(new Color(255, 204, 255));
		Border border = BorderFactory.createLineBorder(null);
		bntsesion.setBorder(border);
		bntsesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarsesision();
			}
		});
		panel.add(bntsesion);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 153, 255));
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
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
		
		JButton btnNewButton = new JButton("Registrarme");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrarme();
			}
		});
		btnNewButton.setForeground(new Color(255, 0, 255));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorder(border);
		btnNewButton.setBounds(91, 265, 193, 39);
		panel_1.add(btnNewButton);
	}

	
	void iniciarsesision(){
		
		String clave = txtpasswd.getText();
		String usuario = txtusuario.getText();
    	if (!clave.equals("") && !usuario.equals("")) {
    		
    		try {
    			st = con.createStatement();
    			ResultSet rs = st.executeQuery(consultausuario);
    			boolean condicion = true;
    			while(rs.next()) {
    				String usuariotemp =  rs.getString(1);
    				String clavetemp =  rs.getString(2);
    				if (usuario.equalsIgnoreCase(usuariotemp) && clave.equals(clavetemp)) {
    					new ListaEstudiantes().setVisible(true);;
    					this.dispose();
    					condicion=false;
    				}
    			}
    			if (condicion) {
    				JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
    				txtpasswd.setText("");
    				txtusuario.setText("");
    				txtusuario.requestFocus();
    			}
    			
    		} catch (SQLException e1) {
    			System.out.println(e1.getMessage());
    		}
			
		}else {
			JOptionPane.showMessageDialog(null, "Debes rellenar ambos campos.");
		}
		
		
	}
	
	void Registrarme() {
		
		Registro ventanaRegistro = new Registro();
		ventanaRegistro.setLocationRelativeTo(null);
		ventanaRegistro.setVisible(true);
		this.dispose();
		
	}
	
}
