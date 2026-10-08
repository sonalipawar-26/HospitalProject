package com.hospital;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientValidator {
	public List<String> validatePatient(Patient patient){
		List<String> errors=new ArrayList<String>();
		
		if(patient.getPatientName()==null || patient.getPatientName().trim().isEmpty()) {
			errors.add("Patient name is mandatory!!!");
		}else if(patient.getPatientName().trim().length()<3) {
			errors.add("Patient name must contain at least 3 characters");
		}
		
		if(patient.getAge()<0 || patient.getAge()>120) {
			errors.add("Age must be between 0 and 120");
		}
		
		if (patient.getGender() == null
                || !(patient.getGender().equals("Male")
                || patient.getGender().equals("Female")
                || patient.getGender().equals("Other"))) {

            errors.add("Gender must be Male, Female or Other");
        }
		
		if (patient.getDisease() == null
                || patient.getDisease().trim().isEmpty()) {

            errors.add("Disease is mandatory");
        }
		
		if (patient.getAdmissionType() == null
                || !(patient.getAdmissionType().equals("Emergency")
                || patient.getAdmissionType().equals("Regular"))) {

            errors.add("Admission type must be Emergency or Regular");
        }
		
		if (patient.getConditionStatus() == null
                || !(patient.getConditionStatus().equals("Critical")
                || patient.getConditionStatus().equals("Moderate")
                || patient.getConditionStatus().equals("Stable"))) {

            errors.add("Condition status must be Critical, Moderate or Stable");
        }
		
		if (patient.getTriageScore() < 1
                || patient.getTriageScore() > 10) {

            errors.add("Invalid triage score");
        }
		
		if (patient.getDoctorName() == null
                || patient.getDoctorName().trim().isEmpty()) {

            errors.add("Doctor name is mandatory");
        }
		
		if (patient.getAdmissionDate() == null) {

            errors.add("Admission date is mandatory");

        } else if (patient.getAdmissionDate().isAfter(LocalDate.now())) {

            errors.add("Admission date cannot be a future date");
        }
		
		if (patient.getMobile() == null
                || !patient.getMobile().matches("\\d{10}")) {

            errors.add("Mobile must contain exactly 10 digits");
        }
		
		if (patient.getTransferStatus() == null
                || !patient.getTransferStatus().equals("PENDING")) {

            errors.add("Transfer status must be PENDING");
        }

        return errors;
	}
}
