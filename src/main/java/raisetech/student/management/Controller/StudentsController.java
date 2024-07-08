package raisetech.student.management.Controller;

import raisetech.student.management.Controller.converter.StudentsConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.domain.StudentsDetail;
import raisetech.student.management.service.StudentsService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<Student> students = studentsService.searchStudentList();
    List<StudentsCourses> studentsCourses = studentsService.searchStudentsCoursesList();
    List<StudentsDetail> studentsDetails = converter.convertStudentsDetails(students,
        studentsCourses);

    model.addAttribute("studentsList", studentsDetails);
    model.addAttribute("studentsCoursesList", studentsCourses);
    return "studentsAndCoursesList"; // 統合されたテンプレート名
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("studentsDetail", new StudentsDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute("studentsDetail") StudentsDetail studentsDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }

    Student newStudent = studentsDetail.getStudent();
    Student savedStudent = studentsService.insertStudent(newStudent);
    String savedStudentId = savedStudent.getId();

    for (StudentsCourses course : studentsDetail.getStudentsCourses()) {
      String courseId = UUID.randomUUID().toString();
      course.setCourseID(courseId);
      course.setStudentID(savedStudentId);
      studentsService.insertStudentsCourses(course);
    }
    return "redirect:/student";
  }

  @GetMapping("/studentsDetail/{id}")
  public String getStudentDetails(@PathVariable String id, Model model) {
    StudentsDetail studentsDetail = studentsService.findStudentById(id);
    System.out.println("学生詳細: " + studentsDetail);
    model.addAttribute("studentsDetail", studentsDetail);
    return "updateStudents";
  }

  @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute("studentsDetail") StudentsDetail studentsDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "updateStudents";
    }
    System.out.println("更新する学生: " + studentsDetail.getStudent());
    System.out.println("キャンセル状態: " + studentsDetail.getStudent().isDeleted());


    studentsService.updateStudent(studentsDetail.getStudent());

    System.out.println("更新する学生: " + studentsDetail.getStudent());
    System.out.println("キャンセル状態: " + studentsDetail.getStudent().isDeleted());


    for (StudentsCourses course : studentsDetail.getStudentsCourses()) {
      course.setStudentID(studentsDetail.getStudent().getId());  // ここで studentID を設定
      studentsService.updateStudentsCourses(course);
    }
    return "redirect:/student";
  }
}

