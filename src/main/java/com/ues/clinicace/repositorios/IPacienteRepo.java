package com.ues.clinicace.repositorios;

import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.reportesDtos.IMedicocantidadEspe;
import com.ues.clinicace.reportesDtos.IPacientecantEspe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepo extends JpaRepository<Paciente,Integer> {

    @Query("from Paciente p WHERE (LOWER(p.nombrePaciente) like %:filtro% or lower(p.apellidoPaciente) like %:filtro%)")
    List<Paciente> buscarPaciente(@Param("filtro") String filtro);


    @Query(value="SELECT\n" +
            "\te.nombre_especiadad AS nombreEspecialidad,\n" +
            "\tCOUNT( p.id_paciente ) AS cantidadPaciente \n" +
            "FROM\n" +
            "\tpaciente p\n" +
            "\tINNER JOIN consulta c ON p.id_paciente = c.id_paciente\n" +
            "\tINNER JOIN especialidad e ON c.id_especialidad = e.id_especialidad \n" +
            "GROUP BY\n" +
            "\te.id_especialidad \n" +
            "ORDER BY\n" +
            "\tcantidadPaciente DESC", nativeQuery = true)
    List<IPacientecantEspe> cantidadPacientexEspeLine();
}
