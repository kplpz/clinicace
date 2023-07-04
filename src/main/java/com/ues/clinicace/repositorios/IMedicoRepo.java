package com.ues.clinicace.repositorios;

import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.reportesDtos.IMedicocantidadEspe;
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


    @Query(value="SELECT\n" +
            "\te.nombre_especiadad AS nombreEspecialidad,\n" +
            "\tCOUNT( m.id_medico ) AS cantidadMedicos \n" +
            "FROM\n" +
            "\tmedico m\n" +
            "\tINNER JOIN consulta c ON m.id_medico = c.id_medico\n" +
            "\tINNER JOIN especialidad e ON c.id_especialidad = e.id_especialidad \n" +
            "GROUP BY\n" +
            "\te.id_especialidad \n" +
            "ORDER BY\n" +
            "\tcantidadMedicos DESC", nativeQuery = true)
    List<IMedicocantidadEspe> cantidadMedicoxEspePie();
}

