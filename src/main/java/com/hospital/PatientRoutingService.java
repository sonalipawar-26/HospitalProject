package com.hospital;

import java.sql.Connection;
import java.util.List;

public class PatientRoutingService {

    private PatientDao patientDao;
    private PatientValidator validator;

    public PatientRoutingService() {

        patientDao = new PatientDaoImpl();
        validator = new PatientValidator();
    }

    // Get all PENDING patients
    public List<Patient> getPendingPatients() throws Exception {

        return patientDao.getPendingPatients();
    }

    // Decide whether patient goes to Critical Care
    public boolean isCritical(Patient patient) {

        // Rule 1:
        // condition_status = Critical
        if ("Critical".equals(patient.getConditionStatus())) {

            return true;
        }

        // Rule 2:
        // Emergency AND triage score >= 7
        if ("Emergency".equals(patient.getAdmissionType())
                && patient.getTriageScore() >= 7) {

            return true;
        }

        // Rule 3:
        // Moderate AND triage score >= 8
        if ("Moderate".equals(patient.getConditionStatus())
                && patient.getTriageScore() >= 8) {

            return true;
        }

        return false;
    }

    // Process all pending patients
    public ProcessingSummary processPatients() {

        ProcessingSummary summary =
                new ProcessingSummary();

        try {

            List<Patient> patients =
                    getPendingPatients();

            summary.setTotalPendingRecords(
                    patients.size());

            System.out.println();
            System.out.println(
                    " HOSPITAL PATIENT ROUTING");

            System.out.println(
                    "----------------------------------------");

            for (Patient patient : patients) {

                processSinglePatient(
                        patient,
                        summary);
            }

        } catch (Exception e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage());
        }

        return summary;
    }

    // Process one patient
    private void processSinglePatient(
            Patient patient,
            ProcessingSummary summary) {

        // Validate patient
        List<String> errors =
                validator.validatePatient(patient);

        if (!errors.isEmpty()) {

            System.out.println(
                    "Patient "
                    + patient.getPatientId()
                    + " -> Failed -> "
                    + String.join(",", errors));

            summary.incrementValidationFailed();

            return;
        }

        Connection connection = null;

        try {

            // Get database connection
            connection =
                    DBConnection.getConnection();

            // Start transaction
            connection.setAutoCommit(false);

            // Check duplicate
            if (patientDao.alreadyTransferred(
                    patient.getPatientId(),
                    connection)) {

                System.out.println(
                        "Patient "
                        + patient.getPatientId()
                        + " -> Skipped -> Already Processed...");

                summary.incrementSkippedDuplicate();

                connection.rollback();

                return;
            }

            // Decide destination
            if (isCritical(patient)) {

                // Insert into Critical Care
                patientDao.insertCriticalPatient(
                        patient,
                        connection);

                System.out.println(
                        "Patient "
                        + patient.getPatientId()
                        + " -> Critical Case -> Success...");

                summary.incrementCriticalCare();

            } else {

                // Insert into General Care
                patientDao.insertGenaralPatient(
                        patient,
                        connection);

                System.out.println(
                        "Patient "
                        + patient.getPatientId()
                        + " -> General Case -> Success...");

                summary.incrementGeneralCare();
            }

            // Mark source patient as PROCESSED
            patientDao.markProcessed(
                    patient.getPatientId(),
                    connection);

            // Commit transaction
            connection.commit();

            summary.incrementSuccessfullyProcessed();

        } catch (Exception e) {

            System.out.println(
                    "Patient "
                    + patient.getPatientId()
                    + " -> Failed -> "
                    + e.getMessage());

            
            if (connection != null) {

                try {

                    connection.rollback();

                } catch (Exception rollbackException) {

                    System.out.println(
                            "Rollback Failed -> "
                            + rollbackException.getMessage());
                }
            }

        } finally {

            // Close connection
            if (connection != null) {

                try {

                    connection.close();

                } catch (Exception e) {

                    System.out.println(
                            "Connection Close Error -> "
                            + e.getMessage());
                }
            }
        }
    }
}

