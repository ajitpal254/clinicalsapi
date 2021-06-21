package com.ajit.clinicals.controllers;


import com.ajit.clinicals.dto.ClinicalDataRequest;
import com.ajit.clinicals.entities.ClinicalData;
import com.ajit.clinicals.entities.Patient;
import com.ajit.clinicals.repos.ClinicalDataRepository;
import com.ajit.clinicals.repos.PatientRepository;
import com.ajit.clinicals.util.BmiCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {


    private ClinicalDataRepository repository;


    private PatientRepository pRepository;

    @Autowired
    ClinicalDataController(ClinicalDataRepository repository,PatientRepository pRepository){
    this.repository = repository;
    this.pRepository = pRepository;
    }


    @RequestMapping(value = "/clinicals",method = RequestMethod.POST)
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request){
        Patient patient = pRepository.findById(request.getPatientId()).get();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());
        clinicalData.setPatient(patient);
        return repository.save(clinicalData);
    }

    @RequestMapping(value = "/clinicals/{patientId}/{componentName}",method = RequestMethod.GET)
    public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,@PathVariable("componentName") String componentName){

        if(componentName.equals("bmi")){
            componentName = "hw";
        }
        List<ClinicalData> clinicalData = repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
        ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for(ClinicalData eachEntry:duplicateClinicalData){
            BmiCalculator.calculateBmi(clinicalData,eachEntry);
        }
        return clinicalData;
    }
}
