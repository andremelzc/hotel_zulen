/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import java.util.List;

/**
 *
 * @author Suyco
 */
public interface IActualizar <T> {
    // Crear (agregar un nuevo elemento a la lista)
    void agregar(List<T> lista, T elemento);

    // Actualizar (modificar un elemento existente en la lista)
    void actualizar(List<T> lista, T elemento);

    // Eliminar (eliminar un elemento de la lista por su ID, o alguna propiedad identificadora)
    void eliminar(List<T> lista, T elemento);
    
    void mostrarLista(List<T> lista);
    
    T obtenerPorId(List<T> lista, int id); // Método para obtener un objeto por ID
    
    
}
