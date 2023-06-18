package com.ues.clinicace.controladores;

import com.ues.clinicace.dtos.ConsultaDTO;
import com.ues.clinicace.modelo.Consulta;
import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.servicio.IConsultaService;
import com.ues.clinicace.servicio.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final IConsultaService consultaService;
    private final IMedicoService medicoService;

    private Medico medico;
    private Consulta consulta;

    @Autowired
    public ConsultaController(IConsultaService consultaService, IMedicoService medicoService) {
        this.consultaService = consultaService;
        this.medicoService = medicoService;
    }

    @GetMapping("/hateos")
    public ResponseEntity<List<ConsultaDTO>> getAllHateosConsulta(){
        List<Consulta> listConsulta = this.consultaService.listar();
        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();
        if(listConsulta.size()>0){
            listConsulta.stream().forEach(c ->{
                ConsultaDTO consultaDTO = new ConsultaDTO(
                        c.getIdConsulta(),
                        c.getPaciente().getIdPaciente(),
                        c.getMedico().getIdMedico(),
                        c.getEspecialidad().getIdEspecialidad(),
                        c.getNumConsultorio(),
                        c.getFechaConsulta(),
                        c.getHoraConsulta()
                );
                Link medicoLink =  linkTo(methodOn(MedicoController.class)
                        .medicoById(c.getMedico().getIdMedico())).withSelfRel();
                consultaDTO.add(medicoLink);
                listConsultaDTO.add(consultaDTO);
            });
        }
        return new ResponseEntity<List<ConsultaDTO>>(listConsultaDTO, HttpStatus.OK);
    }
}
