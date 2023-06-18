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
@Table(name="medico")
public class Medico {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idMedico;
    private String nombreMedico;
    private String apellidoMedico;
    private String jvpm;
}