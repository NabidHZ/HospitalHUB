package com.hospitalhub.hospitalhub.repository;

import com.hospitalhub.hospitalhub.model.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalVisitRepository extends JpaRepository<MedicalVisit, Long> {
    List<MedicalVisit> findByPatientIdOrderByVisitDateDesc(Long patientId);
}