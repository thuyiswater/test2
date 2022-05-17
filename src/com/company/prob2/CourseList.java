package com.company.prob2;

import java.util.ArrayList;

public class CourseList {
    public static void main(String[] args) {
        RMITCourse c1 = new RMITCourse("Programming 1", "C123");
        RMITCourse c2 = new RMITCourse("Web Programming", "C234");
        RMITCourse c3 = new RMITCourse("Data Structures", "C345");
        RMITCourse c4 = new RMITCourse("Database Application", "C456");
        CourseList list = new CourseList();
        list.addCourse(c1);
        list.addCourse(c2);
        list.addCourse(c3);
        list.addCourse(c4);
        list.addPrerequisite(c2, c1);  // make Programming 1 a prerequisite of Web Programming
        list.addPrerequisite(c3, c1);  // make Programming 1 a prerequisite of Data Structures
        list.addPrerequisite(c4, c2);  // make Web Programming a prerequisite of Database Application
        System.out.println(list.takeFirst(c1));  // true
        System.out.println(list.takeFirst(c3));  // false
        list.courseTaken();
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

    public void courseTaken() {
        int n = courses.size();
        ArrayList<RMITCourse> res = new ArrayList<>();
        boolean[] added = new boolean[n];

        // Do until we have extracted n courses
        while (res.size() < n) {
            for (int i = 0; i < n; i ++) {
                if (added[i]) continue;
                RMITCourse c = courses.get(i);
                boolean found = false;
                for (int j = 0; j  < n; j++) {
                    if (added[j]) continue;
                    if (i == j) continue;
                    RMITCourse preq = courses.get(j);
                    if (c.pre.contains(preq)) {
                        found = true;
                        break;
                    }
                }
                if (found) continue;
                res.add(c);
                added[i] = true;
                for (int j = 0; j < n; j++) {
                    RMITCourse a = courses.get(j);
                    if (a.pre.contains(c)) a.pre.remove(c);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(res.get(i).getName());
        }
    }
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

    public String getName() {
        return name;
    }

    public void addPre(RMITCourse require) {
        pre.add(require);
    }
}
