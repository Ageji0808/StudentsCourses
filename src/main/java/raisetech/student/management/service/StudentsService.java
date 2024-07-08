package raisetech.student.management.service;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.repository.StudentsCoursesRepository;
import raisetech.student.management.repository.StudentsRepository;
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
    student.setId(UUID.randomUUID().toString());
    studentsRepository.insertStudent(student);
    return student;
  }

  public List<Student> searchStudentList() {
    return studentsRepository.getAllStudents();
  }

  public StudentsDetail findStudentById(String id) {
    Student student = studentsRepository.findStudentById(id);
    List<StudentsCourses> courses = studentsCoursesRepository.findCourseByStudentId(id);
    return new StudentsDetail(student, courses);
  }

  @Transactional
  public StudentsCourses insertStudentsCourses(StudentsCourses studentsCourses) {
    studentsCourses.setCourseID(UUID.randomUUID().toString());
    studentsCoursesRepository.insertStudentsCourses(studentsCourses);
    return studentsCourses;
  }

  public List<StudentsCourses> searchStudentsCoursesList() {
    return studentsCoursesRepository.getAllStudentsCourses();
  }

  @Transactional
  public void updateStudent(Student student) {
    studentsRepository.updateStudent(student);
  }

  @Transactional
  public void updateStudentsCourses(StudentsCourses studentsCourses) {
    studentsCoursesRepository.updateStudentsCourses(studentsCourses);
  }




}

