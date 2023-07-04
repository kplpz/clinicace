package com.ues.clinicace.servicioImpl;

import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.repositorios.IPacienteRepo;
import com.ues.clinicace.servicio.IPacienteService;
import com.ues.clinicace.servicio.IReportesServicePDF;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {
    private final IPacienteRepo servicioPaciente;
    private final IReportesServicePDF serviceReporte;

    @Autowired
    public PacienteServiceImpl(IPacienteRepo servicioMedico, IReportesServicePDF serviceReporte) {
        // TODO Auto-generated constructor stub
        this.servicioPaciente = servicioMedico;
        this.serviceReporte = serviceReporte;
    }

    @Override
    public Paciente registrar(Paciente obj) {
        // TODO Auto-generated method stub
        return this.servicioPaciente.save(obj);
    }

    @Override
    public Paciente modificar(Paciente obj) {
        // TODO Auto-generated method stub
        //return null;
        return this.servicioPaciente.save(obj);
    }

    @Override
    public List<Paciente> listar() {
        List<Paciente> listMedicos= this.servicioPaciente.findAll();
        return listMedicos;
    }

    @Override
    public Paciente leerPorId(Integer id) {
        // TODO Auto-generated method stub
        return this.servicioPaciente.findById(id).get();
    }

    @Override
    public boolean eliminar(Paciente obj) {
        // TODO Auto-generated method stub
        try {
            this.servicioPaciente.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception

            return false;
        }

    }
    @Override
    public List<Paciente> buscarPaciente(String filtro) {
        // TODO Auto-generated method stub
        return  this.servicioPaciente.buscarPaciente(filtro) ;
    }

    @Override
    public void generarReportePaciente(HttpServletResponse response) throws IOException {
        final InputStream stream = this.getClass().getResourceAsStream(("/reports/ReportePaciente.jrxml"));
        this.serviceReporte.generarReporte(stream, response, listar());
    }

    @Override
    public void generarReportePorConsultaLine(HttpServletResponse response) throws IOException {
        final InputStream stream = this.getClass().getResourceAsStream(("/reports/CantPacientexEspe.jrxml"));
        this.serviceReporte.generarReporte(stream, response, this.servicioPaciente.cantidadPacientexEspeLine());
    }


}
