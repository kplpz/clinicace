package com.ues.clinicace.servicio;

import java.util.List;

public interface ICRUD<T> {

    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T leerPorId(Integer id);
    boolean eliminar(T obj);
}

