package Raisetech.Student.ManagementSystem.Controller;

import Raisetech.Student.ManagementSystem.Controller.converter.StudentsConverter;
import Raisetech.Student.ManagementSystem.data.Student;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.domain.StudentsDetail;
import Raisetech.Student.ManagementSystem.service.StudentsService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentsController {

  private StudentsService studentsService;
  private StudentsConverter converter;

  @Autowired
  public StudentsController(StudentsService studentsService, StudentsConverter converter) {
    this.studentsService = studentsService;
    this.converter = converter;
  }

  @GetMapping("/student")
  public String getStudentsList(Model model) {
    List<Student> student = studentsService.searchStudentList();
    List<StudentsCourses> studentsCourses = studentsService.searchStudentsCoursesList();
    model.addAttribute("studentsList", converter.convertStudentsDetails(student, studentsCourses));
    model.addAttribute("studentsCoursesList", studentsCourses);
    return "studentsAndCoursesList"; // 統合されたテンプレート名
  }


  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("studentsDetail", new StudentsDetail());
    return "registerStudent";
  }


  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentsDetail studentsDetail,
      BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    // 1. 新しい学生エンティティを取得
    Student newStudent = studentsDetail.getStudent();

    // 2. 学生をデータベースに保存して、保存後のエンティティを取得
    Student savedStudent = studentsService.insertStudent(newStudent);

    // 3. 保存された学生のIDを確認用に出力
    String savedStudentId = savedStudent.getId();
    System.out.println("Saved student ID: " + savedStudentId);

    // 4. 受講コースの学生IDを設定して保存
    for (StudentsCourses course : studentsDetail.getStudentsCourses()) {
      String courseId = UUID.randomUUID().toString(); // UUIDでコースIDを生成
      course.setCourseID(courseId); // コースIDを設定
      course.setStudentID(savedStudentId); // 学生IDを設定
      studentsService.insertStudentsCourses(course);
      System.out.println("Saved course ID: " + courseId); // 確認用出力
    }

    return "redirect:/student";
  }
}



