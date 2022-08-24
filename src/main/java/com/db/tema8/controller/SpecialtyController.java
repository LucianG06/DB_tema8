package com.db.tema8.controller;

import com.db.tema8.exception.SpecialtyNotFoundException;
import com.db.tema8.model.Specialty;
import com.db.tema8.model.Student;
import com.db.tema8.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("specialty")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping("all/{specialtyId}")
    public List<Student> getAllStudentBySpecialty(@PathVariable Integer specialtyId)
            throws SpecialtyNotFoundException {
        return specialtyService.getAllStudentBySpecialty(specialtyId);
    }

    @GetMapping("mostStudents")
    public Specialty getSpecialtyWithTheMostStudents() throws SpecialtyNotFoundException {
        return specialtyService.getSpecialtyWithTheMostStudents();
    }

    @GetMapping("specialtyGrade/{specialtyId}")
    public Integer getSpecialtyAverageGrade(@PathVariable Integer specialtyId) throws SpecialtyNotFoundException {
        return specialtyService.getSpecialtyAverageGrade(specialtyId);
    }
}
