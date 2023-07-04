package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import com.nasa.robotic.rovers.exception.RoverNavigatorException;
import com.nasa.robotic.rovers.interfaces.IRoverNavigatorService;
import com.nasa.robotic.rovers.model.Cardinal;
import com.nasa.robotic.rovers.model.Plateau;
import com.nasa.robotic.rovers.model.Rover;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RoverNavigatorService implements IRoverNavigatorService {

    private Plateau plateau;
    private final ReadLandingInstructionsService instructionsService = new ReadLandingInstructionsService();
    private List<Rover> rovers;
    private final Map<Character, Consumer<Rover>> commands = new HashMap<Character, Consumer<Rover>>() {{
        put('M', (Rover rover) -> moveToNewCoordinates(rover));
        put('L', (Rover rover) -> rotateLeft(rover));
        put('R', (Rover rover) -> rotateRight(rover));
    }};

    @Override
    public void process(final String fileName) throws RoverNavigatorException, ReadLandingInstructionsException {
        List<String> inputs = instructionsService.readInstructionsFrom(fileName);
        Pair<Integer, Integer> plateauCoordinates = instructionsService.extractPlateauCoordinates(inputs);
        plateau = createPlateau(plateauCoordinates);
        List<Triple<Integer, Integer, Character>> roversPosition = instructionsService.extractRoversPosition(inputs);
        rovers = createRovers(roversPosition);
        List<String> roversInstructions = instructionsService.extractRoversInstructions(inputs);
        navigate(roversInstructions);
        rovers.forEach(rover -> System.out.println(rover.toString()));
    }

    private void navigate(final List<String> roversInstructions) {
        if (roversInstructions != null && rovers != null && roversInstructions.size() == rovers.size()) {
            for (int i = 0; i < roversInstructions.size(); i++) {
                Rover rover = rovers.get(i);
                char[] steps = roversInstructions.get(i).toCharArray();
                for (char step : steps) {
                    Consumer<Rover> command = commands.get(step);
                    if (command != null) {
                        command.accept(rover);
                    } else {
                        throw new RoverNavigatorException("Invalid instructions for navigation");
                    }
                }
            }
        } else {
            throw new RoverNavigatorException("Invalid instructions for navigation");
        }
    }

    private void moveToNewCoordinates(Rover rover) {
        switch (rover.getCardinal()) {
            case 'N':
                moveY(rover, rover.getY() + 1);
                break;
            case 'E':
                moveX(rover, rover.getX() + 1);
                break;
            case 'S':
                moveY(rover, rover.getY() - 1);
                break;
            case 'W':
                moveX(rover, rover.getX() - 1);
                break;
            default:
                throw new RoverNavigatorException("Erreur occurred when calculating new coordinates, cardinal not recognized");
        }
    }

    private void moveX(Rover rover, int coordinate) {
        if (plateau.getMinX() <= coordinate && coordinate <= plateau.getMaxX()) {
            rover.setX(coordinate);
        } else {
            throw new RoverNavigatorException("Erreur occurred while navigating, rover is out of the plateau");
        }
    }

    private void moveY(Rover rover, int coordinate) throws RoverNavigatorException {
        if (plateau.getMinY() <= coordinate && coordinate <= plateau.getMaxY()) {
            rover.setY(coordinate);
        } else {
            throw new RoverNavigatorException("Erreur occurred while navigating, rover is out of the plateau");
        }
    }

    private void rotateRight(Rover rover) {
        char cardinal = rover.getCardinal();
        char newCardinal = Cardinal.next(cardinal);
        rover.setCardinal(newCardinal);
    }

    private void rotateLeft(Rover rover) {
        char cardinal = rover.getCardinal();
        char newCardinal = Cardinal.precedent(cardinal);
        rover.setCardinal(newCardinal);
    }

    private Plateau createPlateau(final Pair<Integer, Integer> plateauCoordinates) {
        return new Plateau(plateauCoordinates.getLeft(), plateauCoordinates.getRight());
    }

    private List<Rover> createRovers(final List<Triple<Integer, Integer, Character>> roversPosition) {
        return roversPosition.stream()
                .map(roverPosition -> new Rover(roverPosition.getLeft(), roverPosition.getMiddle(), roverPosition.getRight()))
                .collect(Collectors.toList());
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public List<Rover> getRovers() {
        return this.rovers;
    }
}
