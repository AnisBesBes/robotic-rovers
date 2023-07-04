package com.nasa.robotic.rovers.service;

import java.io.File;
import java.net.URL;

public class TestUtils {

    private TestUtils() {}

    public static File fileFromResourcePath(final String path) {
        try {
            URL url = ReadLandingInstructionsServiceTest.class.getClassLoader().getResource(path);
            return new File(url.toURI());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
