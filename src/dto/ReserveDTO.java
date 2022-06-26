//package dto;
//
//public class ProgramDataDTO {
//    private String id;
//    private String sID;
//    private String pID;
//    private String date;
//
//    public ProgramDataDTO() {
//    }
//
//    public ProgramDataDTO(String id, String sID, String pID, String date) {
//        this.id = id;
//        this.sID = sID;
//        this.pID = pID;
//        this.date = date;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getsID() {
//        return sID;
//    }
//
//    public void setsID(String sID) {
//        this.sID = sID;
//    }
//
//    public String getpID() {
//        return pID;
//    }
//
//    public void setpID(String pID) {
//        this.pID = pID;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    @Override
//    public String toString() {
//        return "ProgramDataDTO{" +
//                "id='" + id + '\'' +
//                ", sID='" + sID + '\'' +
//                ", pID='" + pID + '\'' +
//                ", date='" + date + '\'' +
//                '}';
//    }
//}

package dto;

import lombok.*;

import javax.persistence.JoinColumn;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class ReserveDTO {
    private String id;
    private String sID;
    private String rID;
    private String date;
    private double keyMoney;

}
