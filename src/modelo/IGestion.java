package modelo;

import java.util.List;

public interface IGestion {
    
    public List<Cliente> consultar();
    
    public int guardar(Cliente usuario);
    
    public boolean eliminarPorDocumento(String documento);
}
