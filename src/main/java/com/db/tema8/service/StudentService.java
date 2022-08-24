package com.db.tema8.service;

import com.db.tema8.exception.NoGradesException;
import com.db.tema8.exception.SpecialtyNotFoundException;
import com.db.tema8.exception.StudentNotFoundException;
import com.db.tema8.model.Grade;
import com.db.tema8.model.Specialty;
import com.db.tema8.model.Student;
import com.db.tema8.repository.SpecialtyRepository;
import com.db.tema8.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final SpecialtyRepository specialtyRepository;

    public void addGrade(Grade grade, Integer studentId) throws StudentNotFoundException {
        Optional<Student> tmpOptionalStudent = studentRepository.findById(studentId);
        if (tmpOptionalStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            tmpOptionalStudent.get().getGrades().add(grade);
            studentRepository.save(tmpOptionalStudent.get());
        }
    }

    public List<Grade> getAllGradesByStudent(Integer studentId) throws StudentNotFoundException {
        Optional<Student> tmpOptionalStudent = studentRepository.findById(studentId);
        if (tmpOptionalStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            Student tmpStudent = tmpOptionalStudent.get();
            return tmpStudent.getGrades();
        }
    }

    public List<Student> getAllStudentsGradeGreaterThanEight() {
        return studentRepository.findAll().stream().filter((Student student) -> {
            try {
                return student.getAnnualAverageGrade() >= 8;
            } catch (NoGradesException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    public Student getStudentWithHighestGrade() throws StudentNotFoundException {
        Optional<Student> tmpOptionalStudent = studentRepository.findAll().stream().max(Comparator.comparingInt(s -> {
            try {
                return s.getAnnualAverageGrade();
            } catch (NoGradesException e) {
                e.printStackTrace();
            }
            return 0;
        }));

        if (tmpOptionalStudent.isEmpty()) {
            throw new StudentNotFoundException();
        } else {
            return tmpOptionalStudent.get();
        }
    }

    public Student getStudentWithHighestGradeAtSpecialty(Integer specialtyId) throws StudentNotFoundException, SpecialtyNotFoundException {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(specialtyId);
        if (optionalSpecialty.isEmpty()) {
            throw new SpecialtyNotFoundException();
        } else  {
            Optional<Student> student = optionalSpecialty.get().getStudents().stream().max(Comparator.comparingInt(s -> {
                try {
                    return s.getAnnualAverageGrade();
                } catch (NoGradesException e) {
                    e.printStackTrace();
                }
                return 0;
            }));

            if (student.isEmpty()) {
                throw new StudentNotFoundException();
            } else {
                return student.get();
            }
        }

    }
}
