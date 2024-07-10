package raisetech.student.management.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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


@RestController
public class StudentsController {

  private StudentsService studentsService;
  private StudentsConverter converter;

  @Autowired
  public StudentsController(StudentsService studentsService, StudentsConverter converter) {
    this.studentsService = studentsService;
    this.converter = converter;
  }

  @GetMapping("/student")
  public List<StudentsDetail> getStudentsList() {
    List<Student> students = studentsService.searchStudentList();
    List<StudentsCourses> studentsCourses = studentsService.searchStudentsCoursesList();
    return converter.convertStudentsDetails(students,
        studentsCourses);
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    model.addAttribute("studentsDetail", new StudentsDetail());
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute("studentsDetail") StudentsDetail studentsDetail,
      BindingResult result) {
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

 

  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentsDetail studentsDetail
      ) {

    studentsService.updateStudent(studentsDetail.getStudent());

    return ResponseEntity.ok("更新処理が成功しました。");
  }
}

