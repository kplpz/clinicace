package com.ues.clinicace.modelo;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idConsulta;

    @ManyToOne
    @JoinColumn(name="id_paciente",nullable=false,
            foreignKey=@ForeignKey(name="FK_consulta_paciente"))
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name="id_medico",nullable=false,
            foreignKey=@ForeignKey(name="FK_consulta_medico"))
    private Medico medico;

    @ManyToOne
    @JoinColumn(name="id_especialidad",nullable=false,
            foreignKey=@ForeignKey(name="FK_consulta_especialidad"))
    private Especialidad especialidad;
    private String numConsultorio;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fechaConsulta;

    @OneToMany(mappedBy="consulta", cascade= { CascadeType.ALL }, orphanRemoval=true)
    @JsonManagedReference
    private List<DetalleConsulta> detalleConsulta;
    private LocalTime horaConsulta;

    @Override
    public String toString() {
        return "Consulta [idConsulta=" + idConsulta + ", paciente=" + paciente + ", medico=" + medico
                + ", especialidad=" + especialidad + ", numConsultorio=" + numConsultorio + ", fechaConsulta="
                + fechaConsulta + ", detalleConsulta=" + detalleConsulta + "]";
    }
}