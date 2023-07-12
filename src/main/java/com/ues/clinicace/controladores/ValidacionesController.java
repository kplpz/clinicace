package com.ues.clinicace.controladores;

import com.ues.clinicace.dtos.PacienteValidDTO;
import com.ues.clinicace.exceptions.InvalidDataException;
import com.ues.clinicace.modelo.GenericResponse;
import com.ues.clinicace.modelo.Paciente;
import com.ues.clinicace.servicio.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/validar")
public class ValidacionesController {

    private IPacienteService pacienteService;

    public ValidacionesController(IPacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @PostMapping("/insertar")
    public ResponseEntity<GenericResponse<PacienteValidDTO>> guardarConsultar(@Valid @RequestBody PacienteValidDTO pacienteValidDTO){
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse<PacienteValidDTO> resp = new GenericResponse<>(0, "ERROR DE ALMACENAMIENTO DE DATOS DEL PACIENTE", pacienteValidDTO);
        Optional<PacienteValidDTO> opt = Optional.of(pacienteValidDTO);
        if(opt.isPresent()){
            Paciente paciente = new Paciente();
            paciente.setNombrePaciente(pacienteValidDTO.getNombrePaciente());
            paciente.setApellidoPaciente(pacienteValidDTO.getApellidoPaciente());
            paciente.setIdentPaciente(pacienteValidDTO.getIdentPaciente());
            paciente.setEmailPaciente(pacienteValidDTO.getEmailPaciente());
            paciente.setDireccionPaciente(pacienteValidDTO.getDireccionPaciente());
            paciente.setTelefonoPaciente(pacienteValidDTO.getTelefonoPaciente());
            paciente.setFechaVencimientoDui(String.valueOf(pacienteValidDTO.getFechaVencimientoDui()));
            paciente.setFechaNacimiento(String.valueOf(pacienteValidDTO.getFechaNacimiento()));
            paciente.setTieneExpediente(String.valueOf(pacienteValidDTO.isTieneExpediente()));
            try{
                this.pacienteService.registrar(paciente);
                resp.setCode(1);
                resp.setMessage("Exito - Paciente alamacenado exitosamente");
                http = HttpStatus.OK;
            }catch (Exception e){

            }
        }
        return new ResponseEntity<GenericResponse<PacienteValidDTO>>(resp, http);
    }
    @PostMapping
    public ResponseEntity<PacienteValidDTO> guardarPaciente(@Valid
                                                            @RequestBody PacienteValidDTO paciente,
                                                            BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new InvalidDataException(result);
        }
        return ResponseEntity.ok(paciente);
    }
}
