package Persistencia;

import java.io.IOException;
import java.util.List;

public interface IRepository<T> {
    List<T> cargarCSVtoLista(String archivo) throws IOException;
    void cargarListaToCSV(List<T> lista,String archivo);
    void cargarRegistroToCSV(T elemento,String archivo);
}
