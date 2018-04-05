package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class LibraryTest {
    public static final String TEST_STUDENT_INFO = "张三, 121, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
    public static final String TEST_STUDENT_INFO2 = "李四, 122, 数学: 85, 语文: 80, 英语: 70, 编程: 90";
    CommandReader reader;
    Library library;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        library = new Library();
        reader = new CommandReader();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintMainMenu() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("3");
        library.printMainMenu();
        assertThat(systemOut()).isEqualTo("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：\n");
    }

    private String systemOut() { return outContent.toString(); }

    @Test
    public void shouldReturnFalseWhenInput3() throws Exception {
        reader = mock(CommandReader.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("3");
        assertFalse(library.printMainMenu());
    }

    @Test
    public void shouldReturnFalseWhenInputOutOf3() throws Exception {
        reader = mock(CommandReader.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("55");
        assertFalse(library.printMainMenu());
    }

    @Test
    public void shouldReturnFalseWhenInputLessThan0() throws Exception {
        reader = mock(CommandReader.class);
        library = mock(Library.class);
        when(reader.read(2)).thenReturn("-1");
        assertFalse(library.printMainMenu());
    }

    @Test
    public void shouldPromptMsgWhenAddStudent() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.printMainMenu();
        assertThat(systemOut().contains("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n")).isTrue();
    }

    @Test
    public void shouldReturnSuccessWhenAddInRightFormat() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.addStudent();
        assertThat(systemOut().endsWith("学生张三的成绩被添加\n")).isTrue();
    }

    @Test
    public void shouldGenerateEmptyReportWhenInputNoId() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("121");
        library.printMainMenu();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "========================\n" +
                "全班总分平均数：0.0\n" +
                "全班总分中位数：0\n")).isTrue();
    }

    @Test
    public void shouldGenerateReportWhenInputId() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.printMainMenu();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("121");
        library.printMainMenu();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "========================\n" +
                "全班总分平均数：330.0\n" +
                "全班总分中位数：330\n")).isTrue();
    }

    @Test
    public void shouldGenerateReportWhenInputIdNotExist() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.printMainMenu();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("121, 122");
        library.printMainMenu();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "========================\n" +
                "全班总分平均数：330.0\n" +
                "全班总分中位数：330\n")).isTrue();
    }

    @Test
    public void shouldGenerateRightReportWhenInputIdMoreThanOne() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.printMainMenu();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO2);
        library.printMainMenu();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("121, 122");
        library.printMainMenu();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|80|82.5|330\n" +
                "李四|85|80|70|90|81.25|325\n" +
                "========================\n" +
                "全班总分平均数：327.5\n" +
                "全班总分中位数：325\n")).isTrue();
    }

    @Test
    public void shouldUpdateReportWhenInputSameIdInfo() throws Exception {
        reader = mock(CommandReader.class);
        library = new Library(reader, new ArrayList<>());
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn(TEST_STUDENT_INFO);
        library.printMainMenu();
        when(reader.read(2)).thenReturn("1");
        when(reader.read(0)).thenReturn("张三, 121, 数学: 75, 语文: 95, 英语: 80, 编程: 70");
        library.printMainMenu();
        when(reader.read(2)).thenReturn("2");
        when(reader.read(1)).thenReturn("121");
        library.printMainMenu();
        assertThat(systemOut().endsWith("成绩单\n"
                + "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75|95|80|70|80.0|320\n" +
                "========================\n" +
                "全班总分平均数：320.0\n" +
                "全班总分中位数：320\n")).isTrue();
    }

//    @Test
//    public void testSomeLibraryMethod(){
//        Library classUnderTest = new Library();
//        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
//    }

//    @Test
//    public void testMockClass() throws Exception {
//        // you can mock concrete classes, not only interfaces
//        LinkedList mockedList = mock(LinkedList.class);
//
//        // stubbing appears before the actual execution
//        String value = "first";
//        when(mockedList.get(0)).thenReturn(value);
//
//        assertEquals(mockedList.get(0), value);
//
//    }

}
