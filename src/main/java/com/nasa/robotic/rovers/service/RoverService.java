package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.interfaces.IRoverService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RoverService implements IRoverService {

    @Override
    public void moveForward() {
        throw new NotImplementedException();
    }

    @Override
    public void spinLeft() {
        throw new NotImplementedException();
    }

    @Override
    public void spinRight() {
        throw new NotImplementedException();
    }
}
