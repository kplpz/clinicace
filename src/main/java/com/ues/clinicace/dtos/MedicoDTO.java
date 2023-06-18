package com.ues.clinicace.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicoDTO implements Serializable {
    private Integer idMedico;
    private String nombreMedico;
    private String apellidoMedico;
    private String jvpm;
}

