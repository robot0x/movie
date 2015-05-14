package me.veryyoung.movie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by veryyoung on 2015/5/11.
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    private String subjectId;

    private String userId;

    private short rating;

    //简介
    @Column(length = 1000)
    private String content;

    private Date submitDate;
}
