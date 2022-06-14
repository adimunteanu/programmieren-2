package impl.stats;

import util.*;

import java.util.List;
import java.util.Map;

/**
 * ACHTUNG: LÖSEN SIE DIESE AUFGABE AUSSCHLIESSLICH MIT JAVA STREAMS!
 *
 * SIE DÜRFEN FÜR IHRE LÖSUNG NUR DAS RETURN-STATEMENT DER GEGEBENEN METHODEN VERÄNDERN,
 * INDEM SIE DORT EINE STREAM-PIPELINE IMPLEMENTIEREN.
 *
 * HILFSVARIABLEN, -METHODEN ODER -KLASSEN SIND NICHT ZULÄSSIG.
 *
 * SIE HABEN IN JEDER METHODE ZUGRIFF AUF DIE LISTE VON STUDENTEN UND UNIVERSITÄTEN. ENTSCHEIDEN SIE FÜR JEDE TEILAUFGABE
 * ENTSPRECHEND, OB SIE DIE STREAM-PIPELINE AUF students ODER universities AUFSETZEN.
 *
 * NESTED STREAMS SIND AUSDRÜCKLICH ERLAUBT.
 */
public class UniversityStats {

    /**
     * 4.2.1: 2 Punkte
     * Finden Sie die Namen aller Studenten, die aus Deutschland kommen, und sortieren Sie sie alphabetisch.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return List of names of the students that are from Germany ('DE'), ordered alphabetically.
     */
    public static List<String> germanStudentsSortedByName(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return null; //TODO: replace null with stream pipeline
    }

    /**
     * 4.2.2: 2 Punkte
     * Finden Sie die Anzahl der Studenten, deren Namen mit 'L' anfangen.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return Number of students that have names beginning with the letter 'L'.
     */
    public static long countStudentNamesBeginningWithL(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return -1; //TODO: replace -1 with stream pipeline
    }

    /**
     * 4.2.3: 2 Punkte
     * Berechnen Sie die Durschnittsnote des Kurses INFORMATION_TECHNOLOGY.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return Average (mean) grade of students that took the INFORMATION_TECHNOLOGY course. Only count their grade for
     * INFORMATION_TECHNOLOGY, even if they also completed other courses.
     */
    public static double averageGradeInformationTechnology(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return -1; //TODO: replace -1 with stream pipeline
    }

    /**
     * 4.2.4: 2 Punkte
     * Finden Sie die Anzahl der Kurse, die jeweils an den Universitäten angeboten werden.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return Map with the name of each university as keys and the number of courses they offer as values.
     */
    public static Map<String, Integer> courseCountPerUniversity(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return null; //TODO: replace null with stream pipeline
    }

    /**
     * 4.2.5: 3 Punkte
     * Finden Sie zu jeder Universität die Anzahl der Studenten, die an ihr Kurse abgeschlossen haben.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return Map with the name of each university as keys and the number of students that took courses at each as values.
     * Students may be counted for multiple universities if they took courses at more than one university, but may not be
     * counted twice for the same university.
     */
    public static Map<String, Long> studentCountPerUniversity(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return null; //TODO: replace null with stream pipeline
    }

    /**
     * 4.2.6: 3 Punkte
     * Finden Sie die Namen aller Betreuer und sortieren Sie sie aufsteigend nach der Zahl der von ihnen betreuten Studenten.
     *
     * @param students     List of students with their courses and supervisors.
     * @param universities List of universities with the courses they offer.
     *
     * @return List of names of all supervisors, ordered by the number of students they each supervised, from fewest to most.
     */
    public static List<String> supervisorsOrderedByStudentCountAscending(List<Student> students, List<University> universities) {
        // do not modify the method outside of the return statement
        return null; //TODO: replace null with stream pipeline
    }
}
