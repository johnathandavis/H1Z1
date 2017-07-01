package h1z1.io;

import java.io.File;

/**
 * A class to convert file paths across platforms
 * for both relative and absolute paths.
 */
public class ResourceManager {

    /**
     * Get a platform-specific reference to a file specified by its absolute path.
     * @param path the platform-agnostic absolute file path
     * @return the platform-specific file path
     */
    public static File getRelativeFile(String path) {
        ClassLoader classLoader = ResourceManager.class.getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

}
