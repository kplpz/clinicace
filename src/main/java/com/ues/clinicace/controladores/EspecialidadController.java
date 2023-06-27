package com.ues.clinicace.controladores;

import com.ues.clinicace.modelo.Especialidad;
import com.ues.clinicace.modelo.GenericResponse;
import com.ues.clinicace.modelo.Medico;
import com.ues.clinicace.servicio.IEspecialidadService;
import com.ues.clinicace.servicio.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {
    private final IEspecialidadService servicioEsp;

    @Autowired
    public EspecialidadController(IEspecialidadService servicioEsp) {
        this.servicioEsp = servicioEsp;
    }

    @GetMapping
    public ResponseEntity<List<Especialidad>> mostrarEspecialidades(){
        List<Especialidad> especialidades = this.servicioEsp.listar();
        return new ResponseEntity<List<Especialidad>>(especialidades, HttpStatus.OK);
    }
    @GetMapping("/especialidad/{idEspecialidad}")
    public ResponseEntity<Especialidad> especilidadById(@PathVariable("idEspecialidad") Integer idEspecialidad){
        Especialidad especialidad= this.servicioEsp.leerPorId(idEspecialidad);
        return new ResponseEntity<Especialidad>(especialidad,HttpStatus.OK);
    }

    @PostMapping
    public Especialidad guardarEspecilidad(@RequestBody Especialidad especialidad) {
        return this.servicioEsp.registrar(especialidad);
    }

    // editar informacion existente
    @PutMapping
    public ResponseEntity<GenericResponse<Especialidad>> editarEspecialidad(@RequestBody Especialidad especialidad) {
        Optional<Especialidad> opt = Optional.ofNullable(this.servicioEsp.leerPorId(especialidad.getIdEspecialidad()));
        GenericResponse<Especialidad> resp;
        Especialidad espeResponse;
        System.out.println("prev "+especialidad.getIdEspecialidad()+" "+especialidad.getNombreEspeciadad());
        if(opt.isPresent()) {
            espeResponse=guardarEspecilidad(especialidad);
           /* System.out.println(especialidad.getNombreEspeciadad());*/
            resp = new GenericResponse<Especialidad>(1,"Guardado con exito",espeResponse);
            return new ResponseEntity<GenericResponse<Especialidad>>(resp,HttpStatus.OK);
        }else {
            resp = new GenericResponse<Especialidad>(0,"No fue guardado",especialidad);
            return new ResponseEntity<GenericResponse<Especialidad>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Especialidad>> eliminarEspecialidad(@PathVariable("id") Integer id){
        Optional<Especialidad> opt = Optional.ofNullable(this.servicioEsp.leerPorId(id));
        GenericResponse<Especialidad> resp=new GenericResponse<Especialidad>();
        HttpStatus http = HttpStatus.INTERNAL_SERVER_ERROR;
        if(opt.isPresent()) {
            if(this.servicioEsp.eliminar(opt.get())) {
                resp.setCode(1);
                resp.setMessage("Exito - Se elimino ");
                resp.setResponse(opt.get());
            }else {
                resp.setCode(0);
                resp.setMessage("Fallo - No pudo eliminarse ");
                resp.setResponse(opt.get());
            }
        }else {
            resp.setCode(0);
            resp.setMessage("Fallo - No hay nada que eliminar");
        }
        return new ResponseEntity<GenericResponse<Especialidad>>(resp,http);
    }
}
