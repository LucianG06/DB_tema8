package com.db.tema8.service;

import com.db.tema8.exception.SpecialtyNotFoundException;
import com.db.tema8.model.Specialty;
import com.db.tema8.model.Student;
import com.db.tema8.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public List<Student> getAllStudentBySpecialty(Integer specialtyId) throws SpecialtyNotFoundException {
        Optional<Specialty> tmpOptionalSpecialty = specialtyRepository.findById(specialtyId);
        if (tmpOptionalSpecialty.isEmpty()) {
            throw new SpecialtyNotFoundException();
        } else {
            return tmpOptionalSpecialty.get().getStudents();
        }
    }

    public Specialty getSpecialtyWithTheMostStudents() throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findAll().stream()
                .max(Comparator.comparingInt(s -> s.getStudents().size()));
        if (specialty.isEmpty()) {
            throw new SpecialtyNotFoundException();
        } else {
            return specialty.get();
        }
    }

    public Integer getSpecialtyAverageGrade(Integer specialtyId) throws SpecialtyNotFoundException {
        Optional<Specialty> tmpOptionalSpecialty = specialtyRepository.findById(specialtyId);
        if (tmpOptionalSpecialty.isEmpty()) {
            throw new SpecialtyNotFoundException();
        } else {
            return tmpOptionalSpecialty.get().getAverageGrade();
        }
    }
}
