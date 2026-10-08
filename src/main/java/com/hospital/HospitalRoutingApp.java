package com.hospital;

import com.hospital.PatientRoutingService;

public class HospitalRoutingApp {
	public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println(" HOSPITAL PATIENT TRIAGE & ROUTING SYSTEM");
        System.out.println("========================================");

        PatientRoutingService service =
                new PatientRoutingService();

        ProcessingSummary summary =service.processPatients();

        summary.printSummary();

        System.out.println();
        System.out.println("Processing completed successfully.");
    }
}
