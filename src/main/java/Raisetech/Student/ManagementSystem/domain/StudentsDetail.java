package Raisetech.Student.ManagementSystem.domain;

import Raisetech.Student.ManagementSystem.data.Student;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

  public StudentsDetail() {
    this.student = new Student();
    this.studentsCourses = new ArrayList<>();
  }

  // Studentを受け取るコンストラクタ
  public StudentsDetail(Student student) {
    this.student = student;
    this.studentsCourses = new ArrayList<>();
  }

  // StudentsCoursesを受け取るコンストラクタ
  public StudentsDetail(List<StudentsCourses> studentsCourses) {
    this.student = new Student();
    this.studentsCourses = studentsCourses;
  }

  public StudentsDetail(Student student, List<StudentsCourses> studentsCourses) {
    this.student = student;
    this.studentsCourses = studentsCourses;
  }
}