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
        System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
        String reg = "\\d+(,\\d+)*";
        double allTotalScore = 0;
        while (true) {
            String input = aquireReader.read(3);
            if (input.matches(reg)) {
                System.out.println("成绩单\n姓名|学号|数学|语文|英语|编程|平均分|总分");
                System.out.println("==============================================================================");
                String[] arr = input.split(",");
                for (String id : arr) {
                    Set<String> set=map.keySet();
                    if(set.contains(id)){
                        Student student=map.get(id);
                        System.out.println(map.get(id).printGradeInfo());
                        allTotalScore +=student.getSum();
                    }

                }

                System.out.println("============================================================================= ");
                System.out.println("全班总分平均数：" + allTotalScore /  map.size());
                System.out.println("全班总分中位数：" + calculateMidea());
                break;
            } else {
                System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
            }
        }
    }

    //计算中位数
    private double calculateMidea() {
        if (map.size() == 0) {
            return 0;
        }
        List<Integer> tempList = new ArrayList<Integer>();
        Set<String> set=map.keySet();
        for (String id : set) {
            tempList.add(map.get(id).getSum());
        }
        Collections.sort(tempList);
        int size = tempList.size();
        double mid = 0;

        if (size > 0 && size % 2 == 0) {
            mid = (tempList.get(size / 2 - 1) + tempList.get(size / 2)) / (double) 2;
        } else {
            mid = tempList.get(size / 2);
        }
        return mid;
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
