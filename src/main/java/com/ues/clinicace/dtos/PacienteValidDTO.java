package com.ues.clinicace.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteValidDTO {

    @NotBlank(message = "El nombre del paciente no debe ser vacío")
    @NotNull(message = "El nombre del paciente no debe ser nulo")
    @Size(min = 3, max = 30, message = "Nombre del paciente debe tener como mínimo 3 caracteres y como máximo 30")
    private String nombrePaciente;

    @NotBlank(message = "El apellido del paciente no debe ser vacío")
    @NotNull(message = "El apellido del paciente no debe ser nulo")
    private String apellidoPaciente;

    @Size(max = 10, message = "No DUI del paciente debe tener como mínimo 10, incluye el guión")
    private String identPaciente;
    @Column(name = "direccion_paciente", nullable = false, length = 100)
    private String direccionPaciente;


    @NotBlank(message = "El número de teléfono no debe estar vacío")
    private String telefonoPaciente;

    @NotEmpty
    @Email
    private String emailPaciente;

    @AssertTrue
    private boolean tieneExpediente;

    @DateTimeFormat(pattern = "yyy-MM-dd", iso = ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @Past(message = "Debe ser una fecha inferior al presente")
    private LocalDate fechaNacimiento;

    @DateTimeFormat(pattern = "yyy-MM-dd", iso = ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyy-MM-dd")
    @FutureOrPresent(message = "Debe ser una fecha superior al presente")
    private LocalDate fechaVencimientoDui;
}
