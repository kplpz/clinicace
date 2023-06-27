package com.ues.clinicace.servicioImpl;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ues.clinicace.servicio.IReportesServicePDF;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;

@Service
public class ReportesServicePDFImpl<T> implements IReportesServicePDF<T> {
    @Override
    public void generarReporte(InputStream stream, HttpServletResponse response, List<T> data) throws IOException {
        // TODO Auto-generated method stub
        try {
            final JasperReport report = JasperCompileManager.compileReport(stream);
            final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(data);
            final Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Admin");

            /**************************************************/
            //CON PARAMETROS DE idEspecialidad y fechaConsulta
            final File imgLogo = ResourceUtils.getFile("classpath:images/logobufmpues.jpg");
            parameters.put("idEspecialidadParam", "idEspecialidadParam");
            parameters.put("fechaConsultaParam", "fechaConsultParam");
            parameters.put("imgLogo", new FileInputStream(imgLogo));



            final JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, source);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-disposition", "inline; filename=App_report_en.pdf");
            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
