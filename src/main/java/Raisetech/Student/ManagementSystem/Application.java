package Raisetech.Student.ManagementSystem;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@RestController
public class Application {
	@Autowired
	private StudentsRepository studentsRepository;
	@Autowired
	private Students_coursesRepository students_coursesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/students")
	public List<Students> getStudentsList() {
		return studentsRepository.getAllStudents();
	}

	@GetMapping("/students_courses")
	public List<Students_courses> getStudents_coursesList() {
		return students_coursesRepository.getAllStudents_courses();
	}
}