package me.veryyoung.movie.validator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by veryyoung on 2015/4/21.
 */
@Data
@AllArgsConstructor
public class Violation {

    private String message;

    private Object invalidValue;

    private Object invalidObject;
}
