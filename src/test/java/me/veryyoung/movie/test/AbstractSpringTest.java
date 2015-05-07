package me.veryyoung.movie.test;

/**
 * Created by veryyoung on 2015/5/7.
 */

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public abstract class AbstractSpringTest {

    protected Logger logger;
    String classname;

    public AbstractSpringTest() {
        super();
        classname = getClass().getName();
        logger = LoggerFactory.getLogger(classname);
    }


}