package Raisetech.Student.ManagementSystem.Controller;

import Raisetech.Student.ManagementSystem.Controller.converter.StudentsConverter;
import Raisetech.Student.ManagementSystem.data.Students;
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

  @GetMapping("/students")
  public String getStudentsList(Model model) {
    List<Students> students = studentsService.searchStudentsList();
    List<StudentsCourses> studentsCourses = studentsService.searchStudentsCoursesList();
    model.addAttribute("studentsList", converter.convertStudentsDetails(students, studentsCourses));
    model.addAttribute("studentsCoursesList", studentsCourses);
    return "studentsAndCoursesList"; // 統合されたテンプレート名
  }


  @GetMapping("/newStudents")
  public String newStudents(Model model) {
    model.addAttribute("studentsDetail", new StudentsDetail());
    return "registerStudents";
  }


  @PostMapping("/registerStudents")
  public String registerStudents(@ModelAttribute StudentsDetail studentsDetail,
      BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudents";
    }
    // 1. 新しい学生エンティティを取得
    Students newStudent = studentsDetail.getStudents();

    // 2. 学生をデータベースに保存して、保存後のエンティティを取得
    Students savedStudent = studentsService.insertStudents(newStudent);

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

    return "redirect:/students";
  }
}



