package com.db.tema8.model;

import com.db.tema8.exception.NoGradesException;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotNull
    @OneToMany
    List<Grade> grades = new ArrayList<>();

    @NotNull
    @ManyToOne
    Specialty specialty;

    public Integer getAnnualAverageGrade() throws NoGradesException {
        if(grades.isEmpty()) {
            throw new NoGradesException();
        } else {
            Integer averageGrade = 0;
            for (Grade grade: grades) {
                averageGrade += grade.getGrade();
            }
            return averageGrade/grades.size();
        }
    }
}
