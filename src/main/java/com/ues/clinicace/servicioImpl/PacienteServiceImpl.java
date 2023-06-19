package com.ues.clinicace.servicioImpl;

import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.repositorios.IPacienteRepo;
import com.ues.clinicace.servicio.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {
    private final IPacienteRepo servicioPaciente;

    @Autowired
    public PacienteServiceImpl(IPacienteRepo servicioMedico) {
        // TODO Auto-generated constructor stub
        this.servicioPaciente = servicioMedico;
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
}
