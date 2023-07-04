package com.ues.clinicace.servicio;

import com.ues.clinicace.modelo.Consulta;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IConsultaService extends ICRUD<Consulta>  {
    void generarReportePorConsultaParam(HttpServletResponse response,
                                   int codigoEspecialidadParam,
                                   String fechaConsultaParam) throws IOException;
    void generarReportePornumConsultaParam(HttpServletResponse response,
                                        int numConsultorio) throws IOException;

    void generarReportePorConsulta(HttpServletResponse response) throws IOException;

    void generarReportePorConsultaGraficoBarra(HttpServletResponse response)throws Exception;
}
