package com.nasa.robotic.rovers.service;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import com.nasa.robotic.rovers.interfaces.IReadLandingInstructionsService;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.List;

public class ReadLandingInstructionsService implements IReadLandingInstructionsService {

    @Override
    public List<String> readInstructionsFrom(File file) throws ReadLandingInstructionsException {
        throw new NotImplementedException();
    }

    @Override
    public Pair<Integer, Integer> extractPlateauCoordinates(List<String> directions) throws ReadLandingInstructionsException {
        throw new NotImplementedException();
    }

    @Override
    public List<Triple<Integer, Integer, String>> extractRoversPosition(List<String> directions) throws ReadLandingInstructionsException {
        throw new NotImplementedException();
    }

    @Override
    public List<String> extractRoversInstructions(List<String> directions) throws ReadLandingInstructionsException {
        throw new NotImplementedException();
    }
}
