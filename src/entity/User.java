package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

@Entity
public class User implements SuperEntity{
    @Id
    private String UserID;
    private String userName;

    private String password;
}
