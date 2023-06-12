package com.upc.simulacro.repository;

import com.upc.simulacro.entitys.Applicant;
import com.upc.simulacro.entitys.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//NO TE OLVIDES DE PONER EL JPAREPOSITORY
public interface RepositoryApplicant extends JpaRepository<Applicant, Long> {
    List<Applicant> findApplicantByStatus(Status status);
}
