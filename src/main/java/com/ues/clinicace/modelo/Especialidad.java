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
@Table(name="especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idEspecialidad;
    private String nombreEspeciadad;
}