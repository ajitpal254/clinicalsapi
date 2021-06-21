package com.ajit.clinicals.repos;

import com.ajit.clinicals.entities.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData,Integer> {


    List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);
}
