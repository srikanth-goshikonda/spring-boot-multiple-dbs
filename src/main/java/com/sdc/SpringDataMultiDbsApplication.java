package com.sdc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sdc.entity.student.Student;
import com.sdc.entity.teacher.Teacher;
import com.sdc.repo.student.StudentRepo;
import com.sdc.repo.teacher.TeacherRepo;

@SpringBootApplication
public class SpringDataMultiDbsApplication implements CommandLineRunner {
	@Autowired
	private StudentRepo repo;
	@Autowired
	private TeacherRepo repo2;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataMultiDbsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Student student = new Student();
		student.setName("SRIKANTH");
		repo.save(student);

		Teacher teacher = new Teacher();
		teacher.setName("TeACHER");
		repo2.save(teacher);
	}

}
