package com.hospitalhub.hospitalhub.repository;

import com.hospitalhub.hospitalhub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Puedes agregar métodos personalizados aquí si lo necesitas
}