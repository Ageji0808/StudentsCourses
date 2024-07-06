package Raisetech.Student.ManagementSystem.domain;

import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsDetail {

  public Students students;
  public List<StudentsCourses> studentsCourses;


  public StudentsDetail() {
    this.students = new Students();
    this.studentsCourses = new ArrayList<>();

  }


  // Studentsを受け取るコンストラクタ
  public StudentsDetail(Students students) {
    this.students = students;
    this.studentsCourses = new ArrayList<>();
  }

  // StudentsCoursesを受け取るコンストラクタ
  public StudentsDetail(List<StudentsCourses> studentsCourses) {
    this.students = new Students();
    this.studentsCourses = studentsCourses;
  }

  public StudentsDetail(Students students, List<StudentsCourses> studentsCourses) {
    this.students = students;
    this.studentsCourses = studentsCourses;
  }


}
