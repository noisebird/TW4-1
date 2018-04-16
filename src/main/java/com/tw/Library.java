package com.tw;

import java.util.*;

/**
 * Created by wangjie on 2018/4/9.
 */
public class Library {
    private Map<String,Student> map;
    private AquireReader aquireReader;

    public Library() {
    }

    public Library(Map<String,Student> student1, AquireReader reader) {
        this.map = student1;
        this.aquireReader = reader;
    }

    // 程序初始化
    public boolean init() {
        System.out.println("1. 添加学生\n2. 生成成绩单\n3. 退出\n请输入你的选择（1～3）：");
        switch (Integer.parseInt(aquireReader.read(2))) {
            case 1:
                addStudent();
                return true;
            case 2:
                printScore();
                return true;
            case 3:
                return false;
            default:
                return false;
        }
    }

    // 打印成绩
    private void printScore() {

    }
    //添加学生信息
    private void addStudent() {
    }

    //程序入口
    public void enter() {
        while (init()) {
        }
    }

}
