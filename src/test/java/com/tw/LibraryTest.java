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
}
