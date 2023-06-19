package com.ues.clinicace.servicioImpl;

import com.ues.clinicace.modelo.Especialidad;
import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.repositorios.IEspecialidadRepo;
import com.ues.clinicace.repositorios.IPacienteRepo;
import com.ues.clinicace.servicio.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    private final IEspecialidadRepo servicioEspecialidad;

    @Autowired
    public EspecialidadServiceImpl(IEspecialidadRepo servicioEspecialidad) {
        // TODO Auto-generated constructor stub
        this.servicioEspecialidad = servicioEspecialidad;
    }
    @Override
    public Especialidad registrar(Especialidad obj) {
        return this.servicioEspecialidad.save(obj);
    }

    @Override
    public Especialidad modificar(Especialidad obj) {
        return this.servicioEspecialidad.save(obj);
    }

    @Override
    public List<Especialidad> listar() {
        List<Especialidad> listEspecialidad= this.servicioEspecialidad.findAll();
        return listEspecialidad;
    }

    @Override
    public Especialidad leerPorId(Integer id) {
        return this.servicioEspecialidad.findById(id).get();
    }

    @Override
    public boolean eliminar(Especialidad obj) {
        try {
            this.servicioEspecialidad.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception

            return false;
        }
    }
}
