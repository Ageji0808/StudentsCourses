package Raisetech.Student.ManagementSystem.service;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.repository.StudentsCoursesRepository;
import Raisetech.Student.ManagementSystem.repository.StudentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {

  private StudentsRepository studentsRepository;
  private StudentsCoursesRepository studentsCoursesRepository;

  @Autowired
  public StudentsService(StudentsRepository studentsRepository,
      StudentsCoursesRepository studentsCoursesRepository) {
    this.studentsRepository = studentsRepository;
    this.studentsCoursesRepository = studentsCoursesRepository;

  }

  public void insertStudents(Students students) {
    studentsRepository.insertStudents(students);
  }


  public List<Students> searchStudentsList() {

    return studentsRepository.getAllStudents();

  }

  public void insertStudentsCourses(StudentsCourses studentsCourses) {
    studentsCoursesRepository.insertStudentsCourses(studentsCourses);

  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return studentsCoursesRepository.getAllStudentsCourses();
  }
}


