package Raisetech.Student.ManagementSystem.data;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {

  private String courseID;
  private String studentID;
  private String courseName;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;


  public StudentsCourses() {
    this.courseID = UUID.randomUUID().toString(); // UUIDを生成してcourseIdにセットする
  }

  // パラメータ付きコンストラクタ
  public StudentsCourses(String courseName, String studentId) {
    this.courseID = UUID.randomUUID().toString(); // UUIDを生成してcourseIdにセットする
    this.courseName = courseName;
    this.studentID = studentId;
  }



}


