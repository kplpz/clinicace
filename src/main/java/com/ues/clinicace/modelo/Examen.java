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
@Table(name="examen")
public class Examen {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idExamen;
    private String nombreExamen;
    private String lectura;
}