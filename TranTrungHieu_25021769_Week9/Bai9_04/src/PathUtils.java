//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * Cross-platform path utilities.
// *
// * Refactored to use java.nio.file.Path so that the correct
// * separator (backslash on Windows, slash on Linux/macOS) is
// * chosen at runtime instead of being hardcoded.
// *
// * See README.md for the original buggy version and the
// * matrix-strategy CI run that exposed it.
// */
//public final class PathUtils {
//
//    private PathUtils() {
//        // utility class - no instances
//    }
//
//    /**
//     * Join a base path with one or more child segments using
//     * the OS-correct separator.
//     *
//     * Example:
//     *   join("data", "input", "users.csv")
//     *     - on Windows: "data\\input\\users.csv"
//     *     - on Linux/macOS: "data/input/users.csv"
//     */
//    public static String join(String first, String... more) {
//        return Paths.get(first, more).toString();
//    }
//
//    /**
//     * Build a log file path: <baseDir>/logs/<filename>
//     * with the OS-correct separator.
//     */
//    public static String buildLogFilePath(String baseDir, String filename) {
//        return Paths.get(baseDir, "logs", filename).toString();
//    }
//
//    /**
//     * Extract the final name component of a path string in
//     * a cross-platform way.
//     */
//    public static String getFileName(String pathStr) {
//        Path p = Paths.get(pathStr);
//        Path name = p.getFileName();
//        return name == null ? "" : name.toString();
//    }
//}
public final class PathUtils {
    private PathUtils() {}
    public static String join(String first, String... more) {
        StringBuilder sb = new StringBuilder(first);
        for (String s : more) sb.append("\\").append(s);
        return sb.toString();
    }
    public static String buildLogFilePath(String baseDir, String filename) {
        return baseDir + "\\logs\\" + filename;
    }
    public static String getFileName(String pathStr) {
        int i = pathStr.lastIndexOf('\\');
        return i < 0 ? pathStr : pathStr.substring(i + 1);
    }
}
