package com.ues.clinicace.servicio;

import java.io.IOException;
import java.util.List;

import com.ues.clinicace.modelo.Medico;
import jakarta.servlet.http.HttpServletResponse;

public interface IMedicoService extends ICRUD<Medico> {
    /*List<Medico> buscarMedico(String filtro);*/


    void generarReportePorConsultaPie(HttpServletResponse response) throws IOException;

    void generarReporteMedicos(HttpServletResponse response) throws IOException;
}