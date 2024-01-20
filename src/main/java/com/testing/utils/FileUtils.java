package com.testing.utils;

import java.io.InputStream;
import java.util.Objects;

import static java.lang.String.join;

public class FileUtils {

    private static final String SCHEMAS_FOLDER = "schemas";

    public static InputStream readFileAsStream(String resource) {
        return Objects.requireNonNull(FileUtils.class.getClassLoader().getResourceAsStream(resource));
    }

    public static InputStream loadSchema(String resource) {
        return readFileAsStream(join("/", SCHEMAS_FOLDER, resource));
    }

}
