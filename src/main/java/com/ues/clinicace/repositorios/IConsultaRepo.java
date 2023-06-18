package com.ues.clinicace.repositorios;

import com.ues.clinicace.modelo.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IConsultaRepo extends JpaRepository<Consulta, Integer> {

}

