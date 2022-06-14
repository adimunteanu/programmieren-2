package util;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * DO NOT CHANGE THIS FILE
 */

public class Data {

    public static MonitoredList<University> universities() {
        JSONParser parser = new JSONParser();
        InputStream ioStream =
                Data.class.getClassLoader().getResourceAsStream("universities.json");

        JSONArray universitiesJSON;
        try {
            if (ioStream != null) {
                universitiesJSON = (JSONArray) parser.parse(new InputStreamReader(ioStream));
            } else {
                return null;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        MonitoredList<University> universities = new MonitoredList<>();
        for (Object o : universitiesJSON) {
            universities.add(new University((JSONObject) o));
        }

        return universities;
    }

    public static MonitoredList<Student> students() {
        JSONParser parser = new JSONParser();
        InputStream ioStream =
                Data.class.getClassLoader().getResourceAsStream("students.json");

        JSONArray studentsJSON;
        try {
            if (ioStream != null) {
                studentsJSON = (JSONArray) parser.parse(new InputStreamReader(ioStream));
            } else {
                return null;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        MonitoredList<Student> students = new MonitoredList<>();
        for (Object o : studentsJSON) {
            students.add(new Student((JSONObject) o));
        }

        return students;
    }

    public static MonitoredList<Event> events() {
        JSONParser parser = new JSONParser();
        InputStream ioStream =
                Data.class.getClassLoader().getResourceAsStream("events.json");

        JSONArray eventsJSON;
        try {
            if (ioStream != null) {
                eventsJSON = (JSONArray) parser.parse(new InputStreamReader(ioStream));
            } else {
                return null;
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        MonitoredList<Event> events = new MonitoredList<>();
        for (Object o : eventsJSON) {
            events.add(new Event((JSONObject) o));
        }

        return events;
    }
}
