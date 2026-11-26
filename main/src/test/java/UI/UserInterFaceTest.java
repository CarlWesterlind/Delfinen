package UI;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterFaceTest {

    @Test
    void juniorSwimmerIsDisplayedWithDisciplines() {
        // Arrange
        String birthDate = LocalDate.now().minusYears(15)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String input = String.join("\n",
                "1",
                "3",
                "Anna",
                birthDate,
                "1",
                "2",
                "0",
                "0",
                "3",
                "1",
                "0",
                "0"
        ) + "\n";

        // Act
        String result = runProgram(input);

        // Assert
        assertTrue(result.contains("Junior Team:"));
        assertTrue(result.contains(
                "Anna --- Disciplines: butterfly, freestyle"
        ));
    }

    @Test
    void emptyJuniorTeamDisplaysMessage() {
        // Arrange
        String input = String.join("\n",
                "3",
                "1",
                "0",
                "0"
        ) + "\n";

        // Act
        String result = runProgram(input);

        // Assert
        assertTrue(result.contains("Junior Team:"));
        assertTrue(result.contains(
                "There are no swimmers on this team."
        ));
    }

    @Test
    void juniorSwimmerWithOneDisciplineIsDisplayed() {
        // Arrange
        String birthDate = LocalDate.now().minusYears(15)
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String input = String.join("\n",
                "1",
                "3",
                "Peter",
                birthDate,
                "2",
                "0",
                "0",
                "3",
                "1",
                "0",
                "0"
        ) + "\n";

        // Act
        String result = runProgram(input);

        // Assert
        assertTrue(result.lines().anyMatch(
                line -> line.equals(
                        "Peter --- Disciplines: freestyle"
                )
        ));
    }

    @Test
    void multipleJuniorSwimmersAreDisplayed() {
        // Arrange
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String annaBirthDate = LocalDate.now()
                .minusYears(15).format(formatter);

        String peterBirthDate = LocalDate.now()
                .minusYears(16).format(formatter);

        String input = String.join("\n",
                "1",
                "3",
                "Anna",
                annaBirthDate,
                "1",
                "2",
                "0",
                "3",
                "Peter",
                peterBirthDate,
                "3",
                "0",
                "0",
                "3",
                "1",
                "0",
                "0"
        ) + "\n";

        // Act
        String result = runProgram(input);

        // Assert
        assertTrue(result.lines().anyMatch(
                line -> line.equals(
                        "Anna --- Disciplines: butterfly, freestyle"
                )
        ));

        assertTrue(result.lines().anyMatch(
                line -> line.equals(
                        "Peter --- Disciplines: backstroke"
                )
        ));
    }

    @Test
    void seniorSwimmerIsNotDisplayedOnJuniorTeam() {
        // Arrange
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String juniorBirthDate = LocalDate.now()
                .minusYears(15).format(formatter);

        String seniorBirthDate = LocalDate.now()
                .minusYears(25).format(formatter);

        String input = String.join("\n",
                "1",
                "3",
                "Anna",
                juniorBirthDate,
                "1",
                "0",
                "3",
                "Mikkel",
                seniorBirthDate,
                "2",
                "0",
                "0",
                "3",
                "1",
                "0",
                "0"
        ) + "\n";

        // Act
        String result = runProgram(input);

        // Assert
        assertTrue(result.lines().anyMatch(
                line -> line.equals(
                        "Anna --- Disciplines: butterfly"
                )
        ));

        assertFalse(result.contains(
                "Mikkel --- Disciplines:"
        ));
    }

    private String runProgram(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(output));

            UserInterFace ui = new UserInterFace();
            ui.startProgram();

            return output.toString();

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}