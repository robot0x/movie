package me.veryyoung.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 正在热映电影
 * Created by veryyoung on 2015/5/7.
 */
@Data
@Entity
@Table(name = "playing")
@AllArgsConstructor
@NoArgsConstructor
public class Playing {
    //对应豆瓣电影的id
    @Id
    @Column(length = 8)
    private String id;
}
