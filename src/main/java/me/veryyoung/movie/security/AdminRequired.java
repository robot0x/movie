package me.veryyoung.movie.security;

/**
 * Created by veryyoung on 2015/4/28.
 */

import java.lang.annotation.*;

/**
 * Need admin to view/trigger action or resources no matter what privilege
 *
 * @author veryyoung
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface AdminRequired {
}
