package me.veryyoung.movie.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 电影条目
 * Created by veryyoung on 2015/3/12.
 */
@Data
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(length = 32)
    private String id;

    //对应豆瓣电影的id
    private String doubanId;

    //标题
    private String title;

    //原名
    private String originalTitle;

    //又名
    private String aka;

    //评分
    private float rating;

    //宣传海报
    private String image;

    //导演
    private String directors;

    //主演
    private String casts;

    //编剧
    private String writers;


    //上映日期
    private Date pubDate;

    //上映年份
    private short year;

    //语言
    private String languages;

    //片长
    private String durations;

    //影片类型
    private String genres;

    //制片国家/地区
    private String countries;

    //简介
    private String summary;

    //预告片URL
    private String trailers;

    //电影剧照
    private String photos;


}
