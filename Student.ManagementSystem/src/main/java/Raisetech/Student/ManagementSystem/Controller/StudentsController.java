package Raisetech.Student.ManagementSystem.Controller;

import Raisetech.Student.ManagementSystem.Controller.converter.StudentsConverter;
import Raisetech.Student.ManagementSystem.data.Students;
import Raisetech.Student.ManagementSystem.data.StudentsCourses;
import Raisetech.Student.ManagementSystem.domain.StudentsDetail;
import Raisetech.Student.ManagementSystem.service.StudentsService;
import java.util.List;
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
    Students newStudent = studentsDetail.getStudents();
    studentsService.insertStudents(newStudent);
    String generatedStudentId = newStudent.getId();
    System.out.println("Generated student ID: " + generatedStudentId); // 確認用出力

    for (StudentsCourses course : studentsDetail.getStudentsCourses()) {
      course.setStudentID(generatedStudentId);
      studentsService.insertStudentsCourses(course);
      System.out.println("Course student ID: " + course.getStudentID()); // 確認用出力
    }

    return "redirect:/students";
  }
}


