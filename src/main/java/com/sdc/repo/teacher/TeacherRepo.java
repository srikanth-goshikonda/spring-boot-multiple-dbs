package com.sdc.repo.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdc.entity.teacher.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

}
