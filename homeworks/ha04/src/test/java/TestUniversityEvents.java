import impl.events.UniversityEvents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Data;
import util.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestUniversityEvents {

    static ArrayList<Event> events;

    @BeforeAll
    static void setUp() {
        events = Data.events();
    }

    @Test
    @DisplayName("4.1.1 (2 Punkte) : testSortedLectures")
    void testSortedLectures() {
        String[] expected = {"Gepro Vorlesung", "Prog1 Vorlesung", "Prog2 Vorlesung", "WebTech Vorlesung"};

        assertArrayEquals(expected, UniversityEvents.getLectureNames(events).toArray());
    }

    @Test
    @DisplayName("4.1.2 (2 Punkte) : testTutoriumAverageStudent")
    void testTutoriumAverageStudent() {
        assertEquals(27.76923, UniversityEvents.averageTutorialStudentCount(events), 0.01);
    }

    @Test
    @DisplayName("4.1.3 (2 Punkte) : testPopularTutorium")
    void testPopularTutorium() {
        assertEquals("Prog1 Tut2", UniversityEvents.mostPopularTutorial(events));
    }
}
