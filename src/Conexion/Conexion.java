package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

//Creacion del objeto a usar en todas las conexiones usando el patron de diseno SINGLETON
public class Conexion {
	
	private static Conexion obj = new Conexion();
	private Connection conexion;
	
	private Conexion() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/interfazgraciaestudiantes", "root", "");
			System.out.println("Conexion exitosa.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de datos.");
		}
	}
	
	public static Conexion getobj() {
		
		return obj;
		
	}
	
	public Connection getConexion() {
		return conexion;
	}

}
