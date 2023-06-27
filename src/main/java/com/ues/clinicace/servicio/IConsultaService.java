package com.ues.clinicace.servicio;

import com.ues.clinicace.modelo.Consulta;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface IConsultaService extends ICRUD<Consulta>  {
    void generarReportePorConsulta(HttpServletResponse response,
                                   int codigoEspecialidadParam,
                                   String fechaConsultaParam) throws IOException;

    void generarReportePorConsultaGraficoBarra(HttpServletResponse response)throws Exception;
}
