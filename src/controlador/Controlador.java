package controlador;
/**
 * @autor Duvan Botero
 */
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Gestion;
import modelo.Cliente;

public class Controlador {
    private Gestion gestion;
    
    public DefaultTableModel listar(){
        DefaultTableModel modelo = new DefaultTableModel();
        gestion = new Gestion();
        List<Cliente> usuarios = new ArrayList<>();
        modelo = new DefaultTableModel(){
          @Override
          public boolean isCellEditable(int row, int column){
              return false;
          }
        };
        modelo.addColumn("Documento");
        modelo.addColumn("Direccion");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        usuarios = gestion.consultar();
        String registros[] = new String[4];
        for(Cliente us:usuarios){
            registros[0] = us.getDocumento();
            registros[1]= us.getDireccion();
            registros[2] = us.getNombre();
            registros[3] = us.getTelefono();
            modelo.addRow(registros);
        }
        return modelo;
    }
    
    public int agregar(Cliente usuario){
        int resultado = 0;
        gestion = new Gestion();
        resultado = gestion.guardar(usuario);
        return resultado;
    }
    
    public boolean eliminar(String documento){
        boolean res = false;
        gestion = new Gestion();
        res = gestion.eliminarPorDocumento(documento);
        return res;
    }
    
    
}
