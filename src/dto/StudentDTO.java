//package dto;
//
//public class StudentDTO {
//    private String studentID;
//    private String name;
//    private int age;
//    private String address;
//    private String dateOfBirth;
//    private String nic;
//    private String phoneNumber;
//    private String parentPhoneNumber;
//    private String parentName;
//
//    public StudentDTO(String studentID, String name, int age, String address, String dateOfBirth, String studentAddress, String phoneNumber) {
//        this.studentID = studentID;
//        this.name = name;
//        this.age = age;
//        this.address = address;
//        this.dateOfBirth = dateOfBirth;
//        this.phoneNumber = phoneNumber;
//    }
//
//
//
//    public StudentDTO(String studentID, String name, int age, String address, String dateOfBirth, String nic, String phoneNumber, String parentPhoneNumber, String parentName) {
//        this.studentID = studentID;
//        this.name = name;
//        this.age = age;
//        this.address = address;
//        this.dateOfBirth = dateOfBirth;
//        this.nic = nic;
//        this.phoneNumber = phoneNumber;
//        this.parentPhoneNumber = parentPhoneNumber;
//        this.parentName = parentName;
//    }
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
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getParentPhoneNumber() {
//        return parentPhoneNumber;
//    }
//
//    public void setParentPhoneNumber(String parentPhoneNumber) {
//        this.parentPhoneNumber = parentPhoneNumber;
//    }
//
//    public String getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(String parentName) {
//        this.parentName = parentName;
//    }
//
//    @Override
//    public String toString() {
//        return "StudentDTO{" +
//                "studentID='" + studentID + '\'' +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", address='" + address + '\'' +
//                ", dateOfBirth='" + dateOfBirth + '\'' +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", parentPhoneNumber='" + parentPhoneNumber + '\'' +
//                ", parentName='" + parentName + '\'' +
//                '}';
//    }
//
//    public String getNic() {
//        return nic;
//    }
//
//    public void setNic(String nic) {
//        this.nic = nic;
//    }
//}
package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String studentID;
    private String name;
    private int age;
    private String address;
    private String contactNo;
    private String dateOfBirth;
    private String nic;
    private String gender;
    private double keyMoney;


}
