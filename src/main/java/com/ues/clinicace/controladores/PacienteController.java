package com.ues.clinicace.controladores;

import com.ues.clinicace.exceptions.ModeloNotFoundException;
import com.ues.clinicace.modelo.GenericResponse;
import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.servicio.IMedicoService;
import com.ues.clinicace.servicio.IPacienteService;
import com.ues.clinicace.servicioImpl.ReportesServiceEXCELImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final IPacienteService servicioPaciente;
    private final ReportesServiceEXCELImpl reportesServiceEXCEL;
    @Autowired
    public PacienteController(IPacienteService servicioPaciente, ReportesServiceEXCELImpl reportesServiceEXCEL) {
        this.servicioPaciente = servicioPaciente;
        this.reportesServiceEXCEL = reportesServiceEXCEL;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> mostrarPacientes(){
        List<Paciente> pacientes = this.servicioPaciente.listar();
        return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
    }
    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<Paciente> pacienteById(@PathVariable("idPaciente") Integer idPaciente){
        Paciente paciente= this.servicioPaciente.leerPorId(idPaciente);
        if(paciente.getIdPaciente() == null){
            throw new ModeloNotFoundException("Paciente no encontrado");
        }
        return new ResponseEntity<Paciente>(paciente,HttpStatus.OK);
    }

    @PostMapping
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return this.servicioPaciente.registrar(paciente);
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Paciente>> editarPaciente(@RequestBody Paciente paciente) {
        Optional<Paciente> opt = Optional.ofNullable(this.servicioPaciente.leerPorId(paciente.getIdPaciente()));
        GenericResponse<Paciente> resp;
        Paciente pacienteResponse;
        System.out.println("prev "+paciente.getIdPaciente()+" "+paciente.getNombrePaciente()+" "+ paciente.getApellidoPaciente());
        if(opt.isPresent()) {
            pacienteResponse=guardarPaciente(paciente);
            /*System.out.println(medico.getNombreMedico()+" "+ medico.getApellidoMedico());*/
            resp = new GenericResponse<Paciente>(1,"Paciente guardado con exito",pacienteResponse);
            return new ResponseEntity<GenericResponse<Paciente>>(resp,HttpStatus.OK);
        }else {
            resp = new GenericResponse<Paciente>(0,"No fue guardado",paciente);
            return new ResponseEntity<GenericResponse<Paciente>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Eliminar medicos
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Paciente>> eliminarPaciente(@PathVariable("id") Integer id){
        Optional<Paciente> opt = Optional.ofNullable(this.servicioPaciente.leerPorId(id));
        GenericResponse<Paciente> resp=new GenericResponse<Paciente>();
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        if(opt.isPresent()) {
            if(this.servicioPaciente.eliminar(opt.get())) {
                resp.setCode(1);
                resp.setMessage("Exito - Se elimino");
                resp.setResponse(opt.get());
            }else {
                resp.setCode(0);
                resp.setMessage("Fallo - No pudo eliminarse");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - No hay nada que eliminar");
        }
        return new ResponseEntity<GenericResponse<Paciente>>(resp,http);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachmen;filename=pacientes.xls";
        response.setHeader(headerKey, headerValue);
        reportesServiceEXCEL.generateExcel(response);
        response.flushBuffer();
    }

    @GetMapping("/pdfPaciente")
    public void pdfPaciente(ModelAndView model, HttpServletResponse response) throws IOException{
        this.servicioPaciente.generarReportePaciente(response);
    }

    @GetMapping("/pdf/linea")
    public void pdfCantidadMedxEspe(ModelAndView model, HttpServletResponse response) throws IOException {
        this.servicioPaciente.generarReportePorConsultaLine(response);
    }
}
