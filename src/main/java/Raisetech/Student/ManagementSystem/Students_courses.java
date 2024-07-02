package Raisetech.Student.ManagementSystem;


import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Students_courses {
  private String course_ID;
  private String student_ID;
  private String course_name;
  private Date start_date;
  private Date end_date;

}
