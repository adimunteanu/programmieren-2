package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;


public class Student {

    private String country;


    private String name;

    private HashMap<Courses, Double> resultSheet = new HashMap<>();

    private List<String> supervisors = new MonitoredList<>();

    public Student(String name, String country, HashMap resultSheet) {
        this.name=name;
        this.country = country;
        this.resultSheet = resultSheet;
    }

    public Student(JSONObject student) {
        this.name = (String) student.get("name");
        this.country = (String) student.get("country");
        JSONArray results = (JSONArray) student.get("results");
        for (Object result : results) {
            JSONObject e = (JSONObject) result;
            resultSheet.put(Courses.valueOf((String) e.get("name")), (Double) e.get("grade"));
        }
        JSONArray supervisors = (JSONArray) student.get("supervisors");
        for(Object supervisor : supervisors) {
            this.supervisors.add((String) supervisor);
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap getResultSheet() {
        return resultSheet;
    }

    public void setResultSheet(HashMap<Courses, Double> resultSheet) {
        this.resultSheet = resultSheet;
    }

    public List<String> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<String> supervisors) {
        this.supervisors = supervisors;
    }

    @Override
    public String toString() {
        return "Student{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", resultSheet=" + resultSheet +
                ", supervisors=" + supervisors +
                '}';
    }
}
