package com.ues.clinicace.servicioImpl;

import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.repositorios.IMedicoRepo;
import com.ues.clinicace.servicio.IMedicoService;
import com.ues.clinicace.servicio.IReportesServicePDF;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class MedicoServiceImpl implements IMedicoService {

    private final IMedicoRepo servicioMedico;
    private final IReportesServicePDF servicioReportes;

    @Autowired
    public MedicoServiceImpl(IMedicoRepo servicioMedico, IReportesServicePDF servicioReportes) {
        // TODO Auto-generated constructor stub
        this.servicioMedico = servicioMedico;
        this.servicioReportes = servicioReportes;
    }

    @Override
    public Medico registrar(Medico obj) {
        // TODO Auto-generated method stub
        return this.servicioMedico.save(obj);
    }

    @Override
    public Medico modificar(Medico obj) {
        // TODO Auto-generated method stub
        //return null;
        return this.servicioMedico.save(obj);
    }

    @Override
    public List<Medico> listar() {
        List<Medico> listMedicos= this.servicioMedico.findAll();
        return listMedicos;
    }

    @Override
    public Medico leerPorId(Integer id) {
        // TODO Auto-generated method stub
        return this.servicioMedico.findById(id).get();
    }

    @Override
    public boolean eliminar(Medico obj) {
        // TODO Auto-generated method stub
        try {
            this.servicioMedico.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception

            return false;
        }

    }

    @Override
    public void generarReportePorConsultaPie(HttpServletResponse response) throws IOException {
    final InputStream stream = this.getClass().getResourceAsStream("/reports/MedicosxEspecialidad.jrxml");
    this.servicioReportes.generarReporte(stream, response, servicioMedico.cantidadMedicoxEspePie());
    }

    @Override
    public void generarReporteMedicos(HttpServletResponse response) throws IOException {
        final InputStream stream = this.getClass().getResourceAsStream("/reports/PacienteRepo.jrxml");
        this.servicioReportes.generarReporte(stream, response, listar());
    }
    /*@Override
    public List<Medico> buscarMedico(String filtro) {
        // TODO Auto-generated method stub
        return  this.servicioMedico.buscarMedico(filtro) ;
    }*/

}
