package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import com.nasa.robotic.rovers.model.Plateau;
import com.nasa.robotic.rovers.model.Rover;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoverNavigatorServiceTest {

    private static RoverNavigatorService validNavigator;

    @BeforeAll
    static void setUp() {
        validNavigator = new RoverNavigatorService();
        validNavigator.process("valid-instructions.txt");
    }

    @Test
    public void plateau_created_test() {
        Plateau plateau = validNavigator.getPlateau();
        assertNotNull(plateau);
        assertEquals(5, plateau.getMaxX());
        assertEquals(0, plateau.getMinX());
        assertEquals(5, plateau.getMaxY());
        assertEquals(0, plateau.getMinY());
    }

    @Test
    public void rovers_expected_positions_test() {
        List<Rover> rovers = validNavigator.getRovers();
        assertNotNull(rovers);
        assertEquals(2, rovers.size());
        Rover rover1 = rovers.get(0);
        assertNotNull(rover1);
        assertEquals(1, rover1.getX());
        assertEquals(3, rover1.getY());
        assertEquals("N", rover1.getCardinal());
        Rover rover2 = rovers.get(1);
        assertNotNull(rover2);
        assertEquals(5, rover2.getX());
        assertEquals(1, rover2.getY());
        assertEquals("E", rover2.getCardinal());
    }

    @Test
    void rover_out_of_plateau_test() {
        RoverNavigatorService invalidNavigator = new RoverNavigatorService();
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                invalidNavigator.process("rover-out-of-plateau-instructions.txt")
        );

        String expectedMessage = "Erreur occurred while navigating, rover is out of the plateau";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void position_already_occupied_by_another_rover_test() {
        RoverNavigatorService invalidNavigator = new RoverNavigatorService();
        Exception exception = assertThrows(ReadLandingInstructionsException.class, () ->
                invalidNavigator.process("position-already-occupied-by-another-rover-instructions")
        );

        String expectedMessage = "Erreur occurred while navigating, another rover is already occupying the position";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void print_output_test() {
        List<Rover> rovers = validNavigator.getRovers();
        assertNotNull(rovers);
        assertEquals(2, rovers.size());
        Rover rover1 = rovers.get(0);
        assertNotNull(rover1);
        assertEquals("1 3 N", rover1.toString());
        Rover rover2 = rovers.get(1);
        assertNotNull(rover2);
        assertEquals("5 1 E", rover2.toString());
    }
}
