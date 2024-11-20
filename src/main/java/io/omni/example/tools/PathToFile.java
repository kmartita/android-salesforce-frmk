package io.omni.example.tools;

import java.nio.file.FileSystems;

public class PathToFile {

    public static String getPathToTestResourcesFolder() {
        return String.join(FileSystems.getDefault().getSeparator(),
                System.getProperty("user.dir"), "src", "test", "resources");
    }
}
