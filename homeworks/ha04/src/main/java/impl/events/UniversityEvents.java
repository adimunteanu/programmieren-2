package impl.events;

import util.Event;

import java.util.List;

/**
 * ERGÄNZEN SIE IN DEN UNTENSTEHENDEN STREAM-PIPELINES DIE FEHLENDEN LAMBDA-AUSRÜCKE.
 *
 * VERÄNDERN SIE SONST NICHTS AN DER VORGABE!
 */
public class UniversityEvents {

    /**
     * 4.1.1: 2 Punkte
     * Finden Sie die Namen aller Vorlesungen.
     *
     * @param events List of events including their name, type, number of participants, and duration.
     *
     * @return List of names of all events that are lectures, ordered alphabetically.
     */
    public static List<String> getLectureNames(List<Event> events) {
        return events.stream()
                .filter() //TODO: get lecture events
                .sorted() //TODO: sort alphabetically
                .map() //TODO: map to event name
                .toList();
    }

    /**
     * 4.1.2: 2 Punkte
     * Finden Sie die durschnittliche Teilnehmerzahl aller Tutorien (= Veranstaltungen, die keine Vorlesungen sind).
     *
     * @param events List of events including their name, type, number of participants, and duration.
     *
     * @return The average (mean) number of participants of events that are not lectures.
     */
    public static double averageTutorialStudentCount(List<Event> events) {
        long tutorialCount = events.stream()
                .filter() //TODO: get non-lecture events
                .count();

        return events.stream()
                .filter() //TODO: get non-lecture events
                .map() //TODO: map to participant count
                .reduce() //TODO: add up the participant counts
                .doubleValue() / tutorialCount;
    }

    /**
     * 4.1.3: 2 Punkte
     * Finden Sie den Namen des Tutoriums mit den meisten Teilnehmern.
     *
     * @param events List of events including their name, type, number of participants, and duration.
     *
     * @return The name of the non-lecture event that has the highest number of participants.
     */
    public static String mostPopularTutorial(List<Event> events) {
        return events.stream()
                .filter() //TODO: get non-lecture events
                .max() //TODO: find highest tutorial with participant count
                .map() //TODO: map to event name
                .orElse(null);
    }
}