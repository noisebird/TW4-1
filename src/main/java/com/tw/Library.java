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
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
        String reg = "[\u4E00-\u9FA5A-Za-z]+,\\d+(,[\u4E00-\u9FA5]+:(0|[1-9][0-9]?|100))+";
        while (true) {
            String input = aquireReader.read(3);
            if (input.matches(reg)) {
                String[] arr = input.split(",");
                Student student = new Student();
                student.setName(arr[0]);
                student.setId(Integer.parseInt(arr[1]));
                for (int i = 2; i < arr.length; i++) {
                    String[] subject = arr[i].split(":");
                    if (subject[0].equals("数学")) {
                        student.setMath_score(Integer.parseInt(subject[1]));
                    }
                    if (subject[0].equals("语文")) {
                        student.setChinese_score(Integer.parseInt(subject[1]));
                    }
                    if (subject[0].equals("英语")) {
                        student.setEnglish_score(Integer.parseInt(subject[1]));
                    }
                    if (subject[0].equals("编程")) {
                        student.setProgramming_score(Integer.parseInt(subject[1]));
                    }
                }
                map.put(arr[1],student);
                System.out.println("学生" + student.getName() + "的成绩被添加");
                break;
            } else {
                System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            }
        }

    }

    //程序入口
    public void enter() {
        while (init()) {
        }
    }

}
