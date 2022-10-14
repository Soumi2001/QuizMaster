package com.example.quizmaster;

public class UserClass {
    private String name;
    private String subject;
    private String marks;
    private String examdate;

    public UserClass(String name, String subject, String marks, String examdate) {
        this.name = name;
        this.subject = subject;
        this.marks = marks;
        this.examdate = examdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }
}

