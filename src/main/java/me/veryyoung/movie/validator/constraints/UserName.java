package me.veryyoung.movie.validator.constraints;

/**
 * Created by veryyoung on 2015/4/21.
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Validation constraints for loginName
 * 限制登录名不能为手机号或者邮箱地址
 *
 * @author veryyoung
 */
@Pattern(regexp = "^(?!(([1][3|5|8][0-9]{9})|([\\\\w-]+(\\\\.[\\\\w-]+)*@[\\\\w-]+(\\\\.[\\\\w-]+)+)))([0-9a-zA-Z_\u4E00-\u9FBF]+)", message = "用户名为数字字母或汉字，不能为手机号或邮箱")
@NotNull(message = "用户名不能为空")
@Size(min = 2, max = 30, message = "用户名长度需为2-30位")
@Constraint(validatedBy = {})
@Target({ElementType.METHOD,
        ElementType.FIELD,
        ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserName {

    String message() default "{common.loginName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
