package me.veryyoung.movie.security;

import java.lang.annotation.*;

/**
 * 当Resource整体被@LoginRequired修饰时，可以单独标记在方法上免去登陆需求
 *
 * @author veryyoung
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface FreeAccess {
}
