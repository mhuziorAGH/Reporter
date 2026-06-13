package org.example.cli;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CLITest {

    // Tworzymy bufor, który przechwyci tekst z konsoli
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Przekierowujemy System.out do naszego bufora przed każdym testem
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Przywracamy oryginalny terminal po każdym teście
        System.setOut(originalOut);
    }

//    @Test
//    void powinienWypisacPoprawnyKomunikat() {
//        // Arrange
//        String[] mockArgs = {"pomoc"};
//        MojaAplikacja app = new MojaAplikacja();
//
//        // Act - wywołujemy metodę void, która robi System.out.println()
//        app.przetwórzArgumenty(mockArgs);
//
//        // Assert - sprawdzamy zawartość konsoli (metoda .trim() usuwa znaki nowej linii)
//        String oczekiwanyTekst = "Uruchomiono tryb pomocy.";
//        String aktualnyTekst = outContent.toString().trim();
//
//        assertEquals(oczekiwanyTekst, aktualnyTekst, "Tekst wypisany w terminalu jest niepoprawny!");
//    }

    @Test
    public void testWithoutParams() {
        String [] mockArgs = {""};
        CLI cli = new CLI(mockArgs);
        cli.run();
        String wantedOutput = "ReportEmployeesCommand";
        String actualOutput = outContent.toString().trim();

    assertEquals(wantedOutput, actualOutput);





    }

    @Test
    public void testWithOneSetofParams() {
        String [] mockArgs = {"--path", "asdasda"};
        CLI cli2 = new CLI(mockArgs);
        cli2.run();
        String wantedOutput = "ReportEmployeesCommand";
        String actualOutput = outContent.toString().trim();

//        assertEquals(wantedOutput, actualOutput);

    }

    @Test
    public void testWithMoreSetsofParams() {

    }
}
