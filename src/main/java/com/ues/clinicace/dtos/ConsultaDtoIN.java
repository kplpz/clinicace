package com.ues.clinicace.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ues.clinicace.modelo.DetalleConsulta;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDtoIN {

    /// para la modificación de datos

    private Integer idConsulta;
    private Integer idPaciente;
    private Integer idMedico;
    private Integer idEspecialidad;
    private String numConsultorio;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaConsulta;
    private LocalTime horaConsulta;
    private List<DetalleConsulta> detalleConsulta;


}

