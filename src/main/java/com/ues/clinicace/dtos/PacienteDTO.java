package com.ues.clinicace.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PacienteDTO implements Serializable {
    private Integer idPaciente;
    private String nombrePaciente;
    private String apellidoPaciente;
    private String identPaciente;
    private String direccionPaciente;
    private String telefonoPaciente;
    private String emailPaciente;
}

