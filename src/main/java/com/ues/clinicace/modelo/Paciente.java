package com.ues.clinicace.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;
    @Column(name = "nombre_paciente", nullable = false, length = 70)
    private String nombrePaciente;
    @Column(name = "apellido_paciente", nullable = false, length = 70)
    private String apellidoPaciente;
    @Column(name = "ident_paciente", nullable = true, length = 20)
    private String identPaciente;
    @Column(name = "direccion_paciente", nullable = false, length = 100)
    private String direccionPaciente;
    @Column(name = "telefono_paciente", nullable = false, length = 10)
    private String telefonoPaciente;
    @Column(name = "email_paciente", nullable = true, length = 25)
    private String emailPaciente;
    @Column(name = "fecha_nacimiento_paciente", nullable = true, length = 25)
    private String fechaNacimiento;
    @Column(name = "tiene_expediente_paciente", nullable = true, length = 25)
    private String tieneExpediente;
    @Column(name = "fecha_vencimiento_dui_paciente", nullable = true, length = 25)
    private String fechaVencimientoDui;

    public String getNombreCompletoPaciente(){
        return  this.nombrePaciente != null && this.apellidoPaciente != null ?
                this.nombrePaciente + " " + this.apellidoPaciente: "------";
    }
}



