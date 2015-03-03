package me.veryyoung.movie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by veryyoung on 2015/3/2.
 */

@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    @Column(length = 20)
    private String userName;

    @Column(length = 20)
    private String password;


    private boolean isAdmin;

    private boolean enable;

}
