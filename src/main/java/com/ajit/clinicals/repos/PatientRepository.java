package com.ajit.clinicals.repos;

import com.ajit.clinicals.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
