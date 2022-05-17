package com.company.prob2;

import java.util.ArrayList;

public class CourseList {
    public static void main(String[] args) {
        RMITCourse c1 = new RMITCourse("Programming 1", "C123");
        RMITCourse c2 = new RMITCourse("DSA", "C234");
        RMITCourse c3 = new RMITCourse("SED", "C345");
        RMITCourse c4 = new RMITCourse("CAD", "C456");
        CourseList list = new CourseList();
        list.addCourse(c1);
        list.addCourse(c2);
        list.addCourse(c3);
        list.addCourse(c4);
        System.out.println(list);
    }

    ArrayList<RMITCourse> courses;
    public CourseList() {
        courses = new ArrayList<>();
    }

    public void addCourse(RMITCourse c) {
        courses.add(c);
    }

    public void addPrerequisite(RMITCourse c, RMITCourse pre) {
        c.addPre(pre);
    }

    public boolean takeFirst(RMITCourse c) {
        return (c.pre.size() == 0);
    }

//    public String courseTaken() {
//        int n = courses.size();
//        ArrayList<RMITCourse> res = new ArrayList<>();
//
//        while (res.size() < n) {
//            for (int i = 0; i < n; i++) {
//
//            }
//        }
//    }
}

class RMITCourse {
    private String name;
    private String code;
    ArrayList<RMITCourse> pre;

    public RMITCourse(String name, String code) {
        this.name = name;
        this.code = code;
        pre = new ArrayList<>();
    }

    public void addPre(RMITCourse require) {
        pre.add(require);
    }
}
