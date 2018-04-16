package com.tw;

import java.util.Scanner;

/**
 * Created by wangjie on 2018/4/9.
 */
public class AquireReader {
    public Scanner scanner=new Scanner(System.in);

    public String read(int value) {
        String input = scanner.next();
        return input;
    }
}
