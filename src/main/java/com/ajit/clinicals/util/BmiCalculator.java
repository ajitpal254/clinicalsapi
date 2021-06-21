package com.ajit.clinicals.util;

import com.ajit.clinicals.entities.ClinicalData;

import java.util.List;

public class BmiCalculator {
    public static void calculateBmi(List<ClinicalData> clinicalData, ClinicalData eachEntry) {
        if (eachEntry.getComponentName().equals("hw")) {

            String[] heightAndWeight = eachEntry.getComponentValue().split("/");
            if (heightAndWeight != null && heightAndWeight.length > 1) {
                float heightInMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
                float bmi = Float.parseFloat(heightAndWeight[1]) / (heightInMetres * heightInMetres);
                ClinicalData bmiData = new ClinicalData();
                bmiData.setComponentName("bmi");
                bmiData.setComponentValue(Float.toString(bmi));
                clinicalData.add(bmiData);
            }
        }
    }
}
