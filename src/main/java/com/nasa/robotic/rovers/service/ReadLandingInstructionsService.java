package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import com.nasa.robotic.rovers.interfaces.IReadLandingInstructionsService;
import com.nasa.robotic.rovers.validator.InstructionsValidator;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadLandingInstructionsService implements IReadLandingInstructionsService {

    @Override
    public List<String> readInstructionsFrom(String fileName) throws ReadLandingInstructionsException {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             Scanner sc = new Scanner(fileInputStream)) {
            List<String> instructions = new ArrayList<>();
            while (sc.hasNextLine()) {
                instructions.add(sc.nextLine());
            }
            InstructionsValidator.validateInputsLength(instructions);
            return instructions;
        } catch (IOException e) {
            throw new ReadLandingInstructionsException("Erreur occurred when reading instruction from file");
        }
    }

    @Override
    public Pair<Integer, Integer> extractPlateauCoordinates(List<String> inputs) throws ReadLandingInstructionsException {
        if (inputs != null && inputs.size() > 0) {
            String plateauCoordinates = inputs.get(0);
            InstructionsValidator.validatePlateauCoordinates(plateauCoordinates);
            String[] xy = plateauCoordinates.split(" ");
            return Pair.of(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
        }

        throw new ReadLandingInstructionsException("Erreur occurred when reading plateau coordinates");
    }

    @Override
    public List<Triple<Integer, Integer, String>> extractRoversPosition(List<String> inputs) throws ReadLandingInstructionsException {
        if (inputs != null && inputs.size() > 1) {
            List<Triple<Integer, Integer, String>> roversPosition = new ArrayList<>(inputs.size() / 2);
            for (int i = 1; i < inputs.size(); i += 2) {
                String roverPosition = inputs.get(i);
                InstructionsValidator.validateRoverPosition(roverPosition);
                String[] xyP = roverPosition.split(" ");
                roversPosition.add(Triple.of(Integer.parseInt(xyP[0]), Integer.parseInt(xyP[1]), xyP[2]));
            }

            return roversPosition;
        }

        throw new ReadLandingInstructionsException("Erreur occurred when reading rovers positions");
    }

    @Override
    public List<String> extractRoversInstructions(List<String> inputs) throws ReadLandingInstructionsException {
        if (inputs != null && inputs.size() > 2) {
            List<String> instructions = new ArrayList<>(inputs.size() / 2);
            for (int i = 2; i < inputs.size(); i += 2) {
                String roverInstructions = inputs.get(i);
                InstructionsValidator.validateRoverInstructions(roverInstructions);
                instructions.add(roverInstructions);
            }

            return instructions;
        }

        throw new ReadLandingInstructionsException("Erreur occurred when reading rovers instructions");
    }
}
