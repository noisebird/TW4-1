package com.tw;

/**
 * Created by wangjie on 2018/4/9.
 */
public class Student {
    public static final String SPERATOR="|";
    private String name;
    private int id;
    private int math_score;
    private int chinese_score;
    private  int english_score;
    private int programming_score;
    private double avg;
    private int sum;

    public Student() {
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMath_score() {
        return math_score;
    }

    public void setMath_score(int math_score) {
        this.math_score = math_score;
    }

    public int getChinese_score() {
        return chinese_score;
    }

    public void setChinese_score(int chinese_score) {
        this.chinese_score = chinese_score;
    }

    public int getEnglish_score() {
        return english_score;
    }

    public void setEnglish_score(int english_score) {
        this.english_score = english_score;
    }

    public int getProgramming_score() {
        return programming_score;
    }

    public void setProgramming_score(int programming_score) {
        this.programming_score = programming_score;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
    public void calculateAvg(){

    }
    public void calculateSum() {

    }
    public String printGradeInfo(){
        return null;
    }

}
