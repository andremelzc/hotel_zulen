package Persistencia;

import java.io.IOException;
import java.util.List;

public interface IRepository<T> {
    
    void crear(T objeto);
    T obtener(int id);
    void actualizar(T objeto);
    void eliminar(int id);
    
}
