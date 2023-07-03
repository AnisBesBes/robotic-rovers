package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReadLandingInstructionsServiceTest {

    private final ReadLandingInstructionsService readLandingDirectionsService = new ReadLandingInstructionsService();

    private final List<String> directionsMock = new ArrayList<String>() {{
        add("5 7");
        add("1 2 N");
        add("LMLMLMLMM");
        add("3 3 E");
        add("MMRMMRMRRM");
    }};

    private final List<String> invalidDirectionsMock = new ArrayList<String>() {{
        add("5 -7");
        add("1 2 N");
        add("LMLALMLMM");
        add("3 3 D");
        add("MBRMMRMRRM");
    }};

    @Test
    public void read_instructions_valid() throws ReadLandingInstructionsException, URISyntaxException {
        List<String> directions = readLandingDirectionsService.readInstructionsFrom((fileFromResourcePath("valid-instructions.txt")).getAbsolutePath());
        Assertions.assertNotNull(directions);
        assertTrue(directions.contains("5 5"));
        assertTrue(directions.contains("1 2 N"));
        assertTrue(directions.contains("LMLMLMLMM"));
    }

    @Test
    public void read_instructions_expect_exception() {
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                readLandingDirectionsService.readInstructionsFrom("invalid-path")
        );

        String expectedMessage = "Erreur occurred when reading instruction from file";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void read_instructions_expect_exception_invalid_length() {
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                readLandingDirectionsService.readInstructionsFrom((fileFromResourcePath("invalid-length-instructions.txt")).getAbsolutePath())
        );

        String expectedMessage = "Erreur occurred when reading inputs, instructions maybe incomplete";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void extract_plateau_coordinates_valid() throws ReadLandingInstructionsException {
        Pair<Integer, Integer> plateauCoordinates = readLandingDirectionsService.extractPlateauCoordinates(directionsMock);
        assertEquals(5, plateauCoordinates.getLeft().intValue());
        assertEquals(7, plateauCoordinates.getRight().intValue());
    }

    @Test
    public void extract_plateau_coordinates_expect_exception() {
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                readLandingDirectionsService.extractPlateauCoordinates(invalidDirectionsMock)
        );

        String expectedMessage = "Erreur occurred when reading plateau coordinates";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void extract_rovers_positions_valid() throws ReadLandingInstructionsException {
        List<Triple<Integer, Integer, String>> positions = readLandingDirectionsService.extractRoversPosition(directionsMock);
        assertEquals(2, positions.size());
        assertEquals(1, positions.get(0).getLeft().intValue());
        assertEquals(2, positions.get(0).getMiddle().intValue());
        assertEquals("N", positions.get(0).getRight());
        assertEquals(3, positions.get(1).getLeft().intValue());
        assertEquals(3, positions.get(1).getMiddle().intValue());
        assertEquals("E", positions.get(1).getRight());
    }

    @Test
    public void extract_rovers_positions_expect_exception() {
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                readLandingDirectionsService.extractRoversPosition(invalidDirectionsMock)
        );

        String expectedMessage = "Erreur occurred when reading rover positions";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void extractRoversInstructionsTest() throws ReadLandingInstructionsException {
        List<String> instructions = readLandingDirectionsService.extractRoversInstructions(directionsMock);
        assertEquals(2, instructions.size());
        assertEquals("LMLMLMLMM", instructions.get(0));
        assertEquals("MMRMMRMRRM", instructions.get(1));
    }

    @Test
    public void extract_rovers_instructions_expect_exception() {
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                readLandingDirectionsService.extractRoversInstructions(invalidDirectionsMock)
        );

        String expectedMessage = "Erreur occurred when reading rovers instructions";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    private static File fileFromResourcePath(final String path) {
        try {
            URL url = ReadLandingInstructionsServiceTest.class.getClassLoader().getResource(path);
            return new File(url.toURI());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
