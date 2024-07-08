package raisetech.student.management.data;


import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private String id;
  private String name;
  private String hurigana;
  private String nickname;
  private String mailaddress;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted;


  public Student() {this.id = UUID.randomUUID().toString();

    ;} // デフォルトは削除されていない状態}


  public Student(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    ; // 初期値として false を設定
  }


}