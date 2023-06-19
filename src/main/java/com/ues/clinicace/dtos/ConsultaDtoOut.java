package com.ues.clinicace.dtos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ConsultaDtoOut {
    private Integer idConsulta;
    private String nombreCompletoPaciente;
    private String nombreCompletoMedico;
    private String nombreEspecialidad;
    private String numConsultorio;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaConsulta;
    private LocalTime horaConsulta;

}