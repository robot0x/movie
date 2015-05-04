package me.veryyoung.movie.rest;

import lombok.Data;

/**
 * Created by veryyoung on 2015/4/21.
 * Define a standard JSON return format for client
 */
@Data
public class RestData {

    //response status
    private int success = 0;  // 默认为0,失败
    //return code
    private String code;
    //if some error occurs, it will set some reasons to comment
    private String comment;
    //data field
    private Object data;
}
