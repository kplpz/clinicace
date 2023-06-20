package com.ues.clinicace.repositorios;

import com.ues.clinicace.dtos.IConsMedicoDtoRep;
import com.ues.clinicace.modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer> {
    /* SQL nativo para las consultas médicas */
    @Query(value=
    "SELECT consulta.fecha_consulta as fechaConsulta,\n" +
            "consulta.hora_consulta as horaConsulta, consulta.num_consultorio as\n" +
            "numConsultorio, especialidad.nombre_especiadad as nombreEspecialidad,\n" +
            "concat_ws(' ', medico.nombre_medico, medico.apellido_medico) as\n" +
            "nombreCompletoMedico, concat_ws(' ', paciente.nombre_paciente,\n" +
            "paciente.apellido_paciente) as nombreCompletoPaciente FROM consulta\n" +
            "INNER JOIN especialidad ON consulta.id_especialidad =\n" +
            "especialidad.id_especialidad INNER JOIN medico ON consulta.id_medico =\n" +
            "medico.id_medico INNER JOIN paciente ON consulta.id_paciente =\n" +
            "paciente.id_paciente ORDER BY\n" +
            "especialidad.nombre_especiadad", nativeQuery=true
    )
    List<IConsMedicoDtoRep> totalConsultasPacientes();
}

