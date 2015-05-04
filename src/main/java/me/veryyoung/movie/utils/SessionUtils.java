package me.veryyoung.movie.utils;

import lombok.Data;
import me.veryyoung.movie.entity.User;

/**
 * Created by veryyoung on 2015/4/23.
 */
@Data
public class SessionUtils {

    /**
     * The current login user.
     */
    private User user;


}
