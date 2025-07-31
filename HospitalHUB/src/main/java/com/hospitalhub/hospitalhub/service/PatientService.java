package com.hospitalhub.hospitalhub.service;


import com.hospitalhub.hospitalhub.model.Patient;
import com.hospitalhub.hospitalhub.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired // Inyección de dependencias de Spring
    private PatientRepository patientRepository;



    //Metdod que recupera a todos los pacientes de la base de datos
    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }

    //Método que recupera un paciente por su ID
    public Optional<Patient> getPatientById(Long id){

        return patientRepository.findById(id);
    }

    //Metodo para crear un nuevo paciente
    public Patient createPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    //Actualizar paciente
    public Optional<Patient> updatePatient(Long id, Patient updatedPatient) {
        return patientRepository.findById(id).map(patient -> {
            patient.setName(updatedPatient.getName());
            patient.setSurnames(updatedPatient.getSurnames());
            patient.setBirthDate(updatedPatient.getBirthDate());
            return patientRepository.save(patient);
        });
    }

    //Eliminar paciente por id
    public void deletePatient(Long id) {

        patientRepository.deleteById(id);
    }


    // Cuenta el número de visitas de un paciente
    public int countVisitsByPatient(Patient patient) {
        return patient.getVisits().size();
    }

    // Lista pacientes sin visitas (inactivos)
    public List<Patient> getInactivePatients() {
        return patientRepository.findAll().stream()
                .filter(p -> p.getVisits() == null || p.getVisits().isEmpty())
                .collect(Collectors.toList());
    }

    // Calcula la edad promedio de los pacientes registrados
    public double getAverageAge() {
        List<Patient> patients = patientRepository.findAll();
        if (patients.isEmpty()) return 0.0;
        double totalAge = patients.stream()
                .mapToInt(p -> Period.between(p.getBirthDate(), LocalDate.now()).getYears())
                .sum();
        return totalAge / patients.size();
    }



}
