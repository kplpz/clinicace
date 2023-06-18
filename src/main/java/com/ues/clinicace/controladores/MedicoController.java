package com.ues.clinicace.controladores;

import java.util.List;
import java.util.Optional;

import com.ues.clinicace.modelo.GenericResponse;
import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.servicio.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medico")
public class MedicoController {
    private final IMedicoService servicioMedico;

    @Autowired
    public MedicoController(IMedicoService servicioMedico) {
        this.servicioMedico = servicioMedico;
    }

    @GetMapping
    public ResponseEntity<List<Medico>> mostrarMedicos(){
        List<Medico> medicos = this.servicioMedico.listar();
        return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
    }
    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<Medico> medicoById(@PathVariable("idMedico") Integer idMedico){
        Medico medico= this.servicioMedico.leerPorId(idMedico);
        return new ResponseEntity<Medico>(medico,HttpStatus.OK);
    }

    /*@GetMapping("/{filtro}")
    public ResponseEntity<List<Medico>> buscarMedico(@PathVariable("filtro") String filtro){
        List<Medico> medicos = this.servicioMedico.buscarMedico(filtro);
        return new ResponseEntity<List<Medico>>(medicos,HttpStatus.OK);
    }*/

    //Ingresar informacion
    @PostMapping
    public Medico guardarMedico(@RequestBody Medico medico) {
        return this.servicioMedico.registrar(medico);
    }

    // editar informacion existente
    @PutMapping
    public ResponseEntity<GenericResponse<Medico>> editarMedico(@RequestBody Medico medico) {
        Optional<Medico> opt = Optional.ofNullable(this.servicioMedico.leerPorId(medico.getIdMedico()));
        GenericResponse<Medico> resp;
        Medico medicoResponse;
        System.out.println("prev "+medico.getIdMedico()+" "+medico.getNombreMedico()+" "+ medico.getApellidoMedico());
        if(opt.isPresent()) {
            medicoResponse=guardarMedico(medico);
            System.out.println(medico.getNombreMedico()+" "+ medico.getApellidoMedico());
            resp = new GenericResponse<Medico>(1,"Medico guardado con exito",medicoResponse);
            return new ResponseEntity<GenericResponse<Medico>>(resp,HttpStatus.OK);
        }else {
            resp = new GenericResponse<Medico>(0,"Medico no fue guardado",medico);
            return new ResponseEntity<GenericResponse<Medico>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Eliminar medicos
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Medico>> eliminarMedico(@PathVariable("id") Integer id){
        Optional<Medico> opt = Optional.ofNullable(this.servicioMedico.leerPorId(id));
        GenericResponse<Medico> resp=new GenericResponse<Medico>();
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        if(opt.isPresent()) {
            if(this.servicioMedico.eliminar(opt.get())) {
                resp.setCode(1);
                resp.setMessage("Exito - Se elimino Medico");
                resp.setResponse(opt.get());
            }else {
                resp.setCode(0);
                resp.setMessage("Fallo - No pudo eliminarse medico");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - No hay medico que eliminar");
        }
        return new ResponseEntity<GenericResponse<Medico>>(resp,http);
    }
}
