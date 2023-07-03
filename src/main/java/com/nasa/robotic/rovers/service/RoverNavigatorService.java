package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.RoverNavigatorException;
import com.nasa.robotic.rovers.interfaces.IRoverNavigatorService;
import com.nasa.robotic.rovers.model.Plateau;
import com.nasa.robotic.rovers.model.Rover;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class RoverNavigatorService implements IRoverNavigatorService {

    private final Plateau plateau = new Plateau();
    private final ReadLandingInstructionsService instructionsService = new ReadLandingInstructionsService();
    private final List<Rover> rovers = new ArrayList<>();

    @Override
    public void process(String fileName) throws RoverNavigatorException {
        throw new NotImplementedException();
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    public List<Rover> getRovers() {
        return this.rovers;
    }
}
