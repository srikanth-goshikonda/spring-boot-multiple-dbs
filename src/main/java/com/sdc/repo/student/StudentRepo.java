package com.sdc.repo.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdc.entity.student.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
