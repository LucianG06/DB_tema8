package com.db.tema8.model;

import com.db.tema8.exception.NoGradesException;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @OneToMany
    @NotNull
    List<Student> students = new ArrayList<>();

    @OneToMany
    @NotNull
    List<Course> courses = new ArrayList<>();

    public Integer getAverageGrade() {
        Integer averageGrade = 0;
        for(Student student : students) {
            try {
                averageGrade += student.getAnnualAverageGrade();
            } catch (NoGradesException e) {

                System.out.println(e.getMessage());
            }
        }

        return averageGrade / students.size();
    }
}