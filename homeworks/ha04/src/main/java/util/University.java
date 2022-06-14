package util;

import org.json.simple.JSONObject;

import java.util.List;

public class University {
    private String name;
    private List<Courses> courses = new MonitoredList<>();

    public University(String name, List courses) {
        this.name = name;
        this.courses = courses;
    }

    public University(JSONObject uni) {
        this.name = (String) uni.get("name");
        List<String> courses = (List<String>) uni.get("courses");
        for(String course : courses) {
            this.courses.add(Courses.valueOf(course));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getCourses() {
        return courses;
    }

    public void setCourses(List courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
