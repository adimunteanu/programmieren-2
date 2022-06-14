package util;

import org.json.simple.JSONObject;

/**
 * DO NOT CHANGE THIS FILE
 * This is simple event class.
 * To keep it simple in our case Event can be a Lecture or Tutorium.
 * Event can have different duration in minutes as well as different number of participants.
 * Every event must have a unique title.
 */
public class Event {
    private final String name;
    private final boolean isLecture;
    private final int participants;
    private final int duration;

    public Event(String name, boolean isLecture, int participants, int duration) {
        this.name = name;
        this.isLecture = isLecture;
        this.participants = participants;
        this.duration = duration;
    }

    public Event(JSONObject json) {
        this.name = (String) json.get("name");
        this.isLecture = (boolean) json.get("isLecture");
        this.participants = (int) (long) json.get("participants");
        this.duration = (int) (long) json.get("duration");
    }

    public String getName() {
        return name;
    }

    public boolean isLecture() {
        return isLecture;
    }

    public int getParticipants() {
        return participants;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + name + '\'' +
                ", isLecture=" + isLecture +
                ", totalParticipants=" + participants +
                ", duration=" + duration +
                '}';
    }
}
