package com.nasa.robotic.rovers.interfaces;

import com.nasa.robotic.rovers.exception.RoverNavigatorException;

public interface IRoverNavigatorService {

    void process(String fileName) throws RoverNavigatorException;
}
