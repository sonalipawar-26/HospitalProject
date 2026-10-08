package com.hospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {
	 private int patientId;
	 private String patientName;
	 private int age;
	 private String gender;
	 private String disease;
	 private String admissionType;
	 private String conditionStatus;
	 private int triageScore;
	 private String doctorName;
	 private LocalDate admissionDate;
	 private String mobile;
	 private String transferStatus;
	 private LocalDateTime processedAt;
	 private LocalDateTime createdAt;
	 
	 public Patient() {
		super();
	 }

	 public Patient(int patientId, String patientName, int age, String gender, String disease, String admissionType,
			String conditionStatus, int triageScore, String doctorName, LocalDate admissionDate, String mobile,
			String transferStatus, LocalDateTime processedAt, LocalDateTime createdAt) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.gender = gender;
		this.disease = disease;
		this.admissionType = admissionType;
		this.conditionStatus = conditionStatus;
		this.triageScore = triageScore;
		this.doctorName = doctorName;
		this.admissionDate = admissionDate;
		this.mobile = mobile;
		this.transferStatus = transferStatus;
		this.processedAt = processedAt;
		this.createdAt = createdAt;
	 }

	 public int getPatientId() {
		 return patientId;
	 }

	 public void setPatientId(int patientId) {
		 this.patientId = patientId;
	 }

	 public String getPatientName() {
		 return patientName;
	 }

	 public void setPatientName(String patientName) {
		 this.patientName = patientName;
	 }

	 public int getAge() {
		 return age;
	 }

	 public void setAge(int age) {
		 this.age = age;
	 }

	 public String getGender() {
		 return gender;
	 }

	 public void setGender(String gender) {
		 this.gender = gender;
	 }

	 public String getDisease() {
		 return disease;
	 }

	 public void setDisease(String disease) {
		 this.disease = disease;
	 }

	 public String getAdmissionType() {
		 return admissionType;
	 }

	 public void setAdmissionType(String admissionType) {
		 this.admissionType = admissionType;
	 }

	 public String getConditionStatus() {
		 return conditionStatus;
	 }

	 public void setConditionStatus(String conditionStatus) {
		 this.conditionStatus = conditionStatus;
	 }

	 public int getTriageScore() {
		 return triageScore;
	 }

	 public void setTriageScore(int triageScore) {
		 this.triageScore = triageScore;
	 }

	 public String getDoctorName() {
		 return doctorName;
	 }

	 public void setDoctorName(String doctorName) {
		 this.doctorName = doctorName;
	 }

	 public LocalDate getAdmissionDate() {
		 return admissionDate;
	 }

	 public void setAdmissionDate(LocalDate admissionDate) {
		 this.admissionDate = admissionDate;
	 }

	 public String getMobile() {
		 return mobile;
	 }

	 public void setMobile(String mobile) {
		 this.mobile = mobile;
	 }

	 public String getTransferStatus() {
		 return transferStatus;
	 }

	 public void setTransferStatus(String transferStatus) {
		 this.transferStatus = transferStatus;
	 }

	 public LocalDateTime getProcessedAt() {
		 return processedAt;
	 }

	 public void setProcessedAt(LocalDateTime processedAt) {
		 this.processedAt = processedAt;
	 }

	 public LocalDateTime getCreatedAt() {
		 return createdAt;
	 }

	 public void setCreatedAt(LocalDateTime createdAt) {
		 this.createdAt = createdAt;
	 }
}
