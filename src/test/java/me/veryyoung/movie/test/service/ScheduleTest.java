package me.veryyoung.movie.test.service;

import me.veryyoung.movie.test.AbstractSpringTest;
import me.veryyoung.movie.utils.ScheduleUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by veryyoung on 2015/5/29.
 */
public class ScheduleTest extends AbstractSpringTest {

    @Autowired
    private ScheduleUtils scheduleUtils;

    @Test
    public void test() {
        scheduleUtils.renewPlaying();
    }
}
