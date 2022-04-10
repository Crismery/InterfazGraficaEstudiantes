package entidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import Conexion.Conexion;

public class Modelos extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Contacto> listadoContactos = null;
	private Connection con;
	private Statement st;
	
	String[]encabezados={"Usuario","Nombre","Apellido","Telefono","Email"};
	
	
	public Modelos(){
		
		//listadoContactos = conexion.mostrar();
		Conexion a = Conexion.getobj();
		con = a.getConexion();
		listadoContactos = mostrar();
	}
	
	public ArrayList<Contacto> mostrar(){
		ArrayList<Contacto> listaDeContactos = new ArrayList<Contacto>();
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("CALL PROC_RellenadoTabla()");
			while(rs.next()){
				listaDeContactos.add(new Contacto(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),null));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDeContactos;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return encabezados.length;
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return encabezados[column];
	}
	
	public void eliminar(int fila){
		/*conexion.eliminar(listadoContactos.get(fila).getCodigo());
		listadoContactos.remove(fila);		
		fireTableRowsDeleted(fila,fila);*/
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listadoContactos.size();
	}

	
	@Override
	public Object getValueAt(int x, int y) {
		// TODO Auto-generated method stub
		Object retorno = null;
		Contacto contacto = listadoContactos.get(x);
		switch (y) {
		case 0:
			retorno = contacto.getUsuario();
		break;
		case 1:
			retorno = contacto.getNombre();
			break;
		case 2:
			retorno = contacto.getApellido();
			break;
		case 3:
			retorno = contacto.getTelefono();
			break;
		case 4:
			retorno = contacto.getEmail();
			break;	
			
		}
		return retorno;
	}
	
	
	
	

}

