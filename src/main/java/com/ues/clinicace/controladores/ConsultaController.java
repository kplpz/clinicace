package com.ues.clinicace.controladores;

import com.ues.clinicace.dtos.ConsultaDTO;
import com.ues.clinicace.dtos.ConsultaDtoIN;
import com.ues.clinicace.modelo.*;
import com.ues.clinicace.servicio.IConsultaService;
import com.ues.clinicace.servicio.IEspecialidadService;
import com.ues.clinicace.servicio.IMedicoService;
import com.ues.clinicace.servicio.IPacienteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final IConsultaService consultaService;
    private final IMedicoService medicoService;
    private final IEspecialidadService especialidadService;
    private final IPacienteService pacienteService;

    private Medico medico;
    private Consulta consulta;
    private Especialidad especialidad;
    private Paciente paciente;
    @Autowired
    public ConsultaController(IConsultaService consultaService, IMedicoService medicoService, IEspecialidadService especialidadService, IPacienteService pacienteService) {
        this.consultaService = consultaService;
        this.medicoService = medicoService;
        this.especialidadService = especialidadService;
        this.pacienteService = pacienteService;
    }
    @GetMapping
    public ResponseEntity<List<Consulta>> mostrarConsulta() {
        List<Consulta> consultas = this.consultaService.listar();
        return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
    }

    @GetMapping("/hateos")
    public ResponseEntity<List<ConsultaDTO>> getAllHateosConsulta() {
        List<Consulta> listConsulta = this.consultaService.listar();
        List<ConsultaDTO> listConsultaDTO = new ArrayList<>();
        if (listConsulta.size() > 0) {
            listConsulta.stream().forEach(c -> {
                ConsultaDTO consultaDTO = new ConsultaDTO(
                        c.getIdConsulta(),
                        c.getPaciente().getIdPaciente(),
                        c.getMedico().getIdMedico(),
                        c.getEspecialidad().getIdEspecialidad(),
                        c.getNumConsultorio(),
                        c.getFechaConsulta(),
                        c.getHoraConsulta()
                );
                /*MEDICO*/
                Link medicoLink = linkTo(methodOn(MedicoController.class)
                        .medicoById(c.getMedico().getIdMedico())).withSelfRel();
                consultaDTO.add(medicoLink);
                /*PACIENTE*/
                Link pacienteLink = linkTo(methodOn(PacienteController.class)
                        .pacienteById(c.getPaciente().getIdPaciente())).withSelfRel();
                consultaDTO.add(pacienteLink);
                /*ESPECIALIDAD*/
                Link espeLink = linkTo(methodOn(EspecialidadController.class)
                        .especilidadById(c.getEspecialidad().getIdEspecialidad())).withSelfRel();
                consultaDTO.add(espeLink);


                listConsultaDTO.add(consultaDTO);
            });
        }
        return new ResponseEntity<List<ConsultaDTO>>(listConsultaDTO, HttpStatus.OK);
    }

    @PostMapping("/insertar")
    public ResponseEntity<GenericResponse
            <ConsultaDtoIN>> guardarConsulta(@RequestBody ConsultaDtoIN consultaDtoIN) {
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<ConsultaDtoIN> resp = new GenericResponse<>(0, "ERROR DE ALMACENAMIENTO DE LA CONSULTA", consultaDtoIN);
        Optional<ConsultaDtoIN> opt = Optional.of(consultaDtoIN);
        if (opt.isPresent()) {
            this.medico = new Medico();
            this.especialidad = new Especialidad();
            this.paciente = new Paciente();
            this.consulta = new Consulta();
            this.consulta.setMedico(this.medicoService.leerPorId(consultaDtoIN.getIdMedico()));
            this.consulta.setEspecialidad(this.especialidadService.leerPorId(consultaDtoIN.getIdEspecialidad()));
            this.consulta.setPaciente((this.pacienteService.leerPorId(consultaDtoIN.getIdPaciente())));
            this.consulta.setFechaConsulta(consultaDtoIN.getFechaConsulta());
            this.consulta.setHoraConsulta(consultaDtoIN.getHoraConsulta());
            this.consulta.setNumConsultorio(consultaDtoIN.getNumConsultorio());

            if (consultaDtoIN.getDetalleConsulta().size() > 0) {
                consultaDtoIN.getDetalleConsulta().stream().peek(d -> d.setConsulta(consulta))
                        .collect(Collectors.toList());
                try {
                    this.consultaService.registrar(consulta);
                    resp.setCode(1);
                    resp.setMessage("Exito - Consulta almacenada EXITOSAMENTE !!");
                    http = HttpStatus.OK;
                } catch (Exception e) {
// TODO: handle exception
                    System.out.println(e.getMessage());

                }
            }


        }
        return new ResponseEntity<GenericResponse<ConsultaDtoIN>>(resp, http);
    }
    @PostMapping
    public ResponseEntity<GenericResponse
            <Consulta>> guardarConsultaa(@RequestBody Consulta consulta) {

        GenericResponse<Consulta> resp = new GenericResponse<>(0, "ERROR DE ALMACENAMIENTO DE LA CONSULTA", consulta);
        Optional<Consulta> opt = Optional.of(consulta);
        Consulta conSelect = new Consulta();
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        if (opt.isPresent()) {
            if (consulta.getDetalleConsulta().size() > 0) {
                consulta.getDetalleConsulta()
                        .stream().peek(d -> d.setConsulta(consulta))
                        .collect(Collectors.toList());
                try {
                   conSelect =  this.consultaService.registrar(consulta);
                    resp.setCode(1);
                    resp.setMessage("Exito - Consulta almacenada EXITOSAMENTE !!");
                    resp.setResponse(conSelect);
                } catch (Exception e) {
                    resp.setMessage("Fallo error : " + e.getMessage());
                }
            }
        }
            return new ResponseEntity<GenericResponse<Consulta>>(resp, HttpStatus.OK);
        }

        @PutMapping("/modificar")
        public ResponseEntity<GenericResponse<ConsultaDtoIN>> editarConsulta(@RequestParam ConsultaDtoIN consultaDtoIN){
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<ConsultaDtoIN> resp = new GenericResponse<>(0,
                "ERROR DE ALMACENAMIENTO DE LA CONSULTA", consultaDtoIN);
            Optional<ConsultaDtoIN> opt = Optional.of(consultaDtoIN);
            if (opt.isPresent()) {
                this.medico = new Medico();
                this.especialidad = new Especialidad();
                this.paciente = new Paciente();
                this.consulta = new Consulta();
                this.consulta.setMedico(this.medicoService.leerPorId(consultaDtoIN.getIdMedico()));
                this.consulta.setEspecialidad(this.especialidadService.leerPorId(consultaDtoIN.getIdEspecialidad()));
                this.consulta.setPaciente((this.pacienteService.leerPorId(consultaDtoIN.getIdPaciente())));
                this.consulta.setFechaConsulta(consultaDtoIN.getFechaConsulta());
                this.consulta.setHoraConsulta(consultaDtoIN.getHoraConsulta());
                this.consulta.setNumConsultorio(consultaDtoIN.getNumConsultorio());
                if (consultaDtoIN.getDetalleConsulta().size() > 0) {
                    consultaDtoIN.getDetalleConsulta().stream().peek(d -> d.setConsulta(consulta))
                            .collect(Collectors.toList());
                    try {
                        this.consultaService.modificar(consulta);
                        resp.setCode(1);
                        resp.setMessage("Exito - Consulta modificada EXITOSAMENTE !!");
                        http = HttpStatus.OK;
                    } catch (Exception e) {
// TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    }
                }
                return new ResponseEntity<GenericResponse<ConsultaDtoIN>>(resp, http);
        }
        @GetMapping("/{id}")
        public ResponseEntity<ConsultaDTO> consultaById(@PathVariable("id") Integer id){
            Consulta consulta = this.consultaService.leerPorId(id);
            ConsultaDTO consultaDTO=null;
            if(consulta!=null) {
                consultaDTO = new ConsultaDTO(
                        consulta.getIdConsulta(),
                        consulta.getPaciente().getIdPaciente(),
                        consulta.getMedico().getIdMedico(),

                        consulta.getEspecialidad().getIdEspecialidad(),
                        consulta.getNumConsultorio(),
                        consulta.getFechaConsulta(),
                        consulta.getHoraConsulta()

                );

            }
            return new ResponseEntity<ConsultaDTO>(consultaDTO, HttpStatus.OK);
        }
        @GetMapping( "/pdfparam")
    private void listConsultaMedicasxEspecialidadPdf(ModelAndView model, HttpServletResponse response,
        @RequestParam int idEspecialidadParam, @RequestParam String fechaConsultaParam) throws IOException{
        this.consultaService.generarReportePorConsultaParam(response, idEspecialidadParam, fechaConsultaParam);
        }
    @GetMapping( "/pdf")
    private void listConsultaMedicasxEspecialidadPdf(ModelAndView model, HttpServletResponse response) throws IOException{
        this.consultaService.generarReportePorConsulta(response);
    }
    @GetMapping( "report/pdf")
    private  void consultaCantidad(ModelAndView model, HttpServletResponse response) throws Exception {
            this.consultaService.generarReportePorConsultaGraficoBarra(response);
    }

    @GetMapping( "/pdfNumConsultorio")
    private void numConsultorioParam(ModelAndView model, HttpServletResponse response,
                                                     @RequestParam int numConsultorioParam) throws IOException{
        this.consultaService.generarReportePornumConsultaParam(response, numConsultorioParam);
    }
}
