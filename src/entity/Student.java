//package entity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import java.util.List;
//
//
//@Entity
//public class Student implements SuperEntity {
//    @Id
//    private String studentID;
//    private String name;
//    private String address;
//    private String contactNo;
//    private String dateOfBirth;
//    private int age;
//    private String gender;
//
//    @OneToMany(mappedBy = "sID", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<ProgramData> programDataList;
//
//    public Student() {
//    }
//
//    public Student(String studentID, String name, String address, String contactNo, String dateOfBirth, int age) {
//        this.studentID = studentID;
//        this.name = name;
//        this.address = address;
//        this.contactNo = contactNo;
//        this.dateOfBirth = dateOfBirth;
//        this.age = age;
//    }
//
//
//    public String getStudentID() {
//        return studentID;
//    }
//
//    public void setStudentID(String studentID) {
//        this.studentID = studentID;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getContactNo() {
//        return contactNo;
//    }
//
//    public void setContactNo(String contactNo) {
//        this.contactNo = contactNo;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public List<ProgramData> getProgramDataList() {
//        return programDataList;
//    }
//
//    public void setProgramDataList(List<ProgramData> programDataList) {
//        this.programDataList = programDataList;
//    }
//
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "studentID='" + studentID + '\'' +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", contactNo='" + contactNo + '\'' +
//                ", dateOfBirth='" + dateOfBirth + '\'' +
//                ", age=" + age +
//                ", gender='" + gender + '\'' +
//                ", programDataList=" + programDataList +
//                '}';
//    }
//}


package entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

@Entity
public class Student implements SuperEntity {
    @Id
    private String studentID;
    private String name;
    private int age;//-----------------
    private String address;
    private String contactNo;
    private String dateOfBirth;
    private String nic;//------------------
    private String gender;
    private double keyMoney;//-------------------


    @OneToMany(mappedBy = "sID", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reserve> reserveList;

    public Student(String studentID, String name, int age, String address, String contactNo, String dateOfBirth, String nic, String gender, double keyMoney) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contactNo = contactNo;
        this.dateOfBirth = dateOfBirth;
        this.nic = nic;
        this.gender = gender;
        this.keyMoney = keyMoney;
    }
}

