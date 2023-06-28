package com.nasa.robotic.rovers.interfaces;

import java.io.File;
import java.util.List;

public interface IReadLandingDirectionsService {

    List<String> readDirectionFrom(File file);
}
