package com.testing.utils;

import java.io.InputStream;
import java.util.Objects;

public class FileUtils {

    public static InputStream readFileAsStream(String resource){
        return Objects.requireNonNull(FileUtils.class.getClassLoader().getResourceAsStream(resource));
    }

}
