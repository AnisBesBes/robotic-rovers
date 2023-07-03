package com.nasa.robotic.rovers.validator;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;

import java.util.List;
import java.util.regex.Pattern;

public class InstructionsValidator {

    public static void validatePlateauCoordinates(final String plateauCoordinates) throws ReadLandingInstructionsException {
        if(!Pattern.matches("^\\d\\s\\d$", plateauCoordinates)) {
            throw new ReadLandingInstructionsException("Erreur occurred when reading plateau coordinates");
        }
    }

    public static void validateRoverPosition(final String roverPosition) throws ReadLandingInstructionsException {
        if(!Pattern.matches("^\\d\\s\\d\\s[NESO]$", roverPosition)) {
            throw new ReadLandingInstructionsException("Erreur occurred when reading rover positions");
        }
    }

    public static void validateRoverInstructions(final String roverInstructions) throws ReadLandingInstructionsException {
        if(!Pattern.matches("^[LRM]+$", roverInstructions)) {
            throw new ReadLandingInstructionsException("Erreur occurred when reading rovers instructions");
        }
    }

    public static void validateInputsLength(final List<String> inputs) throws ReadLandingInstructionsException {
        if(inputs == null || inputs.size() % 2 == 0) {
            throw new ReadLandingInstructionsException("Erreur occurred when reading inputs, instructions maybe incomplete");
        }
    }
}
