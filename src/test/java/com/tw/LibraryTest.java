package com.tw;



import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by wangjie on 2018/4/9.
 */
public class LibraryTest {
    private Library library;
    private AquireReader reader;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static final String STUDENT_INFO = "张三,1001,数学:85,语文:78,英语:79,编程:98";
    public static final String STUDENT_INFO1 = "李四,1002,数学:88,语文:74,英语:79,编程:68";

    @Before
    public void setUp() {
        reader = mock(AquireReader.class);
        library = new Library(new HashMap<String, Student>(), reader);
        System.setOut(new PrintStream(outContent));

    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_test_return_init() throws Exception {

        when(reader.read(2)).thenReturn("3");
        library.init();
        assertThat(systemOut()).isEqualTo("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：\n");
    }

    @Test
    public void should_test_return_out_of_bound_init() throws Exception {
        when(reader.read(2)).thenReturn("4");
        assertThat(library.init()).isFalse();
    }

    @Test
    public void should_test_exit() throws Exception {
        when(reader.read(2)).thenReturn("3");
        assertThat(library.init()).isFalse();
    }


    @Test
    public void should_test_one_return_content() throws Exception {
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO);
        library.init();
        assertThat(systemOut().contains("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n")).isTrue();

    }

    @Test
    public void should_test_two_return_content() throws Exception {
        when(reader.read(2)).thenReturn("2");
        when(reader.read(3)).thenReturn("1001,1002");
        library.init();
        assertThat(systemOut().contains("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n")).isTrue();
    }

    @Test
    public void should_test_add_student_info_correct() throws Exception {
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO);
        library.init();
        assertThat(systemOut()).contains("学生张三的成绩被添加\n");
    }

    @Test
    public void should_test_add_student_info_false() throws Exception {
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn("张三,1001").thenReturn("张三,1001,数学:100");
        library.init();
        assertThat(systemOut()).contains("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n");
    }

    @Test
    public void should_test_query_student_info_list_correct() throws Exception {
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO);
        library.init();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO1);
        library.init();

        when(reader.read(2)).thenReturn("2");
        when(reader.read(3)).thenReturn("1001,1002");
        library.init();
        assertThat(systemOut()).contains("成绩单\n" +
                "姓名|学号|数学|语文|英语|编程|平均分|总分\n" +
                "==============================================================================\n" +
                "张三|1001|85|78|79|98|85.0|340\n" +
                "李四|1002|88|74|79|68|77.25|309\n" +
                "============================================================================= \n" +
                "全班总分平均数：324.5\n" +
                "全班总分中位数：324.5"
        );
    }

    @Test
    public void should_test_add_student_info_list_false() throws Exception {
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO);
        library.init();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(3)).thenReturn(STUDENT_INFO1);
        library.init();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(3)).thenReturn("1001:1002").thenReturn("1001,1002");
        library.init();
        assertThat(systemOut()).contains("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
    }


}
