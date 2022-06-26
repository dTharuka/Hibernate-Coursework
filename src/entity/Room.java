package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@EqualsAndHashCode

@Entity(name = "room")
public class Room implements SuperEntity {
    @Id
    private String roomID;
    private String roomType;

    @Column(name = "roomQty",columnDefinition = "INT UNSIGNED")
    private int roomQty;

//    private String roomQty;

    private double monthlyRent;

    @Transient
    @OneToMany(cascade = CascadeType.ALL)
    private  List<Student> studentList;

    @OneToMany(mappedBy = "rID", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reserve> reserveList;

    public Room(String roomID, String roomType, int roomQty, double monthlyRent) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.roomQty = roomQty;
        this.monthlyRent = monthlyRent;
    }

    public Room(String roomType, int roomQty, double monthlyRent) {
        this.roomType = roomType;
        this.roomQty = roomQty;
        this.monthlyRent = monthlyRent;
    }

    //    public Room(String roomID, String roomType, String roomQty, double monthlyRent) {
//        this.roomID = roomID;
//        this.roomType = roomType;
//        this.roomQty = roomQty;
//        this.monthlyRent = monthlyRent;
//    }
//
//    public Room(String roomType, String roomQty, double monthlyRent) {
//        this.roomType = roomType;
//        this.roomQty = roomQty;
//        this.monthlyRent = monthlyRent;
//    }
}
