package com.ues.clinicace.servicio;

import com.ues.clinicace.modelo.Paciente;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface IPacienteService extends ICRUD<Paciente>{

    List<Paciente>buscarPaciente(String filtro);
    void generarReportePaciente(HttpServletResponse response)throws IOException;

    void generarReportePorConsultaLine(HttpServletResponse response) throws IOException;
}
