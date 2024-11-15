package io.omni.example.tools;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class PathToFile {

    private static final String SEPARATOR = File.separator;

    public static String getRootOfProject() {
        Path path = Paths.get(Objects.requireNonNull(PathToFile.class.getClassLoader().getResource(".")).getPath());
        return path.getParent().getParent().toString();
    }

    public static String getPathToResourcesFolder() {
        return getRootOfProject() + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources" + SEPARATOR;
    }
}
