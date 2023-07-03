package com.nasa.robotic.rovers.interfaces;

import com.nasa.robotic.rovers.exception.ReadLandingInstructionsException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.io.File;
import java.util.List;

public interface IReadLandingInstructionsService {

    List<String> readInstructionsFrom(File file) throws ReadLandingInstructionsException;

    Pair<Integer, Integer> extractPlateauCoordinates(List<String> directions) throws ReadLandingInstructionsException;

    List<Triple<Integer, Integer, String>> extractRoversPosition(List<String> directions) throws ReadLandingInstructionsException;

    List<String> extractRoversInstructions(List<String> directions) throws ReadLandingInstructionsException;
}
