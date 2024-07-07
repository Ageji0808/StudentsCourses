package Raisetech.Student.ManagementSystem.service;

import Raisetech.Student.ManagementSystem.data.Student;
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
  public Student insertStudent(Student student) {
    // UUIDを生成してIDにセットする
    student.setId(UUID.randomUUID().toString());
    studentsRepository.insertStudent(student);
    return student; // 保存後のエンティティを返す
  }


  public List<Student> searchStudentList() {

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


