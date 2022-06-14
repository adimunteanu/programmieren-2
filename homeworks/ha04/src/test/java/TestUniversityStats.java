import impl.stats.UniversityStats;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import util.*;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestUniversityStats {
    static MonitoredList<University> universities;
    static MonitoredList<Student> students;

    @BeforeAll
    static void setUp() {
        universities = Data.universities();
        students = Data.students();
    }

    @Test
    @DisplayName("4.2.1 (2 Punkte) : testSortedStudentsFromDE")
    void testGermanStudentsSortedByName() {
        String[] expected = {"Adrian", "Anna", "Lisa", "Maria", "Max", "Pablo", "Sanjeet", "Willi"};

        assertArrayEquals(expected, UniversityStats.germanStudentsSortedByName(students, universities).toArray());
    }


    @Test
    @DisplayName("4.2.2 (2 Punkte) : testCountStudentWithNameL")
    void testCountStudentNamesBeginningWithL() {
        assertEquals(3, UniversityStats.countStudentNamesBeginningWithL(students, universities));
    }


    @Test
    @DisplayName("4.2.3 (2 Punkte) : testAverageGradeInformationTechnology")
    void testAverageGradeInformationTechnology() {
        assertEquals(2.775, UniversityStats.averageGradeInformationTechnology(students, universities), 0.01);
    }


    @Test
    @DisplayName("4.2.4 (2 Punkte) : testCourseCountPerUniversity")
    void testCourseCountPerUniversity() {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("TU Berlin", 4);
        expected.put("HU Berlin", 3);
        expected.put("FU Berlin", 2);

        assertEquals(expected, UniversityStats.courseCountPerUniversity(students, universities));
    }


    @Test
    @DisplayName("4.2.5 (3 Punkte) : testStudentCountPerUniversity")
    void testStudentCountPerUniversity() {
        HashMap<String, Long> expected = new HashMap<>();
        expected.put("TU Berlin", 13L);
        expected.put("HU Berlin", 11L);
        expected.put("FU Berlin", 7L);

        assertEquals(expected, UniversityStats.studentCountPerUniversity(students, universities));
    }

    @Test
    @DisplayName("4.2.6 (3 Punkte) : testSupervisorsOrderedByStudentCountAscending")
    void testSupervisorsOrderedByStudentCountAscending() {
        String[] expected = {"Sebastian", "Tobias", "Philip", "Sandro", "Peter", "Tom", "Axel"};

        assertArrayEquals(expected, UniversityStats.supervisorsOrderedByStudentCountAscending(students, universities).toArray());
    }
}
