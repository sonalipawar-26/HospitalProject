package com.hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.hospital.DBConnection;
import com.hospital.Patient;

public class PatientDaoImpl implements PatientDao {
	private static Connection connection=null;
	private static Statement statement=null;
	private static ResultSet resultset=null;
	private static PreparedStatement preparedStatement=null;
	@Override
	public List<Patient> getPendingPatients() throws SQLException {
		List<Patient> patients = new ArrayList<>();
		String sql = "SELECT * FROM hospital_patient_intake WHERE transfer_status = ?";
		try (Connection con = com.hospital.DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, "PENDING");
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Patient patient = new Patient();

	                    patient.setPatientId(rs.getInt("patient_id"));
	                    patient.setPatientName(rs.getString("patient_name"));
	                    patient.setAge(rs.getInt("age"));
	                    patient.setGender(rs.getString("gender"));
	                    patient.setDisease(rs.getString("disease"));
	                    patient.setAdmissionType(rs.getString("admission_type"));
	                    patient.setConditionStatus(rs.getString("condition_status"));
	                    patient.setTriageScore(rs.getInt("triage_score"));
	                    patient.setDoctorName(rs.getString("doctor_name"));

	                    if (rs.getDate("admission_date") != null) {
	                        patient.setAdmissionDate(
	                            rs.getDate("admission_date").toLocalDate()
	                        );
	                    }

	                    patient.setMobile(rs.getString("mobile"));
	                    patient.setTransferStatus(
	                            rs.getString("transfer_status")
	                    );

	                    if (rs.getTimestamp("processed_at") != null) {
	                        patient.setProcessedAt(
	                            rs.getTimestamp("processed_at").toLocalDateTime()
	                        );
	                    }

	                    if (rs.getTimestamp("created_at") != null) {
	                        patient.setCreatedAt(
	                            rs.getTimestamp("created_at").toLocalDateTime()
	                        );
	                    }

	                    patients.add(patient);
				}
			}
		}

	  return patients;
	}

	@Override
	public void insertCriticalPatient(Patient patient, Connection connection) throws SQLException {
		String query="INSERT INTO critical_care_patients "
                + "(source_patient_id, patient_name, age, disease, "
                + "admission_type, condition_status, triage_score, "
                + "doctor_name) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
			preparedStatement.setInt(1,patient.getPatientId());
			preparedStatement.setString(2,patient.getPatientName());
			preparedStatement.setInt(3, patient.getAge());
			preparedStatement.setString(4, patient.getDisease());
			preparedStatement.setString(5, patient.getAdmissionType());
			preparedStatement.setString(6, patient.getConditionStatus());
			preparedStatement.setInt(7, patient.getTriageScore());
			preparedStatement.setString(8, patient.getDoctorName());

			preparedStatement.executeUpdate();
			
		}
	}

	@Override
	public void insertGenaralPatient(Patient patient, Connection connection) throws SQLException {
		String query="INSERT INTO general_care_patients "
                + "(source_patient_id, patient_name, age, disease, "
                + "admission_type, condition_status, triage_score, "
                + "doctor_name) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement preparedStatement=connection.prepareStatement(query)){
			preparedStatement.setInt(1,patient.getPatientId());
			preparedStatement.setString(2,patient.getPatientName());
			preparedStatement.setInt(3, patient.getAge());
			preparedStatement.setString(4, patient.getDisease());
			preparedStatement.setString(5, patient.getAdmissionType());
			preparedStatement.setString(6, patient.getConditionStatus());
			preparedStatement.setInt(7, patient.getTriageScore());
			preparedStatement.setString(8, patient.getDoctorName());

			preparedStatement.executeUpdate();
			
		}
		
	}

	@Override
	public void markProcessed(int patientId, Connection connection) throws Exception{
		String sql = "UPDATE hospital_patient_intake "
                + "SET transfer_status = ?, processed_at = NOW() "
                + "WHERE patient_id = ?";

     try (PreparedStatement preparedStatement= connection.prepareStatement(sql)) {

    	 preparedStatement.setString(1, "PROCESSED");
    	 preparedStatement.setInt(2, patientId);

    	 preparedStatement.executeUpdate();
     }
	}

	@Override
	public boolean alreadyTransferred(
	        int patientId, Connection con) throws SQLException {

	    String sql =
	            "SELECT COUNT(*) " +
	            "FROM (" +
	            "SELECT source_patient_id " +
	            "FROM critical_care_patients " +
	            "WHERE source_patient_id = ? " +

	            "UNION ALL " +

	            "SELECT source_patient_id " +
	            "FROM general_care_patients " +
	            "WHERE source_patient_id = ?" +
	            ") AS transferred";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, patientId);
	        ps.setInt(2, patientId);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }

	    return false;
	}
}
