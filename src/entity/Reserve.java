package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

@Entity
public class Reserve implements SuperEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "student_ID", referencedColumnName = "studentID")
    private Student sID;
    @ManyToOne
    @JoinColumn(name = "room_ID", referencedColumnName = "roomID")
    private Room rID;
    private String date;

    @JoinColumn(name = "key_money",referencedColumnName = "keyMoney")
    private double key_money;
}
