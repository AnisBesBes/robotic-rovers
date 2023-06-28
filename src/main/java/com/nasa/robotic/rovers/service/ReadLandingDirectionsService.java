package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.interfaces.IReadLandingDirectionsService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.List;

public class ReadLandingDirectionsService implements IReadLandingDirectionsService {
    @Override
    public List<String> readDirectionFrom(File file) {
        throw new NotImplementedException();
    }
}
