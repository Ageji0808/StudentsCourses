package Raisetech.Student.ManagementSystem.service;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.repository.StudentsCoursesRepository;
import Raisetech.Student.ManagementSystem.repository.StudentsRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public Students insertStudents(Students students) {
    // UUIDを生成してIDにセットする
    students.setId(UUID.randomUUID().toString());
    studentsRepository.insertStudents(students);
    return students; // 保存後のエンティティを返す
  }


  public List<Students> searchStudentsList() {

    return studentsRepository.getAllStudents();

  }

  @Transactional
  public StudentsCourses insertStudentsCourses(StudentsCourses studentsCourses) {
    // UUIDを生成してIDにセットする
    studentsCourses.setCourseID(UUID.randomUUID().toString());
    studentsCoursesRepository.insertStudentsCourses(studentsCourses);
    return studentsCourses; // 保存後のエンティティを返す
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return studentsCoursesRepository.getAllStudentsCourses();
  }
}


