package com.ues.clinicace.repositorios;

import com.ues.clinicace.modelo.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepo extends JpaRepository<Paciente,Integer> {
}
