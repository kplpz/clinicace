package com.ues.clinicace.servicioImpl;

import com.ues.clinicace.modelo.Consulta;
import com.ues.clinicace.repositorios.IConsultaRepo;
import com.ues.clinicace.servicio.IConsultaService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor //ESTO SUSTITUYE A @Autowired
@Service
public class ConsultaServiceImpl implements IConsultaService {
    private final IConsultaRepo consultaRepo;


    // @RequiredArgsConstructor ESTO SUSTITUYE A @Autowired
	/*
	@Autowired
	public ConsultaServiceImpl(IConsultaRepo consultaRepo) {
		// TODO Auto-generated constructor stub
		this.consultaRepo = consultaRepo;
	}
*/

    @Override
    public Consulta registrar(Consulta obj) {
        return this.consultaRepo.save(obj);
    }

    @Override
    public Consulta modificar(Consulta obj) {
        return null;
    }

    @Override
    public List<Consulta> listar() {
        List<Consulta> listConsultas = this.consultaRepo.findAll();
        return listConsultas;
    }

    @Override
    public Consulta leerPorId(Integer id) {
        return this.consultaRepo.findById(id).get();
    }

    @Override
    public boolean eliminar(Consulta obj) {
        try {
            this.consultaRepo.delete(obj);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}