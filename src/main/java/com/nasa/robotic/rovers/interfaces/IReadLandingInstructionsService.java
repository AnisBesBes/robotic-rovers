package com.nasa.robotic.rovers.interfaces;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.FileNotFoundException;
import java.util.List;

public interface IReadLandingInstructionsService {

    List<String> readInstructionsFrom(final String fileName) throws ReadLandingInstructionsException, FileNotFoundException;

    Pair<Integer, Integer> extractPlateauCoordinates(final List<String> directions) throws ReadLandingInstructionsException;

    List<Triple<Integer, Integer, String>> extractRoversPosition(final List<String> directions) throws ReadLandingInstructionsException;

    List<String> extractRoversInstructions(final List<String> directions) throws ReadLandingInstructionsException;
}
