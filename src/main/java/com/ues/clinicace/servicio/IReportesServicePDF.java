package com.ues.clinicace.servicio;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IReportesServicePDF<T> {

    void generarReporte(InputStream stream, HttpServletResponse response, List<T> data) throws IOException;
    void generarReporteParam(InputStream stream, HttpServletResponse response, List<T> data) throws IOException;
    void generarReportenumConsultorioParam(InputStream stream, HttpServletResponse response, List<T> data) throws IOException;


}
