package demo.hpg.org.pauldemo.rxjava;

import java.util.ArrayList;

/**
 * Created by Paul on 15/11/30.
 */
public class Student {
    String name;

    ArrayList<Course> courses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
