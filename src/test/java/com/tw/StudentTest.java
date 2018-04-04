package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student = null;

    @Before
    public void beforeEach() {
        student = new Student();
    }

    @Test
    public void ShouldReturnRightStudentInfoWhenhasFourScores() {
        Map<String,Integer> map = new HashMap<String,Integer>(){{
            put("数学", 75);
            put("语文", 95);
            put("英语", 80);
            put("编程", 80);
        }};
        student.add("张三", 121, map);
        assertEquals("张三|75|95|80|80|82.5|330", student.printStudentInfo());

    }

    //缺某些课程成绩时按0分处理，正常计算总分、平均分
    @Test
    public void ShouldReturnRightStudentInfoWhenhasLessThanFourScores() {
        Map<String,Integer> map = new HashMap<String,Integer>(){{
            put("数学", 75);
            put("语文", 95);
            put("英语", 80);
        }};
        student.add("张三", 121, map);
        assertEquals("张三|75|95|80|0|62.5|250", student.printStudentInfo());

    }
}