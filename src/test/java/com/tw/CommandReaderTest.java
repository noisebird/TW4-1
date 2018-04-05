package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CommandReaderTest {
    CommandReader reader;

    @Before
    public final void before(){
        reader = new CommandReader();
    }
    private void setInputStream(String input) throws NoSuchFieldException, IllegalAccessException {
        Field scannerField = reader.getClass().getDeclaredField("scanner");
        scannerField.setAccessible(true);
        Scanner scannerWithMockedStream = new Scanner(new ByteArrayInputStream(input.getBytes()));
        scannerField.set(reader, scannerWithMockedStream);
    }

    /* validate the studentInfo*/

    @Test
    public void shouldReturnInputWhenInRightFormat() throws Exception {
        String inputString = "张三, 121, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals(inputString, reader.read(0));
    }

    //no scores return invalid
    @Test
    public void shouldReturnInvalidWhenHasNoScores() throws Exception {
        String inputString = "张三, 121";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //wrong separators return invalid
    @Test
    public void shouldReturnInvalidWhenHasWrongSeparator() throws Exception {
        String inputString = "张三, 121, 数学:75, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //score is not digit return invalid
    @Test
    public void shouldReturnInvalidWhenScoreNotDigit() throws Exception {
        String inputString = "张三, 121, 数学: ss, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    //id is not digit return invalid
    @Test
    public void shouldReturnInvalidWhenIdNotDigit() throws Exception {
        String inputString = "张三, ss, 数学: 75, 语文: 95, 英语: 80, 编程: 80";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(0));
    }

    /* validate the student id list */
    @Test
    public void shouldReturnIdListWhenInRightFormat() throws Exception {
        //格式： 学号, 学号,
        String inputString = "12, 11";
        setInputStream(inputString);
        assertEquals(inputString, reader.read(1));
    }

    @Test
    public void shouldReturnInvalidWhenIdIsNotDigit() throws Exception {
        //格式： 学号, 学号,
        String inputString = "aa, 11";
        setInputStream(inputString);
        assertEquals("invalid", reader.read(1));
    }

    /* validate the input command 1～3 */
    @Test
    public void shouldReturnTheSameWhenInputDigitCommand() throws Exception {
        String inputString = "1";
        setInputStream(inputString);
        assertEquals("1", reader.read(2));
    }
}