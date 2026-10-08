package com.hospital;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
	List<Patient> getPendingPatients() throws SQLException;
	void insertCriticalPatient(Patient patient,Connection connection) throws SQLException;
	void insertGenaralPatient(Patient patient,Connection connection) throws SQLException;
	void markProcessed(int patientId,Connection connection) throws SQLException, Exception;
	boolean alreadyTransferred(int patientId,Connection connection) throws SQLException, Exception;
	
}
