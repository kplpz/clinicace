package com.ues.clinicace.repositorios;

import com.ues.clinicace.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMedicoRepo extends JpaRepository<Medico,Integer> {

// para el filtro de busqueda

    @Query("from Medico m where (LOWER(m.nombreMedico) like %:filtro% or LOWER(m.apellidoMedico) like %:filtro%)")
    List<Medico> buscarMedico(@Param("filtro") String filtro);
}

