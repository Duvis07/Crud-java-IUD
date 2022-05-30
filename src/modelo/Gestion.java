package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Mensaje;

public class Gestion implements IGestion{

    private Conexion conn;
    private PreparedStatement pStm;
    
    @Override
    public List<Cliente> consultar() {
        List<Cliente> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        conn = new Conexion();
        try {
            Statement st = conn.getCon().createStatement();
            ResultSet rS = st.executeQuery(sql);
            while(rS.next()){
                String documento = rS.getString("documento");
              String direccion = rS.getString("direccion");
                String nombre = rS.getString("nombre");
                String telefono = rS.getString("telefono");
                Cliente usuario = new Cliente(documento, direccion, nombre, telefono);
                usuarios.add(usuario);
            }
            st.close();
            rS.close();
        } catch (SQLException ex) {
            Mensaje.mensajeError("Error", ex.getMessage());
        }
        return usuarios;
    }

    @Override
    public int guardar(Cliente usuario) {
        int resultado = 0;
        conn = new Conexion();
        String sql = "INSERT INTO usuarios "
                + "(documento,direccion,nombre,telefono) " +
                "VALUES (?,?,?,?)";
        try {
            pStm = conn.getCon().prepareStatement(sql);
            pStm.setString(1, usuario.getDocumento());
            pStm.setString(2, usuario.getDireccion());
            pStm.setString(3, usuario.getNombre());
            pStm.setString(4, usuario.getTelefono());
            resultado = pStm.executeUpdate();
        } catch (SQLException ex) {
            Mensaje.mensajeError("Error", ex.getMessage());
        }
        return resultado;
    }

    @Override
    public boolean eliminarPorDocumento(String documento) {
         boolean resultado = false;
         conn = new Conexion();
         String sql = "DELETE FROM usuarios WHERE documento=?";         
        try {
            pStm = conn.getCon().prepareStatement(sql);
            pStm.setString(1, documento);
            pStm.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            Mensaje.mensajeError("Error", ex.getMessage());
        }
         return resultado;
    }    
}
