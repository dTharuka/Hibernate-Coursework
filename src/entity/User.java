package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements SuperEntity{
    private String userName;
    @Id
    private String password;
}
