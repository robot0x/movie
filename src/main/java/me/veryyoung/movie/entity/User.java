package me.veryyoung.movie.entity;

import lombok.Data;

/**
 * Created by veryyoung on 2015/3/2.
 */

@Data
public class User extends BaseEntity {

    private String userName;

    private String password;

    private boolean isAdmin;

    private boolean enable;

}
