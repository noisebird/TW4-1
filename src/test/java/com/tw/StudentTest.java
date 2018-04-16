package com.tw;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by wangjie on 2018/4/16.
 */
public class StudentTest {
    private Student student;

    @Before
    public void before() {
        student = new Student("zhangsan", 1001);
        student.setMath_score(80);
        student.setEnglish_score(90);
        student.setProgramming_score(86);
        student.setChinese_score(78);
    }

    @Test
    public void should_student_calculate_the_avg_score_will_return_correct() throws Exception {
        student.calculateAvg();
        assertEquals(83.5, student.getAvg());
    }

    @Test
    public void should_student_calculate_the_sum_score_will_return_correct() throws Exception {
        student.calculateSum();
        assertEquals(334, student.getSum());
    }

    @Test
    public void should_student_print_grade_info_will_return_correct() throws Exception {
        String gradeInfo = student.printGradeInfo();
        assertEquals("zhangsan|1001|80|78|90|86|83.5|334", gradeInfo);
    }
}
