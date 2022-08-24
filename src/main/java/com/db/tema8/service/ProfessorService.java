package com.db.tema8.service;

import com.db.tema8.exception.ProfessorNotFoundException;
import com.db.tema8.model.Course;
import com.db.tema8.model.Professor;
import com.db.tema8.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<Course> getAllCourseByProfessor(Integer professorId) throws ProfessorNotFoundException {
        Optional<Professor> tmpOptionalProfessor = professorRepository.findById(professorId);
        if (tmpOptionalProfessor.isEmpty()) {
            throw new ProfessorNotFoundException();
        } else {
            return tmpOptionalProfessor.get().getCourses();
        }
    }
}
