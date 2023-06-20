package com.ues.clinicace.servicio;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IReporteServicioEXCEL {

    void generateExcel(HttpServletResponse response) throws IOException;
}
