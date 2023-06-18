package com.ues.clinicace.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EspecialidadDTO implements Serializable {
    private Integer idEspecialidad;
    private String nombreEspeciadad;
}

