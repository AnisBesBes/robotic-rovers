package com.nasa.robotic.rovers;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import com.nasa.robotic.rovers.service.RoverNavigatorService;

public class Main {

    public static void main(String[] args) throws ReadLandingInstructionsException {
        RoverNavigatorService navigator = new RoverNavigatorService();
        navigator.process(args[0]);
    }
}
